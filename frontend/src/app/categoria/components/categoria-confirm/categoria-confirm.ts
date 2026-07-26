import { Component, EventEmitter, Input, Output, signal } from '@angular/core';

import { CategoriaService } from '../../services/categoria';
import { CategoriaResponse } from '../../models/categoria-response';

@Component({
  selector: 'app-categoria-confirm',
  imports: [],
  templateUrl: './categoria-confirm.html',
  styleUrl: './categoria-confirm.scss'
})
export class CategoriaConfirm {

  @Input()
  categoria: CategoriaResponse | null = null;

  @Input()
  accion: 'activar' | 'desactivar' = 'desactivar';

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  accionRealizada = new EventEmitter<void>();

  cargando = signal(false);

  mensajeError = signal('');

  constructor(
    private categoriaService: CategoriaService
  ) { }

  cancelar(): void {
    this.cerrar.emit();
  }

  confirmar(): void {

    if (!this.categoria) {
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

    if (!this.categoria) {
      return;
    }

    this.categoriaService
      .eliminar(this.categoria.id)
      .subscribe({

        next: () => {

          this.cargando.set(false);

          this.accionRealizada.emit();

          this.cerrar.emit();

        },

        error: (error) => {

          console.error(
            'Error al desactivar categoría:',
            error
          );

          this.cargando.set(false);

          this.mensajeError.set(
            error.error?.message ??
            'No fue posible desactivar la categoría.'
          );

        }

      });

  }

  private activar(): void {

    if (!this.categoria) {
      return;
    }

    this.categoriaService
      .activar(this.categoria.id)
      .subscribe({

        next: () => {

          this.cargando.set(false);

          this.accionRealizada.emit();

          this.cerrar.emit();

        },

        error: (error) => {

          console.error(
            'Error al activar categoría:',
            error
          );

          this.cargando.set(false);

          this.mensajeError.set(
            error.error?.message ??
            'No fue posible activar la categoría.'
          );

        }

      });

  }

}