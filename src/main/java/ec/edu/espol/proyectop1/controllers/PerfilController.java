/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eliez
 */
public class PerfilController implements Initializable {

    @FXML
    private Text nombre;
    @FXML
    private Text apellidos;
    @FXML
    private Text organizacion;
    @FXML
    private Text correo;
    @FXML
    private Text clave;
    
    private Usuario usuarioPerfil;
    @FXML
    private Button regresar;
    @FXML
    private Hyperlink cambiarContra;
    @FXML
    private ImageView imgV;
    
    public void setUsuarioPerfil(Usuario usuario) {
        this.usuarioPerfil = usuario;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (usuarioPerfil != null) {
        nombre.setText(usuarioPerfil.getNombres());
        apellidos.setText(usuarioPerfil.getApellidos());
        organizacion.setText(usuarioPerfil.getOrganizacion());
        correo.setText(usuarioPerfil.getCorreo());
        clave.setText(usuarioPerfil.getClave());
    
        }
        
        String rutaImagen = obtenerRutaImagen();

        if (rutaImagen != null) {
            // Cargar la imagen almacenada
            Image imagen = new Image(new File(rutaImagen).toURI().toString());
            imgV.setImage(imagen);
        }




    }
    private String obtenerRutaImagen() {
        try (BufferedReader lector = new BufferedReader(new FileReader("ruta_imagen.txt"))) {
            String linea;
            if ((linea = lector.readLine()) != null) {
                return linea; // Devuelve la primera línea del archivo que contiene la ruta de la imagen
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Devuelve null si no se pudo obtener la ruta de la imagen
    }    

    @FXML
    private void cambiarContraseña(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/cambiarClave.fxml"));
        Parent root = loader.load();
        
        // Obtén una referencia al controlador de cambio de clave
        CambiarClaveController cambioClaveController = loader.getController();

        // Pasa el usuario al controlador de cambio de clave
        cambioClaveController.setUsuarioPerfil(usuarioPerfil);

        Scene scene = new Scene(root);
        Stage stage = (Stage) cambiarContra.getScene().getWindow(); 
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    
    }


        
    }

    @FXML
    private void volverInicio(MouseEvent event) {
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

    @FXML
    private void seleccionarImagen(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.png", "*.jpeg"));


        File archivoSeleccionado = fileChooser.showOpenDialog(null);

        if (archivoSeleccionado != null) {

            Image imagen = new Image(archivoSeleccionado.toURI().toString());
            imgV.setImage(imagen);
            
            guardarRutaImagen(archivoSeleccionado.getAbsolutePath());



        }

    }
    private void guardarRutaImagen(String rutaImagen) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("ruta_imagen.txt"))) {
            escritor.write(rutaImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
