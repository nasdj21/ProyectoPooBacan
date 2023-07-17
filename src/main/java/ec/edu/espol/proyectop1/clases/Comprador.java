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
