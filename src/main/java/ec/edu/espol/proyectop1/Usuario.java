package ec.edu.espol.proyectop1;
import static ec.edu.espol.proyectop1.Utilitaria.getSHA;
import ec.edu.espol.proyectop1.excepciones.CorreoExistenteException;
import ec.edu.espol.proyectop1.excepciones.UsuarioNoExisteException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;



public class Usuario implements Serializable{
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String correo;
    protected String clave;
    protected ArrayList<Vehiculo>vehiculos;

    
    public Usuario(int id, String nombres, String apellidos, String organizacion, String correo, String clave){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        vehiculos = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    
    
   
    
    
    
    //AVANCE DEL PROYECTO//
    
    public void saveSer(String nomfile){
        ArrayList<Usuario>usuarios = Usuario.readListFromFileSer(nomfile);
        usuarios.add(this);
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(usuarios);
        }
        catch(IOException e){
            System.out.println("Error al guardar el archivo de usuario: " + e.getMessage());
            
        }
        
        
    }
    
    public static ArrayList<Usuario> readListFromFileSer(String nombreArchivo) {
        ArrayList<Usuario> usuario = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            usuario = (ArrayList<Usuario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo de usuario: " + e.getMessage());
        }
        return usuario;
    }
    
    
    
    //validar el usuario con el correo electronico
    public static Usuario usuarioExiste(String correo, String clave, String archivoSerializado) throws UsuarioNoExisteException {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSer(archivoSerializado);
        for (Usuario u : usuarios) {
            if (correo.equals(u.getCorreo()) && clave.equals(u.getClave())) {
                return u; // El usuario existe
            }
        }
       throw new UsuarioNoExisteException("El usuario con correo " + correo + " y clave proporcionada no existe."); 
    }
    
    public static boolean correoYaExiste(String correo, ArrayList<Usuario> usuarios) throws CorreoExistenteException {
        for (Usuario u : usuarios) {
            if (correo.equals(u.getCorreo())) {
                throw new CorreoExistenteException("El correo electrónico ya está registrado.");
            }
        }
        return false;
    }


    
    ////////////////////////////////////////////////
    
  
    

    
    
        
    
}