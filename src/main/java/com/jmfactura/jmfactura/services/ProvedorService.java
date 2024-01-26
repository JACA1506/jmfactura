package com.jmfactura.jmfactura.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmfactura.jmfactura.models.proveedores.Proveedor;
import com.jmfactura.jmfactura.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProvedorService {

    private final ProveedorRepository proveedorRepository;

    public List<Proveedor> getAll() {
        return proveedorRepository.findAll();
    }

    public void deleteById(Integer id) {

        proveedorRepository.deleteById(id);
    }

    public Optional<Proveedor> getById(Integer id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor saveOrUpdate(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

}
