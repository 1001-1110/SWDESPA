/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends DefaultTableCellRenderer{

	List<String> days;
	
	public TableRenderer(int month, int year, List<String> selectedDates) {
		
		days = new ArrayList<>();
		
		for(int i = 0 ; i < selectedDates.size(); i++) {
			String[] splitDate = selectedDates.get(i).split(" "); 
			String[] splitMonthYear = splitDate[0].split("-");
			if(year == Integer.parseInt(splitMonthYear[0]) && month == Integer.parseInt(splitMonthYear[1])) {
				days.add(splitDate[1]);
			}
		}
		
	}
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            setBorder(null);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            
            if (this.getText().equals(new String())) {
                setBackground(Color.LIGHT_GRAY); 
            }else {
                String dayText = this.getText().replace("<html>","");
                dayText = dayText.replace("<font color = \"red\">&#160","");
                dayText = dayText.trim();
                for(int i = 0 ; i < days.size() ; i++) {
                    if (dayText.equals(days.get(i))) {
                    	setBackground(Color.PINK);
                    	break;
                    }else{
                    	setBackground(Color.WHITE);           	
                    }
                                	
                }           	
            }

            return this;  
    }
}
