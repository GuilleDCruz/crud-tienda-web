import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProveedorResponse } from '../../models/proveedor-response';

import { ProveedorService } from '../../services/proveedor';
import { ProveedorRequest } from '../../models/proveedor-request';
@Component({
  selector: 'app-proveedor-modal',
  imports: [FormsModule], 
  standalone: true,
  templateUrl: './proveedor-modal.html',
  styleUrl: './proveedor-modal.scss',
})
export class ProveedorModal {

  @Input()
  modo: 'crear' | 'editar' = 'crear';

  @Input()
  proveedor: ProveedorResponse | null = null;

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  proveedorGuardado = new EventEmitter<void>();

  nombre = '';
  descripcion = '';
  cargando = signal(false);
  mensajeError = signal('');
  constructor(private proveedorService: ProveedorService) { }

  ngOnInit(): void {
    if (this.modo === 'editar' && this.proveedor) {
      this.nombre = this.proveedor.nombre;
      this.descripcion = this.proveedor.descripcion;
    }
  }

  guardar(): void {
    this.mensajeError.set('');
    if (!this.nombre.trim()) {
      this.mensajeError.set('El nombre del proveedor es obligatorio.');
      return;
    }
    if (!this.descripcion.trim()) {
      this.mensajeError.set('La descripción del proveedor es obligatoria.');
      return;
    }
    const request: ProveedorRequest = {
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
  
  private crear(request: ProveedorRequest): void {
    this.proveedorService.guardar(request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.proveedorGuardado.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al crear proveedor:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible crear el proveedor.');
      }
    });
  }

  private actualizar(request: ProveedorRequest): void {
    if (!this.proveedor) {
      return;
    }
    this.proveedorService.actualizar(this.proveedor.id, request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.proveedorGuardado.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al actualizar proveedor:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible actualizar el proveedor.');
      }
    });
  }

  cancelar(): void {
    this.cerrar.emit();
  }

}
