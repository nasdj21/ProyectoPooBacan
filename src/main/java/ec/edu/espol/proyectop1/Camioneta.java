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
public class Camioneta extends Auto implements Serializable{
    private String tipoTraccion;

    public Camioneta(int id, String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, String tipoVidrios, String tipoTransmision, double precio, String tipoTraccion) {
        super(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, precio);
        this.tipoTraccion = tipoTraccion;
    }

    public String getTipoTraccion() {
        return tipoTraccion;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo de tracción: " + tipoTraccion);
    }

    @Override
    public void guardarInformacion() {
        String informacion = getId() + "|" + getPlaca() + "|" + getMarca() + "|" + getModelo() + "|" + getTipoMotor() + "|" + getAnio() + "|" + getRecorrido() + "|" + getColor() + "|" + getTipoCombustible() + "|" + getTipoVidrios() + "|" + getTipoTransmision() + "|" + tipoTraccion + "|" + getPrecio();

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehiculos.txt"), true))) {
            pw.println(informacion);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
    
    public static ArrayList<Camioneta> leerVehiculo1(String archivoVehiculo) {
        ArrayList<Camioneta> camionetas = new ArrayList<>();

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
                String tipoTraccion = tokens[12];

                Camioneta camioneta = new Camioneta(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, precio, tipoTraccion);
                camionetas.add(camioneta);
            }
        
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
       

        return camionetas;
        }
    
    //SERIALIZADO
    public void guardarInfoSer(String nomfile){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile,true))){
            out.writeObject(this);
        }
        catch(IOException e){
            
        }
        
        
    }
    
    public static void guardarInfoCamArraySer(String nombreArchivo, ArrayList<Vehiculo> camioneta) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo,true))) {
            out.writeObject(camioneta);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
}
