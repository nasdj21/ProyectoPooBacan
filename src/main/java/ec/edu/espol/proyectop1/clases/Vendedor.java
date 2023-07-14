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
    private ArrayList<Vehiculo>vehiculos;

    public Vendedor(int id, String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(id, nombres, apellidos, organizacion, correo, clave);
        this.vehiculos = new ArrayList<>();
    }
    
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
    public void vMenu() throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) { // Mientras la opción no sea "Regresar"
            System.out.println("----- MENÚ VENDEDOR -----");
            System.out.println("1. Registrar un nuevo vendedor");
            System.out.println("2. Registrar un nuevo vehículo");
            System.out.println("3. Aceptar oferta");
            System.out.println("4. Regresar");
            System.out.println("--------------------------");
            System.out.println("Ingrese su opción:");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del salto de línea

            switch (opcion) {
                case 1:
                    crearVendedor();
                    break;
                case 2:
                    ingresarNuevoVehiculo();
                    break;
                case 3:
//                    aceptarOferta();
//                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }
    
    
    public static void crearVendedor() throws NoSuchAlgorithmException{
        Scanner sc = new Scanner(System.in);
        Usuario.crearUsuario("vendedores.txt");
        
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

                Auto auto = new Auto(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, 0.0);
                auto.guardarInformacion();
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                System.out.println("Ingrese el tipo de tracción del vehículo:");
                String tipoTraccion = sc.nextLine();

                Camioneta camioneta = new Camioneta(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, "", tipoTraccion, 0.0);
                camioneta.guardarInformacion();
            }
        } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
            Auto moto = new Auto(id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, "", "", 0.0);
            moto.guardarInformacion();
        }

        System.out.println("El vehículo se ha ingresado exitosamente.");
    }
     

}

    
    


    
