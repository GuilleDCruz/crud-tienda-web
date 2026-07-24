import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../../environments/environment';

import { CategoriaRequest } from '../models/categoria-request';
import { CategoriaResponse } from '../models/categoria-response';

@Injectable({
    providedIn: 'root'
})
export class CategoriaService {

    private apiUrl = `${environment.apiUrl}/categoria`;

    constructor(private http: HttpClient) {

    }

    listar() {
        return this.http.get<CategoriaResponse[]>(this.apiUrl);
    }

}