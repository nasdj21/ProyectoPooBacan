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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolassierra
 */
public class InicioController implements Initializable {

    @FXML
    private Label bienvenido;
    @FXML
    private Button nuevoCarro;
    @FXML
    private Button verCarros;
    
    private Usuario usuarioInicio;
    @FXML
    private Button regresar;
    private ImageView imagenDePerfil;
    private Image imagenPerfil;
    private ArrayList<Vehiculo> carrosFiltrados;
    @FXML
    private Button misCarrosButton;
    public void setUsuario(Usuario usuario) {
        this.usuarioInicio = usuario;
        System.out.println("Usuario recibido: " + usuarioInicio.getNombres()); // Agrega esta línea

    }
     public void setVSer(ArrayList<Vehiculo> carros) {
        this.carrosFiltrados = carros;
        
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void nuevoVehiculo(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/creacion.fxml"));
        Parent root = loader.load();
        
        CreacionController creacionControler  = loader.getController();
        creacionControler.setUsuario(usuarioInicio);


        Scene scene = new Scene(root);
        Stage stage = (Stage) nuevoCarro.getScene().getWindow(); // nuevoCarro es el botón que desencadenó el evento
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void regresarIn(MouseEvent event) {
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

    @FXML
    private void verPerfil(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/perfil.fxml"));
            Parent root = loader.load();
            
            // Obtén una referencia al controlador de mis vehiculos
            PerfilController perfilController = loader.getController();

            // Pasa el usuario al controlador de creación
            perfilController.setUsuarioPerfil(usuarioInicio);

            Scene scene = new Scene(root);
            Stage stage = (Stage) verCarros.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void verVehiculosOfertar(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/misCarros.fxml"));
            Parent root = loader.load();
            
            // Obtén una referencia al controlador de mis vehiculos
            MisCarrosController carrosController = loader.getController();

            // Pasa el usuario al controlador de creación
            carrosController.setUsuarioMisCarros(usuarioInicio);

            Scene scene = new Scene(root);
            Stage stage = (Stage) verCarros.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void verMisVehiculos(MouseEvent event) {
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/carrosUsuario.fxml"));
            Parent root = loader.load();

            CarrosUsuarioController misCarros = loader.getController();
            //cencontradorController.mostrar(vehicls);
            carrosFiltrados = Vehiculo.filtrarVehiculoPorUsuario(usuarioInicio, "vehiculos.ser");
            misCarros.setArrayCarros(carrosFiltrados);
            misCarros.setMisCarros(usuarioInicio);

            Scene scene = new Scene(root);
            Stage stage = (Stage) misCarrosButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
    }
        
    }
    
}
