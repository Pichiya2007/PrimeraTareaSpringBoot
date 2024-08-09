package com.luispichiya.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luispichiya.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    
}
