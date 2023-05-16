package com.spring.sessions.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/*
    Data: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields,
    and @Setter on all non-final fields, and @RequiredArgsConstructor
    Documentação do Lombok:
    https://projectlombok.org/features/
*/
@Data
@Entity // Prepara para o banco de dados
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    private String nome;
    private String endereco;
    /*
    Opcional, porém se não informado, JPA assume um por default com regras específicas.
    Mais informações na documentação:
    https://docs.jboss.org/hibernate/jpa/2.2/api/javax/persistence/JoinColumn.html
    * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id", nullable = false)
    private Conta conta;
}
