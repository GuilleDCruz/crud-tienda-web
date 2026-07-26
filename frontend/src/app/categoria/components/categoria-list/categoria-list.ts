import { Component, OnInit, signal } from '@angular/core';

import { CategoriaService } from '../../services/categoria';
import { CategoriaResponse } from '../../models/categoria-response';
import { CategoriaModal } from '../categoria-modal/categoria-modal';
import { CategoriaConfirm } from '../categoria-confirm/categoria-confirm';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-categoria-list',
  imports: [FormsModule, CategoriaModal, CategoriaConfirm],
  templateUrl: './categoria-list.html',
  styleUrl: './categoria-list.scss'
})

export class CategoriaList implements OnInit {

  categorias = signal<CategoriaResponse[]>([]);

  cargando = signal(false);

  mensajeError = signal('');

  mostrarModal = signal(false);

  nombreBusqueda = '';

  modoModal = signal<'crear' | 'editar' | null>(null);

  categoriaSeleccionada = signal<CategoriaResponse | null>(null);

  mostrarConfirmacion = signal(false);

  accionConfirmacion = signal<'activar' | 'desactivar' | null>(null);

  constructor(
    private categoriaService: CategoriaService
  ) { }

  abrirModal(): void {
    this.mostrarModal.set(true);
  }

  cerrarModal(): void {

    this.modoModal.set(null);

    this.categoriaSeleccionada.set(null);

  }

  categoriaCreada(): void {
    this.mostrarModal.set(false);

    this.listarCategorias();
  }

  categoriaGuardada(): void {

    this.cerrarModal();

    this.listarCategorias();

  }

  ngOnInit(): void {
    this.listarCategorias();
  }

  abrirCrear(): void {

    this.categoriaSeleccionada.set(null);

    this.modoModal.set('crear');

  }

  listarCategorias(): void {

    this.categoriaService
      .listar()
      .subscribe({

        next: (categorias) => {

          this.categorias.set(categorias);

          this.mensajeError.set('');

          this.nombreBusqueda = '';

        },

        error: (error) => {

          console.error(
            'Error al listar categorías:',
            error
          );

          this.mensajeError.set(
            'No fue posible obtener las categorías.'
          );

        }

      });

  }

  buscarPorId(id: string): void {
    console.log('Mostrar categoría con ID:', id);
  }

  limpiarBusqueda(): void {
    this.listarCategorias();
  }

  agregarCategoria(): void {
    console.log('Abrir formulario para agregar categoría');
  }

  editarCategoria(id: number): void {

    this.categoriaService.buscarPorId(id).subscribe({

      next: (categoria) => {

        this.categoriaSeleccionada.set(categoria);

        this.modoModal.set('editar');

      },

      error: (error) => {

        console.error(
          'Error al obtener categoría:',
          error
        );

        this.mensajeError.set(
          'No fue posible obtener la categoría.'
        );

      }

    });

  }

  abrirConfirmacion(
    categoria: CategoriaResponse
  ): void {

    this.categoriaSeleccionada.set(categoria);

    this.accionConfirmacion.set(
      categoria.activo
        ? 'desactivar'
        : 'activar'
    );

    this.mostrarConfirmacion.set(true);

  }

  cerrarConfirmacion(): void {

    this.mostrarConfirmacion.set(false);

    this.categoriaSeleccionada.set(null);

    this.accionConfirmacion.set(null);

  }

  accionRealizada(): void {

    this.cerrarConfirmacion();

    this.listarCategorias();

  }

  buscarCategoria(): void {

    const nombre = this.nombreBusqueda.trim();

    if (!nombre) {
      this.listarCategorias();
      return;
    }

    this.categoriaService
      .buscarPorNombre(nombre)
      .subscribe({

        next: (categoria) => {

          this.categorias.set([categoria]);

          this.mensajeError.set('');

        },

        error: (error) => {

          console.error(
            'Error al buscar categoría:',
            error
          );

          this.mensajeError.set(
            error.error?.message ??
            'La categoría no existe.'
          );

        }

      });

  }

}