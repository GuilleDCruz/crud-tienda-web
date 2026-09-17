import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { SucursalService } from '../../services/sucursal';
import { SucursalResponse } from '../../models/sucursal-response';
import { SucursalRequest } from '../../models/sucursal-request';


@Component({
  selector: 'app-sucursal-modal',
  imports: [FormsModule],
  standalone: true,
  templateUrl: './sucursal-modal.html',
  styleUrl: './sucursal-modal.scss',
})
export class SucursalModal {
  @Input() modo: 'crear' | 'editar' = 'crear';
  @Input() sucursal: SucursalResponse | null = null;
  @Output() cerrar = new EventEmitter<void>();
  @Output() sucursalGuardada = new EventEmitter<void>();

  nombre = '';
  direccion = '';
  codigo = '';
  telefono = '';
  email = '';
  cargando = signal(false);
  mensajeError = signal('');

  constructor(private sucursalService: SucursalService) { }

  ngOnInit(): void {
    if (this.modo === 'editar' && this.sucursal) {
      this.nombre = this.sucursal.nombre;
      this.direccion = this.sucursal.direccion;
      this.codigo = this.sucursal.codigo;
      this.telefono = this.sucursal.telefono;
      this.email = this.sucursal.email;

    }
  }

  guardar(): void {
    this.mensajeError.set('');
    if (!this.nombre.trim()) {
      this.mensajeError.set('El nombre de la sucursal es obligatorio.');
      return;
    }
    if (!this.direccion.trim()) {
      this.mensajeError.set('La dirección de la sucursal es obligatoria.');
      return;
    }
    if (!this.codigo.trim()) {
      this.mensajeError.set('El código de la sucursal es obligatorio.');
      return;
    }
    if (!this.telefono.trim()) {
      this.mensajeError.set('El teléfono de la sucursal es obligatorio.');
      return;
    }
    if (!this.email.trim()) {
      this.mensajeError.set('El correo electrónico de la sucursal es obligatorio.');
      return;
    }
    const request: SucursalRequest = {
      nombre: this.nombre.trim(),
      direccion: this.direccion.trim(),
      codigo: this.codigo.trim(),
      telefono: this.telefono.trim(),
      email: this.email.trim()
    };
    this.cargando.set(true);
    if (this.modo === 'crear') {
      this.crear(request);
    } else {
      this.actualizar(request);
    }
  }

  private crear(request: SucursalRequest): void {
    this.sucursalService.guardar(request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.sucursalGuardada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al crear sucursal:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible crear la sucursal.');
      }
    });
  }

  private actualizar(request: SucursalRequest): void {
    if (!this.sucursal) {
      return;
    }
    this.sucursalService.actualizar(this.sucursal.id, request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.sucursalGuardada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al actualizar sucursal:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible actualizar la sucursal.');
      }
    });
  }

  cancelar(): void {
    this.cerrar.emit();
  }

}
