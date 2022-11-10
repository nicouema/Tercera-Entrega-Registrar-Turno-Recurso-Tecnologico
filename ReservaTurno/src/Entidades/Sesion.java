package Entidades;
import java.time.*;
import java.util.ArrayList;

public class Sesion {
    //Atributos
    LocalDateTime fechaHoraInicio;
    LocalDateTime fechaHoraFin;
    Usuario usuario;

    public Sesion() {
    }

    public Sesion(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Usuario usuario) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.usuario = usuario;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    //Empieza CU en Sesion
    //Metodo 38
    public boolean esActiva(){
        if(this.fechaHoraFin == null)
            return true;
        return false;
    }
    
    //Metodo 40
    public PersonalCientifico obtenerCientificoEnSesion(ArrayList<PersonalCientifico> personal){
        for(PersonalCientifico cientifico: personal){
            if(usuario.esTuCientifico(cientifico))
                return usuario.obtenerCientifico(cientifico);
        }
        return null;
    }
    
}
