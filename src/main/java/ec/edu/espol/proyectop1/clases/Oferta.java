/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1.clases;

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
    private double precioOfertado;

    
   
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
                        au.mostrarInformacion(); }
                }   
            } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
                
                for(Auto au : vehiculos) {
                
                if(au.getTipoVidrios() == null && Integer.parseInt(r[0]) >= au.getRecorrido() && Integer.parseInt(r[1]) <= au.getRecorrido() && Integer.parseInt(a[0]) >= au.getAnio() && Integer.parseInt(a[1]) <= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio()){
                    System.out.println("Vehiculo encontrado:");
                    au.mostrarInformacion(); }
                }
                
            
                
                
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                
                for(Camioneta ca : camionetas) {
                    
                    if(Integer.parseInt(r[0]) >= ca.getRecorrido() && Integer.parseInt(r[1]) <= ca.getRecorrido() && Integer.parseInt(a[0]) >= ca.getAnio() && Integer.parseInt(a[1]) <= ca.getAnio() && Double.parseDouble(p[0]) <= ca.getPrecio() && Double.parseDouble(p[1]) >= ca.getPrecio()){
                        System.out.println("Vehiculo encontrado:"); 
                        ca.mostrarInformacion(); }
                    
                
                
                
            } else {
                
                System.out.println("Tipo de vehículo no válido. Intente nuevamente.");
                
            }
                
           
                
         } while (!tipoVehiculo.equalsIgnoreCase("auto") && !tipoVehiculo.equalsIgnoreCase("moto") && !tipoVehiculo.equalsIgnoreCase("camioneta"));
        
            }
            
            
                    
                
        
                
 
