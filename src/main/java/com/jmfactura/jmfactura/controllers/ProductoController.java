package com.jmfactura.jmfactura.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmfactura.jmfactura.models.productos.Producto;
import com.jmfactura.jmfactura.models.proveedores.Proveedor;
import com.jmfactura.jmfactura.services.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "getAll")
    public @ResponseBody List<Producto> getAll() {

        List<Producto> listProveedor = productoService.getAll();

        return listProveedor;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Integer id) {
        productoService.deleteById(id);
        return ResponseEntity.ok("Producto deleted successfully!.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "getById/{id}")
    public @ResponseBody Optional<Producto> getById(@PathVariable("id") Integer id) {

        Optional<Producto> producto = productoService.getById(id);

        return producto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public @ResponseBody Producto update(@PathVariable String id, @RequestBody Producto producto) {

        return productoService.saveOrUpdate(producto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save")
    public @ResponseBody Producto save(@RequestBody Producto producto) {

        return productoService.saveOrUpdate(producto);
    }

}
