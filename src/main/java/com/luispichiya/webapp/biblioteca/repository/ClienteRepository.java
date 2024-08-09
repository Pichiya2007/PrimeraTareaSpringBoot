package com.luispichiya.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luispichiya.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
