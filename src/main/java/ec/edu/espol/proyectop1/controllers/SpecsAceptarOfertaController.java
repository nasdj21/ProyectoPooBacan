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
import java.io.InputStream;
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
        String nombreImagenConExtension = ve.getPlaca() + ".png";
        String nombreImagenSinExtension = ve.getPlaca();

        Image imagen = cargarImagenConExtension(nombreImagenConExtension);

        // Si la imagen no se encontró con la extensión .png, intentamos sin la extensión. Esto es debido a que en el JAR 
        //no se guarda como nombre en png, sino que el png le ayuda a saber que es una imagen, sino e sun archivo X.
        if (imagen == null) {
            imagen = cargarImagenSinExtension(nombreImagenSinExtension);
        }

        if (imagen != null) {
            imView.setImage(imagen);
        } else {
            
        }
//        
        
//        String nombreImagen = ve.getPlaca() + ".png"; 
//        Image imagen = new Image("/imagenesVehiculos/" + nombreImagen); 
//        imView.setImage(imagen); // Establece la imagen en el ImageView
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
    private Image cargarImagenConExtension(String nombreImagen) {//AQUI EN NETBEANS SI MUESTRA IMAGEN
        try {//Lo hago con InputStream stream = getClass().getResourceAsStream porque en JAR segun investigamos 
              //esta seria la mejor manera de adquirir los recursos como imagenes, sin embargo no funcio, con o sin esta forma.
              //Abajo esta el emtodo como estaba inicialmente y aun asi no funciona
            InputStream stream = getClass().getResourceAsStream("/imagenesVehiculos/" + nombreImagen);
            if (stream != null) {
                return new Image(stream);
            } else {
                // La imagen no se encontró.
                return null;
            }
        } catch (Exception e) {
            // Ocurrió un error al cargar la imagen.
            return null;
        }
//        try {
//
//            return new Image(File.separator+"imagenesVehiculos"+File.separator + nombreImagen);
//        } catch (Exception e) {
//            // La imagen con la extensión .png no se encontró.
//            return null;
//        }
    }
    private Image cargarImagenSinExtension(String nombreImagen) {//AQUI EN NETBEANS SI MUESTRA IMAGEN
        try { //Lo hago con InputStream stream = getClass().getResourceAsStream porque en JAR segun investigamos 
              //esta seria la mejor manera de adquirir los recursos como imagenes, sin embargo no funcio, con o sin esta forma.
              //Abajo esta el emtodo como estaba inicialmente y aun asi no funciona
            InputStream stream = getClass().getResourceAsStream("/imagenesVehiculos/" + nombreImagen+ ".png");
            if (stream != null) {
                return new Image(stream);
            } else {
                // La imagen no se encontró.
                return null;
            }
        } catch (Exception e) {
            // Ocurrió un error al cargar la imagen.
            return null;
        }
//        try {
//
//            return new Image(File.separator+"imagenesVehiculos"+File.separator + nombreImagen + ".png");
//        } catch (Exception e) {
//            // La imagen sin la extensión .png tampoco se encontró.
//            return null;
//        }
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
        
         //vehiculosSerializados = Vehiculo.leerInfoSer("vehiculos.ser"); 
        if (vehiculosSerializados != null) {

            int index = -1;
            for (int i = 0; i < vehiculosSerializados.size(); i++) {
                if (vehiculosSerializados.get(i).getPlaca().equals(vehiculosSelec.getPlaca())) {
                    index = i;
                    break;
                }
            }


            if (index != -1) {
                vehiculosSerializados.remove(index);





                vehiculosFiltrados.remove(vehiculosSelec);
                
            }
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
