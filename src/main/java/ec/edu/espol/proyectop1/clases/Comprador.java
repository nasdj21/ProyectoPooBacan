/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1.clases;

import static ec.edu.espol.proyectop1.clases.Utilitaria.getSHA;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import ec.edu.espol.proyectop1.clases.Oferta;
import java.io.FileNotFoundException;



/**
 *
 * @author nicolassierra
 */
public class Comprador extends Usuario{
    private ArrayList<Oferta>ofertas;
    
    public Comprador(int id, String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(id, nombres, apellidos, organizacion, correo, clave);
        this.ofertas = new ArrayList<>();
    }
    
    
    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }    
    
    public static ArrayList<Comprador> leerComprador(String nomfile)  { //Para leer un archivo
        ArrayList<Comprador> compradores= new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                
                // linea = Nicolas|Sierra|Espol|nasierra@espol.edu.ec|claveNueva
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Comprador u = new Comprador(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                compradores.add(u); 
                
            }
                
        }
        
        catch(Exception e){
                System.out.println(e.getMessage());
        }
        return compradores;
        
    
    }
    
    
    public void cMenu() throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) { 
            System.out.println("----- MENÚ COMPRADOR -----");
            System.out.println("1. Registrar un nuevo comprador");
            System.out.println("2. Ofertar por un vehículo");
            System.out.println("3. Regresar");
            System.out.println("--------------------------");
            System.out.println("Ingrese su opción:");

            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    crearComprador();
                    break;
                case 2:
                    crearOferta(); 
                   break;
                case 3:
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }
          
    
    
    public static void crearComprador() throws NoSuchAlgorithmException{
        Scanner sc = new Scanner(System.in);
        Usuario.crearUsuario("compradores.txt");
        
    }
    public void crearOferta(){
        Oferta oferta = new Oferta(null, null, null, 0);
        
        this.ofertarVehiculo();
        
        
    }
    
    public void ofertar(Vehiculo v){
        Scanner sc = new Scanner (System.in);
        System.out.println("¿Cuanto desea ofertar?");
        double valorOfertado = sc.nextDouble();
        Oferta oferta = new Oferta(this, v.getDueño(), v, valorOfertado);
        oferta.guardarOferta("ofertas.txt");
    }
    
    public void ofertarVehiculo(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Vehiculo>lista = filtrarVehiculo();
        for(int i = 0; i < lista.size(); i++){
            int opcion = 0;
            while(opcion != 4){
                lista.get(i).mostrarInformacion();
                System.out.println("1. Ofertar");
                System.out.println("2. Siguiente");
                System.out.println("3. Anterior");
                System.out.println("4. Regresar");
                
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch(opcion){
                    case 1:
                        ofertar(lista.get(i));
                        break;
                    case 2:
                        if(i < lista.size()-1)
                            i++;
                        break;
                    case 3:
                        if(i > 0)
                            i--;
                        break;
                    default:
                        System.out.println("Opcion invalida, ingrese una opcion válida");
                        break;
                                
                }
                
            }
        }
    }
    
    @SuppressWarnings("empty-statement")
    public ArrayList<Vehiculo> filtrarVehiculo(){
        ArrayList<Auto> vehiculos = Auto.leerVehiculo("vehiculos.txt");
        ArrayList<Camioneta> camionetas = Camioneta.leerVehiculo1("vehiculos.txt");
        ArrayList<Vehiculo> fin = new ArrayList<>();
        
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
            tipoVehiculo = sc.next();
            

            if (tipoVehiculo.equalsIgnoreCase("auto")) {
               
                for(Auto au : vehiculos) {
                    if(!(au.getTipoVidrios().equals(null)) && Integer.parseInt(r[0]) <= au.getRecorrido() && Integer.parseInt(r[1]) >= au.getRecorrido() && Integer.parseInt(a[0]) <= au.getAnio() && Integer.parseInt(a[1]) >= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio())
                        fin.add(au);  
                }   
            } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
                
                for(Auto au : vehiculos) {
                
                    if(au.getTipoVidrios().equals(null) && Integer.parseInt(r[0]) <= au.getRecorrido() && Integer.parseInt(r[1]) >= au.getRecorrido() && Integer.parseInt(a[0]) <= au.getAnio() && Integer.parseInt(a[1]) >= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio())
                        fin.add(au);
                }
                
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                
                for(Camioneta ca : camionetas) {
                    
                    if(Integer.parseInt(r[0]) <= ca.getRecorrido() && Integer.parseInt(r[1]) >= ca.getRecorrido() && Integer.parseInt(a[0]) <= ca.getAnio() && Integer.parseInt(a[1]) >= ca.getAnio() && Double.parseDouble(p[0]) <= ca.getPrecio() && Double.parseDouble(p[1]) >= ca.getPrecio())
                        fin.add(ca);
                    
                }
                 
     
            }else
                
                System.out.println("Tipo de vehículo no válido. Intente nuevamente.");
                
            
                
           
                
         } while (!tipoVehiculo.equalsIgnoreCase("auto") && !tipoVehiculo.equalsIgnoreCase("moto") && !tipoVehiculo.equalsIgnoreCase("camioneta"));
        return fin;
        
            }
    
}
