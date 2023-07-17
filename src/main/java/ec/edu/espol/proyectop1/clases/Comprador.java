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
        
    }
}
