package com.jmfactura.jmfactura.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmfactura.jmfactura.models.clientes.Cliente;
import com.jmfactura.jmfactura.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public void deleteById(Integer id) {

        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> getById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveOrUpdate(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
