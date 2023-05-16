package com.spring.sessions.springboot.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.sessions.springboot.models.Banco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BancoRequest {

    private String cnpj;
    private String nome;
    private String codigo;
    private String descricao;

    public Banco convert(){
        return Banco.builder()
                .cnpj(cnpj)
                .nome(nome)
                .codigo(codigo)
                .descricao(descricao)
                .build();
    }
}
