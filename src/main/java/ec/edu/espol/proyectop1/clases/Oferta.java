package ec.edu.espol.proyectop1.clases;

import java.io.File;
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
    
    public void guardarOferta(String nomFile){ //Para escribir y crear un archivo
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(this.comprador+"|"+this.vendedor+"|"+this.vehiculo+"|"+this.precioOfertado);
        }
        catch(Exception e){ //lo que deberia hacer en el caso de que el archivo no existe
            System.out.println(e.getMessage());
        }
    }  
    
   
    
    @SuppressWarnings("empty-statement")
    public void ofertarVehiculo(){
        ArrayList<Auto> vehiculos = Auto.leerVehiculo("vehiculos.txt");
        ArrayList<Camioneta> camionetas = Camioneta.leerVehiculo1("vehiculos.txt");
        
        Scanner sc = new Scanner(System.in);
        
        int recoBajo = 0;
        int recoAlto = 0;
        int añoBajo = 0;
        int añoAlto = 0;
        double precioBajo = 0;
        double precioAlto = 0;
        
        String tipoVehiculo;
        System.out.println("Ingrese rango de recorrido(-)");
        String rec = sc.next();
        System.out.println("Ingrese rango de año(-)");
        String an = sc.next();
        System.out.println("Ingrese rango de precio(-)");
        String costo = sc.next();
        
        String[]r = rec.split("-");
        String[]a = an.split("-");
        String[]p = costo.split("-");
        
        do {
            System.out.println("Ingrese un tipo de vehículo (auto, moto o camioneta):");
            tipoVehiculo = sc.nextLine();
            

            if (tipoVehiculo.equalsIgnoreCase("auto")) {
               
                for(Auto au : vehiculos) {
                
                    if(Integer.parseInt(r[0]) >= au.getRecorrido() && Integer.parseInt(r[1]) <= au.getRecorrido() && Integer.parseInt(a[0]) >= au.getAnio() && Integer.parseInt(a[1]) <= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio()){
                        System.out.println("Vehiculo encontrado:"); 
                        au.mostrarInformacion();
                        System.out.println("\n ¿ Desea ofertar $ ?");
                        String respuesta = sc.next();
                        if(respuesta.equalsIgnoreCase("si")){
                            System.out.println("¿Cuanto desea ofertar?");
                            double valorOfertado = sc.nextDouble();
                            Oferta oferta = new Oferta(this.comprador, au.getDueño(), au, valorOfertado);
                            oferta.guardarOferta("ofertas.txt");
                        }
                        else{}
                    }
                    
                }   
            } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
                
                for(Auto au : vehiculos) {
                
                    if(au.getTipoVidrios() == null && Integer.parseInt(r[0]) >= au.getRecorrido() && Integer.parseInt(r[1]) <= au.getRecorrido() && Integer.parseInt(a[0]) >= au.getAnio() && Integer.parseInt(a[1]) <= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio()){
                    System.out.println("Vehiculo encontrado:");
                    au.mostrarInformacion(); 
                    System.out.println("\n ¿ Desea ofertar $ ?");
                        String respuesta = sc.next();
                        if(respuesta.equalsIgnoreCase("si")){
                            System.out.println("¿Cuanto desea ofertar?");
                            double valorOfertado = sc.nextDouble();
                            Oferta oferta = new Oferta(this.comprador, au.getDueño(), au, valorOfertado);
                            oferta.guardarOferta("ofertas.txt");
                        }
                        else{}
                    }
                }
                
            
                
                
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                
                for(Camioneta ca : camionetas) {
                    
                    if(Integer.parseInt(r[0]) >= ca.getRecorrido() && Integer.parseInt(r[1]) <= ca.getRecorrido() && Integer.parseInt(a[0]) >= ca.getAnio() && Integer.parseInt(a[1]) <= ca.getAnio() && Double.parseDouble(p[0]) <= ca.getPrecio() && Double.parseDouble(p[1]) >= ca.getPrecio()){
                        System.out.println("Vehiculo encontrado:"); 
                        ca.mostrarInformacion(); 
                        System.out.println("\n ¿ Desea ofertar $ ?");
                        String respuesta = sc.next();
                        if(respuesta.equalsIgnoreCase("si")){
                            System.out.println("¿Cuanto desea ofertar?");
                            double valorOfertado = sc.nextDouble();
                            Oferta oferta = new Oferta(this.comprador, ca.getDueño(), ca, valorOfertado);
                            oferta.guardarOferta("ofertas.txt");
                        }
                        else{}
                        
                    }
                }
                 
                    
                
                
                
            }else
                
                System.out.println("Tipo de vehículo no válido. Intente nuevamente.");
                
            
                
           
                
         } while (!tipoVehiculo.equalsIgnoreCase("auto") && !tipoVehiculo.equalsIgnoreCase("moto") && !tipoVehiculo.equalsIgnoreCase("camioneta"));
        
            }
        }


            
                
        
                
 
