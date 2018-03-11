/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends DefaultTableCellRenderer{

	String day;
	
	public TableRenderer(int month, int year, int selectedMonth, int selectedDay, int selectedYear) {

		if(month == selectedMonth && year == selectedYear) {
			this.day = Integer.toString(selectedDay);
		} else {
			this.day = "Unselected";
		}
	}
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            setBorder(null);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            
            String dayText = this.getText().replace("<html>","");
            dayText = dayText.replace("<font color = \"red\">&#160","");
            dayText = dayText.trim();
            
            if (dayText.equals(day)) {
            	setBackground(Color.PINK);
            	//setBorder(BorderFactory.createLineBorder(Color.RED));  
            }else if (dayText.equals("Unselected")) {
                setBackground(Color.WHITE); 
            }else if (dayText.equals(new String())) {
                setBackground(Color.LIGHT_GRAY); 
            }else{
            	setBackground(Color.WHITE);           	
            }
            
            return this;  
    }
}
