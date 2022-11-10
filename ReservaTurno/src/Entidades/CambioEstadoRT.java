package Entidades;

import Entidades.state.Estado;

import java.time.*;

public class CambioEstadoRT {
    //Atributos
    private Estado estado;
    private LocalDateTime fechaHoraDesde;
    private LocalDateTime fechaHoraHasta;

    public CambioEstadoRT() {
    }

    public CambioEstadoRT(Estado estado, LocalDateTime fechaHoraDesde, LocalDateTime fechaHoraHasta) {
        this.estado = estado;
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
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
    
    //Empieza CU en CambioEstadoRT
    
    //Metodo 6 - Metodo 22
    //Verificar si es el cambio actual
    public boolean esActual(){
        if(this.fechaHoraHasta == null)
            return true;
        return false;
    }
    
    //Metodo 7
    //Verificar si el estado es activo
//    public boolean esActivo(){
//        return estado.esActivo();
//    }
    
    //Metodo 23
    public String obtenerEstado(){
        return estado.getNombre();
    }
    
}
