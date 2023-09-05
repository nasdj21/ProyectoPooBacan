/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Auto;
import ec.edu.espol.proyectop1.Camioneta;
import ec.edu.espol.proyectop1.Oferta;
import ec.edu.espol.proyectop1.Usuario;
import ec.edu.espol.proyectop1.Utilitaria;
import ec.edu.espol.proyectop1.Vehiculo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author eliez
 */
public class SpecsAceptarOfertaController implements Initializable {

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
    private Label tipo;
    @FXML
    private Button regresar;
    @FXML
    private VBox vBox;
    @FXML
    private ImageView imView;
    
    private Usuario usuarioAceptarOferta;
    private Vehiculo vehiculosSelec;
    private ArrayList<Vehiculo> vehiculosFiltrados;
    private ArrayList<Button> botonesOferta = new ArrayList<>();

    
    private ArrayList<Vehiculo> vehiculosSerializados;
    
   
    
    public void setUsuarioAceptar(Usuario usuarioU){
        this.usuarioAceptarOferta= usuarioU;
    }
    
    public void mostrarDetalles(Vehiculo vehiculoSeleccionado){
        this.vehiculosSelec= vehiculoSeleccionado;
    }
    
    public void setVehiculos(ArrayList<Vehiculo>  misCarros){
        this.vehiculosFiltrados = misCarros;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vehiculosSerializados = Vehiculo.leerInfoSer("vehiculos.ser"); 
        
        
        
        
    }
    
    public  void llenarDatos(Vehiculo ve){
        
        String nombreImagen = ve.getPlaca() + ".png"; 
        Image imagen = new Image("/imagenesVehiculos/" + nombreImagen); 
        imView.setImage(imagen); // Establece la imagen en el ImageView
        
        if(vehiculosSelec instanceof Camioneta){
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
    
    public void cargarBotonesOfertas() {
        ArrayList<Oferta> ofertas = Oferta.filtrarOfertasPorVehiculo(vehiculosSelec, "ofertas.ser"); 
        for (Oferta oferta : ofertas) {
            Button botonOferta = new Button("Oferta:" + oferta.getPrecioOfertado());
            botonOferta.setOnAction(event -> aceptarOferta(botonOferta,oferta));
            vBox.getChildren().add(botonOferta);
            botonesOferta.add(botonOferta);
        }
    }
    
    
    
    private void aceptarOferta(Button botonOferta,Oferta oferta) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Ha aceptado la oferta");
        alerta.show();
        ArrayList<Oferta>ofs = Oferta.readListFromFileSer("ofertas.ser");
        ArrayList<Oferta> oc = new ArrayList<>();
        for(Oferta o : ofs){
            if(!(o.equals(oferta)))
                oc.add(o);
        }
        Oferta.saveListToSer(oc);
        
        ArrayList<Vehiculo> vei = Vehiculo.leerInfoSer("vehiculos.ser");
        ArrayList<Vehiculo> fin = new ArrayList<>();
        for(Vehiculo vehic : vei){
            if(!(vehic.equals(oferta.getVehiculo())))
                fin.add(vehic);
        }
        Vehiculo.saveListToSer(fin);
        vehiculosFiltrados.remove(oferta.getVehiculo());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/carrosUsuario.fxml"));
            Parent root = loader.load();
            
            CarrosUsuarioController usu  = loader.getController();
            usu.setMisCarros(usuarioAceptarOferta);
            usu.setArrayCarros(vehiculosFiltrados);
            usu.vehiculosAer(fin);
            

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        for (Button button : botonesOferta) {
            button.setDisable(true);
        }

        Utilitaria.enviarConGMail(oferta.getUsuario().getCorreo(), "Oferta aceptada","Su oferta al vehículo con placa " + vehiculosSelec.getPlaca() + " ha sido aceptada por su dueño.\nInformación del dueño:\nNombres: " + vehiculosSelec.getUsuario().getNombres() + "\nApellidos: " + vehiculosSelec.getUsuario().getApellidos() + "\nOrganización: " + vehiculosSelec.getUsuario().getOrganizacion() + "\nCorreo: " + vehiculosSelec.getUsuario().getCorreo()  );
    }


    @FXML
    private void regresar(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/carrosUsuario.fxml"));
            Parent root = loader.load();
            
            CarrosUsuarioController usu  = loader.getController();
            usu.setMisCarros(usuarioAceptarOferta);
            usu.setArrayCarros(vehiculosFiltrados);
            usu.vehiculosAer(vehiculosSerializados);
            
            
            
            
            

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
