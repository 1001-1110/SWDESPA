/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends DefaultTableCellRenderer
{

	String day;
	
	public TableRenderer(int month, int year, int selectedMonth, int selectedDay, int selectedYear) {
		if(month == selectedMonth && year == selectedYear) {
			this.day = " "+selectedDay;
		} else {
			this.day = new String();
		}
	}
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            if (this.getText().equals(new String()))
            	setBackground(Color.LIGHT_GRAY);
            else if (this.getText().equals(day)) 
            	setBackground(Color.CYAN);
            else
            	setBackground(Color.WHITE);
            
            setBorder(null);
            setForeground(Color.black);
            return this;  
    }
}
