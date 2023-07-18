package ec.edu.espol.proyectop1.clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nicolassierra
 */
public class Oferta {
    Comprador comprador;
    Vendedor vendedor;
    Vehiculo vehiculo;
    private final double precioOfertado;

    
   
    public Oferta(Comprador comprador, Vendedor vendedor, Vehiculo vehiculo, double precioOfertado) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.vehiculo = vehiculo;
        this.precioOfertado = precioOfertado;
        
            
    }
    public double getPrecioOfertado() {
        return this.precioOfertado;
    }
    
    public Comprador getComprador() {
        return this.comprador;
    }
    
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }
    
    public void guardarOferta(String nomFile){ 
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(this.comprador.getCorreo()+"|"+this.vendedor.getCorreo()+"|"+this.vehiculo.getModelo()+"|"+this.vehiculo.getPlaca()+"|"+this.precioOfertado);
        }
        catch(Exception e){ 
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Oferta> leerOfertaPorPlaca(String archivo, String placa) throws NoSuchAlgorithmException {
        ArrayList<Oferta> ofertas = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] tokens = linea.split("\\|");

                String correoComprador = tokens[0];
                String correoVendedor =  tokens[1];
                String modeloVehiculo = tokens[2];
                String placaVehiculo = tokens[3];
                double precioOfertado = Double.parseDouble(tokens[4]);
                Vendedor vend = Vendedor.encontrarVendedor(correoVendedor);
                Vehiculo veh = Auto.encontrarVehiculo(placaVehiculo);
                Comprador comp = Comprador.encontrarComprador(correoComprador);
                
                Oferta of = new Oferta(comp,vend,veh, precioOfertado);
                ofertas.add(of);

                }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de ofertas: " + e.getMessage());
        }

        return ofertas;
        }   
    
 }


            
                
        
                
 
