package com.jmfactura.jmfactura.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmfactura.jmfactura.auth.AuthService;
import com.jmfactura.jmfactura.models.proveedores.Proveedor;
import com.jmfactura.jmfactura.services.ProvedorService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProvedorService provedorService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "getAll")
    public @ResponseBody List<Proveedor> getAll() {

        List<Proveedor> listProveedor = provedorService.getAll();

        return listProveedor;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Integer id) {
        provedorService.deleteById(id);
        return ResponseEntity.ok("Proveedor deleted successfully!.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "getById/{id}")
    public @ResponseBody Optional<Proveedor> getById(@PathVariable("id") Integer id) {

        Optional<Proveedor> proveedor = provedorService.getById(id);

        return proveedor;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public @ResponseBody Proveedor update(@PathVariable String id, @RequestBody Proveedor proveedor) {

        return provedorService.saveOrUpdate(proveedor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save")
    public @ResponseBody Proveedor save(@RequestBody Proveedor proveedor) {

        return provedorService.saveOrUpdate(proveedor);
    }

}
