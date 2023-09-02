/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Usuario;
import ec.edu.espol.proyectop1.Utilitaria;
import ec.edu.espol.proyectop1.excepciones.CorreoExistenteException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eliez
 */
public class NuevousuarioController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField organizacion;
    @FXML
    private TextField correo;
    @FXML
    private TextField clave;
    @FXML
    private Button registrarUs;
    @FXML
    private Button regresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarUsuario(MouseEvent event) {
        String nombres = nombre.getText();
        String apellidos = apellido.getText();
        String organizacions = organizacion.getText();
        String corre = correo.getText();
        String clav = clave.getText();

        try {
            if (corre.isEmpty() || clav.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, complete todos los campos.");
                alerta.show();
                return;
            }

            // Verifica si el correo ya existe en la lista de usuarios
            ArrayList<Usuario> usuarios = Usuario.readListFromFileSer("usuarios.ser");
            Usuario.correoYaExiste(corre, usuarios);

            
            

            // Crea un nuevo usuario y guárdalo
//            ArrayList<Usuario> usuarioGuardado = new ArrayList<>();
           Usuario nuevoUsuario = new Usuario( nombres, apellidos, organizacions, corre, clav);
//            usuarioGuardado.add(nuevoUsuario);
            nuevoUsuario.saveSer("usuarios.ser");

            // Muestra un mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Usuario registrado correctamente.");
            alerta.show();
        } catch (CorreoExistenteException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.show();
        }
    }

    @FXML
    private void borrar(MouseEvent event) {
        nombre.setText("");
        apellido.setText("");
        organizacion.setText("");
        this.correo.setText("");
        this.clave.setText("");
    }

    @FXML
    private void regresarInicio(MouseEvent event) {
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
