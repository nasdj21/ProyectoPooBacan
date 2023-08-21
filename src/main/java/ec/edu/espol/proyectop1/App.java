/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.proyectop1;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
        
    }
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        
//        Usuario usuario1 = new Usuario(1, "carlos", "monte", "espol", "carlosmonte@gmail.com", "256107");
//        Usuario usuario2 = new Usuario(2, "juan", "caicedo", "espol", "juancaicedo@gmail.com", "326598");
//        Usuario usuario3 = new Usuario(3, "nicolas", "sierra", "espol", "nicolassierra@gmail.com", "635241");
//        Usuario usuario4 = new Usuario(4, "josue", "castro", "primax", "josuecastro@gmail.com", "256107");
//        Usuario usuario5 = new Usuario(5, "eliezer", "acebo", "espol", "eliezeracebo@gmail.com", "652253");
//        Usuario usuario6 = new Usuario(6, "ismael", "camacho", "karscher", "ismaelcamacho@gmail.com", "647832");
//        Usuario usuario7 = new Usuario(7, "ariel", "torres", "humboldt", "arieltorres@gmail.com", "569852");
//        Usuario usuario8 = new Usuario(8, "johana", "aroca", "cnt", "johanaaroca@gmail.com", "917356");
//        Usuario usuario9 = new Usuario(9, "nayeli", "angulo", "estatal", "nayeliangulo@gmail.com", "614395");
//        Usuario usuario10 = new Usuario(10, "aaron", "zapata", "humboldt", "aaronzapata@gmail.com", "678231");
//        
//        
//     
//       
//        
//        
//        //AVANCE DEL PROYECTO, SERIALIZAR
//        ArrayList<Usuario> usuariosSer = new ArrayList<>();
//        usuariosSer.add(usuario1);
//        usuariosSer.add(usuario2);
//        usuariosSer.add(usuario3);
//        usuariosSer.add(usuario4);
//        usuariosSer.add(usuario5);
//        usuariosSer.add(usuario6);
//        usuariosSer.add(usuario7);
//        usuariosSer.add(usuario8);
//        usuariosSer.add(usuario9);
//        usuariosSer.add(usuario10);
//        
//        Usuario.saveListToFileSer("usuarios.ser", usuariosSer);
//        ArrayList<Usuario> usuarioLeidos = Usuario.readListFromFileSer("usuarios.ser");
//    }
//    
    }
    
    
}
