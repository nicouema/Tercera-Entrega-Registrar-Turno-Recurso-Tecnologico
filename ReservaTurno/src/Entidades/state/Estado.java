package Entidades.state;

import Entidades.CambioEstadoTurno;
import Entidades.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Estado {
    //Atributos
    private String nombre;
    private String descripcion;
    private String ambito;
//    private boolean esReservable;
//    private boolean esCancelable;

    public Estado() {
    }
    public Estado(String nombre, String ambito){
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public Estado(String nombre, String descripcion, String ambito) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ambito = ambito;
//        this.esReservable = esReservable;
//        this.esCancelable = esCancelable;
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

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

//    public boolean isEsReservable() {
//        return esReservable;
//    }

//    public void setEsReservable(boolean esReservable) {
//        this.esReservable = esReservable;
//    }

//    public boolean isEsCancelable() {
//        return esCancelable;
//    }

//    public void setEsCancelable(boolean esCancelable) {
//        this.esCancelable = esCancelable;
//    }
    
    //Empieza CU en Estado
    //Metodo 8
    //Verificar si el estado es activo, NO BAJA TECNICA, NI BAJA DEFINITIVA
    public boolean esActivo(){
        if (this.nombre.equals("Baja tecnica") || this.nombre.equals("Baja definitiva"))
            return false;
        return true;
    }
//
    //Metodo 24
    public String getNombre() {
        return nombre;
    }
    
    public void  reservarConConfirmacionPendiente(){
        throw new RuntimeException("Metodo no soportado");
    }

    
    //registrarInicioUsoReserva()
    public void iniciarUsoReserva(){
        throw new RuntimeException("Metodo no soportado");
    }
    public void finalizarUsoReserva(){
        throw new RuntimeException("Metodo no soportado");
    }
    public void registrarReservaSinUso(){
        throw new RuntimeException("Metodo no soportado");
    }
    public void cancelarTurnoPorMantenimientoRT(){
        throw new RuntimeException("Metodo no soportado");
    }
    public void registrarTurnoSinUso()
    {
        throw new RuntimeException("Metodo no soportado");
    }
//
//    public boolean esAmbitoTurno(){
//        if(this.ambito.equals("Turno"))
//            return true;
//        return false;
//    }
//
//    public boolean esReservado(){
//        if(this.nombre.equals("Reservado"))
//            return true;
//        return false;
//    }

//    METODO POLIMORFICO
    public void reservarTurno(Turno turno, ArrayList<CambioEstadoTurno> cambios, LocalDateTime fechaHoraActual){
        throw new RuntimeException("Metodo no soportado");
    }

    public Estado crearEstado(){
        throw new RuntimeException("Metodo no soportado");
    }

    public CambioEstadoTurno crearCambioEstadoTurno(Estado estado, LocalDateTime fechaHoraActual) {
        throw new RuntimeException("Metodo no soportado");
    }
}
