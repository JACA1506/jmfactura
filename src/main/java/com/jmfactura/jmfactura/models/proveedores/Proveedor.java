package com.jmfactura.jmfactura.models.proveedores;

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
@Table(name = "proveedores", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Proveedor {

    @Id
    @GeneratedValue
    Integer id;

    String code;
    String provedorName;
    String number;
    String represent;
    String representNumber;

}
