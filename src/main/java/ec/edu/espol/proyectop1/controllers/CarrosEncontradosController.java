package ec.edu.espol.proyectop1.controllers;

import ec.edu.espol.proyectop1.Auto;
import ec.edu.espol.proyectop1.Usuario;
import ec.edu.espol.proyectop1.Vehiculo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    
    
    private Usuario usuarioE;
    
    private ArrayList<Vehiculo>vehiculos;
    @FXML
    private TableView<Vehiculo> table;
    
    public void setUsuario(Usuario usuario){
        this.usuarioE = usuario;
        System.out.println("Usuario recibido: " + usuarioE.getNombres()); 
    }
    
    public void setArrayCarrosTodos(ArrayList<Vehiculo> carros){
        this.vehiculos = carros;
        
    }
    
      
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
         

    @FXML
    private void regresarAtras(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/misCarros.fxml"));
            Parent root = loader.load();
            
            MisCarrosController miscarrosController = loader.getController();
            
            miscarrosController.setUsuarioMisCarros(usuarioE);

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void mostrar(ArrayList<Vehiculo>vehiculos){
        this.vehiculos = vehiculos;
        vcarros.setSpacing(10); 
        TableColumn marcaColumn = new TableColumn("Marca");
        TableColumn modeloColumn = new TableColumn("Modelo");
        TableColumn placaColumn = new TableColumn("Placa");
        TableColumn añoColumn = new TableColumn("Año");
        TableColumn recorridoColumn = new TableColumn("Recorrido");
        TableColumn precioColumn = new TableColumn("Precio");
        ObservableList<Vehiculo>data = FXCollections.observableArrayList(vehiculos);
        marcaColumn.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("modelo"));
        placaColumn.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("placa"));
        añoColumn.setCellValueFactory(new PropertyValueFactory<Vehiculo,Integer>("anio"));
        recorridoColumn.setCellValueFactory(new PropertyValueFactory<Vehiculo,Integer>("recorrido"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<Vehiculo,Double>("precio"));
        
        table.getColumns().addAll(marcaColumn, modeloColumn, placaColumn, añoColumn, recorridoColumn, precioColumn);
        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
    }

    @FXML
    private void specs(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectop1/specsCarro.fxml"));
            Parent root = loader.load();
            
            SpecsCarroController specsController = loader.getController();

            specsController.show(table.getSelectionModel().getSelectedItem());
            specsController.pasarVehiculo(table.getSelectionModel().getSelectedItem());
            specsController.setUsuarioSpecs(usuarioE);
            specsController.pasarListaVehiculos(vehiculos);

            Scene scene = new Scene(root);
            Stage stage = (Stage) regresar.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}