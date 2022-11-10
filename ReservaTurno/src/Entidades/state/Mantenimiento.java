package Entidades.state;

public class Mantenimiento extends Estado{

    public Mantenimiento() {
    }

    public Mantenimiento(String nombre, String ambito) {
        super(nombre, ambito);
    }

    public Mantenimiento(String nombre, String descripcion, String ambito) {
        super(nombre, descripcion, ambito);
    }
}
