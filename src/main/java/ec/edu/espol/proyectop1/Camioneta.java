package ec.edu.espol.proyectop1;
import java.io.File;
import java.io.FileInputStream;
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
public class Camioneta extends Auto{
    private String tipoTraccion;

    public Camioneta(Usuario usuario,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, String tipoVidrios, String tipoTransmision, double precio, String tipoTraccion) {
        super(usuario, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, precio);
        this.tipoTraccion = tipoTraccion;
    }

    public String getTipoTraccion() {
        return tipoTraccion;
    }

   
 
   
    
    public static void guardarInfoCamArraySer(String nombreArchivo, ArrayList<Vehiculo> camioneta) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo,true))) {
            out.writeObject(camioneta);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
}
