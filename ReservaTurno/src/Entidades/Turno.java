package Entidades;
import Entidades.state.Disponible;
import Entidades.state.Estado;
import Entidades.state.Reservado;

import java.time.*;
import java.util.ArrayList;

public class Turno {
    //Atributos
    private ArrayList<CambioEstadoTurno> cambiosDeEstadosTurno = new ArrayList<>();
    private LocalDate fechaGeneracion;
    private String diaSemana;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    private Estado estadoActual;

    public Turno() {
    }

    public Turno(ArrayList<CambioEstadoTurno> cambiosDeEstadosTurno, LocalDate fechaGeneracion, String diaSemana, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.cambiosDeEstadosTurno = cambiosDeEstadosTurno;
        this.fechaGeneracion = fechaGeneracion;
        this.diaSemana = diaSemana;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estadoActual = new Disponible("Disponible", "Turno");
    }

    public ArrayList<CambioEstadoTurno> getCambiosDeEstadosTurno() {
        return cambiosDeEstadosTurno;
    }

    public void setCambiosDeEstadosTurno(ArrayList<CambioEstadoTurno> cambiosDeEstadosTurno) {
        this.cambiosDeEstadosTurno = cambiosDeEstadosTurno;
    }
    
    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalDateTime getFechaHoraInicio() {
        return this.fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    
    //Empieza CU en Turno
    public ArrayList<String> obtenerDatosTurno(){
        ArrayList<String> b = new ArrayList<>();
        String estado = "";
//        String estado = estadoActual.getNombre();
        for(CambioEstadoTurno cambio: cambiosDeEstadosTurno){
            if(cambio.esActual())
                estado = cambio.obtenerEstado();
        }
        String[] split = String.valueOf(fechaHoraInicio).split("T"); String[] fechaSplit = split[0].split("-");String[] tiempoSplit = split[1].split(":");
        String fecha = fechaSplit[2]+"-"+fechaSplit[1]+"-"+fechaSplit[0];
        String horaInicio = tiempoSplit[0]+":"+tiempoSplit[1];

        String[] split2 = String.valueOf(fechaHoraFin).split("T"); String[] finSplit = split2[1].split(":");
        String horaFin = finSplit[0]+":"+finSplit[1];
        
        b.add(estado);
        b.add(fecha);
        b.add(horaInicio);
        b.add(horaFin);
        return b;
    }
    
    public boolean esPosterior(LocalDateTime fechaHoraDesde){
        if(fechaHoraDesde.isBefore(this.getFechaHoraInicio()))
            return true;
        return false;
    }
    public void agregarCambio(CambioEstadoTurno cambioActual){
        cambiosDeEstadosTurno.add(cambioActual);
    }
    
    public void reservarTurno(LocalDateTime fechaHoraActual){
//        for(CambioEstadoTurno cambio: cambiosDeEstadosTurno){
//            if(cambio.esActual())
//                cambio.setFechaHoraHasta(fechaHoraActual);
//        }
//        CambioEstadoTurno cet = new CambioEstadoTurno(estadoReservado,fechaHoraActual,null);
//        cambiosDeEstadosTurno.add(cet);
        this.estadoActual.reservarTurno(this, cambiosDeEstadosTurno, fechaHoraActual);
    }

    public void setEstado(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }
}