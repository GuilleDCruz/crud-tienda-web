import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../../environments/environment';
import { ProveedorResponse } from '../models/proveedor-response';
import { Observable } from 'rxjs/internal/Observable';
import { ProveedorRequest } from '../models/proveedor-request';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {
    private apiUrl = `${environment.apiUrl}/proveedor`;

    constructor(private http: HttpClient) { }

    // Listar todos los proveedores
    listar() {
        return this.http.get<ProveedorResponse[]>(`${this.apiUrl}`);
    }

    // Buscar proveedor por ID
    buscarPorId(id: number): Observable<ProveedorResponse> {
        return this.http.get<ProveedorResponse>(`${this.apiUrl}/${id}`);
    }

    // Crear proveedor
    guardar(request: ProveedorRequest): Observable<ProveedorResponse> {
        return this.http.post<ProveedorResponse>(this.apiUrl, request);
    }

    // Actualizar proveedor
    actualizar(id: number, request: ProveedorRequest): Observable<ProveedorResponse> {
        return this.http.put<ProveedorResponse>(`${this.apiUrl}/${id}`, request);
    }

    // Desactivar proveedor
    eliminar(id: number): Observable<string> {
        return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
    }

    // Activar proveedor
    activar(id: number): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/${id}/activar`, {});
    }

    // Buscar proveedor por nombre
    buscarPorNombre(nombre: string): Observable<ProveedorResponse> {
        return this.http.get<ProveedorResponse>(`${this.apiUrl}/buscar`, { params: { nombre: nombre } });
    }   
}
