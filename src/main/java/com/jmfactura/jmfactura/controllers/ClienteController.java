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

import com.jmfactura.jmfactura.models.clientes.Cliente;
import com.jmfactura.jmfactura.models.productos.Producto;
import com.jmfactura.jmfactura.services.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "getAll")
    public @ResponseBody List<Cliente> getAll() {

        List<Cliente> listClientes = clienteService.getAll();

        return listClientes;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.ok("Cliente deleted successfully!.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "getById/{id}")
    public @ResponseBody Optional<Cliente> getById(@PathVariable("id") Integer id) {

        Optional<Cliente> cliente = clienteService.getById(id);

        return cliente;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public @ResponseBody Cliente update(@PathVariable String id, @RequestBody Cliente cliente) {

        return clienteService.saveOrUpdate(cliente);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save")
    public @ResponseBody Cliente save(@RequestBody Cliente cliente) {

        return clienteService.saveOrUpdate(cliente);
    }

}
