/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eliez
 */
public class PerfilController implements Initializable {

    
    @FXML
    private Text apellidos;
    @FXML
    private Text organizacion;
    @FXML
    private Text correo;
    @FXML
    private Text clave;
    
    private Usuario usuario;
    @FXML
    private Button regresar;
    @FXML
    private Hyperlink cambiarContra;
//    @FXML
//    private ImageView imgV;
    
    @FXML
    private Text nombre;
    @FXML
    private ToggleButton Mostrar;
    
    public void setUsuarioPerfil(Usuario usuarioInicio) {
        this.usuario = usuarioInicio;
        
        System.out.println("Usuario perfil: " + usuario.getNombres());
        System.out.println("UsuarioClave perfil: " + usuario.getClave());
        System.out.println("Usuario org: " + usuario.getOrganizacion());
        System.out.println("UsuarioCorreo perfil: " + usuario.getCorreo());
        System.out.println("UsuarioApellido perfil: " + usuario.getApellidos());
        
        

        
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
    }    

    @FXML
    private void cambiarContraseña(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/cambiarClave.fxml"));
        Parent root = loader.load();
        
        // Obtén una referencia al controlador de cambio de clave
        CambiarClaveController cambioClaveController = loader.getController();

        // Pasa el usuario al controlador de cambio de clave
        cambioClaveController.setUsuarioPerfilClave(usuario);

        Scene scene = new Scene(root);
        Stage stage = (Stage) cambiarContra.getScene().getWindow(); 
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    
    }


        
    }

    @FXML
    private void volverInicio(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/inicio.fxml"));
            Parent root = loader.load();
            InicioController devolverUsuario = loader.getController();

            // Pasa el usuario al controlador de cambio de clave
            devolverUsuario.setUsuario(usuario);
            

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void MostrarInfo(ActionEvent event) {
        if (Mostrar.isSelected()) {
        // Si el ToggleButton está seleccionado (activo), muestra la información real
        nombre.setText(usuario.getNombres());
        apellidos.setText(usuario.getApellidos());
        organizacion.setText(usuario.getOrganizacion());
        correo.setText(usuario.getCorreo());
        clave.setText(usuario.getClave());
    } else {
        // Si el ToggleButton no está seleccionado (inactivo), muestra asteriscos o lo que desees
        nombre.setText("**********");
        apellidos.setText("**********");
        organizacion.setText("**********");
        correo.setText("**********");
        clave.setText("**********");
    }
    }

    // cambio prueba
}
