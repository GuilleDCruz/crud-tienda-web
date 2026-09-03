import { Component, OnInit, signal } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';
import { ProductoService } from '../../services/producto';
import { ProductoResponse } from '../../models/producto-response';
import { CategoriaService } from '../../../categoria/services/categoria';
import { CategoriaResponse } from '../../../categoria/models/categoria-response';
import { MarcaService } from '../../../marca/services/marca';
import { MarcaResponse } from '../../../marca/models/marca-response';
import { ProveedorService } from '../../../proveedor/services/proveedor';
import { ProveedorResponse } from '../../../proveedor/models/proveedor-response';
import { ProductoModal } from '../producto-modal/producto-modal';
import { ProductoConfirm } from '../producto-confirm/producto-confirm';

@Component({
  selector: 'app-producto-list',
  standalone: true,
  imports: [CommonModule, FormsModule, DecimalPipe, ProductoModal, ProductoConfirm],
  templateUrl: './producto-list.html'
})
export class ProductoList implements OnInit {
  productos = signal<ProductoResponse[]>([]);
  cargando = signal(false);
  mensajeError = signal('');
  nombreBusqueda = '';

  categorias = signal<CategoriaResponse[]>([]);
  marcas = signal<MarcaResponse[]>([]);
  proveedores = signal<ProveedorResponse[]>([]);

  modoModal = signal<'crear' | 'editar' | null>(null);
  productoSeleccionado = signal<ProductoResponse | null>(null);
  mostrarConfirmacionModal = signal(false);
  accionConfirmacion = signal<'activar' | 'desactivar' | null>(null);

  constructor(
    private productoServicio: ProductoService,
    private categoriaServicio: CategoriaService,
    private marcaServicio: MarcaService,
    private proveedorServicio: ProveedorService
  ) {}

  ngOnInit(): void {
    this.listarProductos();
    this.cargarDatosDeReferencia();
  }

  cargarDatosDeReferencia(): void {
    forkJoin({
      categorias: this.categoriaServicio.listar(),
      marcas: this.marcaServicio.listar(),
      proveedores: this.proveedorServicio.listar()
    }).subscribe({
      next: (res) => {
        this.categorias.set(res.categorias);
        this.marcas.set(res.marcas);
        this.proveedores.set(res.proveedores);
      },
      error: (error) => {
        console.error('Error cargando datos de referencia:', error);
      }
    });
  }

  listarProductos(): void {
    this.cargando.set(true);
    this.mensajeError.set('');

    this.productoServicio.listar().subscribe({
      next: (data) => {
        this.productos.set(data);
        this.cargando.set(false);
        this.mensajeError.set('');
      },
      error: (error) => {
        console.error('Error al listar productos:', error);
        this.productos.set([]);
        this.cargando.set(false);
        this.mensajeError.set('Error al cargar los productos.');
      }
    });
  }

  buscarProductos(): void {
    const nombre = this.nombreBusqueda.trim();
    if (!nombre) {
      this.listarProductos();
      return;
    }

    this.cargando.set(true);
    this.mensajeError.set('');

    this.productoServicio.buscarPorNombres(nombre).subscribe({
      next: (data) => {
        this.productos.set(data);
        this.cargando.set(false);
        if (data.length === 0) {
          this.mensajeError.set(`No se encontraron productos con "${nombre}".`);
        } else {
          this.mensajeError.set('');
        }
      },
      error: (error) => {
        console.error('Error al buscar productos:', error);
        this.productos.set([]);
        this.cargando.set(false);
        this.mensajeError.set('No fue posible realizar la búsqueda.');
      }
    });
  }

  abrirCrear(): void {
    this.productoSeleccionado.set(null);
    this.modoModal.set('crear');
    this.accionConfirmacion.set(null);
  }

  editarProducto(id: number): void {
    const producto = this.productos().find((p) => p.id === id);
    if (producto) {
      this.productoSeleccionado.set(producto);
      this.modoModal.set('editar');
      this.accionConfirmacion.set(null);
    } else {
      this.mensajeError.set(`Error al cargar el producto. ID: ${id}`);
    }
  }

  cerrarModal(): void {
    this.modoModal.set(null);
    this.productoSeleccionado.set(null);
  }

  productoGuardado(): void {
    this.cerrarModal();
    this.listarProductos();
  }

  abrirConfirmacion(producto: ProductoResponse): void {
    this.productoSeleccionado.set(producto);
    this.accionConfirmacion.set(producto.activo ? 'desactivar' : 'activar');
    this.mostrarConfirmacionModal.set(true);
  }

  cerrarConfirmacion(): void {
    this.mostrarConfirmacionModal.set(false);
    this.productoSeleccionado.set(null);
    this.accionConfirmacion.set(null);
  }

  accionRealizada(): void {
    this.cerrarConfirmacion();
    this.listarProductos();
  }
}