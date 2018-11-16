/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jorge
 */
public class TableRender extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel cellComponent = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellComponent.setHorizontalAlignment(CENTER);
        if(value instanceof Boolean){
            value  = (Boolean)value;
            if(column == 3){
                if(value.equals(true)){
                    cellComponent.setText("True");
                    cellComponent.setForeground(new java.awt.Color(0, 128, 64));
                }else{
                    cellComponent.setText("False");
                    cellComponent.setForeground(Color.RED);
                }
            }
        }else
            cellComponent.setForeground(new java.awt.Color(102, 102, 255));
        if(value instanceof String){
            value  = (String)value;
            if(column == 2){
                if(value.equals("")){
                    cellComponent.setText("---");
                }
            }
        }
        return cellComponent;
    }
}
