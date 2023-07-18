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
                    Comprador.encontrarComprador().crearOferta(); 
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
    public void crearOferta() throws NoSuchAlgorithmException{
        this.ofertarVehiculo();
        
        
    }
    public static Comprador encontrarComprador() throws NoSuchAlgorithmException{
        Scanner sc = new Scanner(System.in);
        ArrayList<Comprador>compradores = Comprador.leerComprador("compradores.txt");
        System.out.println("Ingrese correo");
        String mail = sc.next();
        System.out.println("Ingrese clave");
        String clave = sc.next();
        for(Comprador c : compradores){
            if(c.getCorreo().equals(mail) && c.getClave().equals(Utilitaria.toHexString(getSHA(clave)))){
                return c;
            }
            else{
                System.out.println("Comprador no existente");
                return null;
            }
        }
        return null;
    }
    
    public static Comprador encontrarComprador(String correo) throws NoSuchAlgorithmException{
        ArrayList<Comprador>compradores = Comprador.leerComprador("compradores.txt");
        for(Comprador c : compradores){
            if(correo.equals(c.getCorreo()))
                return c;
            else
                return null;
        }
        return null;
        
    }
    
    public void ofertar(Vehiculo v){
        Scanner sc = new Scanner (System.in);
        System.out.println("¿Cuanto desea ofertar?");
        double valorOfertado = sc.nextDouble();
        Oferta oferta = new Oferta(this, v.getDueño(), v, valorOfertado);
        oferta.guardarOferta("ofertas.txt");
        this.ofertas.add(oferta);
    }
    
    public void ofertarVehiculo() throws NoSuchAlgorithmException{
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
                        Comprador.encontrarComprador().ofertar(lista.get(i));
                        break;
                    case 2:
                        if(i < lista.size()-1)
                            i++;
                        break;
                    case 3:
                        if(i > 0)
                            i--;
                        break;
                    case 4:
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
                    if(!(au.getTipoVidrios() == (null)) && Integer.parseInt(r[0]) <= au.getRecorrido() && Integer.parseInt(r[1]) >= au.getRecorrido() && Integer.parseInt(a[0]) <= au.getAnio() && Integer.parseInt(a[1]) >= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio())
                        fin.add(au); 
                    else
                        System.out.println("No se ha encontrado vehiculo con sus especificaciones");
                }   
            } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
                
                for(Auto au : vehiculos) {
                
                    if(au.getTipoVidrios() == null && Integer.parseInt(r[0]) <= au.getRecorrido() && Integer.parseInt(r[1]) >= au.getRecorrido() && Integer.parseInt(a[0]) <= au.getAnio() && Integer.parseInt(a[1]) >= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio())
                        fin.add(au);
                    else
                        System.out.println("No se ha encontrado vehiculo con sus especificaciones");

                }
                
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                
                for(Camioneta ca : camionetas) {
                    
                    if(Integer.parseInt(r[0]) <= ca.getRecorrido() && Integer.parseInt(r[1]) >= ca.getRecorrido() && Integer.parseInt(a[0]) <= ca.getAnio() && Integer.parseInt(a[1]) >= ca.getAnio() && Double.parseDouble(p[0]) <= ca.getPrecio() && Double.parseDouble(p[1]) >= ca.getPrecio())
                        fin.add(ca);
                    else
                        System.out.println("No se ha encontrado vehiculo con sus especificaciones");

                    
                }
                 
     
            }else
                
                System.out.println("Tipo de vehículo no válido. Intente nuevamente.");
                
            
                
           
                
         } while (!tipoVehiculo.equalsIgnoreCase("auto") && !tipoVehiculo.equalsIgnoreCase("moto") && !tipoVehiculo.equalsIgnoreCase("camioneta"));
        return fin;
        
            }
    
    
}
