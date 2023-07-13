/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1.clases;

import static ec.edu.espol.proyectop1.clases.Utilitaria.getSHA;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author nicolassierra
 */
public class Vendedor extends Usuario{

    public Vendedor(String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(nombres, apellidos, organizacion, correo, clave);
    }
    
    
    
    public static void crearVendedor(){
        try{
            ArrayList<Usuario>usuarios = Usuario.readFile("vendedores.txt");

            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese nombre");
            String nombre = sc.next();
            System.out.println("Ingrese apellido");
            String apellido = sc.next();
            System.out.println("Ingrese organizacion");
            String org = sc.next();

            System.out.println("Ingrese correo");
            String mail = sc.next();
            for(Usuario u : usuarios){
                if(mail.equals(u.getCorreo())){
                    System.out.println("Este correo ya existe");
                    return;
                }

            }
            System.out.println("Ingrese clave");
            String clav = sc.next();

            Usuario un = new Vendedor(nombre, apellido, org, mail, Utilitaria.toHexString(getSHA(clav)));
            un.saveFile("vendedores.txt");
        }
        catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        
    }


    
}
