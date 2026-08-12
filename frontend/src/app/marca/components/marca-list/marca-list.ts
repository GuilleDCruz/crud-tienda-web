import { Component, OnInit, signal } from '@angular/core';
import { MarcaResponse } from '../../models/marca-response';
import { MarcaService } from '../../services/marca';
import { MarcaConfirm } from '../marca-confirm/marca-confirm';
import { MarcaModal } from '../marca-modal/marca-modal';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-marca-list',
  imports: [FormsModule, MarcaModal, MarcaConfirm],
  templateUrl: './marca-list.html',
  styleUrl: './marca-list.scss',
})
export class MarcaList implements OnInit {

  marcas = signal<MarcaResponse[]>([]);
  cargando = signal(false);
  mensajeError = signal('');
  mostrarModal = signal(false);
  nombreBusqueda = '';
  modoModal = signal<'crear' | 'editar' | null>(null);
  marcaSeleccionada = signal<MarcaResponse | null>(null);
  mostrarConfirmacion = signal(false);
  accionConfirmacion = signal<'activar' | 'desactivar' | null>(null);

  constructor(
    private marcaService: MarcaService
  ) { }

  abrirModal(): void {
    this.mostrarModal.set(true);
  }

  cerrarModal(): void {
    this.modoModal.set(null);
    this.marcaSeleccionada.set(null);
  }

  marcaCreada(): void {
    this.mostrarModal.set(false);
    this.listarMarcas();
  }

  marcaGuardada(): void {
    this.cerrarModal();
    this.listarMarcas();
  }

  ngOnInit(): void {
    this.listarMarcas();
  }

  abrirCrear(): void {
    this.marcaSeleccionada.set(null);
    this.modoModal.set('crear');
  }
  
  listarMarcas(): void {
    this.marcaService.listar().subscribe({
      next: (data) => {
        this.marcas.set(data);
        console.log(
          this.marcas().length
        );
        this.mensajeError.set('');
        this.cargando.set(false);
      },
      error: (error) => {
        this.marcas.set([]);
        this.cargando.set(false);
        this.mensajeError.set('No fue posible obtener las marcas.');
      }
    });
  }
  
  buscarPorId(id: string): void {
    console.log('Buscar marca por ID:', id);
  }

  limpiarBusqueda(): void {
    this.listarMarcas();
  }

  agregarMarca(): void {
    console.log('Abrir formulario para agregar una nueva marca');
  }

  editarMarca(id: number): void {
    this.marcaService.buscarPorId(id).subscribe({
      next: (marca) => {
        this.marcaSeleccionada.set(marca);
        this.modoModal.set('editar');
      },
      error: (error) => {
        console.error('Error al buscar la marca:', error);
        this.mensajeError.set('No fue posible obtener la marca.');
      }
    });
  }

  abrirConfirmacion(marca: MarcaResponse): void {
    this.marcaSeleccionada.set(marca);
    this.accionConfirmacion.set(
      marca.activo
        ? 'desactivar'
        : 'activar'
    );
    this.mostrarConfirmacion.set(true);
  }

  cerrarConfirmacion(): void {
    this.mostrarConfirmacion.set(false);
    this.marcaSeleccionada.set(null);
    this.accionConfirmacion.set(null);
  }

  accionRealizada(): void {
    this.cerrarConfirmacion();
    this.listarMarcas();
  }

  buscarMarca(){
    const nombre = this.nombreBusqueda.trim();
    if (!nombre) {
      this.listarMarcas();
      return;
    }
    this.marcaService.buscarPorNombres(nombre).subscribe({
      next: (data) => {
        this.marcas.set(data);
        if (data.length === 0) {
          this.mensajeError.set(
            `No se encontraron marcas con "${nombre}".`
          );
        } else {
          this.mensajeError.set('');
        }
      },
      error: (error) => {
        console.error('Error al buscar marca:', error);
        this.marcas.set([]);
        this.mensajeError.set(
          'No fue posible realizar la búsqueda.'
        );
      }
    });
  }
}
