/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1.clases;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author eliez
 */
public class Auto extends Vehiculo {
    private String tipoVidrios;
    private String tipoTransmision;

    public Auto(String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, String tipoVidrios, String tipoTransmision, double precio) {
        super(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.tipoVidrios = tipoVidrios;
        this.tipoTransmision = tipoTransmision;
    }

    public Auto(String tipoVidrios, String tipoTransmision, String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio) {
        this(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible,null,null, precio);
        
    }

    

    

    public String getTipoVidrios() {
        return tipoVidrios;
    }

    public String getTipoTransmision() {
        return tipoTransmision;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Información del Auto:");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Tipo de motor: " + getTipoMotor());
        System.out.println("Año: " + getAnio());
        System.out.println("Recorrido: " + getRecorrido());
        System.out.println("Color: " + getColor());
        System.out.println("Tipo de combustible: " + getTipoCombustible());
        System.out.println("Tipo de vidrios: " + tipoVidrios);
        System.out.println("Tipo de transmisión: " + tipoTransmision);
        System.out.println("Precio: " + getPrecio());
    }

    @Override
    public void guardarInformacion() {
    String informacion = getPlaca() + "|" + getMarca() + "|" + getModelo() + "|" + getTipoMotor() + "|" + getAnio() + "|" + getRecorrido() + "|" + getColor() + "|" + getTipoCombustible() + "|" + getPrecio();

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehiculos.txt"), true))) {
            pw.println(informacion);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
   
   
    
    public static ArrayList<Auto> leerVehiculo(String archivoVehiculo) {
        ArrayList<Auto> autos = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(archivoVehiculo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] tokens = linea.split("\\|");

                String placa = tokens[0];
                String marca = tokens[1];
                String modelo = tokens[2];
                String tipoMotor = tokens[3];
                int anio = Integer.parseInt(tokens[4]);
                int recorrido = Integer.parseInt(tokens[5]);
                String color = tokens[6];
                String tipoCombustible = tokens[7];
                String tipoVidrios = tokens[8];
                String tipoTransmision = tokens[9];
                double precio = Double.parseDouble(tokens[10]);

                Auto auto = new Auto(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, precio);
                autos.add(auto);
            }
        
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
       

        return autos;
        }
        
    
}