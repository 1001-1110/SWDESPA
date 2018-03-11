/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class InfoTableRenderer extends DefaultTableCellRenderer{

	int selectedRow;
	
	public InfoTableRenderer(int row) {
		this.selectedRow = row;
	}
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            setBorder(null);
            setBackground(Color.WHITE);
            setForeground(Color.black);
            
            if(this.getText().contains("<s>")) {
            	setBackground(Color.LIGHT_GRAY);
            	setForeground(Color.BLACK);
            }
 
            if(selectedRow == row) {
            	//setBorder(BorderFactory.createLineBorder(Color.RED));
            	setBackground(Color.pink);
            }else {
            	setBorder(null);
            }
            	
            return this;  
    }
}
