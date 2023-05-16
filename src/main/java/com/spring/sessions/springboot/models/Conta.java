package com.spring.sessions.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="contas")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Owner
    @ManyToOne
    @JoinColumn(name = "banco_id", referencedColumnName = "id", nullable = false)
    private Banco banco;
    private String agencia;
    private String numero;
    private BigDecimal saldo;
}
