package Entidades;
import java.time.*;
import java.util.ArrayList;

public class AsignacionCientificoDelCI {
    //Atributos
    private ArrayList<Turno> turnos = new ArrayList<>();
    private PersonalCientifico personalCientifico;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    public AsignacionCientificoDelCI() {
    }

    public AsignacionCientificoDelCI(PersonalCientifico personalCientifico, LocalDate fechaDesde, LocalDate fechaHasta) {
        this.personalCientifico = personalCientifico;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    public PersonalCientifico getPersonalCientifico() {
        return personalCientifico;
    }

    public void setPersonalCientifico(PersonalCientifico personalCientifico) {
        this.personalCientifico = personalCientifico;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    
    //Empieza CU en AsignacionCientificoDelCI
    //Metodo 46
    public boolean esCientificoDelCentro(PersonalCientifico cientifico){
        if(cientifico == this.personalCientifico)
            return true;
        return false;
    }
}
