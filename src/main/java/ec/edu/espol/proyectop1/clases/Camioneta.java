/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1.clases;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
/**
 *
 * @author eliez
 */
public class Camioneta extends Auto {
    private String tipoTraccion;

    public Camioneta(String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, String tipoVidrios, String tipoTransmision, String tipoTraccion, double precio) {
        super(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, tipoVidrios, tipoTransmision, precio);
        this.tipoTraccion = tipoTraccion;
    }

    public String getTipoTraccion() {
        return tipoTraccion;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo de tracción: " + tipoTraccion);
    }

    @Override
    public void guardarInformacion() {
        String informacion = getPlaca() + "|" + getMarca() + "|" + getModelo() + "|" + getTipoMotor() + "|" + getAnio() + "|" + getRecorrido() + "|" + getColor() + "|" + getTipoCombustible() + "|" + getTipoVidrios() + "|" + getTipoTransmision() + "|" + tipoTraccion + "|" + getPrecio();

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehiculos.txt"), true))) {
            pw.println(informacion);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
