package ec.edu.espol.proyectop1.clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
        return precioOfertado;
    }
    
    public Comprador getComprador() {
        return comprador;
    }
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    public void guardarOferta(String nomFile){ 
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(this.comprador.getCorreo()+"|"+this.vendedor.getCorreo()+"|"+this.vehiculo.getModelo()+"|"+this.precioOfertado);
        }
        catch(Exception e){ 
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Oferta> leerOfertaPorPlaca(String archivo, String placa) {
        ArrayList<Oferta> ofertas = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] tokens = linea.split("\\|");

                String compradorVehiculo = tokens[0];
                String vendedorVehiculo =  tokens[1];
                String vehiculoOft = tokens[2];
                double precioOfertado = Double.parseDouble(tokens[3]);
                
                
            
                
                Vehiculo vehiculo;
                                
                if (vehiculoOft.equals("auto")) {
                vehiculo = new Auto(0, "", "", "", "", 0, 0, "", "", "", "", 0); 
                } else if (vehiculoOft.equals("camioneta")) {
                vehiculo = new Camioneta(0, "", "", "", "", 0,0, "", "", "", "", 0, ""); 
                } else {
               
                System.out.println("Tipo de vehículo no válido en el archivo de ofertas");
                continue; 
                }
                if(vehiculo.getPlaca().equalsIgnoreCase(placa)){
                Comprador comprador = new Comprador(0, compradorVehiculo, "", "", "", ""); 
                Vendedor vendedor = new Vendedor(0, vendedorVehiculo, "", "", "", ""); 
                
                
                Oferta ofertitas = new Oferta(comprador, vendedor, vehiculo, precioOfertado);
            
                
                ofertas.add(ofertitas);
                }
                
                







                }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de ofertas: " + e.getMessage());
        }

        return ofertas;
        }
    
   
    
    
    
    
    
 }


            
                
        
                
 
