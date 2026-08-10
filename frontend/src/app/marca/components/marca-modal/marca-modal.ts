import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { MarcaResponse } from '../../models/marca-response';
import { MarcaService } from '../../services/marca';
import { FormsModule } from '@angular/forms';
import { MarcaRequest } from '../../models/marca-request';

@Component({
  selector: 'app-marca-modal',
  imports: [FormsModule],
  standalone: true,
  templateUrl: './marca-modal.html',
  styleUrl: './marca-modal.scss',
})
export class MarcaModal {

  @Input()
  modo: 'crear' | 'editar' = 'crear';

  @Input()
  marca: MarcaResponse | null = null;

  @Output()
  cerrar = new EventEmitter<void>();

  @Output()
  marcaGuardada = new EventEmitter<void>();

  nombre = '';
  descripcion = '';
  cargando = signal(false);
  mensajeError = signal('');

  constructor(private marcaService: MarcaService) { }

  ngOnInit(): void {
    if (this.modo === 'editar' && this.marca) {
      this.nombre = this.marca.nombre;
      this.descripcion = this.marca.descripcion;
    }
  }

  guardar(): void {
    this.mensajeError.set('');
    if (!this.nombre.trim()) {
      this.mensajeError.set('El nombre de la marca es obligatorio.');
      return;
    }
    if (!this.descripcion.trim()) {
      this.mensajeError.set('La descripción de la marca es obligatoria.');
      return;
    }
    const request: MarcaRequest = {
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

  private crear(request: MarcaRequest): void {
    this.marcaService.guardar(request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.marcaGuardada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al crear marca:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible crear la marca.');
      }
    });
  }

  private actualizar(request: MarcaRequest): void {
    if (!this.marca) {
      return;
    }
    this.marcaService.actualizar(this.marca.id, request).subscribe({
      next: () => {
        this.cargando.set(false);
        this.marcaGuardada.emit();
        this.cerrar.emit();
      },
      error: (error) => {
        console.error('Error al actualizar marca:', error);
        this.cargando.set(false);
        this.mensajeError.set(error.error?.message ?? 'No fue posible actualizar la marca.');
      }
    });
  }

  cancelar(): void {
    this.cerrar.emit();
  }
}
