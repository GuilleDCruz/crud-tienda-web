import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { MarcaResponse } from '../../models/marca-response';
import { MarcaService } from '../../services/marca';

@Component({
  selector: 'app-marca-confirm',
  imports: [],
  templateUrl: './marca-confirm.html',
  styleUrl: './marca-confirm.scss',
})
export class MarcaConfirm {

  @Input()
  marca: MarcaResponse | null = null;

  @Input()
  accion: 'activar' | 'desactivar' = 'desactivar';

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  accionRealizada = new EventEmitter<void>();

  cargando = signal(false);
  mensajeError = signal('');
  
  constructor(private marcaService: MarcaService) { }
  
  cancelar(): void {
    this.cerrar.emit();
  }

  confirmar(): void {
    if (!this.marca) {
      return;
    }
    this.mensajeError.set('');
    this.cargando.set(true);
    if (this.accion === 'desactivar') {
      this.desactivar();
    } else {
      this.activar();
    }
  }

  private desactivar(): void {
    if (!this.marca) {
      return;
    }
    this.marcaService
      .eliminar(this.marca.id)
      .subscribe({
        next: () => {
          this.cargando.set(false);
          this.accionRealizada.emit();
          this.cerrar.emit();
        },
        error: (error) => {
          console.error('Error al desactivar marca:', error);
          this.cargando.set(false);
          this.mensajeError.set(error.error?.message ?? 'No fue posible desactivar la marca.');
        }
      });
  }

  private activar(): void {
    if (!this.marca) {
      return;
    }
    this.marcaService
      .activar(this.marca.id)
      .subscribe({
        next: () => {
          this.cargando.set(false);
          this.accionRealizada.emit();
          this.cerrar.emit();
        },
        error: (error) => {
          console.error('Error al activar marca:', error);
          this.cargando.set(false);
          this.mensajeError.set(error.error?.message ?? 'No fue posible activar la marca.');
        }
      });
  }
  
}
