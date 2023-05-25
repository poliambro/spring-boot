package com.spring.sessions.springboot.services;

import com.spring.sessions.springboot.models.Banco;
import com.spring.sessions.springboot.repositories.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Cria o bean na aplicação
public class BancoServiceImpl implements BancoService {

    @Autowired
    BancoRepository repository;

    @Override
    public List<Banco> listarBancos() {
        return repository.findAll();
    }

    @Override
    public Banco buscarBanco(Long id) {
        return repository.getReferenceById(id);
    }


    @Override
    public Banco salvar(Banco banco) {
        return repository.save(banco);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
