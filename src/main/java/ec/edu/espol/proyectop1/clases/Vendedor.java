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
    
    public boolean validarCredenciales(String correo, String clave) throws NoSuchAlgorithmException {
    
        ArrayList<Usuario> vendedores = Vendedor.readFile("vendedores.txt");

        for (Usuario vendedor : vendedores) {
            if (vendedor.getCorreo().equals(correo) && vendedor.getClave().equals(Utilitaria.toHexString(Utilitaria.getSHA(clave)))) {
                return true; 
            }
        }

        return false; 
    }
    
    public boolean validarPlacaExistente(String placa) {
    ArrayList<Auto> vehiculos = Auto.leerVehiculo("vehiculos.txt");

        for (Auto vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return true; 
            }
        }

        return false; 
    }
   
    
    
    public void ingresarNuevoVehiculo() throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);

        
        System.out.println("Ingrese su correo electrónico:");
        String correo = sc.nextLine();
        System.out.println("Ingrese su clave:");
        String clave = sc.nextLine();

        if (!validarCredenciales(correo, clave)) {
            System.out.println("Credenciales inválidas");
            return;
        }

        
        System.out.println("Ingrese el tipo de vehículo (auto, camioneta, moto):");
        String tipoVehiculo = sc.nextLine();

        System.out.println("Ingrese la placa del vehículo:");
        String placa = sc.nextLine();

        
        if (validarPlacaExistente(placa)) { 
            System.out.println("La placa ingresada ya existe en el sistema. No se puede ingresar el vehículo.");
            return;
        }

        System.out.println("Ingrese la marca del vehículo:");
        String marca = sc.nextLine();

        System.out.println("Ingrese el modelo del vehículo:");
        String modelo = sc.nextLine();

        System.out.println("Ingrese el tipo de motor del vehículo:");
        String tipoMotor = sc.nextLine();

        System.out.println("Ingrese el año del vehículo:");
        int anio = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer del salto de línea

        System.out.println("Ingrese el recorrido del vehículo:");
        int recorrido = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer del salto de línea

        System.out.println("Ingrese el color del vehículo:");
        String color = sc.nextLine();

        System.out.println("Ingrese el tipo de combustible del vehículo:");
        String tipoCombustible = sc.nextLine();

        if (tipoVehiculo.equalsIgnoreCase("auto") || tipoVehiculo.equalsIgnoreCase("camioneta")) {
            System.out.println("Ingrese el tipo de vidrios del vehículo:");
            String tipoVidrios = sc.nextLine();

            if (tipoVehiculo.equalsIgnoreCase("auto")) {
                System.out.println("Ingrese el tipo de transmisión del vehículo:");
                String tipoTransmision = sc.nextLine();

                Auto auto = new Auto(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, 0.0);
                auto.guardarInformacion();
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                System.out.println("Ingrese el tipo de tracción del vehículo:");
                String tipoTraccion = sc.nextLine();

                Camioneta camioneta = new Camioneta(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, "", tipoTraccion, 0.0);
                camioneta.guardarInformacion();
            }
        } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
            Auto moto = new Auto(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, "", "", 0.0);
            moto.guardarInformacion();
        }

        System.out.println("El vehículo se ha ingresado exitosamente.");
    }
     

}

    
    


    