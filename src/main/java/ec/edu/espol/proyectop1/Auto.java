package ec.edu.espol.proyectop1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author eliez
 */
public class Auto extends Vehiculo{
    private String tipoVidrios;
    private String tipoTransmision;
    

    public Auto(Usuario usuario, String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, String tipoVidrios, String tipoTransmision, double precio) { //auto de verdad
        super(usuario, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.tipoVidrios = tipoVidrios;
        this.tipoTransmision = tipoTransmision;
    }

   

    
    

    

    public String getTipoVidrios() {
        return tipoVidrios;
    }

    public String getTipoTransmision() {
        return tipoTransmision;
    }

    
    
   
    
}







    


    