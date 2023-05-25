package com.spring.sessions.springboot.controllers;

import com.spring.sessions.springboot.models.Banco;
import com.spring.sessions.springboot.payload.BancoRequest;
import com.spring.sessions.springboot.payload.BancoResponse;
import com.spring.sessions.springboot.payload.BaseResponse;
import com.spring.sessions.springboot.services.BancoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
* Documentação do Spring para controllers:
* https://spring.io/guides/tutorials/rest/
* */

@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    private static final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<BaseResponse> listar(){
        try {
            List<Banco> bancos = bancoService.listarBancos();
            List<BancoResponse> bancosResponse = bancos.stream().map(banco ->
                    modelMapper.map(banco, BancoResponse.class)).toList();
            return ResponseEntity.ok(BaseResponse.generateResponse(HttpStatus.OK, bancosResponse));
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body(
                    BaseResponse.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> detalhar(@PathVariable Long id){
        try {
            Banco banco = bancoService.buscarBanco(id);
            BancoResponse bancoResponse = modelMapper.map(banco, BancoResponse.class);
            return ResponseEntity.ok(BaseResponse.generateResponse(HttpStatus.OK, bancoResponse));
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body(
                    BaseResponse.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<BaseResponse> criar(@RequestBody BancoRequest request){
        try{
            Banco entity = request.convert();
            BancoResponse response = modelMapper.map(bancoService.salvar(entity), BancoResponse.class);
            return ResponseEntity.created(URI.create(String.format("/bancos/%s", response.getCodigo())))
                    .body(BaseResponse.generateResponse(HttpStatus.CREATED, response));
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body(
                    BaseResponse.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> atualizar(@PathVariable Long id, @RequestBody BancoRequest request){
        try{
            Banco bancoEncontrado = bancoService.buscarBanco(id);
            bancoEncontrado.setCnpj(request.getCnpj());
            bancoEncontrado.setNome(request.getNome());
            bancoEncontrado.setCodigo(request.getCodigo());
            bancoEncontrado.setDescricao(request.getDescricao());
            BancoResponse response = modelMapper.map(bancoService.salvar(bancoEncontrado), BancoResponse.class);
            return ResponseEntity.ok(BaseResponse.generateResponse(HttpStatus.OK, response));
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body(
                    BaseResponse.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> remover(@PathVariable Long id){
        try {
            bancoService.remover(id);
            return ResponseEntity.noContent().build();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(
                    BaseResponse.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
        }
    }
}
