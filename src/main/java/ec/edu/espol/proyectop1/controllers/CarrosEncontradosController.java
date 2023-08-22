/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Auto;
import ec.edu.espol.proyectop1.Vehiculo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolassierra
 */
public class CarrosEncontradosController implements Initializable {

    @FXML
    private Label vEncontrados;
    @FXML
    private VBox vcarros;
    @FXML
    private Button regresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vcarros.setSpacing(20);
        ArrayList<Vehiculo>vehiculos = Vehiculo.leerInfoSer("vehiculos.ser");
        for(Vehiculo v : vehiculos){
            Label lb = new Label(v.getMarca()+"|"+v.getModelo()+"|"+v.getPlaca()+"|"+v.getAnio()+"|"+v.getRecorrido()+"|"+v.getPrecio());
            lb.setTextFill(Color.color(1, 1, 1));
            vcarros.getChildren().add(lb);
        }
        vEncontrados.setText("Se han encontrado "+vehiculos.size()+" vehiculos:");
        
    } 

    @FXML
    private void regresarAtras(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/misCarros.fxml"));
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
