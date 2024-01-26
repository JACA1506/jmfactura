package com.jmfactura.jmfactura.repository;

import com.jmfactura.jmfactura.models.proveedores.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

}
