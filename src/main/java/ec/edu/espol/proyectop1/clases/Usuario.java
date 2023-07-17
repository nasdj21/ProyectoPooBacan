package ec.edu.espol.proyectop1.clases;
import static ec.edu.espol.proyectop1.clases.Utilitaria.getSHA;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;



public class Usuario {
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String correo;
    protected String clave;

    
    public Usuario(int id, String nombres, String apellidos, String organizacion, String correo, String clave){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
    }
    
    public void saveFile(String nomFile){ //Para escribir y crear un archivo
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(this.id+"|"+this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo+"|"+this.clave);
        }
        catch(Exception e){ //lo que deberia hacer en el caso de que el archivo no existe
            System.out.println(e.getMessage());
        }
    }  
    
    public static ArrayList<Usuario> readFile(String nomfile) { //Para leer un archivo
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                
                // linea = Nicolas|Sierra|Espol|nasierra@espol.edu.ec|claveNueva
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Usuario u = new Usuario(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                usuarios.add(u);
                
            }
                
        }
            
        
        catch(Exception e){
                System.out.println(e.getMessage());
        }
        return usuarios;
        
    
    }
    
    public static void crearUsuario(String nomfile) throws NoSuchAlgorithmException{
        Scanner sc = new Scanner(System.in);
        ArrayList<Usuario>usuarios = Usuario.readFile(nomfile);
        
        int id = Utilitaria.nextID(nomfile);
        
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
      
        if(nomfile.equals("compradores.txt")){
            Usuario un = new Comprador(id, nombre, apellido, org,mail, Utilitaria.toHexString(getSHA(clav)));
            un.saveFile(nomfile);
        }
        else{
            Usuario un = new Vendedor(id, nombre, apellido, org,mail, Utilitaria.toHexString(getSHA(clav)));
            un.saveFile(nomfile);
        }
        
        
        
                        
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
    
        
    
}
