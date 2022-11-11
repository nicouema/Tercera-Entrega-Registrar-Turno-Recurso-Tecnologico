package Controladores;

import Interfaces.InterfazWhatsapp;
import Interfaces.InterfazMail;
import BaseDatos.Datos;
import Entidades.*;
import java.time.*;
import java.util.ArrayList;


public class GestorReservaTurno{
    
    //Constructor
    public GestorReservaTurno() {
    }
    
    //Atributos
    private ArrayList<RecursoTecnologico> recursosActivos = new ArrayList<>();
    private ArrayList<RecursoTecnologico> recursosDelTipo = new ArrayList<>();
    private RecursoTecnologico recursoSeleccionado;
    private PersonalCientifico cientificoEnSesion;
    private boolean esDelMismoCentro;
    private ArrayList<Turno> turnosPosteriores = new ArrayList<>();
    private Turno turnoSeleccionado;
    
    //Conexion a "base de datos"
    Datos datos = new Datos();
    
    
    //Empieza caso de uso en gestor
    //Metodo 3
    public ArrayList<String> tomarRegistrarReserva(){
        //Conexion a "base de datos"
        datos.generar();

        //Generar recursosActivos
        obtenerRecursosActivos(datos.recursos());
        //Obtener lista de tipos y enviar a la interfaz
        ArrayList<String> tipos = obtenerTipos();
        return tipos;
    }
    
    //Metodo 4
    //Obtener los recursos activos, recursos que no esten dados de Baja tecnica o Baja definitiva
    public void obtenerRecursosActivos(ArrayList<RecursoTecnologico> recursos){
        //Inicializar variable recursosActivos
        recursosActivos = new ArrayList<>();
        //Obtener los recursosActivos
        for(RecursoTecnologico recurso: recursos){
            if(recurso.esActivo()){
                recursosActivos.add(recurso);
            }
        }
    }
    
    //Metodo 9
    //Recorrer los recursos activos y genera una lista de tipos
    public ArrayList<String> obtenerTipos(){
        ArrayList<String> lista = new ArrayList<>();
        for(RecursoTecnologico recurso : recursosActivos){
            if (!lista.contains(recurso.obtenerTipo())){
                lista.add(recurso.obtenerTipo());
            }
        }
        return lista;
    }
    
    //Metodo 15
    //Recibe el tipo seleccionado, genera la lista de recursos del tipo para interfaz
    public ArrayList<ArrayList<String>> tomarTipoSeleccionado(String tipoSeleccionado){
        //En caso ser la opcion TODAS considera a todos los recursosActivos como recursosDelTipo y retorna esa lista ordenada y coloreada
        if (tipoSeleccionado == "TODAS"){
            //Lo mismo de fuera del if, abajo
            recursosDelTipo = recursosActivos;
            ordenarRecursosxCentro(recursosDelTipo);
            ArrayList<ArrayList<String>> recursosDelTipoDatos = obtenerDatosDeRecursosDelTipo();
            colorear(recursosDelTipoDatos);
            return recursosDelTipoDatos;
        }
        
        //Generar recursosDelTipo
        obtenerRecursosDelTipo(tipoSeleccionado);
        //Ordenar recursos por centro
        ordenarRecursosxCentro(recursosDelTipo);
        //Obtener los datos de los recursos del tipo
        ArrayList<ArrayList<String>> recursosDelTipoDatos = obtenerDatosDeRecursosDelTipo();
        //Colorear
        recursosDelTipoDatos = colorear(recursosDelTipoDatos);
        return recursosDelTipoDatos;
    }
    
    //Metodo 16
    public void obtenerRecursosDelTipo(String tipoSeleccionado){
        recursosDelTipo = new ArrayList<>();
        for(RecursoTecnologico recurso : recursosActivos){
            if(recurso.esDeTipo(tipoSeleccionado)){
                recursosDelTipo.add(recurso);
            }
        }
    }
    
    //Metodo 19
    public void ordenarRecursosxCentro(ArrayList<RecursoTecnologico> recursos){
        for(int i = 0; i < recursos.size(); i++){
            for(int j = i+1 ; j < recursos.size(); j++){
                if((recursos.get(i).miCentro(datos.centros())).compareTo(recursos.get(j).miCentro(datos.centros()))>0){
                    recursos.add(i,recursos.get(j));
                    recursos.remove(j+1);
                }
            }
        }
    }
    
    //Metodo 20
    public ArrayList<ArrayList<String>> obtenerDatosDeRecursosDelTipo(){
        ArrayList<ArrayList<String>> recursosDelTipoUI = new ArrayList<>();
        for (RecursoTecnologico recurso: recursosDelTipo){
            recursosDelTipoUI.add(recurso.obtenerDatos(datos.marcas(),datos.centros()));
        }
        return recursosDelTipoUI;
    }
    
    //Metodo 32
    //Colorear filas de una matriz segun su estado, agregar el color al inicio de la fila
    public ArrayList<ArrayList<String>> colorear(ArrayList<ArrayList<String>> matriz){
        for(ArrayList<String> fila : matriz){
            if(fila.get(0) == "Disponible"){
                fila.add(0, "Azul");
            }
            else if(fila.get(0) == "En mantenimiento"){
                fila.add(0, "Verde");
            }
            else if(fila.get(0) == "Con inicio de mantenimiento correctivo" || fila.get(0) == "Con reserva pendiente de confirmacion"){
                fila.add(0, "Gris");
            }
            else if(fila.get(0) == "No disponible" || fila.get(0) == "Reservado"){
                fila.add(0, "Rojo");
            }
        }
        return matriz;
    }   
    
    //Metodo 36
    public ArrayList<Object> tomarRecursoSeleccionado(int indice){
        recursoSeleccionado = recursosDelTipo.get(indice);
        //Obtener sesion activa
        Sesion sesionActiva = obtenerSesionActiva(datos.sesiones());
        //Obtener cientifico en sesion
        obtenerCientificoEnSesion(sesionActiva);
        //Validar si el cientifico es del mismo centro que el recurso
        validarEsDelMismoCentro();
        //Obtener tiempoDeAntelacionReserva del recurso seleccionado
        int tiempoAntelacionReserva = recursoSeleccionado.getTiempoAntelacionReserva(datos.centros());
        //Obtener la fechaHoraDesde
        LocalDateTime fechaHoraActual = obtenerFechaHoraActual();
        //Obtener fechaHoraDesde: fechaHoraActual + tiempoAntelacionReserva, si el cientifico es del centro no suma nada
        LocalDateTime fechaHoraDesde = obtenerFechaHoraDesde(fechaHoraActual, tiempoAntelacionReserva,esDelMismoCentro);
        //Obtener turnos a partir de fechaHoraDesde
        obtenerTurnosDesde(fechaHoraDesde);
        //Obtener datos de los turnos posteriores
        ArrayList<ArrayList<String>> turnosDatos = obtenerDatosTurnosPosteriores();
        //Generar listas de fechas/dias(de los turnos)
        ArrayList<Object> fechasTurnos = generarFechas(turnosDatos);
        //Agregar tambien el boolean en la devolucion para que interfaz informe si es recurso de un centro distinto
        fechasTurnos.add(esDelMismoCentro);
        return fechasTurnos;
    }
    
    //Metodo 37
    public Sesion obtenerSesionActiva(ArrayList<Sesion> sesiones){
        for(Sesion sesion: sesiones){
            if(sesion.esActiva())
                return sesion;
        }
        return null;
    }
    
    //Metodo 39
    public void obtenerCientificoEnSesion(Sesion sesion){
        cientificoEnSesion = sesion.obtenerCientificoEnSesion(datos.personal());
    }
    
    //Metodo 43
    public void validarEsDelMismoCentro(){
        esDelMismoCentro = recursoSeleccionado.esCientificoDelCentro(cientificoEnSesion,datos.centros());
    }
    
    //Metodo 49
    public LocalDateTime obtenerFechaHoraActual(){
        return LocalDateTime.now();
    }
    
    //Metodo 50
    public LocalDateTime obtenerFechaHoraDesde(LocalDateTime fecha, int tiempoAntelacion ,boolean esDelCentro){
        if(esDelCentro)
            return fecha;
        return fecha.plusDays(tiempoAntelacion);
    }
    
    //Metodo 51
    public void obtenerTurnosDesde(LocalDateTime fechaHoraDesde){
        turnosPosteriores = recursoSeleccionado.obtenerTurnosPosteriores(fechaHoraDesde);
    }
    
    //Metodo 52
    public ArrayList<ArrayList<String>> obtenerDatosTurnosPosteriores(){
        ArrayList<ArrayList<String>> h = new ArrayList<>();
        for(Turno turno: turnosPosteriores){
            h.add(turno.obtenerDatosTurno());
        }
        return h;
    }
    
    public ArrayList<Object> generarFechas(ArrayList<ArrayList<String>> turnos){
        ArrayList<String> fechas = new ArrayList<>();
        for(ArrayList<String> turno:turnos){
            if(!fechas.contains(turno.get(1)))
                fechas.add(turno.get(1));
        }
        ArrayList<ArrayList<ArrayList<String>>> turnosXDia = new ArrayList<>();
        ArrayList<ArrayList<String>> fe = new ArrayList<>();
        for (String fecha: fechas){
            ArrayList<Object> o = turnosXFecha(fecha,turnos);
            turnosXDia.add((ArrayList<ArrayList<String>>)o.get(0));
            ArrayList<String> gou = new ArrayList<>();
            if((boolean)o.get(1)){
                gou.add("Disponible");
            }else{
                gou.add("No disponible");
            }
            gou.add(fecha);
            fe.add(gou);
        }
        fe = colorear(fe);
        ArrayList<Object> date = new ArrayList<>();
        date.add(fe); date.add(turnosXDia);
        return date;
    }
    
    public ArrayList<Object> turnosXFecha(String fecha, ArrayList<ArrayList<String>> turnos){
        ArrayList<ArrayList<String>> b = new ArrayList<>();
        boolean esDisponible = false;
        for(ArrayList<String> turno: turnos){
            if(turno.get(1).compareTo(fecha)==0){
                b.add(turno);
                if(turno.get(0)=="Disponible"){
                    esDisponible = true;
                }
            }
        }
        ArrayList<Object> c = new ArrayList<>();
        b = colorear(b);
        c.add(b);
        c.add(esDisponible);
        return c;
    }

    public void tomarTurnoSeleccionado(LocalDateTime fechaHoraInicio){
        for (Turno turno: turnosPosteriores){
            if(turno.getFechaHoraInicio().compareTo(fechaHoraInicio)==0){
                turnoSeleccionado = turno;
                break;
            }
        }
    }

    public void tomarConfirmacionReserva(boolean checkMail, boolean checkWsp, String datosReserva){
//        Estado estadoReservado = obtenerReservado();
        registrarReserva();
        generarNotificaciones(checkMail,checkWsp, datosReserva);
        finCasoDeUso();
    }


    //TODO
    public void registrarReserva(){
        LocalDateTime fechaHoraActual = obtenerFechaHoraActual();
        turnoSeleccionado.reservarTurno(fechaHoraActual);
    }
    
    public void generarNotificaciones(boolean checkMail, boolean checkWsp, String datosReserva){
        if(checkMail){
            InterfazMail interfazMail = new InterfazMail();
            interfazMail.setVisible(true);
            interfazMail.enviarNotificacion(datosReserva,cientificoEnSesion.getCorreoElectronicoInstitucional());
        }
        if(checkWsp){
            InterfazWhatsapp interfazwsp = new InterfazWhatsapp();
            interfazwsp.setVisible(true);
            interfazwsp.enviarNotificacion(datosReserva,cientificoEnSesion.getTelefonoCelular());
        }
    }
    
    public void finCasoDeUso(){
        //Finalizar CU 23
    }
}