package Entidades.state;

public class Disponible extends Estado{

    public Disponible(String nombre, String ambito) {
        super(nombre, ambito);
    }

    public Disponible(String nombre, String descripcion, String ambito) {
        super(nombre, descripcion, ambito);
    }
}
