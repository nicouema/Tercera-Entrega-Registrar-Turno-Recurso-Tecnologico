package Entidades.state;

public class CanceladoMantenimientoCorrectivo extends Estado{

    public CanceladoMantenimientoCorrectivo(String nombre, String ambito) {
        super(nombre, ambito);
    }

    public CanceladoMantenimientoCorrectivo(String nombre, String descripcion, String ambito) {
        super(nombre, descripcion, ambito);
    }
}
