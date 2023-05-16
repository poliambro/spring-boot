package com.spring.sessions.springboot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.sessions.springboot.payload.BancoRequest;
import com.spring.sessions.springboot.payload.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* A anotação de SpringBootTest inicia a aplicação em um container próprio
* que irá ser terminado quando o teste completar sua execução.
* Isso acontece através da criação de contexto de aplicação pelo Spring, assim
* como quando iniciamos a aplicação em um servidor de produção. A diferença neste
* caso é que nem tudo que uma aplicação executa para funcionar por completo será
* criada, chamada ou executada aqui. Algumas configurações devem ser manuais, como
* é o caso do security, por exemplo.
* */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BancoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void deveRetornarCreatedQuandoUmBancoEhCriado() throws JsonProcessingException {
        BancoRequest request = BancoRequest.builder()
                .cnpj("48.482.993/0001-01")
                .codigo("00123")
                .nome("Banco Sucesso")
                .descricao("Banco de teste dos devs")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonSimulado = objectMapper.writeValueAsString(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(jsonSimulado, headers);
        // envia a requisição - nenhum dos serviços é mockado
        ResponseEntity<BaseResponse> response =
                restTemplate.exchange("/bancos", HttpMethod.POST, stringHttpEntity, BaseResponse.class);
        assertEquals(HttpStatusCode.valueOf(HttpStatus.CREATED.value()), response.getStatusCode());

//        ResponseEntity<BaseResponse> listarResponse =
//                restTemplate.exchange("/bancos", HttpMethod.GET, new HttpEntity<>(headers), BaseResponse.class);
//        assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), listarResponse.getStatusCode());
//        Object payload = listarResponse.getBody().getPayload();
    }
}
