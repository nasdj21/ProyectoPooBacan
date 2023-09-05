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
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
    
    private Usuario usuarioC;
    @FXML
    private TextField TipoVidrios;
    @FXML
    private TextField tipoTransmision;
    @FXML
    private TextField tipoTraccion;
    @FXML
    private Button bt;
    @FXML
    private ImageView imagen;
    private File imgFile;
    
    public void setUsuario(Usuario usuario){
        this.usuarioC = usuario;
        System.out.println("Usuario recibido: " + usuarioC.getNombres()); // Agrega esta línea
    }
    
   
    

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
            tipoTraccion.setDisable(true);
            tipoTraccion.clear();

        } else if("Camioneta".equals(seleccion)){
            tipoTraccion.setDisable(false);
            TipoVidrios.setDisable(false);
            tipoTransmision.setDisable(false);
            tipoTraccion.clear(); // Limpia el campo si se deshabilita
            
        }
          else{
            TipoVidrios.setDisable(true);
            tipoTransmision.setDisable(true);
            tipoTraccion.setDisable(true);
            TipoVidrios.clear();
            tipoTransmision.clear();
            tipoTraccion.clear();
            
        }
            
        });
    }


    @FXML
    private void regresarAtras(MouseEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/inicio.fxml"));
                Parent root = loader.load();
                
                InicioController inicioController = loader.getController();
                inicioController.setUsuario(usuarioC);

                Scene scene = new Scene(root);
                Stage stage = (Stage) regresar.getScene().getWindow(); // regresar es el botón que desencadenó el evento
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }





    

    @FXML

    private void registratVehiculo(MouseEvent event) throws IOException {
        String pl = placa.getText();
        String marc = marca.getText();
        String model = modelo.getText();
        String motor = tipoMotor.getText();
        String anoStr = año.getText();
        String recoStr = recorrido.getText();
        String col = color.getText();
        String combust = combustible.getText();
        String priceStr = precio.getText();
        String vidrio = TipoVidrios.getText();
        String transmision = tipoTransmision.getText();

        String seleccion = tipo.getValue();

        if (imgFile == null || pl.isEmpty() || marc.isEmpty() || model.isEmpty() || motor.isEmpty() || col.isEmpty() || combust.isEmpty() || anoStr.isEmpty() || recoStr.isEmpty() || priceStr.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, llene todos los campos requeridos.");
            alerta.show();
            return;
        }

        try {
            int ano = Integer.parseInt(anoStr);
            int reco = Integer.parseInt(recoStr);
            double price = Double.parseDouble(priceStr);

            if (Vehiculo.validarPlacaExistente(pl, "vehiculos.ser")) {
                throw new placaExisteException("El vehiculo ya está registrado");
            } else {
                if ("Moto".equals(seleccion)) {
                    //Si es moto, crea un objeto moto
                    Vehiculo v = new Vehiculo(usuarioC,pl, marc, model, motor, ano, reco, col, combust, price);
                    v.guardarInfoSer("vehiculos.ser");
                    usuarioC.getVehiculos().add(v);
                    guardarImagen(imgFile, pl);

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Auto registrado correctamente.");
                    alerta.show();
                } else if ("Auto".equals(seleccion)) {
                    // Si es Auto, crea un objeto Auto
                    if (vidrio.isEmpty() || transmision.isEmpty()) {
                        Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, llene todos los campos requeridos.");
                        alerta.show();
                        return;
                    }

                    Auto auto = new Auto(usuarioC,pl, marc, model, motor, ano, reco, col, combust, vidrio, transmision, price);
                    auto.guardarInfoSer("vehiculos.ser");
                    usuarioC.getVehiculos().add(auto);
                    guardarImagen(imgFile, pl);

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Auto registrado correctamente.");
                    alerta.show();
                } else if ("Camioneta".equals(seleccion)) {
                    // Si es Camioneta, verifica y procesa el parámetro "capacidad"
                    String traccion = tipoTraccion.getText();
                    if (vidrio.isEmpty() || transmision.isEmpty() || traccion.isEmpty()) {
                        Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, llene todos los campos requeridos.");
                        alerta.show();
                        return;
                    }

                    // Si se proporciona la capacidad, crea un objeto Camioneta
                    Camioneta camioneta = new Camioneta(usuarioC,pl, marc, model, motor, ano, reco, col, combust, vidrio, transmision, price, traccion);
                    camioneta.guardarInfoSer("vehiculos.ser");
                    usuarioC.getVehiculos().add(camioneta);
                    guardarImagen(imgFile, pl);

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Camioneta registrada correctamente.");
                    alerta.show();
                } else {
                    // Si no se selecciona ninguna opción, muestra un mensaje de error
                    Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, seleccione un tipo de vehículo.");
                    alerta.show();
                }
            }
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Año, recorrido y precio deben ser valores numéricos válidos.");
            alerta.show();
        } catch (placaExisteException e) {
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
        imagen.setImage(null);
        imgFile = null;
    }

    @FXML
    private void tipoVehiculo(ActionEvent event) {
    }

    @FXML
    private void buscarImagen(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Buscar Imagen");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.png", "*.jpeg"));
        
        imgFile = fc.showOpenDialog(null);
        if(imgFile!=null)
            imagen.setImage(new Image(imgFile.toURI().toString()));
    }
    
    private void guardarImagen(File imagen,String n) throws IOException{
        String nuevoNombre = n+".png";
        if(imagen!=null){
            String rutaProyecto = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources";
            String rutaCarpetaDestino = rutaProyecto + File.separator + "imagenesVehiculos";
            File destinoCarpeta = new File(rutaCarpetaDestino);
            if(!destinoCarpeta.exists())
                destinoCarpeta.mkdirs();
            File destinoArchivo = new File(rutaCarpetaDestino+File.separator+nuevoNombre);
            if(destinoArchivo.exists())
                destinoArchivo.delete();
            Files.copy(imgFile.toPath(),destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
        }else
            throw new IOException();
        
    }
    
    
    
    
}
