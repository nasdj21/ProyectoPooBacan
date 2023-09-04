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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eliez
 */
public class CarrosUsuarioController implements Initializable {

    @FXML
    private Label vEncontrados;
    @FXML
    private VBox vcarros;
    @FXML
    private TableView<Vehiculo> table;
    @FXML
    private Button regresar;
    private Usuario usuarioU;
    private ArrayList<Vehiculo> misCarros;
    private ArrayList<Vehiculo> vSer;
    
    public void vehiculosAer(ArrayList<Vehiculo> actu){
        this.vSer = actu;
    }
    
    public void setMisCarros(Usuario usuario){
        this.usuarioU = usuario;
        System.out.println("Usuario recibido: " + usuarioU.getNombres()); 
    }
    
    public void setArrayCarros(ArrayList<Vehiculo> carros){
        this.misCarros = carros;
        
    }
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> llenarTabla(misCarros));
        

    }    
    public void llenarTabla(ArrayList<Vehiculo> v) {
        //misCarros = Vehiculo.filtrarVehiculoPorUsuario(usuarioU, "vehiculos.ser");
        vcarros.setSpacing(10);

        TableColumn<Vehiculo, String> marcaColumn = new TableColumn<>("Marca");
        TableColumn<Vehiculo, String> modeloColumn = new TableColumn<>("Modelo");
        TableColumn<Vehiculo, String> placaColumn = new TableColumn<>("Placa");
        TableColumn<Vehiculo, Integer> añoColumn = new TableColumn<>("Año");
        TableColumn<Vehiculo, Integer> recorridoColumn = new TableColumn<>("Recorrido");
        TableColumn<Vehiculo, Double> precioColumn = new TableColumn<>("Precio");

        ObservableList<Vehiculo> data = FXCollections.observableArrayList(v);
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        placaColumn.setCellValueFactory(new PropertyValueFactory<>("placa"));
        añoColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));
        recorridoColumn.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        table.getColumns().addAll(marcaColumn, modeloColumn, placaColumn, añoColumn, recorridoColumn, precioColumn);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    @FXML
    private void specs(MouseEvent event) {
    
    if (table.getSelectionModel().getSelectedItem() != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/specsAceptarOferta.fxml"));
            Parent root = loader.load();
            
            SpecsAceptarOfertaController specsController = loader.getController();
            specsController.setUsuarioAceptar(usuarioU);
            specsController.mostrarDetalles(table.getSelectionModel().getSelectedItem()); 
            specsController.llenarDatos(table.getSelectionModel().getSelectedItem());
            specsController.setVehiculos(misCarros);
            specsController.cargarBotonesOfertas();

            Scene scene = new Scene(root);
            Stage stage = (Stage) table.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    @FXML
    private void regresarAtras(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/inicio.fxml"));
            Parent root = loader.load();
            
            InicioController inicio  = loader.getController();
            inicio.setUsuario(usuarioU);
            inicio.setVSer(vSer);

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
