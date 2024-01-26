package com.jmfactura.jmfactura.models.productos;

import java.math.BigDecimal;

import com.jmfactura.jmfactura.models.proveedores.Proveedor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "productos", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Producto {

    @Id
    @GeneratedValue
    Integer id;
    String code;
    String name;
    String brand;
    Integer goToTax;
    Integer cuantity;
    BigDecimal priceToSell;
    BigDecimal priceToTax;
    BigDecimal priceBuyed;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    Proveedor proveedorId;

}
