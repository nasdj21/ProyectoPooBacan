
package ec.edu.espol.proyectop1;

import ec.edu.espol.proyectop1.excepciones.placaExisteException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;



public class Vehiculo implements Serializable {
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private int anio;
    private int recorrido;
    private String color;
    private String tipoCombustible;
    private double precio;
    private Usuario usuario;

    public Vehiculo(Usuario usuario,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio) {
        this.usuario = usuario;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getTipo(){
        String g = this.getClass().getName();
        return g;
    }
    
    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public int getAnio() {
        return anio;
    }

    public int getRecorrido() {
        return recorrido;
    }

    public String getColor() {
        return color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }

    
    
    
    public void guardarInfoSer(String nomfile){
        ArrayList<Vehiculo>vehiculos = Vehiculo.leerInfoSer(nomfile);
        vehiculos.add(this);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(vehiculos);
        }
        catch(IOException e){
            System.out.println("Error al guardar el archivo de vehiculo: " + e.getMessage());
            
        }
        
        
    }
    
    public static ArrayList<Vehiculo> leerInfoSer(String nombreArchivo) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            vehiculos = (ArrayList<Vehiculo>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo de vehiculo: " + e.getMessage());
        }
        return vehiculos;
    }
    public static boolean validarPlacaExistente(String placa, String archivo) throws placaExisteException{
    ArrayList<Vehiculo> vehiculos = Vehiculo.leerInfoSer(archivo);

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa))
                return true;
            
        }
        return false; 
    }

    public static ArrayList<Vehiculo> filtrarVehiculoPorUsuario(Usuario usuario, String nombreArchivo) {
        ArrayList<Vehiculo> vehiculos = leerInfoSer(nombreArchivo);
        ArrayList<Vehiculo> vehiculosDelUsuario = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getUsuario() != null && vehiculo.getUsuario().getCorreo().equals(usuario.getCorreo())) {
                vehiculosDelUsuario.add(vehiculo);
            }
        }

        return vehiculosDelUsuario;
    }

   
}
    
