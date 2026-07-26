import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { CategoriaService } from '../../services/categoria';
import { CategoriaRequest } from '../../models/categoria-request';
import { CategoriaResponse } from '../../models/categoria-response';

@Component({
  selector: 'app-categoria-modal',
  imports: [FormsModule],
    standalone: true,

  templateUrl: './categoria-modal.html',
  styleUrl: './categoria-modal.scss'
})
export class CategoriaModal {

  @Input()
  modo: 'crear' | 'editar' = 'crear';

  @Input()
  categoria: CategoriaResponse | null = null;

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  categoriaGuardada = new EventEmitter<void>();

  nombre = '';

  descripcion = '';

  cargando = signal(false);

  mensajeError = signal('');

  constructor(
    private categoriaService: CategoriaService
  ) { }

  ngOnInit(): void {

    if (this.modo === 'editar' && this.categoria) {

      this.nombre = this.categoria.nombre;

      this.descripcion = this.categoria.descripcion;

    }

  }

  guardar(): void {

    this.mensajeError.set('');

    if (!this.nombre.trim()) {
      this.mensajeError.set(
        'El nombre de la categoría es obligatorio.'
      );
      return;
    }

    if (!this.descripcion.trim()) {
      this.mensajeError.set(
        'La descripción de la categoría es obligatoria.'
      );
      return;
    }

    const request: CategoriaRequest = {
      nombre: this.nombre.trim(),
      descripcion: this.descripcion.trim()
    };

    this.cargando.set(true);

    if (this.modo === 'crear') {

      this.crear(request);

    } else {

      this.actualizar(request);

    }

  }

  private crear(request: CategoriaRequest): void {

    this.categoriaService.guardar(request).subscribe({

      next: () => {

        this.cargando.set(false);

        this.categoriaGuardada.emit();

        this.cerrar.emit();

      },

      error: (error) => {

        console.error(
          'Error al crear categoría:',
          error
        );

        this.cargando.set(false);

        this.mensajeError.set(
          error.error?.message ??
          'No fue posible crear la categoría.'
        );

      }

    });

  }

  private actualizar(request: CategoriaRequest): void {

    if (!this.categoria) {
      return;
    }

    this.categoriaService
      .actualizar(this.categoria.id, request)
      .subscribe({

        next: () => {

          this.cargando.set(false);

          this.categoriaGuardada.emit();

          this.cerrar.emit();

        },

        error: (error) => {

          console.error(
            'Error al actualizar categoría:',
            error
          );

          this.cargando.set(false);

          this.mensajeError.set(
            error.error?.message ??
            'No fue posible actualizar la categoría.'
          );

        }

      });

  }

  cancelar(): void {
    this.cerrar.emit();
  }

}