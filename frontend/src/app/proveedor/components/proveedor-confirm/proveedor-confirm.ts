import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { ProveedorResponse } from '../../models/proveedor-response';
import { ProveedorService } from '../../services/proveedor';

@Component({
  selector: 'app-proveedor-confirm',
  imports: [],
  templateUrl: './proveedor-confirm.html',
  styleUrl: './proveedor-confirm.scss',
})
export class ProveedorConfirm {

  @Input()
  proveedor: ProveedorResponse | null = null;

  @Input()
  accion: 'activar' | 'desactivar' = 'desactivar';

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  accionRealizada = new EventEmitter<void>();

  cargando = signal(false);
  mensajeError = signal('');

  constructor(private proveedorService: ProveedorService) { }

  cancelar(): void {
    this.cerrar.emit();
  }

  confirmar(): void {
    if (!this.proveedor) {
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
    if (!this.proveedor) {
      return;
    }
    this.proveedorService
      .eliminar(this.proveedor.id)
      .subscribe({
        next: () => {
          this.cargando.set(false);
          this.accionRealizada.emit();
          this.cerrar.emit();
        },
        error: (error) => {
          console.error('Error al desactivar proveedor:', error);
          this.cargando.set(false);
          this.mensajeError.set(error.error?.message ?? 'No fue posible desactivar el proveedor.');
        }
      });
  }

  private activar(): void {
    if (!this.proveedor) {
      return;
    }
    this.proveedorService
      .activar(this.proveedor.id)
      .subscribe({
        next: () => {
          this.cargando.set(false);
          this.accionRealizada.emit();
          this.cerrar.emit();
        },
        error: (error) => {
          console.error('Error al activar proveedor:', error);
          this.cargando.set(false);
          this.mensajeError.set(error.error?.message ?? 'No fue posible activar el proveedor.');
        }
      });
  }
}
