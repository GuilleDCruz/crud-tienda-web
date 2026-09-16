import { Component, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

export interface NavItem {
  label: string;
  route: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('frontend');

  readonly navItems: NavItem[] = [
    { label: 'Categorías', route: '/categoria' },
    { label: 'Marcas', route: '/marca' },
    { label: 'Proveedores', route: '/proveedor' },
    { label: 'Productos', route: '/producto' },
    { label: 'Tipo de Productos', route: '/tipo-producto' }
  ];
}
