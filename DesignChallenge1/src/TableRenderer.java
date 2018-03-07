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
public class TableRenderer extends DefaultTableCellRenderer
{

	String day;
	
	public TableRenderer(int month, int year, int selectedMonth, int selectedDay, int selectedYear) {
		if(month == selectedMonth && year == selectedYear) {
			this.day = " "+selectedDay;
		} else {
			this.day = "Unselected";
		}
	}
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            setBorder(null);
            setBackground(Color.WHITE);
            
            if (this.getText().equals(day)) {
            	setBorder(BorderFactory.createLineBorder(Color.RED));  
            }else if (this.getText().equals("Unselected")) {
                setBackground(Color.WHITE); 
            }else if (this.getText().equals(new String())) {
                setBackground(Color.LIGHT_GRAY); 
            }else{
            	setBackground(Color.WHITE);           	
            }

            setForeground(Color.black);
            return this;  
    }
}
