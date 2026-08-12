import { Component, OnInit, signal } from '@angular/core';
import { ProveedorConfirm } from '../proveedor-confirm/proveedor-confirm';
import { ProveedorModal } from '../proveedor-modal/proveedor-modal';
import { FormsModule } from '@angular/forms';
import { ProveedorResponse } from '../../models/proveedor-response';
import { ProveedorService } from '../../services/proveedor';

@Component({
  selector: 'app-proveedor-list',
  imports: [FormsModule, ProveedorModal, ProveedorConfirm],
  templateUrl: './proveedor-list.html',
  styleUrl: './proveedor-list.scss',
})
export class ProveedorList implements OnInit {

  cargando = signal(false);
  mensajeError = signal('');
  mostrarModal = signal(false);
  modoModal = signal<'crear' | 'editar' | null>(null);
  proveedorSeleccionado = signal<ProveedorResponse | null>(null);
  mostrarConfirmacion = signal(false);
  accionConfirmacion = signal<'activar' | 'desactivar' | null>(null);
  nombreBusqueda = '';
  proveedores = signal<ProveedorResponse[]>([]);

  constructor(private proveedorService: ProveedorService) { }

  abrirModal(): void {
    this.mostrarModal.set(true);
  }

  cerrarModal(): void {
    this.modoModal.set(null);
    this.proveedorSeleccionado.set(null);
  }

  proveedorCreado(): void {
    this.mostrarModal.set(false);
    this.listarProveedores();
  }

  proveedorGuardado(): void {
    this.cerrarModal();
    this.listarProveedores();
  }

  ngOnInit(): void {
    this.listarProveedores();
  }

  abrirCrear(): void {
    this.proveedorSeleccionado.set(null);
    this.modoModal.set('crear');
  }

  listarProveedores(): void {
    this.proveedorService.listar().subscribe({
      next: (data) => {
        this.proveedores.set(data);
        this.mensajeError.set('');
        this.cargando.set(false);
      },
      error: (error) => {
        this.proveedores.set([]);
        this.cargando.set(false);
        this.mensajeError.set('No fue posible obtener los proveedores.');
      }
    });
  }

  limpiarBusqueda(): void {
    this.listarProveedores();
  }

  editarProveedor(id: number): void {
    this.proveedorService.buscarPorId(id).subscribe({
      next: (proveedor) => {
        this.proveedorSeleccionado.set(proveedor);
        this.modoModal.set('editar');
      },
      error: (error) => {
        console.error('Error al obtener proveedor:', error);
        this.mensajeError.set('No fue posible obtener el proveedor.');
      }
    });
  }

  abrirConfirmacion(proveedor: ProveedorResponse): void {
    this.proveedorSeleccionado.set(proveedor);
    this.accionConfirmacion.set(proveedor.activo ? 'desactivar' : 'activar');
    this.mostrarConfirmacion.set(true);
  }

  cerrarConfirmacion(): void {
    this.mostrarConfirmacion.set(false);
    this.proveedorSeleccionado.set(null);
    this.accionConfirmacion.set(null);
  }

  accionRealizada(): void {
    this.cerrarConfirmacion();
    this.listarProveedores();
  }

  buscarProveedor() {
    const nombre = this.nombreBusqueda.trim();
    if (!nombre) {
      this.listarProveedores();
      return;
    }
    this.proveedorService.buscarPorNombre(nombre).subscribe({
      next: (data) => {
        this.proveedores.set(data);
        if (data.length === 0) {
          this.mensajeError.set(`No se encontraron proveedores con "${nombre}".`);
        } else {
          this.mensajeError.set('');
        }
      },
      error: (error) => {
        console.error('Error al buscar proveedor:', error);
        this.proveedores.set([]);
        this.mensajeError.set('No fue posible realizar la búsqueda.');
      }
    });
  }
}
