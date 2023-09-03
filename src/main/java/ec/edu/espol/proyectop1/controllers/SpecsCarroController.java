/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Auto;
import ec.edu.espol.proyectop1.Camioneta;
import ec.edu.espol.proyectop1.Vehiculo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolassierra
 */
public class SpecsCarroController implements Initializable {

    @FXML
    private Label placa;
    @FXML
    private Label marca;
    @FXML
    private Label modelo;
    @FXML
    private Label motor;
    @FXML
    private Label color;
    @FXML
    private Label combustible;
    @FXML
    private Label precio;
    @FXML
    private Label año;
    @FXML
    private Label recorrido;
    @FXML
    private Label vidrios;
    @FXML
    private Label transmision;
    @FXML
    private Label traccion;
    @FXML
    private CheckBox checkOfertar;
    @FXML
    private Button regresar;
    @FXML
    private Button ofertarBoton;
    @FXML
    private Label tipo;
    @FXML
    private TextField fieldValor;

    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean seleccionado = checkOfertar.isSelected();
        fieldValor.setDisable(true);
        checkOfertar.setOnAction(event -> {
            if(seleccionado)
                fieldValor.setDisable(false);
            
        
                
        });
    }    

    @FXML
    private void checkOfertar(MouseEvent event) {
    }

    @FXML
    private void ofertar(MouseEvent event) {
    }
    
    public void show(Vehiculo ve){
        if(ve instanceof Camioneta){
            Camioneta v = (Camioneta)ve;
            tipo.setText("Camioneta");
            placa.setText(v.getPlaca());
            marca.setText(v.getMarca());
            modelo.setText(v.getModelo());
            motor.setText(v.getTipoMotor());
            color.setText(v.getColor());
            combustible.setText(v.getTipoCombustible());
            precio.setText(Double.toString(v.getPrecio()));
            año.setText(Integer.toString(v.getAnio()));
            recorrido.setText(Integer.toString(v.getRecorrido()));
            vidrios.setText(v.getTipoVidrios());
            transmision.setText(v.getTipoTransmision());
            traccion.setText(v.getTipoTraccion());
        }
        else if(ve instanceof Auto){
            Auto v = (Auto)ve;
            tipo.setText("Auto");
            placa.setText(v.getPlaca());
            marca.setText(v.getMarca());
            modelo.setText(v.getModelo());
            motor.setText(v.getTipoMotor());
            color.setText(v.getColor());
            combustible.setText(v.getTipoCombustible());
            precio.setText(Double.toString(v.getPrecio()));
            año.setText(Integer.toString(v.getAnio()));
            recorrido.setText(Integer.toString(v.getRecorrido()));
            vidrios.setText(v.getTipoVidrios());
            transmision.setText( v.getTipoTransmision());
            traccion.setText("N/A");
        }
        else{
            tipo.setText("Moto");
            placa.setText(ve.getPlaca());
            marca.setText(ve.getMarca());
            modelo.setText(ve.getModelo());
            motor.setText(ve.getTipoMotor());
            color.setText(ve.getColor());
            combustible.setText(ve.getTipoCombustible());
            precio.setText(Double.toString(ve.getPrecio()));
            año.setText(Integer.toString(ve.getAnio()));
            recorrido.setText(Integer.toString(ve.getRecorrido()));
            vidrios.setText("N/A");
            transmision.setText("N/A");
            traccion.setText("N/A");
            
        }
        
        
    }
    
    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/carrosEncontrados.fxml"));
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
