package Entidades;
import Entidades.state.Disponible;
import Entidades.state.Estado;

import java.time.*;
import java.util.ArrayList;

public class RecursoTecnologico{
    //Atributos
    private ArrayList<CambioEstadoRT> cambiosDeEstadosRT;
    private ArrayList<Turno> turnos;
    private TipoRecursoTecnologico tipo;
    private Modelo modelo;
    private int numeroRT;
    private LocalDate fechaAlta;
    private int periodicidadMantenimientoPrev;
    private int duracionMantenimientoPrev;
    private int fraccionHorarioTurnos;
    private Estado estadoActual;
    
    public RecursoTecnologico() {
    }

    public RecursoTecnologico(ArrayList<CambioEstadoRT> cambiosDeEstadosRT, ArrayList<Turno> turnos, TipoRecursoTecnologico tipo, Modelo modelo, int numeroRT, LocalDate fechaAlta, int periodicidadMantenimientoPrev, int duracionMantenimientoPrev, int fraccionHorarioTurnos) {
        this.cambiosDeEstadosRT = cambiosDeEstadosRT;
        this.turnos = turnos;
        this.tipo = tipo;
        this.modelo = modelo;
        this.numeroRT = numeroRT;
        this.fechaAlta = fechaAlta;
        this.periodicidadMantenimientoPrev = periodicidadMantenimientoPrev;
        this.duracionMantenimientoPrev = duracionMantenimientoPrev;
        this.fraccionHorarioTurnos = fraccionHorarioTurnos;
        this.estadoActual = new Disponible("Disponible", "Recurso Tecnologico");
        
    }

    public ArrayList<CambioEstadoRT> getCambiosDeEstadosRT() {
        return cambiosDeEstadosRT;
    }

    public void setCambiosDeEstadosRT(ArrayList<CambioEstadoRT> cambiosDeEstadosRT) {
        this.cambiosDeEstadosRT = cambiosDeEstadosRT;
    }
    
    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    public TipoRecursoTecnologico getTipoRT() {
        return tipo;
    }

    public void setTipoRT(TipoRecursoTecnologico tipoRT) {
        this.tipo = tipoRT;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setNumeroRT(int numeroRT) {
        this.numeroRT = numeroRT;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getPeriodicidadMantenimientoPrev() {
        return periodicidadMantenimientoPrev;
    }

    public void setPeriodicidadMantenimientoPrev(int periodicidadMantenimientoPrev) {
        this.periodicidadMantenimientoPrev = periodicidadMantenimientoPrev;
    }

    public int getDuracionMantenimientoPrev() {
        return duracionMantenimientoPrev;
    }

    public void setDuracionMantenimientoPrev(int duracionMantenimientoPrev) {
        this.duracionMantenimientoPrev = duracionMantenimientoPrev;
    }

    public int getFraccionHorarioTurnos() {
        return fraccionHorarioTurnos;
    }

    public void setFraccionHorarioTurnos(int fraccionHorarioTurnos) {
        this.fraccionHorarioTurnos = fraccionHorarioTurnos;
    }
    
    //Empieza CU en RecursoTecnologico
    
    //Metodo 5
    //Verificar si el recurso esta activo, NO BAJA TECNICA, NO BAJA DEFINITIVA
    public boolean esActivo(){
        for (CambioEstadoRT cambio:this.cambiosDeEstadosRT){
            if(cambio.esActual())
                return cambio.esActivo();
        }
        return false;
    }
    
    //Metodo 10
    //Obtener el nombre del tipo
    public String obtenerTipo(){
        return this.tipo.getNombre();
    }
    
    //Metodo 17
    //Validar si es del tipo seleccionado
    public boolean esDeTipo(String tipoSeleccionado){
        if (this.tipo.esDeTipo(tipoSeleccionado))
            return true;
        return false;
    }
    
    //Metodo 21
    public ArrayList<String> obtenerDatos(ArrayList<Marca> marcas, ArrayList<CentroDeInvestigacion> centros){
        ArrayList<String> b = new ArrayList<>();
        //Estado
        String estado ="";
        for (CambioEstadoRT cambio:this.cambiosDeEstadosRT){
            if(cambio.esActual())
                estado = cambio.obtenerEstado();
        }
        //Numero
        String numero = String.valueOf(getNumero());
        //Marca
        String marca = miMarca(marcas);
        //Modelo
        String model = this.modelo.getNombre();
        //Centro
        String centro = miCentro(centros);
        b.add(estado); b.add(numero); b.add(marca); b.add(model); b.add(centro);
        return b;
    }
    
    //Metodo 25
    public int getNumero(){
        return numeroRT;
    }
    
    //Metodo 26
    public String miMarca(ArrayList<Marca> marcas){
        for(Marca marca:marcas){
            if(modelo.esTuMarca(marca))
                return marca.getNombre();
        }
        return "";
    }
    
    //Metodo 30
    public String miCentro(ArrayList<CentroDeInvestigacion> centros){
        //recorro la lista de centros y le pregunto al centro SI SOY recurso del centro
        for(CentroDeInvestigacion centro: centros){
            if(esMiCentro(centro)){
                return centro.getNombre();
            }
        }
        return "";
    }
    
    //Metodo 44
    public boolean esCientificoDelCentro(PersonalCientifico cientifico, ArrayList<CentroDeInvestigacion> centros){
        if(getCentro(centros).esCientificoDelCentro(cientifico))
            return true;
        return false;
    }
    
    //Metodo 47
    public int getTiempoAntelacionReserva(ArrayList<CentroDeInvestigacion> centros){
        return getCentro(centros).getTiempoAntelacionReserva();
    }
    
    //Metodos soporte de dependencia Centro-Recurso
    public CentroDeInvestigacion getCentro(ArrayList<CentroDeInvestigacion> centros){
        for(CentroDeInvestigacion centro: centros){
            if(esMiCentro(centro)){
                return centro;
            }
        }
        return null;
    }
    
    public boolean esMiCentro(CentroDeInvestigacion centro){
        if(centro.esTuRecurso(this))
            return true;
        return false;
    }
    
    //Metodo 52
    public ArrayList<Turno> obtenerTurnosPosteriores(LocalDateTime fechaHoraDesde){
        ArrayList<Turno> turnosPosteriores = new ArrayList<>();
        for(Turno turno:turnos){
            if(turno.esPosterior(fechaHoraDesde))
                turnosPosteriores.add(turno);
        }
        return turnosPosteriores;
    }
}