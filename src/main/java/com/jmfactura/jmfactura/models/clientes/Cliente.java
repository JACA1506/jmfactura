package com.jmfactura.jmfactura.models.clientes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Cliente {

    @Id
    @GeneratedValue
    Integer id;
    String code;
    String idClient;
    String name;
    String telefone;
    String address;
    String email;
    String represent;
    String representNumber;
    // 1 = Fisica 2= Juridica
    Integer typeOfIdClient;
}
