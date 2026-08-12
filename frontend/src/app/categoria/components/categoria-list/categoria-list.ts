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

  constructor(private categoriaService: CategoriaService) { }

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
    this.categoriaService.listar().subscribe({
      next: (data) => {
        this.categorias.set(data);
        this.mensajeError.set('');
        this.cargando.set(false);
      },
      error: (error) => {
        this.categorias.set([]);
        this.cargando.set(false);
        this.mensajeError.set('No fue posible obtener las categorías.');
      }
    });
  }

  limpiarBusqueda(): void {
    this.listarCategorias();
  }

  editarCategoria(id: number): void {
    this.categoriaService.buscarPorId(id).subscribe({
      next: (categoria) => {
        this.categoriaSeleccionada.set(categoria);
        this.modoModal.set('editar');
      },
      error: (error) => {
        console.error('Error al obtener categoría:', error);
        this.mensajeError.set('No fue posible obtener la categoría.');
      }
    });
  }

  abrirConfirmacion(categoria: CategoriaResponse): void {
    this.categoriaSeleccionada.set(categoria);
    this.accionConfirmacion.set(categoria.activo ? 'desactivar' : 'activar');
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

  buscarCategoria() {
    const nombre = this.nombreBusqueda.trim();
    if (!nombre) {
      this.listarCategorias();
      return;
    }
    this.categoriaService.buscarPorNombres(nombre).subscribe({
      next: (data) => {
        this.categorias.set(data);
        if (data.length === 0) {
          this.mensajeError.set('No se encontraron categorías con ese nombre.');
        } else {
          this.mensajeError.set('');
        }
      },
      error: (error) => {
        console.error('Error al buscar categoría:', error);
        this.categorias.set([]);
        this.mensajeError.set('La categoría no existe.');
      }
    });
  }

}