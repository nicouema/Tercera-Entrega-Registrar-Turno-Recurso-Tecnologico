package Entidades;

public class TipoRecursoTecnologico {
    //Atributos
    private String nombre;
    private String descripcion;

    public TipoRecursoTecnologico() {
    }

    public TipoRecursoTecnologico(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    //Empieza CU en TipoRecursoTecnologico
    //Metodo 11
    public String getNombre() {
        return nombre;
    }
    
    //Metodo 18
    public  boolean esDeTipo(String tipo){
        if(this.nombre == tipo)
            return true;
        return false;
    }
}
