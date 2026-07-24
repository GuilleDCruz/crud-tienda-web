import { Component, OnInit, signal } from '@angular/core';

import { CategoriaService } from '../../services/categoria';
import { CategoriaResponse } from '../../models/categoria-response';

@Component({
  selector: 'app-categoria-list',
  imports: [],
  templateUrl: './categoria-list.html',
  styleUrl: './categoria-list.scss'
})
export class CategoriaList implements OnInit {

  categorias = signal<CategoriaResponse[]>([]);

  constructor(
    private categoriaService: CategoriaService
  ) {}

  ngOnInit(): void {

    this.categoriaService.listar().subscribe({
      next: (respuesta) => {

        console.log('Respuesta recibida:', respuesta);

        this.categorias.set(respuesta);

        console.log(
          'Cantidad de categorías:',
          this.categorias().length
        );

      },
      error: (error) => {
        console.error('Error al obtener categorías:', error);
      }
    });

  }
}