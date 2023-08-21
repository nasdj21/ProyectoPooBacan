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
public class Auto extends Vehiculo implements Serializable{
    private String tipoVidrios;
    private String tipoTransmision;

    public Auto(int id, String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, String tipoVidrios, String tipoTransmision, double precio) { //auto de verdad
        super(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.tipoVidrios = tipoVidrios;
        this.tipoTransmision = tipoTransmision;
    }

    public Auto(int id, String tipoVidrios, String tipoTransmision, String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio) { //Moto
        this(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible,null,null, precio);
        
    }

    

    

    public String getTipoVidrios() {
        return tipoVidrios;
    }

    public String getTipoTransmision() {
        return tipoTransmision;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Información del Auto:");
        System.out.println("Dueño: "+ getId());
        System.out.println("Placa: " + getPlaca());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Tipo de motor: " + getTipoMotor());
        System.out.println("Año: " + getAnio());
        System.out.println("Recorrido: " + getRecorrido());
        System.out.println("Color: " + getColor());
        System.out.println("Tipo de combustible: " + getTipoCombustible());
        System.out.println("Tipo de vidrios: " + tipoVidrios);
        System.out.println("Tipo de transmisión: " + tipoTransmision);
        System.out.println("Precio: " + getPrecio());
    }

    @Override
    public void guardarInformacion() {
    String informacion = getId() + "|" + getPlaca() + "|" + getMarca() + "|" + getModelo() + "|" + getTipoMotor() + "|" + getAnio() + "|" + getRecorrido() + "|" + getColor() + "|" + getTipoCombustible() + "|" + tipoVidrios + "|" + tipoTransmision + "|" + getPrecio();

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehiculos.txt"), true))) {
            pw.println(informacion);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
   
   
    
    public static ArrayList<Auto> leerVehiculo(String archivoVehiculo) {
        ArrayList<Auto> autos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(archivoVehiculo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] tokens = linea.split("\\|");
                
                int id = Integer.parseInt(tokens[0]);
                String placa = tokens[1];
                String marca = tokens[2];
                String modelo = tokens[3];
                String tipoMotor = tokens[4];
                int anio = Integer.parseInt(tokens[5]);
                int recorrido = Integer.parseInt(tokens[6]);
                String color = tokens[7];
                String tipoCombustible = tokens[8];
                String tipoVidrios = tokens[9];
                String tipoTransmision = tokens[10];
                double precio = Double.parseDouble(tokens[11]);

                Auto auto = new Auto(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, precio);
                autos.add(auto);
            }
        
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
       

        return autos;
        }
    
    //SERIALIZADO
    public void guardarInfoSer(String nomfile){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile,true))){
            out.writeObject(this);
        }
        catch(IOException e){
            
        }
        
        
    }
    
    public static void guardarInfoArraySer(String nombreArchivo, ArrayList<Auto> auto) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo,true))) {
            out.writeObject(auto);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
}







    


    