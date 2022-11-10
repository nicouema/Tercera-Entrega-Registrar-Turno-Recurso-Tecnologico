package BaseDatos;

import Entidades.*;
import Entidades.state.CanceladoMantenimientoCorrectivo;
import Entidades.state.Disponible;
import Entidades.state.Estado;
import Entidades.state.Mantenimiento;

import java.time.*;
import java.util.ArrayList;

public class Datos {
    private ArrayList<TipoRecursoTecnologico> tipos = new ArrayList<>();
    private ArrayList<Modelo> modelos = new ArrayList<>();
    private ArrayList<Marca> marcas = new ArrayList<>();
    private ArrayList<Estado> estados = new ArrayList<>();
    private ArrayList<RecursoTecnologico> recursos = new ArrayList<>();
    private ArrayList<CentroDeInvestigacion> centros = new ArrayList<>();
    private ArrayList<Turno> turnos = new ArrayList<>();
    private ArrayList<Sesion> sesiones = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<PersonalCientifico> personal = new ArrayList<>();
    private ArrayList<AsignacionCientificoDelCI> asignaciones = new ArrayList<>();
    
    public Datos() {
    }
    
    public void generar(){
        //Tipos
        TipoRecursoTecnologico tipo1= new TipoRecursoTecnologico("Microscopio","Equipo que permite ver objetos diminutos.");
        TipoRecursoTecnologico tipo2= new TipoRecursoTecnologico("Telescopio","Equipo que permite ver objetos lejanos.");
        TipoRecursoTecnologico tipo3= new TipoRecursoTecnologico("Resonador Magnetico","Equipo que obtiene imagenes del cuerpo en dos y tres dimensiones.");
        TipoRecursoTecnologico tipo4= new TipoRecursoTecnologico("Computador","Equipo informatico");
        tipos.add(tipo1);
        tipos.add(tipo2);
        tipos.add(tipo3);
        tipos.add(tipo4);
        
        //Modelos
        Modelo modelo1= new Modelo("FX");
        Modelo modelo2= new Modelo("FY");
        Modelo modelo3= new Modelo("FZ");
        Modelo modelo4= new Modelo("GX");
        Modelo modelo5= new Modelo("GY");
        Modelo modelo6= new Modelo("GZ");
        
        modelos.add(modelo1);
        modelos.add(modelo2);
        modelos.add(modelo3);
        modelos.add(modelo4);
        modelos.add(modelo5);
        modelos.add(modelo6);
        
        //Marca
        ArrayList<Modelo> a = new ArrayList<>(); 
        a.add(modelo1);
        a.add(modelo2);
        a.add(modelo3);
        
        ArrayList<Modelo> b = new ArrayList<>(); 
        b.add(modelo4);
        b.add(modelo5);
        b.add(modelo6);
        
        Marca marca1= new Marca(a,"FORE");
        Marca marca2= new Marca(b,"GORE");
        
        marcas.add(marca1);
        marcas.add(marca2);
        
        //Estados
        //Estados de RT
        Estado e1 = new Disponible("Disponible","Recurso");
        Estado e2 = new Mantenimiento("En mantenimiento","Recurso");
        Estado e3 = new CanceladoMantenimientoCorrectivo("Con inicio de mantenimiento correctivo","Recurso");
        Estado e4 = new Estado("Baja tecnica","Recurso");
        Estado e5 = new Estado("Baja definitiva","Recurso");

        //Estados de Turno
        Estado e6 = new Estado("Disponible","Turno");
        Estado e7 = new Estado("Con reserva pendiente de confirmacion","Turno");
        Estado e8 = new Estado("Reservado","Turno");

        estados.add(e1);
        estados.add(e2);
        estados.add(e3);
        estados.add(e4);
        estados.add(e5);
        estados.add(e6);
        estados.add(e7);
        estados.add(e8);
        
        
        //Cambios de EstadoRT
        CambioEstadoRT cambioRT1 = new CambioEstadoRT(e1,LocalDateTime.now(),null);
        CambioEstadoRT cambioRT2 = new CambioEstadoRT(e1,LocalDateTime.now(),null);
        CambioEstadoRT cambioRT3 = new CambioEstadoRT(e1,LocalDateTime.now(),null);
        CambioEstadoRT cambioRT4 = new CambioEstadoRT(e1,LocalDateTime.now(),null);
        CambioEstadoRT cambioRT5 = new CambioEstadoRT(e2,LocalDateTime.now(),null);
        CambioEstadoRT cambioRT6 = new CambioEstadoRT(e2,LocalDateTime.now(),null);
        CambioEstadoRT cambioRT7 = new CambioEstadoRT(e3,LocalDateTime.now(),null);
        CambioEstadoRT cambioRT8 = new CambioEstadoRT(e3,LocalDateTime.now(),null);
        
        //Cambios de Estado Turnos
        //RT1
        CambioEstadoTurno cambioTR1day1= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR1day2= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR1day3= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        
        //RT2
        //DAY1
        CambioEstadoTurno cambioTR2day1tur1= new CambioEstadoTurno(e7,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR2day1tur2= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR2day1tur3= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        //DAY2
        CambioEstadoTurno cambioTR2day2tur1= new CambioEstadoTurno(e7,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR2day2tur2= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR2day2tur3= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        //DAY3
        CambioEstadoTurno cambioTR2day3tur1= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR2day3tur2= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR2day3tur3= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        
        //RT3
        //DAY1
        CambioEstadoTurno cambioTR3day1tur1= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR3day1tur2= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        //DAY2
        CambioEstadoTurno cambioTR3day2tur1= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR3day2tur2= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        //DAY3
        CambioEstadoTurno cambioTR3day3tur1= new CambioEstadoTurno(e7,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR3day3tur2= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        
        //RT4
        //DAY1
        CambioEstadoTurno cambioTR4day1tur1= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR4day1tur2= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        //DAY2
        CambioEstadoTurno cambioTR4day2tur1= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        CambioEstadoTurno cambioTR4day2tur2= new CambioEstadoTurno(e7,LocalDateTime.now(),null);
        //DAY3
        //CambioEstadoTurno cambioTR4day3tur1= new CambioEstadoTurno(e8,LocalDateTime.now(),null);
        //CambioEstadoTurno cambioTR4day3tur2= new CambioEstadoTurno(e6,LocalDateTime.now(),null);
        
        //TURNOS RT1
        //crear Turnos
        //RT1 TURNOS
        ArrayList<CambioEstadoTurno> cambiosTurno1RT1 = new ArrayList<>();
        cambiosTurno1RT1.add(cambioTR1day1);
        
        ArrayList<CambioEstadoTurno> cambiosTurno2RT1 = new ArrayList<>();
        cambiosTurno2RT1.add(cambioTR1day2);
        
        ArrayList<CambioEstadoTurno> cambiosTurno3RT1 = new ArrayList<>();
        cambiosTurno3RT1.add(cambioTR1day3);

        Turno turno1RT1 = new Turno(cambiosTurno1RT1,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 13, 00),LocalDateTime.of(2023, 06, 27, 16, 00));
        Turno turno2RT1 = new Turno(cambiosTurno2RT1,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 13, 00),LocalDateTime.of(2023, 06, 28, 16, 00));
        Turno turno3RT1 = new Turno(cambiosTurno3RT1,LocalDate.now(),"Miercoles",LocalDateTime.of(2023, 06, 29, 13, 00),LocalDateTime.of(2023, 06, 29, 16, 00));
        
        //RT2 TURNOS
        ArrayList<CambioEstadoTurno> cambiosTurno1RT2 = new ArrayList<>();
        cambiosTurno1RT2.add(cambioTR2day1tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno2RT2 = new ArrayList<>();
        cambiosTurno2RT2.add(cambioTR2day1tur2);

        ArrayList<CambioEstadoTurno> cambiosTurno3RT2 = new ArrayList<>();
        cambiosTurno3RT2.add(cambioTR2day1tur3);

        ArrayList<CambioEstadoTurno> cambiosTurno4RT2 = new ArrayList<>();
        cambiosTurno4RT2.add(cambioTR2day2tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno5RT2 = new ArrayList<>();
        cambiosTurno5RT2.add(cambioTR2day2tur2);

        ArrayList<CambioEstadoTurno> cambiosTurno6RT2 = new ArrayList<>();
        cambiosTurno6RT2.add(cambioTR2day2tur3);

        ArrayList<CambioEstadoTurno> cambiosTurno7RT2 = new ArrayList<>();
        cambiosTurno7RT2.add(cambioTR2day3tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno8RT2 = new ArrayList<>();
        cambiosTurno8RT2.add(cambioTR2day3tur2);

        ArrayList<CambioEstadoTurno> cambiosTurno9RT2 = new ArrayList<>();
        cambiosTurno9RT2.add(cambioTR2day3tur3);

        Turno turno1RT2 = new Turno(cambiosTurno1RT2,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 13, 00),LocalDateTime.of(2023, 06, 27, 14, 00));
        Turno turno2RT2 = new Turno(cambiosTurno2RT2,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 14, 00),LocalDateTime.of(2023, 06, 27, 15, 00));
        Turno turno3RT2 = new Turno(cambiosTurno3RT2,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 15, 00),LocalDateTime.of(2023, 06, 27, 16, 00));
        Turno turno4RT2 = new Turno(cambiosTurno4RT2,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 13, 00),LocalDateTime.of(2023, 06, 28, 14, 00));
        Turno turno5RT2 = new Turno(cambiosTurno5RT2,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 14, 00),LocalDateTime.of(2023, 06, 28, 15, 00));
        Turno turno6RT2 = new Turno(cambiosTurno6RT2,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 15, 00),LocalDateTime.of(2023, 06, 28, 16, 00));
        Turno turno7RT2 = new Turno(cambiosTurno7RT2,LocalDate.now(),"Miercoles",LocalDateTime.of(2023, 06, 29, 13, 00),LocalDateTime.of(2023, 06, 29, 14, 00));
        Turno turno8RT2 = new Turno(cambiosTurno8RT2,LocalDate.now(),"Miercoles",LocalDateTime.of(2023, 06, 29, 14, 00),LocalDateTime.of(2023, 06, 29, 15, 00));
        Turno turno9RT2 = new Turno(cambiosTurno9RT2,LocalDate.now(),"Miercoles",LocalDateTime.of(2023, 06, 29, 15, 00),LocalDateTime.of(2023, 06, 29, 16, 00));

        //RT3 TURNOS
        ArrayList<CambioEstadoTurno> cambiosTurno1RT3 = new ArrayList<>();
        cambiosTurno1RT3.add(cambioTR3day1tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno2RT3 = new ArrayList<>();
        cambiosTurno2RT3.add(cambioTR3day1tur2);

        ArrayList<CambioEstadoTurno> cambiosTurno3RT3 = new ArrayList<>();
        cambiosTurno3RT3.add(cambioTR3day2tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno4RT3 = new ArrayList<>();
        cambiosTurno4RT3.add(cambioTR3day2tur2);

        ArrayList<CambioEstadoTurno> cambiosTurno5RT3 = new ArrayList<>();
        cambiosTurno5RT3.add(cambioTR3day3tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno6RT3 = new ArrayList<>();
        cambiosTurno6RT3.add(cambioTR3day3tur2);

        Turno turno1RT3 = new Turno(cambiosTurno1RT3,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 13, 00),LocalDateTime.of(2023, 06, 27, 14, 00));
        Turno turno2RT3 = new Turno(cambiosTurno2RT3,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 14, 00),LocalDateTime.of(2023, 06, 27, 15, 00));
        Turno turno3RT3 = new Turno(cambiosTurno3RT3,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 13, 00),LocalDateTime.of(2023, 06, 28, 14, 00));
        Turno turno4RT3 = new Turno(cambiosTurno4RT3,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 14, 00),LocalDateTime.of(2023, 06, 28, 15, 00));
        Turno turno5RT3 = new Turno(cambiosTurno5RT3,LocalDate.now(),"Miercoles",LocalDateTime.of(2023, 06, 29, 13, 00),LocalDateTime.of(2023, 06, 29, 14, 00));
        Turno turno6RT3 = new Turno(cambiosTurno6RT3,LocalDate.now(),"Miercoles",LocalDateTime.of(2023, 06, 29, 14, 00),LocalDateTime.of(2023, 06, 29, 15, 00));

        //RT4 TURNOS
        ArrayList<CambioEstadoTurno> cambiosTurno1RT4 = new ArrayList<>();
        cambiosTurno1RT4.add(cambioTR4day1tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno2RT4 = new ArrayList<>();
        cambiosTurno2RT4.add(cambioTR4day1tur2);

        ArrayList<CambioEstadoTurno> cambiosTurno3RT4 = new ArrayList<>();
        cambiosTurno3RT4.add(cambioTR4day2tur1);

        ArrayList<CambioEstadoTurno> cambiosTurno4RT4 = new ArrayList<>();
        cambiosTurno4RT4.add(cambioTR4day2tur2);

        //ArrayList<CambioEstadoTurno> cambiosTurno5RT4 = new ArrayList<>();
        //cambiosTurno5RT4.add(cambioTR4day3tur1);

        //ArrayList<CambioEstadoTurno> cambiosTurno6RT4 = new ArrayList<>();
        //cambiosTurno6RT4.add(cambioTR4day3tur2);

        Turno turno1RT4 = new Turno(cambiosTurno1RT4,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 13, 00),LocalDateTime.of(2023, 06, 27, 14, 00));
        Turno turno2RT4 = new Turno(cambiosTurno2RT4,LocalDate.now(),"Lunes",LocalDateTime.of(2023, 06, 27, 14, 00),LocalDateTime.of(2023, 06, 27, 15, 00));
        Turno turno3RT4 = new Turno(cambiosTurno3RT4,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 13, 00),LocalDateTime.of(2023, 06, 28, 14, 00));
        Turno turno4RT4 = new Turno(cambiosTurno4RT4,LocalDate.now(),"Martes",LocalDateTime.of(2023, 06, 28, 14, 00),LocalDateTime.of(2023, 06, 28, 15, 00));
        //Turno turno5RT4 = new Turno(cambiosTurno5RT4,LocalDate.now(),"Miercoles",LocalDateTime.of(2022, 06, 29, 13, 00),LocalDateTime.of(2022, 06, 29, 14, 00));
        //Turno turno6RT4 = new Turno(cambiosTurno6RT4,LocalDate.now(),"Miercoles",LocalDateTime.of(2022, 06, 29, 14, 00),LocalDateTime.of(2022, 06, 29, 15, 00));
        
        //Todos los turnos
        turnos.add(turno1RT1);
        turnos.add(turno2RT1);
        turnos.add(turno3RT1);
        turnos.add(turno1RT2);
        turnos.add(turno2RT2);
        turnos.add(turno3RT2);
        turnos.add(turno4RT2);
        turnos.add(turno5RT2);
        turnos.add(turno6RT2);
        turnos.add(turno7RT2);
        turnos.add(turno8RT2);
        turnos.add(turno9RT2);
        turnos.add(turno1RT3);
        turnos.add(turno2RT3);
        turnos.add(turno3RT3);
        turnos.add(turno4RT3);
        turnos.add(turno5RT3);
        turnos.add(turno6RT3);
        turnos.add(turno1RT4);
        turnos.add(turno2RT4);
        turnos.add(turno3RT4);
        turnos.add(turno4RT4);
        //turnos.add(turno5RT4);
        //turnos.add(turno6RT4);
        
        //RECURSOS
        ArrayList<CambioEstadoRT> cambiosEstadoRT1 = new ArrayList<>();
        cambiosEstadoRT1.add(cambioRT1);
        ArrayList<CambioEstadoRT> cambiosEstadoRT2 = new ArrayList<>();
        cambiosEstadoRT2.add(cambioRT2);
        ArrayList<CambioEstadoRT> cambiosEstadoRT3 = new ArrayList<>();
        cambiosEstadoRT3.add(cambioRT3);
        ArrayList<CambioEstadoRT> cambiosEstadoRT4 = new ArrayList<>();
        cambiosEstadoRT4.add(cambioRT4);
        ArrayList<CambioEstadoRT> cambiosEstadoRT5 = new ArrayList<>();
        cambiosEstadoRT5.add(cambioRT5);
        ArrayList<CambioEstadoRT> cambiosEstadoRT6 = new ArrayList<>();
        cambiosEstadoRT6.add(cambioRT6);
        ArrayList<CambioEstadoRT> cambiosEstadoRT7 = new ArrayList<>();
        cambiosEstadoRT7.add(cambioRT7);
        ArrayList<CambioEstadoRT> cambiosEstadoRT8 = new ArrayList<>();
        cambiosEstadoRT8.add(cambioRT8);
        
        //hacer arreglo de turnos
        ArrayList<Turno> turnosRT1 = new ArrayList<>();
        turnosRT1.add(turno1RT1);
        turnosRT1.add(turno2RT1);
        turnosRT1.add(turno3RT1);

        ArrayList<Turno> turnosRT2 = new ArrayList<>();
        turnosRT2.add(turno1RT2); 
        turnosRT2.add(turno2RT2);
        turnosRT2.add(turno3RT2);
        turnosRT2.add(turno4RT2);
        turnosRT2.add(turno5RT2);
        turnosRT2.add(turno6RT2);
        turnosRT2.add(turno7RT2);
        turnosRT2.add(turno8RT2);
        turnosRT2.add(turno9RT2);

        ArrayList<Turno> turnosRT3 = new ArrayList<>();
        turnosRT3.add(turno1RT3);
        turnosRT3.add(turno2RT3);
        turnosRT3.add(turno3RT3);
        turnosRT3.add(turno4RT3);
        turnosRT3.add(turno5RT3);
        turnosRT3.add(turno6RT3);

        ArrayList<Turno> turnosRT4 = new ArrayList<>();
        turnosRT4.add(turno1RT4);
        turnosRT4.add(turno2RT4);
        turnosRT4.add(turno3RT4);
        turnosRT4.add(turno4RT4);
        //turnosRT4.add(turno5RT4);
        //turnosRT4.add(turno6RT4);
        
        RecursoTecnologico recurso1 = new RecursoTecnologico(cambiosEstadoRT1,turnosRT1,tipo1,modelo1,134,null,0,0,1);
        RecursoTecnologico recurso2 = new RecursoTecnologico(cambiosEstadoRT2,turnosRT2,tipo1,modelo2,242,null,0,0,3);
        RecursoTecnologico recurso3 = new RecursoTecnologico(cambiosEstadoRT3,turnosRT3,tipo2,modelo3,535,null,0,0,2);
        RecursoTecnologico recurso4 = new RecursoTecnologico(cambiosEstadoRT4,turnosRT4,tipo2,modelo4,478,null,0,0,2);
        RecursoTecnologico recurso5 = new RecursoTecnologico(cambiosEstadoRT5,null,tipo1,modelo5,509,null,0,0,0);
        RecursoTecnologico recurso6 = new RecursoTecnologico(cambiosEstadoRT6,null,tipo2,modelo6,623,null,0,0,0);
        RecursoTecnologico recurso7 = new RecursoTecnologico(cambiosEstadoRT7,null,tipo1,modelo1,756,null,0,0,0);
        RecursoTecnologico recurso8 = new RecursoTecnologico(cambiosEstadoRT8,null,tipo3,modelo2,234,null,0,0,0);

        
        recursos.add(recurso1);
        recursos.add(recurso2);
        recursos.add(recurso3);
        recursos.add(recurso4);
        recursos.add(recurso5);
        recursos.add(recurso6);
        recursos.add(recurso7);
        recursos.add(recurso8);
        
        //centros
        ArrayList<RecursoTecnologico> recursosCentro1 = new ArrayList<>();
        recursosCentro1.add(recurso1);
        recursosCentro1.add(recurso3);
        recursosCentro1.add(recurso4);
        recursosCentro1.add(recurso5);
        recursosCentro1.add(recurso6);

        ArrayList<RecursoTecnologico> recursosCentro2 = new ArrayList<>();
        recursosCentro2.add(recurso2);
        recursosCentro2.add(recurso7);
        recursosCentro2.add(recurso8);
        
        //usuario...
        //centro1
        Usuario user1 = new Usuario("cientifico","123",true);
        PersonalCientifico pc1 = new PersonalCientifico(user1, 11233,"Luis","Perez",98765,"perez@institucional.com","luisp@gmail.com","+5493514578540");
        AsignacionCientificoDelCI ac1 = new AsignacionCientificoDelCI(pc1,LocalDate.of(2018, 10, 30),null);
        
        ArrayList<AsignacionCientificoDelCI> acc1 = new ArrayList<>();
        acc1.add(ac1);
        
        //centro2
        Usuario user2 = new Usuario("cientifica","123",true);
        PersonalCientifico pc2 = new PersonalCientifico(user2, 75233,"Maria","Jimenez",94565,"jimenez@institucional.com","mariaj@gmail.com","+5493514898540");
        AsignacionCientificoDelCI ac2 = new AsignacionCientificoDelCI(pc2,LocalDate.of(2018, 10, 30),null);
        
        ArrayList<AsignacionCientificoDelCI> acc2 = new ArrayList<>();
        acc2.add(ac2);
        
        CentroDeInvestigacion centro1 = new CentroDeInvestigacion(acc1,recursosCentro1,"Centro de quimica", 2,null, null,null, 0,null, null,null, 0,null, null,null, null,null, null);
        CentroDeInvestigacion centro2 = new CentroDeInvestigacion(acc2,recursosCentro2,"Centro de fisica", 2,null, null,null, 0,null, null,null, 0,null, null,null, null,null, null);
        
        centros.add(centro1);
        centros.add(centro2);
        
        Sesion s = new Sesion(LocalDateTime.now(),null,user1);
        
        sesiones.add(s);
        usuarios.add(user1);
        usuarios.add(user2);
        personal.add(pc1);
        personal.add(pc2);
        asignaciones.add(ac1);
        asignaciones.add(ac2);
    }

    public ArrayList<RecursoTecnologico> recursos(){
        return this.recursos;
    }
    public ArrayList<Modelo> modelos(){
        return this.modelos;
    }
    public ArrayList<Marca> marcas(){
        return this.marcas;
    }
    public ArrayList<Turno> turnos(){
        return this.turnos;
    }
    public ArrayList<CentroDeInvestigacion> centros(){
        return this.centros;
    }
    public ArrayList<TipoRecursoTecnologico> tipos(){
        return this.tipos;
    }
    public ArrayList<Estado> estados(){
        return this.estados;
    }
    
    public ArrayList<Sesion> sesiones(){
        return this.sesiones;
    }
    
    public ArrayList<Usuario> usuarios(){
        return this.usuarios;
    }
    
    public ArrayList<PersonalCientifico> personal(){
        return this.personal;
    }
    
    public ArrayList<AsignacionCientificoDelCI> asignaciones(){
        return this.asignaciones;
    }
}
