import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SucursalConfirm } from '../sucursal-confirm/sucursal-confirm';
import { SucursalModal } from '../sucursal-modal/sucursal-modal';
import { SucursalResponse } from '../../models/sucursal-response';
import { SucursalService } from '../../services/sucursal';


@Component({
  selector: 'app-sucursal-list',
  imports: [FormsModule, SucursalModal, SucursalConfirm],
  templateUrl: './sucursal-list.html',
  styleUrl: './sucursal-list.scss',
})
export class SucursalList implements OnInit {

  cargando = signal(false);
  mensajeError = signal('');
  mostrarModal = signal(false);
  modoModal = signal<'crear' | 'editar' | null>(null);
  sucursalSeleccionado = signal<SucursalResponse | null>(null);
  mostrarConfirmacion = signal(false);
  accionConfirmacion = signal<'activar' | 'desactivar' | null>(null);
  nombreBusqueda = '';
  sucursales = signal<SucursalResponse[]>([]);

  constructor(private sucursalService: SucursalService) { }

  abrirModal(): void {
    this.mostrarModal.set(true);
  }

  cerrarModal(): void {
    this.modoModal.set(null);
    this.sucursalSeleccionado.set(null);
  }

  sucursalCreada(): void {
    this.mostrarModal.set(false);
    this.listarSucursales();
  }

  sucursalGuardada(): void {
    this.cerrarModal();
    this.listarSucursales();
  }

  ngOnInit(): void {
    this.listarSucursales();
  }

  abrirCrear(): void {
    this.sucursalSeleccionado.set(null);
    this.modoModal.set('crear');
  }

  listarSucursales(): void {
    this.sucursalService.listar().subscribe({
      next: (data) => {
        this.sucursales.set(data);
        this.mensajeError.set('');
        this.cargando.set(false);
      },
      error: (error) => {
        this.sucursales.set([]);
        this.cargando.set(false);
        this.mensajeError.set('No fue posible obtener las sucursales.');
      }
    });
  }

  limpiarBusqueda(): void {
    this.listarSucursales();
  }

  editarSucursal(id: number): void {
    this.sucursalService.buscarPorId(id).subscribe({
      next: (sucursal) => {
        this.sucursalSeleccionado.set(sucursal);
        this.modoModal.set('editar');
      },
      error: (error) => {
        console.error('Error al obtener sucursal:', error);
        this.mensajeError.set('No fue posible obtener la sucursal.');
      }
    });
  }

  abrirConfirmacion(sucursal: SucursalResponse): void {
    this.sucursalSeleccionado.set(sucursal);
    this.accionConfirmacion.set(sucursal.activo ? 'desactivar' : 'activar');
    this.mostrarConfirmacion.set(true);
  }

  cerrarConfirmacion(): void {
    this.mostrarConfirmacion.set(false);
    this.sucursalSeleccionado.set(null);
    this.accionConfirmacion.set(null);
  }

  accionRealizada(): void {
    this.cerrarConfirmacion();
    this.listarSucursales();
  }

  buscarSucursal() {
    const nombre = this.nombreBusqueda.trim();
    if (!nombre) {
      this.listarSucursales();
      return;
    }
    this.sucursalService.buscarPorNombre(nombre).subscribe({
      next: (data) => {
        this.sucursales.set(data);
        if (data.length === 0) {
          this.mensajeError.set(`No se encontraron sucursales con "${nombre}".`);
        } else {
          this.mensajeError.set('');
        }
      },
      error: (error) => {
        console.error('Error al buscar sucursal:', error);
        this.sucursales.set([]);
        this.mensajeError.set('No fue posible realizar la búsqueda.');
      }
    });
  }
}
