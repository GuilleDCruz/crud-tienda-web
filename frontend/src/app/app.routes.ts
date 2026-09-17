import { Routes } from '@angular/router';
import { CategoriaList } from './categoria/components/categoria-list/categoria-list';
import { ProveedorList } from './proveedor/components/proveedor-list/proveedor-list';
import { MarcaList } from './marca/components/marca-list/marca-list';
import { ProductoList } from './producto/components/producto-list/producto-list';
import { TipoProductoList } from './tipo-producto/components/tipo-producto-list/tipo-producto-list';
import { SucursalList } from './sucursal/components/sucursal-list/sucursal-list';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'producto',
    pathMatch: 'full'
  },
  {
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
  },
  {
    path: 'tipo-producto',
    component: TipoProductoList
  },
  {
    path: 'sucursal',
    component: SucursalList
  }
];
