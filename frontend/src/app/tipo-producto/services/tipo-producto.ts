import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';

import { environment } from '../../../environments/environment';
import { TipoProductoResponse } from '../models/tipo-producto-response';
import { Observable } from 'rxjs/internal/Observable';
import { TipoProductoRequest } from '../models/tipo-producto-request';

@Injectable({
    providedIn: 'root'
})
export class TipoProductoService {
    private apiUrl = `${environment.apiUrl}/tipo-de-producto`;

    constructor(private http: HttpClient) { }

    // Listar todos los tipos de productos
    listar(): Observable<TipoProductoResponse[]> {
        return this.http.get<TipoProductoResponse[]>(`${this.apiUrl}`);
    }

    // Buscar tipo de producto por ID
    buscarPorId(id: number): Observable<TipoProductoResponse> {
        return this.http.get<TipoProductoResponse>(`${this.apiUrl}/${id}`);
    }

    // Crear tipo de producto
    guardar(request: TipoProductoRequest): Observable<TipoProductoResponse> {
        return this.http.post<TipoProductoResponse>(this.apiUrl, request);
    }

    // Actualizar tipo de producto
    actualizar(id: number, request: TipoProductoRequest): Observable<TipoProductoResponse> {
        return this.http.put<TipoProductoResponse>(`${this.apiUrl}/${id}`, request);
    }

    // Eliminar tipo de producto
    eliminar(id: number): Observable<string> {
        return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
    }

    // Activar tipo de producto
    activar(id: number): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/${id}/activar`, {});
    }

    // Buscar tipo de producto por nombre
    buscarPorNombre(nombre: string): Observable<TipoProductoResponse[]> {
        return this.http.get<TipoProductoResponse[]>(`${this.apiUrl}/buscarNombres`, { params: { nombre } });
    }
}
