package Entidades;

public class Usuario {
    //Atributos
    private String usuario;
    private String clave;
    private boolean habilitado;

    public Usuario() {
    }

    public Usuario(String usuario, String clave, boolean habilitado) {
        this.usuario = usuario;
        this.clave = clave;
        this.habilitado = habilitado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
    //Metodo 32
    public String getUsuario() {
        return usuario;
    }
    
    //Metodo de soporte de dependencia PersonalCientifico-Usuario
    public boolean esTuCientifico(PersonalCientifico cientifico){
        if(cientifico.esTuUsuario(this))
            return true;
        return false;
    }
    
    //Empieza CU en Usuario
    //Metodo 41
    public PersonalCientifico obtenerCientifico(PersonalCientifico cientifico){
        if(esTuCientifico(cientifico))
            return cientifico.getCientifico();
        return null;
    }
}
