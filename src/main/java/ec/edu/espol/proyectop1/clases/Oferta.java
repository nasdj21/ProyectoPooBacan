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
    
    public void ofertarVehiculo(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Auto> vehiculos = Auto.leerVehiculo("vehiculos.txt");
        int recoBajo = 0;
        int recoAlto = 0;
        int añoBajo = 0;
        int añoAlto = 0;
        double precioBajo = 0;
        double precioAlto = 0;
        
        System.out.println("Ingrese tipo de vehiculo (en minúscula)");
        String tipo = sc.next();
        System.out.println("Ingrese rango de recorrido(-)");
        String rec = sc.next();
        System.out.println("Ingrese rango de año(-)");
        String an = sc.next();
        System.out.println("Ingrese rango de precio(-)");
        String costo = sc.next();
        String[]r = rec.split("-");
        String[]a = an.split("-");
        String[]p = costo.split("-");
        for(Auto au : vehiculos){
            if(tipo.equals("moto")){
                if(au.getTipoVidrios() == null && Integer.parseInt(r[0]) >= au.getRecorrido() && Integer.parseInt(r[1]) <= au.getRecorrido() && Integer.parseInt(a[0]) >= au.getAnio() && Integer.parseInt(a[1]) <= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio()){
                    System.out.println("Vehiculo encontrado:");
                    au.mostrarInformacion();
                }
            }else
                if(tipo.equals("auto")){
                    if(Integer.parseInt(r[0]) >= au.getRecorrido() && Integer.parseInt(r[1]) <= au.getRecorrido() && Integer.parseInt(a[0]) >= au.getAnio() && Integer.parseInt(a[1]) <= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio()){
                        System.out.println("Vehiculo encontrado:");
                        au.mostrarInformacion();
                    
                }
                    
                
        
                
        }
    }
   
   }
    
}
