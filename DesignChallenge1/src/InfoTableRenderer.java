/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class InfoTableRenderer extends DefaultTableCellRenderer
{
	
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
            
            if(selectedRow == row) {
            	setBorder(BorderFactory.createLineBorder(Color.RED));
            }else {
            	setBorder(null);
            }
            	
            return this;  
    }
}
