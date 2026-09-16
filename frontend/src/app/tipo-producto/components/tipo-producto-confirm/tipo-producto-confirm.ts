import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { TipoProductoResponse } from '../../models/tipo-producto-response';
import { TipoProductoService } from '../../services/tipo-producto';

@Component({
  selector: 'app-tipo-producto-confirm',
  imports: [],
  templateUrl: './tipo-producto-confirm.html',
  styleUrl: './tipo-producto-confirm.scss',
})
export class TipoProductoConfirm {

  @Input()
  tipoProducto: TipoProductoResponse | null = null;

  @Input()
  accion: 'activar' | 'desactivar' = 'desactivar';

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  accionRealizada = new EventEmitter<void>();

  cargando = signal(false);
  mensajeError = signal('');

  constructor(private tipoProductoService: TipoProductoService) { }

  cancelar(): void {
    this.cerrar.emit();
  }

  confirmar(): void {
    if (!this.tipoProducto) {
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
    if (!this.tipoProducto) {
      return;
    }
    this.tipoProductoService
      .eliminar(this.tipoProducto.id)
      .subscribe({
        next: () => {
          this.cargando.set(false);
          this.accionRealizada.emit();
          this.cerrar.emit();
        },
        error: (error) => {
          console.error('Error al desactivar tipo de producto:', error);
          this.cargando.set(false);
          this.mensajeError.set(error.error?.message ?? 'No fue posible desactivar el tipo de producto.');
        }
      });
  }

  private activar(): void {
    if (!this.tipoProducto) {
      return;
    }
    this.tipoProductoService
      .activar(this.tipoProducto.id)
      .subscribe({
        next: () => {
          this.cargando.set(false);
          this.accionRealizada.emit();
          this.cerrar.emit();
        },
        error: (error) => {
          console.error('Error al activar tipo de producto:', error);
          this.cargando.set(false);
          this.mensajeError.set(error.error?.message ?? 'No fue posible activar el tipo de producto.');
        }
      });
  }
}
