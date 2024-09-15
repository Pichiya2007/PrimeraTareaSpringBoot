package com.luispichiya.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luispichiya.webapp.biblioteca.model.Cliente;
import com.luispichiya.webapp.biblioteca.service.ClienteService;
import com.luispichiya.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class ClienteFXController implements Initializable{

    @Setter
    private Main stage;

    private boolean editar = false;

    @Autowired
    ClienteService clienteService;

    @FXML
    TextField tfDpi, tfNombre, tfApellido, tftelefono, tfBuscar;

    @FXML
    Button btnGuardar, btnVaciar, btnEliminar, btnBuscar, btnRegresar;

    @FXML
    TableView tblClientes;

    @FXML
    TableColumn colDpi, colNombre, colApellido, colTelefono;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar) {
            if(!editar) {
                agregarCliente();
            }else{
                editarCliente();
            }
        }else if(event.getSource() == btnVaciar){
            vaciarForm();
        }else if(event.getSource() == btnEliminar){
            eliminarCliente();
        }else if(event.getSource() == btnBuscar){
            tblClientes.getItems().clear();
            if(tfBuscar.getText().isBlank()) {
                cargarDatos();
            }else{
                tblClientes.getItems().add(buscarCliente());
                colDpi.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dpi"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
                colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
            }
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblClientes.setItems(listarClientes());
        colDpi.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dpi"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
    }

    public void cargarForm(){
        Cliente cliente = (Cliente)tblClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            tfDpi.setText(Long.toString(cliente.getDpi()));
            tfNombre.setText(cliente.getNombre());
            tfApellido.setText(cliente.getApellido());
            tftelefono.setText(cliente.getTelefono());
            editar = true;
        }
    }

    public void vaciarForm(){
        tfDpi.clear();
        tfNombre.clear();
        tfApellido.clear();
        tftelefono.clear();
        editar = false;
    }

    public ObservableList<Cliente> listarClientes(){
        return FXCollections.observableList(clienteService.listarClientes());
    }

    public void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setDpi(Long.parseLong(tfDpi.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tftelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void editarCliente(){
        Cliente cliente = clienteService.buscarClientePorDpi(Long.parseLong(tfDpi.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tftelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void eliminarCliente(){
        Cliente cliente = clienteService.buscarClientePorDpi(Long.parseLong(tfDpi.getText()));
        clienteService.eliminarCliente(cliente);
        cargarDatos();
    }
    
    public Cliente buscarCliente(){
        return clienteService.buscarClientePorDpi(Long.parseLong(tfBuscar.getText()));
    }
}
