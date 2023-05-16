package com.spring.sessions.springboot.repositories;

import com.spring.sessions.springboot.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

}
