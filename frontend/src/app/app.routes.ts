import { Routes } from '@angular/router';
import { CategoriaList } from './categoria/components/categoria-list/categoria-list';
import { ProveedorList } from './proveedor/components/proveedor-list/proveedor-list';
import { MarcaList } from './marca/components/marca-list/marca-list';
import { ProductoList } from './producto/components/producto-list/producto-list';

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
},
{
    path: 'producto',
    component: ProductoList
}
];
