package Entidades.state;

import Entidades.CambioEstadoTurno;
import Entidades.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Disponible extends Estado{

    public Disponible(String nombre, String ambito) {
        super(nombre, ambito);
    }

    public Disponible(String nombre, String descripcion, String ambito) {
        super(nombre, descripcion, ambito);
    }

    @Override
    public void reservarTurno(Turno turno, ArrayList<CambioEstadoTurno> cambios, LocalDateTime fechaHoraActual) {
        for (CambioEstadoTurno cambio:cambios) {
            if (cambio.esActual()) cambio.setFechaHoraHasta(fechaHoraActual);
        }
        Reservado estadoActual = new Reservado("Reservado", "Turno");
        CambioEstadoTurno cambioActual = new CambioEstadoTurno(estadoActual, fechaHoraActual);
        turno.agregarCambio(cambioActual);
        turno.setEstado(estadoActual);
    }
}
