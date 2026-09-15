package com.guilledev.backend.producto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guilledev.backend.categoria.entity.Categoria;
import com.guilledev.backend.categoria.repository.CategoriaRepository;
import com.guilledev.backend.exception.categoria.CategoriaNotFoundException;
import com.guilledev.backend.exception.marca.MarcaNotFoundException;
import com.guilledev.backend.exception.producto.ProductoDuplicadoException;
import com.guilledev.backend.exception.producto.ProductoNotFoundException;
import com.guilledev.backend.exception.proveedor.ProveedorNotFoundException;
import com.guilledev.backend.exception.tipoproducto.TipoProductoNotFoundException;
import com.guilledev.backend.marca.entity.Marca;
import com.guilledev.backend.marca.repository.MarcaRepository;
import com.guilledev.backend.producto.dto.ProductoRequest;
import com.guilledev.backend.producto.dto.ProductoResponse;
import com.guilledev.backend.producto.entity.Producto;
import com.guilledev.backend.producto.mapper.ProductoMapper;
import com.guilledev.backend.producto.repository.ProductoRepository;
import com.guilledev.backend.proveedor.entity.Proveedor;
import com.guilledev.backend.proveedor.repository.ProveedorRepository;
import com.guilledev.backend.tipo_de_producto.entity.TipoDeProducto;
import com.guilledev.backend.tipo_de_producto.repository.TipoDeProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;
    private final ProveedorRepository proveedorRepository;
    private final ProductoMapper productoMapper;
    private final TipoDeProductoRepository tipoProductoRepository;

    public ProductoServiceImpl(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository,
            MarcaRepository marcaRepository,
            ProveedorRepository proveedorRepository,
            ProductoMapper productoMapper,
            TipoDeProductoRepository tipoProductoRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.marcaRepository = marcaRepository;
        this.proveedorRepository = proveedorRepository;
        this.productoMapper = productoMapper;
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Override
    public ProductoResponse guardar(ProductoRequest productoRequest) {
        if (productoRepository.existsByNombreIgnoreCase(productoRequest.getNombre())) {
            throw new ProductoDuplicadoException(productoRequest.getNombre());
        }

        Categoria categoria = categoriaRepository.findById(productoRequest.getCategoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(productoRequest.getCategoriaId()));

        Marca marca = marcaRepository.findById(productoRequest.getMarcaId())
                .orElseThrow(() -> new MarcaNotFoundException(productoRequest.getMarcaId()));

        Proveedor proveedor = proveedorRepository.findById(productoRequest.getProveedorId())
                .orElseThrow(() -> new ProveedorNotFoundException(productoRequest.getProveedorId()));

        TipoDeProducto tipoDeProducto = tipoProductoRepository.findById(productoRequest.getTipoDeProductoId())
                .orElseThrow(() -> new TipoProductoNotFoundException(productoRequest.getTipoDeProductoId()));

        Producto producto = productoMapper.toEntity(productoRequest);

        producto.setCategoria(categoria);
        producto.setMarca(marca);
        producto.setProveedor(proveedor);
        producto.setTipoDeProducto(tipoDeProducto);

        Producto productoGuardado = productoRepository.save(producto);

        return productoMapper.toResponse(productoGuardado);
    }

    @Override
    public List<ProductoResponse> listar() {
        return productoRepository.findAll().stream().map(productoMapper::toResponse).toList();
    }

    @Override
    public List<ProductoResponse> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream().map(productoMapper::toResponse)
                .toList();
    }

    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));

        if (!producto.getNombre().equalsIgnoreCase(request.getNombre())
                && productoRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new ProductoDuplicadoException(request.getNombre());
        }

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(request.getCategoriaId()));

        Marca marca = marcaRepository.findById(request.getMarcaId())
                .orElseThrow(() -> new MarcaNotFoundException(request.getMarcaId()));

        Proveedor proveedor = proveedorRepository.findById(request.getProveedorId())
                .orElseThrow(() -> new ProveedorNotFoundException(request.getProveedorId()));

        TipoDeProducto tipoDeProducto = tipoProductoRepository.findById(request.getTipoDeProductoId())
                .orElseThrow(() -> new TipoProductoNotFoundException(request.getTipoDeProductoId()));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());

        producto.setCategoria(categoria);
        producto.setMarca(marca);
        producto.setProveedor(proveedor);
        producto.setTipoDeProducto(tipoDeProducto);

        Producto productoActualizado = productoRepository.save(producto);

        return productoMapper.toResponse(productoActualizado);
    }

    @Override
    public ProductoResponse eliminar(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
        producto.setActivo(false);
        Producto productoActualizado = productoRepository.save(producto);

        return productoMapper.toResponse(productoActualizado);
    }

    @Override
    public ProductoResponse activar(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
        producto.setActivo(true);
        Producto productoActualizado = productoRepository.save(producto);

        return productoMapper.toResponse(productoActualizado);
    }

    @Override
    public List<ProductoResponse> buscarPorNombres(String nombre) {
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
        return productos.stream().map(productoMapper::toResponse).toList();
    }

}
