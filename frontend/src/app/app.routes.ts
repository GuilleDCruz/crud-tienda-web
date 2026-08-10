import { Routes } from '@angular/router';
import { CategoriaList } from './categoria/components/categoria-list/categoria-list';
import { ProveedorList } from './proveedor/components/proveedor-list/proveedor-list';
import { MarcaList } from './marca/components/marca-list/marca-list';

export const routes: Routes = [{
    path: 'categoria',
    component: CategoriaList
},
{
    path: 'proveedor',
    component: ProveedorList
},
{
    path: 'marca',
    component: MarcaList
}
];
