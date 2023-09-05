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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nicolassierra
 */
public class Oferta implements Serializable{
    Usuario usuario;
    Vehiculo vehiculo;
    private final double precioOfertado;

    
   
    public Oferta(Usuario usuario, double precioOfertado, Vehiculo vehiculo) {
        this.usuario = usuario;
        
        this.vehiculo = vehiculo;
        this.precioOfertado = precioOfertado;
        
            
    }
    

    public Usuario getUsuario(){
        return this.usuario;
    }
    
    
    
    
    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }
    
    public double getPrecioOfertado() {
        return this.precioOfertado;
    }
    

    public void saveSer(String nomfile){
        ArrayList<Oferta>ofertas = Oferta.readListFromFileSer(nomfile);
        ofertas.add(this);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(ofertas);
        }
        catch(IOException e){
            
        }
        
    }
    
      public static ArrayList<Oferta> readListFromFileSer(String nombreArchivo) {
        ArrayList<Oferta>ofertas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ofertas = (ArrayList<Oferta>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return ofertas;
    }
    
      public static ArrayList<Oferta> filtrarOfertasPorVehiculo(Vehiculo vehiculo, String nombreArchivo) {
        ArrayList<Oferta> ofertas = readListFromFileSer(nombreArchivo);
        ArrayList<Oferta> ofertasFiltradas = new ArrayList<>();

        String placaVehiculo = vehiculo.getPlaca();

        for (Oferta oferta : ofertas) {
            Vehiculo vehiculoOferta = oferta.getVehiculo();
            if (vehiculoOferta != null && vehiculoOferta.getPlaca().equals(placaVehiculo)) {
                ofertasFiltradas.add(oferta);
            }
        }

        return ofertasFiltradas;
    }
   
    
    
 }