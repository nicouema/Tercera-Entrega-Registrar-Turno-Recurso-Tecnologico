package Interfaces;

import Controladores.GestorReservaTurno;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PantallaReservaTurno extends javax.swing.JFrame {
    
    //Atributos
    private ArrayList<String> tipos = new ArrayList<>();
    private String tipoSeleccionado;
    private ArrayList<ArrayList<String>> recursos = new ArrayList<>();
    private ArrayList<String> recursoSeleccionado;
    private ArrayList<ArrayList<String>> fechas = new ArrayList<>();
    private int indiceFechaSeleccionada;
    private ArrayList<String> fechaSeleccionada = new ArrayList<>();;
    private ArrayList<ArrayList<ArrayList<String>>> turnos = new ArrayList<>();
    private int indiceTurnoSeleccionado;
    private ArrayList<String> turnoSeleccionado = new ArrayList<>();;
    private String datosDeReserva = "";
    private boolean esDelMismoCentro;
    
    //Nombres de las columnas de las tablas
    private String [] columnasTabFechas = {"","Estado","Fecha"};
    private String [] columnasTabTurnos = {"","Estado","Fecha","Hora Inicio","Hora Fin"};
    
    //Crea modelode las tablas y asigna los nombres de las columnas
    DefaultTableModel modeloFechas = new DefaultTableModel(null,columnasTabFechas);
    DefaultTableModel modeloTurnos = new DefaultTableModel(null,columnasTabTurnos);
    
    //Comunicacion con el gestor
    GestorReservaTurno gestor1 = new GestorReservaTurno();
    
    //Empieza el caso de uso N23 Registrar reserva de turno para utilizacion de recurso tecnologico
    //Metodo 1
    public void tomarBotonRegistrarReserva(){
        habilitarPantalla();
        this.setLocationRelativeTo(null);
        //Recibir los tipos desde el gestor
        tipos = gestor1.tomarRegistrarReserva();
        //Agregar la primera opcion TODAS, seleccionada por defecto
        tipos.add(0,"TODAS");
        //Mostrar los tipos en comboBox
        mostrarTipos();
        //Solicitar seleccion de recurso
        solicitarSeleccionDeTipo();
    }
    
    //Metodo 2
    public void habilitarPantalla(){
        initComponents();
    }
    
    //Metodo 12
    public void mostrarTipos(){
        for(String nombreTipo: tipos){
            cmb_Tipos.addItem(nombreTipo);
        }
    }
    
    //Metodo 13
    public void solicitarSeleccionDeTipo(){
        lbl_seleccioneTipo.setText("Seleccione un tipo de recurso tecnologico:");
    }
    
    //Metodo 14
    public void tomarOpcionTipoSeleccionado(){
        //Enviar el tipo seleccionado al gestor, me devuelve los recursos del tipo
        recursos = gestor1.tomarTipoSeleccionado(tipoSeleccionado);
        //Mostrar tabla recursos
        
        mostrarRecursos();
        //Solicitar seleccion de recurso
        solicitarSeleccionDeRecurso();
    }
    
    //Metodo 33
    public void mostrarRecursos(){
        String [] columnasTabRecursos = {"","Estado","Numero RT","Marca","Modelo","Centro de Investigacion"};
        DefaultTableModel modeloRecursos = new DefaultTableModel(null,columnasTabRecursos);
        mostrarTabla(tablaRecursos, modeloRecursos, recursos);
    }
    
    //Metodo 34
    public void solicitarSeleccionDeRecurso(){
        lbl_seleccioneRecurso.setText("Seleccione un recurso:");
    }
    
    //Metodo 35
    public void tomarOpcionRecursoSeleccionado(int indiceRecursoSeleccionado){
        //Enviar el indice del recurso seleccionado al gestor para recibir las fechasturnos
        ArrayList<Object> c = gestor1.tomarRecursoSeleccionado(indiceRecursoSeleccionado);
        //guardo Fechas y turnos
        fechas = (ArrayList<ArrayList<String>>)c.get(0);
        turnos = (ArrayList<ArrayList<ArrayList<String>>>)c.get(1);
        esDelMismoCentro = (boolean)c.get(2);
        //mostrar tabla Fechas
        mostrarFechasYTurnos(fechas,turnos);
        solicitarSeleccionDeTurno();
    }
    
    public void mostrarFechasYTurnos(ArrayList<ArrayList<String>> fechas, ArrayList<ArrayList<ArrayList<String>>> turnos){
        mostrarTabla(tablaFechas,modeloFechas,fechas);
        if(esDelMismoCentro == false)
            lbl_mismoCentro.setText("Recurso no es del mismo centro");
    }
    
    public void solicitarSeleccionDeTurno(){
        lbl_seleccioneTurno.setText("Seleccione un turno:");
    }

    public void tomarOpcionTurnoSeleccionado(){
        LocalDateTime fechaHoraInicio = convierteAFecha(turnoSeleccionado);
        gestor1.tomarTurnoSeleccionado(fechaHoraInicio);
        mostrarDatosDeReserva();
        solicitarConfirmacionDeReserva();
    }
    
    public LocalDateTime convierteAFecha(ArrayList<String> turno){
        String fechaHora = turnoSeleccionado.get(2)+" "+turnoSeleccionado.get(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
        LocalDateTime dateTime = LocalDateTime.parse(fechaHora, formatter);
        return dateTime;
    }
    
    public void mostrarDatosDeReserva(){
        //
        datosDeReserva = "Datos de la reserva";
        datosDeReserva += "\nRecurso - Numero: "+recursoSeleccionado.get(2)+" , Marca:"+" "+recursoSeleccionado.get(3)+", Modelo:"+recursoSeleccionado.get(4);
        datosDeReserva += "\nFecha: "+fechaSeleccionada.get(2);
        datosDeReserva += "\nHora de inicio: "+turnoSeleccionado.get(3);
        datosDeReserva += "\nHora de fin: "+turnoSeleccionado.get(4);
        textArea_datos.setText(datosDeReserva);
        if(esDelMismoCentro ==false)
            lbl_aviso.setText("Recurso no es del mismo centro. Su reserva puede ser cancelada por el responsable del recurso.");
    }
    
    public void solicitarConfirmacionDeReserva(){
        lbl_seleccioneMedioNotif.setText("Seleccione su medio de notificacion:");
    }
    
    public void tomarBotonConfirmacionReserva(){
        gestor1.tomarConfirmacionReserva(check_mail.isSelected(), check_wsp.isSelected(), datosDeReserva);
        dispose();
    }
    
    public void mostrarTabla(JTable tabla, DefaultTableModel modelo, ArrayList<ArrayList<String>> filas){
        for (ArrayList<String> fila : filas) {
            modelo.addRow(fila.toArray());
        }
        tabla.setModel(modelo);
        tabla.getColumnModel().getColumn(0).setCellRenderer(new RenderPintar());
        tabla.getColumnModel().getColumn(0).setPreferredWidth(6);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120);
    }
    
    
    //Arranque de interfaz
    public PantallaReservaTurno() {
        tomarBotonRegistrarReserva();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmb_Tipos = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRecursos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFechas = new javax.swing.JTable();
        lbl_seleccioneFecha = new javax.swing.JLabel();
        lbl_seleccioneTurno = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaTurnos = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        textArea_datos = new javax.swing.JTextArea();
        lbl_seleccioneMedioNotif = new javax.swing.JLabel();
        check_wsp = new javax.swing.JCheckBox();
        check_mail = new javax.swing.JCheckBox();
        btn_cancelar = new javax.swing.JButton();
        btn_reservar = new javax.swing.JButton();
        lbl_seleccioneTipo = new javax.swing.JLabel();
        lbl_recurso = new javax.swing.JLabel();
        lbl_fecha = new javax.swing.JLabel();
        lbl_turno = new javax.swing.JLabel();
        lbl_seleccioneRecurso = new javax.swing.JLabel();
        lbl_mismoCentro = new javax.swing.JLabel();
        lbl_aviso = new javax.swing.JLabel();
        lbl_ingreseDatos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reservar Turno");
        setResizable(false);

        cmb_Tipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_TiposActionPerformed(evt);
            }
        });

        tablaRecursos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaRecursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaRecursos.setFocusable(false);
        tablaRecursos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaRecursos.getTableHeader().setResizingAllowed(false);
        tablaRecursos.getTableHeader().setReorderingAllowed(false);
        tablaRecursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRecursosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaRecursos);

        tablaFechas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaFechas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaFechas.setFocusable(false);
        tablaFechas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaFechas.getTableHeader().setResizingAllowed(false);
        tablaFechas.getTableHeader().setReorderingAllowed(false);
        tablaFechas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaFechasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFechas);

        lbl_seleccioneFecha.setText("Seleccione una fecha:");

        lbl_seleccioneTurno.setText("Seleccione un turno:");

        tablaTurnos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaTurnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaTurnos.setFocusable(false);
        tablaTurnos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaTurnos.getTableHeader().setResizingAllowed(false);
        tablaTurnos.getTableHeader().setReorderingAllowed(false);
        tablaTurnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTurnosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaTurnos);

        textArea_datos.setEditable(false);
        textArea_datos.setColumns(20);
        textArea_datos.setRows(5);
        jScrollPane7.setViewportView(textArea_datos);

        lbl_seleccioneMedioNotif.setText("Seleccione su medio de notificacion:");

        check_wsp.setText("WhatsApp");

        check_mail.setSelected(true);
        check_mail.setText("Mail");

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_reservar.setText("Confirmar");
        btn_reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reservarActionPerformed(evt);
            }
        });

        lbl_recurso.setForeground(new java.awt.Color(255, 0, 0));

        lbl_fecha.setForeground(new java.awt.Color(255, 0, 0));

        lbl_turno.setForeground(new java.awt.Color(255, 0, 0));

        lbl_mismoCentro.setForeground(new java.awt.Color(255, 153, 0));

        lbl_aviso.setForeground(new java.awt.Color(255, 153, 0));

        lbl_ingreseDatos.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fecha)
                            .addComponent(lbl_seleccioneFecha))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_turno)
                                    .addComponent(lbl_seleccioneTurno))
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_mismoCentro)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_seleccioneMedioNotif)
                            .addComponent(lbl_aviso)
                            .addComponent(lbl_recurso)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_seleccioneTipo)
                                .addGap(18, 18, 18)
                                .addComponent(cmb_Tipos, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_seleccioneRecurso)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(lbl_ingreseDatos)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(check_mail)
                                .addGap(28, 28, 28)
                                .addComponent(check_wsp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_reservar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancelar))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_Tipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_seleccioneTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_seleccioneRecurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_recurso)
                    .addComponent(lbl_mismoCentro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_seleccioneFecha)
                    .addComponent(lbl_seleccioneTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_fecha)
                    .addComponent(lbl_turno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 51, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cancelar)
                            .addComponent(btn_reservar))
                        .addGap(34, 34, 34)
                        .addComponent(lbl_ingreseDatos))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_aviso)
                        .addGap(13, 13, 13)
                        .addComponent(lbl_seleccioneMedioNotif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_mail)
                            .addComponent(check_wsp))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_TiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_TiposActionPerformed
        // TODO add your handling code here:
        tipoSeleccionado = (String)cmb_Tipos.getSelectedItem();
        //Limpiar
        lbl_recurso.setText(null);                            
        lbl_fecha.setText(null);
        lbl_turno.setText(null);
        textArea_datos.setText(null);
        //modeloRecursos.setRowCount(0);
        modeloFechas.setRowCount(0);
        modeloTurnos.setRowCount(0);
        datosDeReserva = "";
        lbl_ingreseDatos.setText(null);
        lbl_mismoCentro.setText(null);
        lbl_aviso.setText(null);
        
        
        //Se ejecuta el metodo 14
        tomarOpcionTipoSeleccionado();
    }//GEN-LAST:event_cmb_TiposActionPerformed

    private void tablaRecursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRecursosMouseClicked
        // TODO add your handling code here:
        //Limpiar
        modeloFechas.setRowCount(0);
        modeloTurnos.setRowCount(0);
        lbl_fecha.setText(null);
        lbl_turno.setText(null);
        textArea_datos.setText(null);
        lbl_recurso.setText(null);
        datosDeReserva = "";
        lbl_ingreseDatos.setText(null);
        lbl_mismoCentro.setText(null);
        lbl_aviso.setText(null);
        //Obtener indice de recurso seleccionado
        int indiceRecursoSeleccionado = tablaRecursos.rowAtPoint(evt.getPoint());
        //obtengo el recurso seleccionado segun el indice seleccionado de la tabla
        recursoSeleccionado = recursos.get(indiceRecursoSeleccionado);
        //validar
        if(recursoSeleccionado.get(1) == "Disponible"){
            tomarOpcionRecursoSeleccionado(indiceRecursoSeleccionado);
            
        }else{
            lbl_recurso.setText("Recurso no disponible. Seleccione otro recurso.");
        }
        
        
    }//GEN-LAST:event_tablaRecursosMouseClicked

    private void tablaFechasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFechasMouseClicked
        // TODO add your handling code here:
        //Limpiar
        modeloTurnos.setRowCount(0);
        lbl_turno.setText(null);
        textArea_datos.setText(null);
        lbl_fecha.setText(null);
        datosDeReserva = "";
        lbl_ingreseDatos.setText(null);
        lbl_aviso.setText(null);
        
        //Obtener indice de fecha seleccionada
        indiceFechaSeleccionada = tablaFechas.rowAtPoint(evt.getPoint());
        //Obtener la fecha seleccionada segun el indice seleccionado de la tabla
        fechaSeleccionada = fechas.get(indiceFechaSeleccionada);
        //validar
        if(fechaSeleccionada.get(1) == "Disponible"){
            mostrarTabla(tablaTurnos, modeloTurnos,turnos.get(indiceFechaSeleccionada));
        }
        else{
            lbl_fecha.setText("Fecha no disponible. Seleccione otra fecha");
        }
    }//GEN-LAST:event_tablaFechasMouseClicked

    private void tablaTurnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTurnosMouseClicked
        // TODO add your handling code here:
        indiceTurnoSeleccionado = tablaTurnos.rowAtPoint(evt.getPoint());
        //Limpiar
        lbl_turno.setText(null);
        lbl_ingreseDatos.setText(null);
        textArea_datos.setText(null);
        lbl_aviso.setText(null);
        datosDeReserva = "";
        //Validar
        turnoSeleccionado = (turnos.get(indiceFechaSeleccionada)).get(indiceTurnoSeleccionado); 
        if(turnoSeleccionado.get(1) != "Disponible"){
            lbl_turno.setText("Turno no disponible. Seleccione otro turno");
            
        }
        else{
            tomarOpcionTurnoSeleccionado();
        }
    }//GEN-LAST:event_tablaTurnosMouseClicked

    private void btn_reservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reservarActionPerformed
        //Validar
        if(datosDeReserva.compareTo("")==0){
            lbl_ingreseDatos.setText("Ingrese todos los datos");
        }else{
            tomarBotonConfirmacionReserva();
        }
    }//GEN-LAST:event_btn_reservarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaReservaTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaReservaTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaReservaTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaReservaTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaReservaTurno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_reservar;
    private javax.swing.JCheckBox check_mail;
    private javax.swing.JCheckBox check_wsp;
    private javax.swing.JComboBox<String> cmb_Tipos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbl_aviso;
    private javax.swing.JLabel lbl_fecha;
    private javax.swing.JLabel lbl_ingreseDatos;
    private javax.swing.JLabel lbl_mismoCentro;
    private javax.swing.JLabel lbl_recurso;
    private javax.swing.JLabel lbl_seleccioneFecha;
    private javax.swing.JLabel lbl_seleccioneMedioNotif;
    private javax.swing.JLabel lbl_seleccioneRecurso;
    private javax.swing.JLabel lbl_seleccioneTipo;
    private javax.swing.JLabel lbl_seleccioneTurno;
    private javax.swing.JLabel lbl_turno;
    private javax.swing.JTable tablaFechas;
    private javax.swing.JTable tablaRecursos;
    private javax.swing.JTable tablaTurnos;
    private javax.swing.JTextArea textArea_datos;
    // End of variables declaration//GEN-END:variables
}
