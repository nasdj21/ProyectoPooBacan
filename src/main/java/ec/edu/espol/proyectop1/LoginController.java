/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1;

import java.io.Serializable;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        boolean existe = Usuario.usuarioExiste(us, cont, "usuarios.ser");
        if (existe) {
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Usuario correcto");
            alerta.show();
            
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Usuario incorrecto");
            alerta.show();
            
        }
       
        
    }
    @FXML
    private void borrar(MouseEvent event) {
        usuario.setText("");
        contraseña.setText("");
       
    }

    
    
}
