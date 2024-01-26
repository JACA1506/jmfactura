package com.jmfactura.jmfactura.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmfactura.jmfactura.models.productos.Producto;
import com.jmfactura.jmfactura.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public void deleteById(Integer id) {

        productoRepository.deleteById(id);
    }

    public Optional<Producto> getById(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto saveOrUpdate(Producto producto) {
        return productoRepository.save(producto);
    }

}
