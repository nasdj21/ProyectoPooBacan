module ec.edu.espol.proyectop1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens ec.edu.espol.proyectop1 to javafx.fxml;
    exports ec.edu.espol.proyectop1;
}
