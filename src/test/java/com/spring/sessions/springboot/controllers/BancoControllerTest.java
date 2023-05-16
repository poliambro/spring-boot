package com.spring.sessions.springboot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.sessions.springboot.payload.BancoRequest;
import com.spring.sessions.springboot.services.BancoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BancoController.class) // Spring MVC configurado para os testes
class BancoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BancoController controller;

    // Deps podem ser mockadas. Neste caso, usando o MockBean
    @MockBean
    private BancoService bancoService;


    @Test
    public void deveRetornarOKQuandoOBancoForCriadoComSucesso() throws Exception {
        // Triple A - arrange, act, assert
        BancoRequest request = BancoRequest.builder()
                .cnpj("48.482.993/0001-01")
                .codigo("00123")
                .nome("Banco Sucesso")
                .descricao("Banco de teste dos devs")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonSimulado = objectMapper.writeValueAsString(request);
        doReturn(request.convert()).when(bancoService).salvar(any());
        mockMvc.perform(post("/bancos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSimulado))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code", is(HttpStatus.CREATED.value())))
                .andExpect(jsonPath("$.payload.codigo", is(request.getCodigo())));
    }
}