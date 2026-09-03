import { Component, EventEmitter, Input, OnInit, Output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductoService } from '../../services/producto';
import { ProductoResponse } from '../../models/producto-response';
import { ProductoRequest } from '../../models/producto-request';
import { CategoriaResponse } from '../../../categoria/models/categoria-response';
import { MarcaResponse } from '../../../marca/models/marca-response';
import { ProveedorResponse } from '../../../proveedor/models/proveedor-response';

@Component({
  selector: 'app-producto-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './producto-modal.html'
})
export class ProductoModal implements OnInit {

  @Input()
  modo: 'crear' | 'editar' = 'crear';

  @Input()
  producto: ProductoResponse | null = null;

  @Input()
  categorias: CategoriaResponse[] = [];

  @Input()
  marcas: MarcaResponse[] = [];

  @Input()
  proveedores: ProveedorResponse[] = [];

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  productoGuardado = new EventEmitter<void>();

  nombre: string = '';
  descripcion: string = '';
  precio: number | null = null;
  stock: number | null = null;
  categoriaId: number | null = null;
  marcaId: number | null = null;
  proveedorId: number | null = null;
  activo: boolean = true;

  cargandoGuardado = signal(false);
  mensajeError = signal('');
  mensajeConfirmacion = signal('');

  constructor(private productoServicio: ProductoService) {}

  ngOnInit(): void {
    if (this.modo === 'editar' && this.producto) {
      this.nombre = this.producto.nombre;
      this.descripcion = this.producto.descripcion || '';
      this.precio = this.producto.precio;
      this.stock = this.producto.stock;
      this.categoriaId = this.producto.categoriaId;
      this.marcaId = this.producto.marcaId;
      this.proveedorId = this.producto.proveedorId;
      this.activo = this.producto.activo;
    } else {
      this.resetFormulario();
    }
  }

  resetFormulario(): void {
    this.nombre = '';
    this.descripcion = '';
    this.precio = null;
    this.stock = null;
    this.categoriaId = this.categorias.length > 0 ? this.categorias[0].id : null;
    this.marcaId = this.marcas.length > 0 ? this.marcas[0].id : null;
    this.proveedorId = this.proveedores.length > 0 ? this.proveedores[0].id : null;
    this.activo = true;
    this.mensajeError.set('');
    this.mensajeConfirmacion.set('');
  }

  guardar(): void {
    if (this.cargandoGuardado()) {
      return;
    }
    this.mensajeError.set('');
    this.mensajeConfirmacion.set('');

    if (!this.nombre.trim()) {
      this.mensajeError.set('El nombre del producto es obligatorio.');
      return;
    }
    if (this.precio === null || this.precio === undefined || this.precio <= 0) {
      this.mensajeError.set('El precio debe ser un número mayor a 0.');
      return;
    }
    if (this.stock === null || this.stock === undefined || this.stock < 0) {
      this.mensajeError.set('El stock debe ser un número mayor o igual a 0.');
      return;
    }
    if (!this.categoriaId) {
      this.mensajeError.set('Debe seleccionar una categoría.');
      return;
    }
    if (!this.marcaId) {
      this.mensajeError.set('Debe seleccionar una marca.');
      return;
    }
    if (!this.proveedorId) {
      this.mensajeError.set('Debe seleccionar un proveedor.');
      return;
    }

    const request: ProductoRequest = {
      nombre: this.nombre.trim(),
      descripcion: this.descripcion ? this.descripcion.trim() : '',
      precio: Number(this.precio),
      stock: Number(this.stock),
      categoriaId: Number(this.categoriaId),
      marcaId: Number(this.marcaId),
      proveedorId: Number(this.proveedorId)
    };

    this.cargandoGuardado.set(true);
    if (this.modo === 'crear') {
      this.crear(request);
    } else {
      this.actualizar(request);
    }
  }

  private crear(request: ProductoRequest): void {
    this.productoServicio.guardar(request).subscribe({
      next: () => {
        this.cargandoGuardado.set(false);
        this.productoGuardado.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al crear producto:', error);
        this.cargandoGuardado.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible crear el producto.');
      }
    });
  }

  private actualizar(request: ProductoRequest): void {
    if (!this.producto) {
      this.cargandoGuardado.set(false);
      return;
    }
    this.productoServicio.actualizar(this.producto.id, request).subscribe({
      next: () => {
        this.cargandoGuardado.set(false);
        this.productoGuardado.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al actualizar producto:', error);
        this.cargandoGuardado.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible actualizar el producto.');
      }
    });
  }

  cancelar(): void {
    this.cerrar.emit();
  }
}
