/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Usuario;
import ec.edu.espol.proyectop1.Vehiculo;
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
        
    private Usuario usuarioC;

    public void setUsuarioMisCarros(Usuario usuario) {
        this.usuarioC = usuario;
        System.out.println("Usuario recibido: " + usuarioC.getNombres()); // Agrega esta línea
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
        
            
        try {
            String[] recorridos = recorrido.getText().split("-");
            String[] año = ano.getText().split("-");
            String[] precios = precio.getText().split("-");
            String tipoVehiculo = tipo.getText();
            ArrayList<Vehiculo>vehiculos = new ArrayList<>();

            if(recorrido.getText().isEmpty() && ano.getText().isEmpty() && precio.getText().isEmpty() && tipoVehiculo.isEmpty())
                vehiculos = Vehiculo.leerInfoSer("vehiculos.ser");
            else{
                ArrayList<Vehiculo>temporal = Vehiculo.leerInfoSer("vehiculos.ser");
                for(Vehiculo v : temporal){
                    if(v.getRecorrido() > Double.parseDouble(recorridos[0]) && v.getRecorrido() < Double.parseDouble(recorridos[1]) && v.getAnio() > Integer.parseInt(año[0]) && v.getAnio() < Integer.parseInt(año[1]) && v.getPrecio() > Double.parseDouble(precios[0]) && v.getPrecio() < Double.parseDouble(precios[1]) && v.getTipo().equals(tipoVehiculo))
                        vehiculos.add(v);
                }

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/carrosEncontrados.fxml"));
            Parent root = loader.load();
            
            CarrosEncontradosController cencontradorController = loader.getController();
            cencontradorController.mostrar(vehiculos);
            cencontradorController.setUsuario(usuarioC);
            
            

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
            
            InicioController devolverUsuario = loader.getController();

            // Pasa el usuario al controlador de cambio de clave
            devolverUsuario.setUsuario(usuarioC);

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
