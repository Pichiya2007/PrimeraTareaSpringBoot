package com.luispichiya.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luispichiya.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
