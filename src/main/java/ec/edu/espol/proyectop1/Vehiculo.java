
package ec.edu.espol.proyectop1;


public abstract class Vehiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private int anio;
    private int recorrido;
    private String color;
    private String tipoCombustible;
    private double precio;
    

    public Vehiculo(int id, String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio) {
        this.id = id;
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

    public int getId() {
        return id;
    }
    
    public String getTipo(){
        String g = this.getClass().getName();
        return g;
    }
    
    public Usuario getDueño(){
        for(Usuario u : Usuario.readListFromFileSer("usuarios.ser")){
            if(u.getId() == this.id)
                return u;
        }
        return null;
           
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

    public abstract void mostrarInformacion();

    public abstract void guardarInformacion(); 
    
    
   
}
    
