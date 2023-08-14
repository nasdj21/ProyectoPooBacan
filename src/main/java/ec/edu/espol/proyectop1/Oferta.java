package ec.edu.espol.proyectop1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nicolassierra
 */
public class Oferta {
    Usuario comprador;
    Usuario vendedor;
    Vehiculo vehiculo;
    private final double precioOfertado;

    
   
    public Oferta(Usuario comprador, Usuario vendedor, double precioOfertado, Vehiculo vehiculo) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.vehiculo = vehiculo;
        this.precioOfertado = precioOfertado;
        
            
    }
    

    public Usuario getComprador(){
        return this.comprador;
    }
    
    public Usuario getVendedor(){
        return this.vendedor;
    }
    
    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }
    
    public double getPrecioOfertado() {
        return this.precioOfertado;
    }
    

    public void saveSer(String nomfile){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(this);
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
    
   
    
    
    
 }


            
                
        
                
 
