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
	List<Occasion>occasions;
	int selectedRow;
	
	public InfoTableRenderer(int row, List<Occasion>occasions) {
		this.occasions = occasions;
		this.selectedRow = row;
	}
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            setBorder(null);
            setBackground(Color.WHITE);
            setForeground(Color.black);

            for(int i = 0 ; i < occasions.size(); i++) {
            	if(occasions.get(i) instanceof Event) {
                	if(this.getText().contains(((Event) occasions.get(i)).getDurationFrom())) {
                		setForeground(Color.BLUE);
                	}

            	}else if(occasions.get(i) instanceof Task) {
                	if(((Task) occasions.get(i)).getDurationFrom().equals(this.getText())) {
                		setForeground(Color.GREEN);                    		
                	}
            	}
            }
            
            if(this.getText().contains("<s>")) {
            	setBackground(Color.LIGHT_GRAY);
            	setForeground(Color.BLACK);
            }
 
            if(selectedRow == row) {
            	//setBorder(BorderFactory.createLineBorder(Color.RED));
            	setBackground(Color.MAGENTA);
            }else {
            	setBorder(null);
            }
            	
            return this;  
    }
}
