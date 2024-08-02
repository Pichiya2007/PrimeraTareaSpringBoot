package com.luispichiya.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luispichiya.webapp.biblioteca.service.CategoriaService;
import com.luispichiya.webapp.biblioteca.model.Categoria;

@Controller
@RestController
@RequestMapping(value = "categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> listarCategorias(){
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /*@PostMapping("/")
    public ResponseEntity<Map<String, Boolean>> guardarCategoria(@RequestBody Categoria categoria){
        Map<String, Boolean> response = new HashMap<>();
        try {
            categoriaService.guardarCategoria(categoria);
            response.put("Se agrego con éxito", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Se agrego con éxito", Boolean.TRUE);
            return ResponseEntity.badRequest().body(response);
        }
    }*/

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> guardarCategoria(@RequestBody Categoria categoria){
        Map<String, String> response = new HashMap<>();
        try {
            categoriaService.guardarCategoria(categoria);
            response.put("message", "La categoría se creó con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo un error al crear la categoría");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarCategoria(@PathVariable Long id, @RequestBody Categoria newCategoria){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(newCategoria.getNombreCategoria());
            categoriaService.guardarCategoria(categoria);
            response.put("mesagge", "La categoría se ha editado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "La categoría no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);    
            response.put("message", "Categoría eliminada con éxito");
            return ResponseEntity.ok(response);  
        } catch (Exception e) {
            response.put("message", "La categoría no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
