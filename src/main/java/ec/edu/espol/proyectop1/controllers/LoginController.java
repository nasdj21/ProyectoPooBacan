/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Usuario;
import ec.edu.espol.proyectop1.excepciones.UsuarioNoExisteException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolassierra
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usuario;
   
    @FXML
    private PasswordField contraseña;
    @FXML
    private Button ingresar;
    @FXML
    private Hyperlink registrar;
    
    private Usuario usuarioEncontrado;
    

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void verificarCorreo(MouseEvent event) {
    String us = usuario.getText();
    String cont = contraseña.getText();
    
    try {
        usuarioEncontrado = Usuario.usuarioExiste(us, cont, "usuarios.ser");
        
        if (us.isEmpty() || cont.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, ingrese correo y contraseña.");
            alerta.show();
            return; // Sale del método si los campos están vacíos
        }
        
        abrirEscenaInicio(usuarioEncontrado);
        
    } catch (UsuarioNoExisteException e) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
        alerta.show();
    }
    }
    private void abrirEscenaInicio(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/inicio.fxml"));
            Parent root = loader.load();

            // Obtén una referencia al controlador de inicio
            InicioController inicioController = loader.getController();

            // Pasa el usuario al controlador de inicio
            inicioController.setUsuario(usuario);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ingresar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    



       
        
    
    @FXML
    private void borrar(MouseEvent event) {
        usuario.setText("");
        contraseña.setText("");
       
    }

    @FXML
    private void registrarUsuario(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/nuevousuario.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) registrar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
}
