/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eliez
 */
public class CambiarClaveController implements Initializable {

    @FXML
    private GridPane regresar;
    @FXML
    private PasswordField claveActual;
    @FXML
    private PasswordField claveNueva;
    @FXML
    private PasswordField claveConf;
    @FXML
    private Button guardar;
    
    private Usuario usuarioClave;
    
    public void setUsuarioPerfilClave(Usuario usuario) {
        this.usuarioClave = usuario;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarDatos(MouseEvent event) {
        String contraseñaActual = claveActual.getText();
        String nuevaContraseña = claveNueva.getText();
        String confirmarContraseña = claveConf.getText();

        
        if (!contraseñaActual.equals(usuarioClave.getClave())) {
            mostrarAlerta("Contraseña actual incorrecta.");
            return;
        }

        
        if (!nuevaContraseña.equals(confirmarContraseña)) {
            mostrarAlerta("La nueva contraseña y la confirmación no coinciden.");
            return;
        }

        
        usuarioClave.setClave(nuevaContraseña);

        
        usuarioClave.saveSer("usuarios.ser");
        Usuario.actualizarUsuario(usuarioClave, "usuarios.ser");

        
        mostrarAlerta2("La contraseña se ha cambiado correctamente. POR FAVOR INICIE SESION NUEVAMENTE");

        
    }


    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.show();

    }
    private void mostrarAlerta2(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensaje);
        alerta.show();

    }

    @FXML
    private void regresarPerfil(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/login.fxml"));
            Parent root = loader.load();
            
            

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
