import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../../environments/environment';

import { CategoriaRequest } from '../models/categoria-request';
import { CategoriaResponse } from '../models/categoria-response';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
    providedIn: 'root'
})
export class CategoriaService {

    private apiUrl = `${environment.apiUrl}/categoria`;

    constructor(private http: HttpClient) {

    }

    // Listar todas las categorías
    listar() {
        return this.http.get<CategoriaResponse[]>(this.apiUrl);
    }

    // Buscar categoría por ID
    buscarPorId(id: number): Observable<CategoriaResponse> {
        return this.http.get<CategoriaResponse>(`${this.apiUrl}/${id}`);
    }

    // Crear categoría
    guardar(request: CategoriaRequest): Observable<CategoriaResponse> {
        return this.http.post<CategoriaResponse>(this.apiUrl, request);
    }

    // Actualizar categoría
    actualizar(id: number, request: CategoriaRequest): Observable<CategoriaResponse> {
        return this.http.put<CategoriaResponse>(`${this.apiUrl}/${id}`, request);
    }

    // Desactivar categoría
    eliminar(id: number): Observable<string> {
        return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
    }

    // Activar categoría
    activar(id: number): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/${id}/activar`, {});
    }

    // Buscar categoría por nombre
    buscarPorNombre(nombre: string): Observable<CategoriaResponse> {
        return this.http.get<CategoriaResponse>(`${this.apiUrl}/buscar`, { params: { nombre: nombre } });
    }
}