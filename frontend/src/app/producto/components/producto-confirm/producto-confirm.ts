import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductoResponse } from '../../models/producto-response';
import { ProductoService } from '../../services/producto';

@Component({
  selector: 'app-producto-confirm',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './producto-confirm.html'
})
export class ProductoConfirm {

  @Input()
  producto: ProductoResponse | null = null;

  @Input()
  accion: 'activar' | 'desactivar' = 'desactivar';

  @Input()
  tituloDialogo: string = '';

  @Input()
  mensajeDialogo: string = '';

  @Input()
  tipoDialogo: 'confirmacion' | 'exito' | 'advertencia' = 'confirmacion';

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  accionRealizada = new EventEmitter<void>();

  cargando = signal(false);
  mensajeError = signal('');

  constructor(private productoService: ProductoService) {}

  cancelar(): void {
    this.cerrar.emit();
  }

  confirmar(): void {
    if (this.cargando()) {
      return;
    }

    if (!this.producto) {
      this.cerrar.emit();
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
    if (!this.producto) {
      return;
    }

    this.productoService.eliminar(this.producto.id).subscribe({
      next: () => {
        this.cargando.set(false);
        this.accionRealizada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al desactivar producto:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible desactivar el producto.');
      }
    });
  }

  private activar(): void {
    if (!this.producto) {
      return;
    }

    this.productoService.activar(this.producto.id).subscribe({
      next: () => {
        this.cargando.set(false);
        this.accionRealizada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al activar producto:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible activar el producto.');
      }
    });
  }
}
