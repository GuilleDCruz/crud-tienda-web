import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { TipoProductoModal } from '../tipo-producto-modal/tipo-producto-modal';
import { TipoProductoConfirm } from '../tipo-producto-confirm/tipo-producto-confirm';
import { TipoProductoResponse } from '../../models/tipo-producto-response';
import { TipoProductoService } from '../../services/tipo-producto';

@Component({
  selector: 'app-tipo-producto-list',
  imports: [FormsModule, TipoProductoModal, TipoProductoConfirm],
  templateUrl: './tipo-producto-list.html',
  styleUrl: './tipo-producto-list.scss',
})

export class TipoProductoList implements OnInit {
  cargando = signal(false);
  mensajeError = signal('');
  mostrarModal = signal(false);
  modoModal = signal<'crear' | 'editar' | null>(null);
  tipoProductoSeleccionado = signal<TipoProductoResponse | null>(null);
  mostrarConfirmacion = signal(false);
  accionConfirmacion = signal<'activar' | 'desactivar' | null>(null);
  nombreBusqueda = '';
  tiposProducto = signal<TipoProductoResponse[]>([]);

  constructor(private tipoProductoService: TipoProductoService) { }

  ngOnInit(): void {
    this.listarTiposProducto();
  }

  abrirCrear(): void {
    this.tipoProductoSeleccionado.set(null);
    this.modoModal.set('crear');
  }

  cerrarModal(): void {
    this.modoModal.set(null);
    this.tipoProductoSeleccionado.set(null);
  }

  tipoProductoCreado(): void {
    this.mostrarModal.set(false);
    this.listarTiposProducto();
  }

  tipoProductoGuardado(): void {
    this.cerrarModal();
    this.listarTiposProducto();
  }

  listarTiposProducto(): void {
    this.tipoProductoService.listar().subscribe({
      next: (data) => {
        this.tiposProducto.set(data);
        this.mensajeError.set('');
        this.cargando.set(false);
      },
      error: (error) => {
        this.tiposProducto.set([]);
        this.cargando.set(false);
        this.mensajeError.set('No fue posible obtener los tipos de producto.');
      }
    });
  }

  limpiarBusqueda(): void {
    this.listarTiposProducto();
  }

  editarTipoProducto(id: number): void {
    this.tipoProductoService.buscarPorId(id).subscribe({
      next: (tipoProducto) => {
        this.tipoProductoSeleccionado.set(tipoProducto);
        this.modoModal.set('editar');
      },
      error: (error) => {
        console.error('Error al obtener tipo de producto:', error);
        this.mensajeError.set('No fue posible obtener el tipo de producto.');
      }
    });
  }

  abrirConfirmacion(tipoProducto: TipoProductoResponse): void {
    this.tipoProductoSeleccionado.set(tipoProducto);
    this.accionConfirmacion.set(tipoProducto.activo ? 'desactivar' : 'activar');
    this.mostrarConfirmacion.set(true);
  }

  cerrarConfirmacion(): void {
    this.mostrarConfirmacion.set(false);
    this.tipoProductoSeleccionado.set(null);
    this.accionConfirmacion.set(null);
  }

  accionRealizada(): void {
    this.cerrarConfirmacion();
    this.listarTiposProducto();
  }

  buscarPorNombre(): void {
    const nombre = this.nombreBusqueda.trim();

    if (!nombre) {
      this.listarTiposProducto();
      return;
    }

    this.tipoProductoService.buscarPorNombre(nombre).subscribe({
      next: (data) => {
        this.tiposProducto.set(data);
        if (data.length === 0) {
          this.mensajeError.set(`No se encontraron tipos de producto con "${nombre}".`);
        } else {
          this.mensajeError.set('');
        }
      },
      error: (error) => {
        this.tiposProducto.set([]);
        this.mensajeError.set('No fue posible realizar la búsqueda.');
      }
    });
  }
}
