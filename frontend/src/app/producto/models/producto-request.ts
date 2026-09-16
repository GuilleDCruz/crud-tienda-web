export interface ProductoRequest {
    nombre: string;
    descripcion: string;
    precio: number;
    stock: number;
    categoriaId: number;
    marcaId: number;
    proveedorId: number;
    tipoDeProductoId: number;
}