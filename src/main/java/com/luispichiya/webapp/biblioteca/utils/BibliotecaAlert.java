package com.luispichiya.webapp.biblioteca.utils;

import javafx.scene.control.Alert;

public class BibliotecaAlert {

    private static BibliotecaAlert instance;

    private BibliotecaAlert(){

    }

    public static BibliotecaAlert getInstance(){
        if(instance == null){
            instance = new BibliotecaAlert();   
        }
        return instance;
    }
    
    public void mostrarAlerta(int code){
        if(code == 2807){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Categoría duplicada");
            alert.setContentText("Está categoría ya existe, por favor intenta creando una diferente.");
            alert.showAndWait();
        }
    }
}
