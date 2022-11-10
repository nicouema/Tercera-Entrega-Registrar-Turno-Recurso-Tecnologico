package Entidades;
import java.time.*;
import java.util.ArrayList;

public class CentroDeInvestigacion {
    //Atributos
    private ArrayList<AsignacionCientificoDelCI> asignaciones;
    private ArrayList<RecursoTecnologico> recursos;
    private String nombre;
    private int tiempoAntelacionReserva;
    private String sigla;
    private String direccion;
    private String edificio;
    private int piso;
    private String coordenadas;
    private String telefonos;
    private String correo;
    private long numResolucion;
    private LocalDate fechaResolucion;
    private String reglamento;
    private String caracteristicas;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String motivoBaja;

    public CentroDeInvestigacion() {
    }

    public CentroDeInvestigacion(ArrayList<AsignacionCientificoDelCI> asignaciones, ArrayList<RecursoTecnologico> recursos, String nombre, int tiempoAntelacionReserva, String sigla, String direccion, String edificio, int piso, String coordenadas, String telefonos, String correo, long numResolucion, LocalDate fechaResolucion, String reglamento, String caracteristicas, LocalDate fechaAlta, LocalDate fechaBaja, String motivoBaja) {
        this.asignaciones = asignaciones;
        this.recursos = recursos;
        this.nombre = nombre;
        this.tiempoAntelacionReserva = tiempoAntelacionReserva;
        this.sigla = sigla;
        this.direccion = direccion;
        this.edificio = edificio;
        this.piso = piso;
        this.coordenadas = coordenadas;
        this.telefonos = telefonos;
        this.correo = correo;
        this.numResolucion = numResolucion;
        this.fechaResolucion = fechaResolucion;
        this.reglamento = reglamento;
        this.caracteristicas = caracteristicas;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.motivoBaja = motivoBaja;
    }

    public ArrayList<AsignacionCientificoDelCI> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(ArrayList<AsignacionCientificoDelCI> asignaciones) {
        this.asignaciones = asignaciones;
    }
    
    public ArrayList<RecursoTecnologico> getRecursos() {
        return recursos;
    }

    public void setRecursos(ArrayList<RecursoTecnologico> recursos) {
        this.recursos = recursos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempoAntelacionReserva(int tiempoAntelacionReserva) {
        this.tiempoAntelacionReserva = tiempoAntelacionReserva;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getNumResolucion() {
        return numResolucion;
    }

    public void setNumResolucion(long numResolucion) {
        this.numResolucion = numResolucion;
    }

    public LocalDate getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDate fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getReglamento() {
        return reglamento;
    }

    public void setReglamento(String reglamento) {
        this.reglamento = reglamento;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }
    
    //Metodo soporte para dependencia Centro-Recurso
    public boolean esTuRecurso(RecursoTecnologico recurso){
        if(recursos.contains(recurso))
            return true;
        return false;
    }
    
    //Empieza CU en CentroDeInvestigacion
    //Metodo 31
    public String getNombre() {
        return nombre;
    }
    
    //Metodo 45
    public boolean esCientificoDelCentro(PersonalCientifico cientifico){
        for(AsignacionCientificoDelCI asignacion : asignaciones){
            if(asignacion.esCientificoDelCentro(cientifico))
                return true;
        }
        return false;
    }
    
    //Metodo 48
    public int getTiempoAntelacionReserva() {
        return tiempoAntelacionReserva;
    }
}
