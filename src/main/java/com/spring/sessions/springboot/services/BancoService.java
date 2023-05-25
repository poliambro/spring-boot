package com.spring.sessions.springboot.services;

import com.spring.sessions.springboot.models.Banco;
import java.util.List;

public interface BancoService {

    List<Banco> listarBancos();

    Banco buscarBanco(Long id);

    Banco salvar(Banco banco);

    void remover(Long id);
}
