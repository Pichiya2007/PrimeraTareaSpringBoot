package com.luispichiya.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luispichiya.webapp.biblioteca.model.Cliente;
import com.luispichiya.webapp.biblioteca.service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RestController
@RequestMapping(value = "")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente> buscarClientePorDpi(@RequestParam Long dpi){ //Se usa la anotación PathVariable para usar la variable que venga en la Path.
        try {
            return ResponseEntity.ok(clienteService.buscarClientePorDpi(dpi));    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PostMapping("/cliente")
    public ResponseEntity<Map<String, String>> guardarCliente(@RequestBody Cliente cliente){ //ResponseEntity para el mensaje de respuesta de confirmación.
        Map<String, String> response = new HashMap<>(); // Se crea un HashMap para el mensaje.
        try {
            clienteService.guardarCliente(cliente); //Esto agrega el objeto.
            response.put("message", "Se agrego el cliente con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo un error al crear el cliente.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>> editarCliente(@RequestParam Long dpi, @RequestBody Cliente newCliente){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorDpi(dpi); //Busca el objeto (Cliente a editar).
            cliente.setNombre(newCliente.getNombre()); //Sustituye el viejo valor del Objeto (set), por el nuevo (get).
            cliente.setApellido(newCliente.getApellido());
            cliente.setTelefono(newCliente.getTelefono());
            clienteService.guardarCliente(cliente);
            response.put("message", "El cliente se edito con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "El cliente no se pudo editar.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarCliente(@RequestParam Long dpi){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorDpi(dpi); //Busca el cliente (Objeto completo) y le da el valor a cliente para eliminarlo después.
            clienteService.eliminarCliente(cliente);
            response.put("message", "El cliente se elimino con éxito.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo un error al eliminar el cliente.");
            return ResponseEntity.badRequest().body(response); //En el badRequest tiene un mensaje por defecto, por lo cual se entra al body para poner el msj de error, a comparación del ok que no lo tiene.
        }
    }  
}
