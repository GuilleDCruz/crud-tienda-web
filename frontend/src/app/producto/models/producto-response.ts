export interface ProductoResponse {
    id: number;
    nombre: string;
    descripcion: string;
    precio: number;
    stock: number;
    activo: boolean;

    categoriaId: number;
    categoriaNombre: string;

    marcaId: number;
    marcaNombre: string;

    proveedorId: number;
    proveedorNombre: string;

    tipoDeProductoId: number;
    tipoDeProductoNombre: string;

    createdAt: string;
    updatedAt: string;
}