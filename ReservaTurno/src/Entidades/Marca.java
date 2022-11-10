package Entidades;
import java.util.ArrayList;

public class Marca {
    //Atributos
    private ArrayList<Modelo> modelos;
    private String nombre;

    public Marca() {
    }

    public Marca(ArrayList<Modelo> modelos, String nombre) {
        this.modelos = modelos;
        this.nombre = nombre;
    }

    public ArrayList<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(ArrayList<Modelo> modelos) {
        this.modelos = modelos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Metodo soporte para dependencia Modelo-Marca
    public boolean esTuModelo(Modelo modelo){
        if(modelos.contains(modelo))
            return true;
        return false;
    }
    
    //Empieza CU en Marca
    //Metodo 28
    public String getNombre() {
        return nombre;
    }
}
