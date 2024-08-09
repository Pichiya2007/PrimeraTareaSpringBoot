package com.luispichiya.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luispichiya.webapp.biblioteca.model.Empleado;
import com.luispichiya.webapp.biblioteca.service.EmpleadoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {
    
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados(){
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(empleadoService.buscarEmpleadoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>> guardarEmpleado(@RequestBody Empleado empleado){
        Map<String, String> response = new HashMap<>();
        try {
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "Se agrego el empleado con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo un error al crear el emplado.");
            return ResponseEntity.badRequest().body(response);      
        }
    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado newEmpleado){
        Map<String, String> response = new HashMap<>();    
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleado.setNombre(newEmpleado.getNombre());
            empleado.setApellido(newEmpleado.getApellido());
            empleado.setTelefono(newEmpleado.getTelefono());
            empleado.setDireccion(newEmpleado.getDireccion());
            empleado.setDpi(newEmpleado.getDpi());
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "El empleado se edito con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "El empleado no se pudo editar.");
            return ResponseEntity.badRequest().body(response);
        }    
    }   

    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>> eliminarEmpleado(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleado);
            response.put("message", "El empleado se elimino con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "El empleado no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
