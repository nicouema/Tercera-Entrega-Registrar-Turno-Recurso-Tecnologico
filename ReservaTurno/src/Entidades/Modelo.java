package Entidades;

public class Modelo {
    //Atributos
    private String nombre;

    public Modelo() {
    }

    public Modelo(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Metodo soporte para dependencia Modelo-Marca
    public boolean esTuMarca(Marca marca){
        if(marca.esTuModelo(this))
            return true;
        return false;
    }
    
    //Empieza CU en Modelo
    //Metodo 27
    public String obtenerMarca(Marca marca){
        if(marca.esTuModelo(this))
            return marca.getNombre();
        return "";
    }
    
    //Metodo 29
    public String getNombre() {
        return nombre;
    }
}
