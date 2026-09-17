import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { SucursalRequest } from '../models/sucursal-request';
import { SucursalResponse } from '../models/sucursal-response';

@Injectable({
    providedIn: 'root'
})
export class SucursalService {
    private apiUrl = `${environment.apiUrl}/sucursal`;

    constructor(private http: HttpClient) { }

    // Listar todas las sucursales
    listar(): Observable<SucursalResponse[]> {
        return this.http.get<SucursalResponse[]>(`${this.apiUrl}`);
    }

    // Buscar sucursal por ID
    buscarPorId(id: number): Observable<SucursalResponse> {
        return this.http.get<SucursalResponse>(`${this.apiUrl}/${id}`);
    }

    // Crear sucursal
    guardar(request: SucursalRequest): Observable<SucursalResponse> {
        return this.http.post<SucursalResponse>(this.apiUrl, request);
    }

    // Actualizar sucursal
    actualizar(id: number, request: SucursalRequest): Observable<SucursalResponse> {
        return this.http.put<SucursalResponse>(`${this.apiUrl}/${id}`, request);
    }

    // Eliminar sucursal
    eliminar(id: number): Observable<string> {
        return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
    }

    // Activar sucursal
    activar(id: number): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/${id}/activar`, {});
    }

    // Buscar sucursal por nombre
    buscarPorNombre(nombre: string): Observable<SucursalResponse[]> {
        return this.http.get<SucursalResponse[]>(`${this.apiUrl}/buscarNombres`, { params: { nombre } });
    }
}