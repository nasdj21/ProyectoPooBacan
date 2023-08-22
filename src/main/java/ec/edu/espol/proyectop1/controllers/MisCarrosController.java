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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolassierra
 */
public class MisCarrosController implements Initializable {

    @FXML
    private Button regresar;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField ano;
    @FXML
    private TextField precio;
    @FXML
    private Button buscar;
    @FXML
    private Button borrar;
    @FXML
    private TextField tipo;
        
    private Usuario usuario;

    public void setUsuarioMisCarros(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

    @FXML
    private void buscarVehiculo(MouseEvent event) {
//        String recorridos = recorrido.getText();
//        String año = ano.getText();
//        String precios = precio.getText();
//        String tipoVehiculo = tipo.getText();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/carrosEncontrados.fxml"));
            Parent root = loader.load();
            

            Scene scene = new Scene(root);
            Stage stage = (Stage) buscar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    

    @FXML
    private void borrar(MouseEvent event) {
        recorrido.setText("");
        ano.setText("");
        precio.setText("");
        tipo.setText("");
    }

    @FXML
    private void regresarInicio(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/inicio.fxml"));
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
