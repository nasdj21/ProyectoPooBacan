/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1.clases;

import static ec.edu.espol.proyectop1.clases.Oferta.leerOfertaPorPlaca;
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
    private ArrayList<Oferta> ofertas;

    public Vendedor(int id, String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(id, nombres, apellidos, organizacion, correo, clave);
        this.vehiculos = new ArrayList<>();
    }
    
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
     public static ArrayList<Vendedor> leerVendedor(String nomfile) { //Para leer un archivo
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                
                // linea = Nicolas|Sierra|Espol|nasierra@espol.edu.ec|claveNueva
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Vendedor u = new Vendedor(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                vendedores.add(u);
                
            }
                
        }
            
        
        catch(Exception e){
                System.out.println(e.getMessage());
        }
        return vendedores;
        
    
    }
    
    public void vMenu() throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) { 
            System.out.println("----- MENÚ VENDEDOR -----");
            System.out.println("1. Registrar un nuevo vendedor");
            System.out.println("2. Registrar un nuevo vehículo");
            System.out.println("3. Aceptar oferta");
            System.out.println("4. Regresar");
            System.out.println("--------------------------");
            System.out.println("Ingrese su opción:");

            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    crearVendedor();
                    break;
                case 2:
                    ingresarNuevoVehiculo();
                    break;
                case 3:
                    aceptarOferta();
                    break;
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
        sc.nextLine(); 

        System.out.println("Ingrese el recorrido del vehículo:");
        int recorrido = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Ingrese el color del vehículo:");
        String color = sc.nextLine();

        System.out.println("Ingrese el tipo de combustible del vehículo:");
        String tipoCombustible = sc.nextLine();
        
        System.out.println("Ingrese precio del vehiculo: ");
        double preciop = sc.nextDouble();

        if (tipoVehiculo.equalsIgnoreCase("auto") || tipoVehiculo.equalsIgnoreCase("camioneta")) {
            System.out.println("Ingrese el tipo de vidrios del vehículo:");
            String tipoVidrios = sc.nextLine();

            if (tipoVehiculo.equalsIgnoreCase("auto")) {
                System.out.println("Ingrese el tipo de transmisión del vehículo:");
                String tipoTransmision = sc.nextLine();

                Auto auto = new Auto(this.id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, preciop);
                auto.guardarInformacion();
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                System.out.println("Ingrese el tipo de tracción del vehículo:");
                String tipoTraccion = sc.nextLine();

                Camioneta camioneta = new Camioneta(this.id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, "", preciop, tipoTraccion);
                camioneta.guardarInformacion();
            }
        } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
            Auto moto = new Auto(this.id, placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, "", "", preciop);
            moto.guardarInformacion();
        }

        System.out.println("El vehículo se ha ingresado exitosamente.");
    }
    
    
    public void aceptarOferta() throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese su correo electrónico:");
        String correo = sc.nextLine();
        System.out.println("Ingrese su clave:");
        String clave = sc.nextLine();

        if (!validarCredenciales(correo, clave)) {
            System.out.println("Credenciales inválidas");
            return;
        }

        System.out.println("Ingrese la placa del vehículo:");
        String placa = sc.nextLine();

        ArrayList<Oferta> ofertas = leerOfertaPorPlaca("oferta.txt", placa);

        if (ofertas.isEmpty()) {
            System.out.println("No se encontraron ofertas para la placa de vehículo ingresada");
            return;
        }

        
        int indiceOfertaActual = 0;
        int cantidadOfertas = ofertas.size();
        boolean aceptarOferta = false;

        while (!aceptarOferta) {
            Oferta ofertaActual = ofertas.get(indiceOfertaActual);
            Vehiculo vehiculo = ofertaActual.getVehiculo();

            System.out.println("Placa: " + placa);
            if (vehiculo instanceof Auto) {
                Auto auto = (Auto) vehiculo;
                System.out.println(auto.getMarca() + " " + auto.getModelo() + " Precio: " + auto.getPrecio());
            } else if (vehiculo instanceof Camioneta) {
                Camioneta camioneta = (Camioneta) vehiculo;
                System.out.println(camioneta.getMarca() + " " + camioneta.getModelo() + " Precio: " + camioneta.getPrecio());
            }
            System.out.println("Se han realizado " + cantidadOfertas + " ofertas");
            System.out.println("Oferta " + (indiceOfertaActual + 1));
            System.out.println("Correo: " + ofertaActual.getComprador().getCorreo());
            System.out.println("Precio Ofertado: " + ofertaActual.getPrecioOfertado());
            System.out.println("1. Siguiente Oferta");
            System.out.println("2. Anterior Oferta");
            System.out.println("3. Aceptar Oferta");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    if (indiceOfertaActual < cantidadOfertas - 1) {
                        indiceOfertaActual++;
                    } else {
                        System.out.println("Ya no hay más ofertas disponibles");
                    }
                    break;
                case 2:
                    if (indiceOfertaActual > 0) {
                        indiceOfertaActual--;
                    } else {
                        System.out.println("Ya estás en la primera oferta");
                    }
                    break;
                case 3:
                    aceptarOferta(ofertaActual, placa);
                    aceptarOferta = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    private void aceptarOferta(Oferta oferta, String placa) {
        Vehiculo vehiculoEncontrado = null;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                vehiculoEncontrado = vehiculo;
                break;
            }
        }

        if (vehiculoEncontrado != null) {
            vehiculos.remove(vehiculoEncontrado);
            ofertas.remove(oferta);

            String destinatario = oferta.getComprador().getCorreo();
            String asunto = "Oferta aceptada";
            String cuerpo = "Su oferta por el vehículo con placa " + placa + " ha sido aceptada.";
            Utilitaria.enviarConGMail(destinatario, asunto, cuerpo);

            

            System.out.println("Se ha aceptado la oferta y se eliminó el vehículo del sistema");
        } else {
            System.out.println("No se encontró un vehículo con la placa ingresada");
        }
    }
    
    
      
     
     

}

    
    


    
