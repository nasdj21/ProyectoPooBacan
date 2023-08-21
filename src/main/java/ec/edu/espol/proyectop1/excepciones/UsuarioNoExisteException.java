/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectop1.excepciones;

/**
 *
 * @author eliez
 */
public class UsuarioNoExisteException extends Exception{
    public UsuarioNoExisteException(String mensaje) {
        super(mensaje);
    }
}

