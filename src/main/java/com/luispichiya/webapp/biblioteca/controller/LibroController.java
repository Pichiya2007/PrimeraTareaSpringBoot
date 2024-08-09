package com.luispichiya.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luispichiya.webapp.biblioteca.model.Libro;
import com.luispichiya.webapp.biblioteca.service.LibroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/libro")
    public ResponseEntity<Libro> buscarLibroPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(libroService.buscarLibroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<Map<String, String>> guardarLibro(@RequestBody Libro libro){
        Map<String, String> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("message", "El libro se creo con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el libro.");
            return ResponseEntity.badRequest().body(response);
        }     
    }

    @PutMapping("/libro")
    public ResponseEntity<Map<String, String>> editarLibro(@RequestParam Long id, @RequestBody Libro newLibro){    
        Map<String, String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libro.setIsbn(newLibro.getIsbn());
            libro.setNombre(newLibro.getNombre());
            libro.setSinopsis(newLibro.getSinopsis());
            libro.setAutor(newLibro.getAutor());
            libro.setEditorial(newLibro.getEditorial());
            libro.setDisponibilidad(newLibro.getDisponibilidad());
            libro.setNumeroEstanteria(newLibro.getNumeroEstanteria());
            libro.setCluster(newLibro.getCluster());
            libro.setCategoria(newLibro.getCategoria());
            libroService.guardarLibro(libro);
            response.put("message", "El libro se edito con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al editar el libro.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/libro")
    public ResponseEntity<Map<String, String>> eliminarLibro(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libroService.eliminarLibro(libro);
            response.put("message", "El libro se elimino con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
