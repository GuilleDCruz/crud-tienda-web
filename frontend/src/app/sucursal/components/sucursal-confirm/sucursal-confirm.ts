import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { SucursalResponse } from '../../models/sucursal-response';
import { SucursalService } from '../../services/sucursal';

@Component({
  selector: 'app-sucursal-confirm',
  imports: [],
  templateUrl: './sucursal-confirm.html',
  styleUrl: './sucursal-confirm.scss',
})
export class SucursalConfirm {

  @Input()
  sucursal: SucursalResponse | null = null;

  @Input()
  accion: 'activar' | 'desactivar' = 'desactivar';

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  accionRealizada = new EventEmitter<void>();

  cargando = signal(false);
  mensajeError = signal('');

  constructor(private sucursalService: SucursalService) { }

  cancelar(): void {
    this.cerrar.emit();
  }

  confirmar(): void {
    if (!this.sucursal) {
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
    if (!this.sucursal) {
      return;
    }
    this.sucursalService.eliminar(this.sucursal.id).subscribe({
      next: () => {
        this.cargando.set(false);
        this.accionRealizada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al desactivar sucursal:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible desactivar la sucursal.');
      }
    });
  }

  private activar(): void {
    if (!this.sucursal) {
      return;
    }
    this.sucursalService.activar(this.sucursal.id).subscribe({
      next: () => {
        this.cargando.set(false);
        this.accionRealizada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al activar sucursal:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible activar la sucursal.');
      }
    });
  }
}
