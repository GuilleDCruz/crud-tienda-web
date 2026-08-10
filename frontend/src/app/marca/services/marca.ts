import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { MarcaResponse } from '../models/marca-response';
import { Observable } from 'rxjs/internal/Observable';
import { MarcaRequest } from '../models/marca-request';

@Injectable({
    providedIn: 'root'
})
export class MarcaService {
    private apiUrl = `${environment.apiUrl}/marca`;
    
    constructor(private http: HttpClient) {
    }

    //Listar todas las marcas
    listar() {
        return this.http.get<MarcaResponse[]>(this.apiUrl);
    }

    //Buscar marca por ID
    buscarPorId(id: number): Observable<MarcaResponse>{
        return this.http.get<MarcaResponse>(
            `${this.apiUrl}/${id}`
        );
    }

    //Crear marca
    guardar(
        request: MarcaRequest
    ): Observable<MarcaResponse> {
        return this.http.post<MarcaResponse>(
            this.apiUrl,
            request
        );
    }

    //Actualizar marca
    actualizar(
        id: number,
        request: MarcaRequest
    ): Observable<MarcaResponse> {
        return this.http.put<MarcaResponse>(
            `${this.apiUrl}/${id}`,
            request
        );
    }
    
    //Desactivar marca
    eliminar(id: number): Observable<string> {
        return this.http.delete(
            `${this.apiUrl}/${id}`,
            {
                responseType: 'text'
            }
        );
    }

    //Activar marca
    activar(id: number): Observable<void> {
        return this.http.put<void>(
            `${this.apiUrl}/activar/${id}`,
            null
        );
    }
    
    //Buscar marca por nombre
    buscarPorNombre(nombre: string): Observable<MarcaResponse> {
        return this.http.get<MarcaResponse>(
            `${this.apiUrl}/buscar`,
            {
                params: {
                    nombre: nombre
                }
            }
        );
    }
}
