package ec.edu.espol.proyectop1.clases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
    private final double precioOfertado;

    
   
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
    
    public void guardarOferta(String nomFile){ //Para escribir y crear un archivo
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(this.comprador+"|"+this.vendedor+"|"+this.vehiculo+"|"+this.precioOfertado);
        }
        catch(Exception e){ //lo que deberia hacer en el caso de que el archivo no existe
            System.out.println(e.getMessage());
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
            tipoVehiculo = sc.nextLine();
            

            if (tipoVehiculo.equalsIgnoreCase("auto")) {
               
                for(Auto au : vehiculos) {
                    if(Integer.parseInt(r[0]) >= au.getRecorrido() && Integer.parseInt(r[1]) <= au.getRecorrido() && Integer.parseInt(a[0]) >= au.getAnio() && Integer.parseInt(a[1]) <= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio())
                        fin.add(au);  
                }   
            } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
                
                for(Auto au : vehiculos) {
                
                    if(au.getTipoVidrios() == null && Integer.parseInt(r[0]) >= au.getRecorrido() && Integer.parseInt(r[1]) <= au.getRecorrido() && Integer.parseInt(a[0]) >= au.getAnio() && Integer.parseInt(a[1]) <= au.getAnio() && Double.parseDouble(p[0]) <= au.getPrecio() && Double.parseDouble(p[1]) >= au.getPrecio())
                        fin.add(au);
                }
                
            } else if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                
                for(Camioneta ca : camionetas) {
                    
                    if(Integer.parseInt(r[0]) >= ca.getRecorrido() && Integer.parseInt(r[1]) <= ca.getRecorrido() && Integer.parseInt(a[0]) >= ca.getAnio() && Integer.parseInt(a[1]) <= ca.getAnio() && Double.parseDouble(p[0]) <= ca.getPrecio() && Double.parseDouble(p[1]) >= ca.getPrecio())
                        fin.add(ca);
                    
                }
                 
     
            }else
                
                System.out.println("Tipo de vehículo no válido. Intente nuevamente.");
                
            
                
           
                
         } while (!tipoVehiculo.equalsIgnoreCase("auto") && !tipoVehiculo.equalsIgnoreCase("moto") && !tipoVehiculo.equalsIgnoreCase("camioneta"));
        return fin;
        
            }
    
    public void ofertarVehiculo(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Vehiculo>lista = filtrarVehiculo();
        for(int i = 0; i < lista.size(); i++){
            int opcion = 0;
            while(opcion != 3){
                lista.get(i).mostrarInformacion();
                System.out.println("1. Ofertar");
                System.out.println("2. Siguiente");
                System.out.println("3. Anterior");
                
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
    
    public void ofertar(Vehiculo v){
        Scanner sc = new Scanner (System.in);
        System.out.println("¿Cuanto desea ofertar?");
        double valorOfertado = sc.nextDouble();
        Oferta oferta = new Oferta(this.comprador, v.getDueño(), v, valorOfertado);
        oferta.guardarOferta("ofertas.txt");
    }
 }


            
                
        
                
 
