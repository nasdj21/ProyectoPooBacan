/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Auto;
import ec.edu.espol.proyectop1.Camioneta;
import ec.edu.espol.proyectop1.Usuario;
import ec.edu.espol.proyectop1.Vehiculo;
import ec.edu.espol.proyectop1.excepciones.placaExisteException;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolassierra
 */
public class CreacionController implements Initializable {

    @FXML
    private ComboBox<String> tipo;
    @FXML
    private TextField placa;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField año;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField color;
    @FXML
    private TextField combustible;
    @FXML
    private TextField tipoMotor;
    @FXML
    private TextField precio;
    @FXML
    private Button regresar;
    @FXML
    private Button registrar;
    @FXML
    private Button limpiar;
    
    private Usuario usuario;
    @FXML
    private TextField TipoVidrios;
    @FXML
    private TextField tipoTransmision;
    @FXML
    private TextField tipoTraccion;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Define los valores de tipo de vehículo
        ArrayList<String> tiposDeVehiculo = new ArrayList<>();
        tiposDeVehiculo.add("Moto");
        tiposDeVehiculo.add("Auto");
        tiposDeVehiculo.add("Camioneta");

        // Asigna los valores al ComboBox
        tipo.getItems().addAll(tiposDeVehiculo);
        // Inicialmente, deshabilita el campo "tipoTraccion"
        TipoVidrios.setDisable(true);
        tipoTransmision.setDisable(true);
        tipoTraccion.setDisable(true);
        
    
        tipo.setOnAction(event -> {
        String seleccion = tipo.getValue();
        if ("Auto".equals(seleccion)) {
            TipoVidrios.setDisable(false);
            tipoTransmision.setDisable(false);

        } else if("Camioneta".equals(seleccion)){
            tipoTraccion.setDisable(false);
            TipoVidrios.setDisable(false);
            tipoTransmision.setDisable(false);
            tipoTraccion.clear(); // Limpia el campo si se deshabilita
        }
        });
    }


    @FXML
    private void regresarAtras(MouseEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/inicio.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = (Stage) regresar.getScene().getWindow(); // regresar es el botón que desencadenó el evento
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }





    

    @FXML
    private void registratVehiculo(MouseEvent event) {
        String pl = placa.getText();
        String marc = marca.getText();
        String model = modelo.getText();
        String motor = tipoMotor.getText();
        int ano = Integer.parseInt(año.getText());
        int reco = Integer.parseInt(recorrido.getText());
        String col = color.getText();
        String combust = combustible.getText();
        double price = Double.parseDouble(precio.getText());
        String vidrio = TipoVidrios.getText();
        String transmision = tipoTransmision.getText();

        String seleccion = tipo.getValue();
        try{

            if(Vehiculo.validarPlacaExistente(pl, "vehiculos.ser")){
                throw new placaExisteException("El vehiculo ya está registrado");
            }
            else{
            
                if("Moto".equals(seleccion)){
                    if (pl.isEmpty() || marc.isEmpty() || model.isEmpty() || motor.isEmpty() || col.isEmpty() || combust.isEmpty()) {
                        Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, llene todos los campos requeridos.");
                        alerta.show();
                        return;
                    }


                    //Si es moto, crea un objeto moto
                    Vehiculo v = new Vehiculo(pl,marc,model,motor,ano,reco,col,combust,price);
                    v.guardarInfoSer("vehiculos.ser");
                    

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Auto registrado correctamente.");
                    alerta.show();
                }


                else if ("Auto".equals(seleccion)) {
                    if (pl.isEmpty() || marc.isEmpty() || model.isEmpty() || motor.isEmpty() || col.isEmpty() || combust.isEmpty() || vidrio.isEmpty() || transmision.isEmpty()) {
                        Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, llene todos los campos requeridos.");
                        alerta.show();
                        return;
                    }
                    // Si es Auto, crea un objeto Auto
                    Auto auto = new Auto(pl, marc, model, motor, ano, reco, col, combust, vidrio, transmision, price);
                    auto.guardarInfoSer("vehiculos.ser");
                    

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Auto registrado correctamente.");
                    alerta.show();

                } else if ("Camioneta".equals(seleccion)) {
                    // Si es Camioneta, verifica y procesa el parámetro "capacidad"
                    String traccion = tipoTraccion.getText();
                    if (pl.isEmpty() || marc.isEmpty() || model.isEmpty() || motor.isEmpty() || col.isEmpty() || combust.isEmpty() || vidrio.isEmpty() || transmision.isEmpty() || traccion.isEmpty()) {
                        Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, llene todos los campos requeridos.");
                        alerta.show();
                        return;
                    }

                    if (!traccion.isEmpty()) {
                        // Si se proporciona la capacidad, crea un objeto Camioneta
                        Camioneta camioneta = new Camioneta(pl, marc, model, motor, ano, reco, col, combust, vidrio, transmision,price,traccion);
                        camioneta.guardarInfoSer("vehiculos.ser");
                        

                        Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Camioneta registrada correctamente.");
                        alerta.show();
                    } else {

                        Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, ingrese Traccion");
                        alerta.show();
                    }
                } else {
                    // Si no se selecciona ninguna opción, muestra un mensaje de error
                    Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, seleccione un tipo de vehículo.");
                    alerta.show();
                }
          }
        }catch(placaExisteException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alerta.show();
            
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        placa.setText("");
        marca.setText("");
        modelo.setText("");
        tipoMotor.setText("");
        año.setText("");
        recorrido.setText("");
        color.setText("");
        combustible.setText("");
        precio.setText("");
        TipoVidrios.setText("");
        tipoTransmision.setText("");
        tipoTraccion.setText("");
    }

    @FXML
    private void tipoVehiculo(ActionEvent event) {
    }
    
    
    
}