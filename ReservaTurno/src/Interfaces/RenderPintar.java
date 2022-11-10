
package Interfaces;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class RenderPintar extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel labelResultado = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value instanceof String){
            String Dato = (String)value;
            if(Dato.equals("Azul")){
                labelResultado.setBackground(Color.blue);
                labelResultado.setForeground(Color.blue);
            }else if(Dato.equals("Verde")){
                labelResultado.setBackground(Color.green);
                labelResultado.setForeground(Color.green);
            }else if(Dato.equals("Gris")){
                labelResultado.setBackground(Color.gray);
                labelResultado.setForeground(Color.gray);
            }else if(Dato.equals("Rojo")){
                labelResultado.setBackground(Color.red);
                labelResultado.setForeground(Color.red);
            }
        }
        
        return labelResultado;
    }
    
    
}
