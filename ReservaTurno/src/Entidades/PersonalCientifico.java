package Entidades;

public class PersonalCientifico {
    //Atributos
    private Usuario usuario;
    private int legajo;
    private String nombre;
    private String apellido;
    private int numeroDocumento;
    private String correoElectronicoInstitucional;
    private String correoElectronicoPersonal;
    private String telefonoCelular;

    public PersonalCientifico() {
    }

    public PersonalCientifico(Usuario usuario, int legajo, String nombre, String apellido, int numeroDocumento, String correoElectronicoInstitucional, String correoElectronicoPersonal, String telefonoCelular) {
        this.usuario = usuario;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDocumento = numeroDocumento;
        this.correoElectronicoInstitucional = correoElectronicoInstitucional;
        this.correoElectronicoPersonal = correoElectronicoPersonal;
        this.telefonoCelular = telefonoCelular;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCorreoElectronicoInstitucional() {
        return correoElectronicoInstitucional;
    }

    public void setCorreoElectronicoInstitucional(String correoElectronicoInstitucional) {
        this.correoElectronicoInstitucional = correoElectronicoInstitucional;
    }

    public String getCorreoElectronicoPersonal() {
        return correoElectronicoPersonal;
    }

    public void setCorreoElectronicoPersonal(String correoElectronicoPersonal) {
        this.correoElectronicoPersonal = correoElectronicoPersonal;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }
    

    public String miUsuario(){
        return this.usuario.getUsuario();
    }
    
    //Metodo de soporte de dependencia Usuario-PersonalCientifico
    public boolean esTuUsuario(Usuario usuario){
        if(usuario == this.usuario)
            return true;
        return false;
    }
    
    //Empieza CU en PersonalCientifico
    //Metodo 42
    public PersonalCientifico getCientifico(){
        return this;
    }
}
