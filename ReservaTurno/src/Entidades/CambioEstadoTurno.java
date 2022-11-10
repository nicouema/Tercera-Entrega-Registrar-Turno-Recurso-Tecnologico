package Entidades;

import Entidades.state.Estado;

import java.time.*;

public class CambioEstadoTurno {
    //Atributos
    private Estado estado;
    private LocalDateTime fechaHoraDesde;
    private LocalDateTime fechaHoraHasta;

    public CambioEstadoTurno() {
    }

    public CambioEstadoTurno(Estado estado, LocalDateTime fechaHoraDesde, LocalDateTime fechaHoraHasta) {
        this.estado = estado;
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public CambioEstadoTurno(Estado estado, LocalDateTime fechaHoraDesde) {
        this.estado = estado;
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public void setFechaHoraDesde(LocalDateTime fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public LocalDateTime getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public void setFechaHoraHasta(LocalDateTime fechaHoraHasta) {
        this.fechaHoraHasta = fechaHoraHasta;
    }
    
    //Empieza CU en CambioEstadoTurno
    public boolean esActual(){
        if(fechaHoraHasta == null)
            return true;
        return false;
    }
    
    public String obtenerEstado(){
        return this.estado.getNombre();
    }
}

