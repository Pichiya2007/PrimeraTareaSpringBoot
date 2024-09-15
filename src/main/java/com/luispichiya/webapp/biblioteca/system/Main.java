package com.luispichiya.webapp.biblioteca.system;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.openssl.pem_password_cb;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.luispichiya.webapp.biblioteca.BibliotecaApplication;
import com.luispichiya.webapp.biblioteca.controller.FXController.CategoriaFXController;
import com.luispichiya.webapp.biblioteca.controller.FXController.ClienteFXController;
import com.luispichiya.webapp.biblioteca.controller.FXController.EmpleadoFXController;
import com.luispichiya.webapp.biblioteca.controller.FXController.IndexController;
import com.luispichiya.webapp.biblioteca.controller.FXController.LibroFXController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(BibliotecaApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Biblioteca");
        Image icon = new Image("C:\\Users\\luisp\\Documents\\SpringBoot Projects\\BibliotecaSpringBoot\\src\\main\\resources\\image\\Icono.png");
        stage.getIcons().add(icon);
        indexView();
        stage.show();
    }

    public Initializable switchScene(String fxmlName, int width, int height) throws IOException{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane) loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();

        resultado = (Initializable)loader.getController();

        return resultado;
    }

    public void indexView(){
        try {
            IndexController indexView = (IndexController)switchScene("index.fxml", 1000, 600);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void categoriaView(){
        try {
            CategoriaFXController categoriaView = (CategoriaFXController)switchScene("CategoriaFXView.fxml", 1000, 600);
            categoriaView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clienteView(){
        try {
            ClienteFXController clienteView = (ClienteFXController)switchScene("ClienteFXView.fxml", 1000, 600);
            clienteView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void empleadoView(){
        try {
            EmpleadoFXController empleadoView = (EmpleadoFXController)switchScene("EmpleadoFXView.fxml", 1000, 600);
            empleadoView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void libroView(){
        try {
            LibroFXController libroView = (LibroFXController)switchScene("LibroFXView.fxml", 1000, 600);
            libroView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
