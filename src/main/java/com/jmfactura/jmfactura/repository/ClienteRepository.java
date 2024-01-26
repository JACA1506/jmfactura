package com.jmfactura.jmfactura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmfactura.jmfactura.models.clientes.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
