import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

import { TipoProductoResponse } from '../../models/tipo-producto-response';
import { TipoProductoService } from '../../services/tipo-producto';
import { TipoProductoRequest } from '../../models/tipo-producto-request';

@Component({
  selector: 'app-tipo-producto-modal',
  imports: [FormsModule],
  templateUrl: './tipo-producto-modal.html',
  styleUrl: './tipo-producto-modal.scss',
})
export class TipoProductoModal {

  @Input()
  modo: 'crear' | 'editar' = 'crear';

  @Input()
  tipoProducto: TipoProductoResponse | null = null;

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  tipoProductoGuardado = new EventEmitter<void>();

  nombre = '';
  descripcion = '';
  cargando = signal(false);
  mensajeError = signal('');

  constructor(
    private tipoProductoService: TipoProductoService
  ) { }

  ngOnInit(): void {
    if (this.modo === 'editar' && this.tipoProducto) {
      this.nombre = this.tipoProducto.nombre;
      this.descripcion = this.tipoProducto.descripcion;
    }
  }

  guardar(): void {
    this.mensajeError.set('');
    if (!this.nombre.trim()) {
      this.mensajeError.set('El nombre del tipo de producto es obligatorio.');
      return;
    }
    if (!this.descripcion.trim()) {
      this.mensajeError.set('La descripción del tipo de producto es obligatoria.');
      return;
    }
    const request: TipoProductoRequest = {
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

  private crear(request: TipoProductoRequest): void {
    this.tipoProductoService.guardar(request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.tipoProductoGuardado.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al crear tipo de producto:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible crear el tipo de producto.');
      }
    });
  }

  private actualizar(request: TipoProductoRequest): void {
    if (!this.tipoProducto) {
      return;
    }
    this.tipoProductoService.actualizar(this.tipoProducto.id, request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.tipoProductoGuardado.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al actualizar tipo de producto:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible actualizar el tipo de producto.');
      }
    });
  }

  cancelar(): void {
    this.cerrar.emit();
  }


}
