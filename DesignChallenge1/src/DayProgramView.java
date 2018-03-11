
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

public class DayProgramView extends JPanel implements DayView, ObserverView{
	
	CalendarView cv;
	
	List<Integer> rowAssignment;
	int currentRow;	
	
	JScrollPane scrollCalendarTable;    
	
        /**** Calendar Table Components ***/
	public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;

    public void update(List<Occasion>occasions) {
    	
		modelCalendarTable.setColumnCount(2);
		modelCalendarTable.setRowCount(48);
		
		for(int i = 0, j = 0 ; i < 48 ; i++) {
			if(i % 2 == 0) {
				if(j >= 1000) {
					String time = Integer.toString(j);
					time = time.substring(0,2) + ":" + time.substring(2,4);
					modelCalendarTable.setValueAt(time, i, 0);
				}else if (j >= 100){
					String time = Integer.toString(j);
					time = "0" + time.charAt(0) + ":" + time.substring(1,3);
					modelCalendarTable.setValueAt(time, i, 0);					
				}else {
					modelCalendarTable.setValueAt("00:00", i, 0);	
				}
				j += 100;
			}
		}    	
    	
		for(int i = 0, j = 0, k = 0; i < 48 ; i++) {
			
			int compare;
			
			if(i % 2 == 0) {
				compare = j;
			}else {
				compare = k;
			}
			
			modelCalendarTable.setValueAt(null, i, 1);
			
			for(int x = 0 ; x < occasions.size() ; x ++) {
				if(occasions.get(x) instanceof Event) {
					String timeFrom = ((Event) occasions.get(x)).getDurationFrom().substring(11,16);
					String[] timeFromPart = timeFrom.split(":");
					int intTimeFrom = (Integer.parseInt(timeFromPart[0])*100) + Integer.parseInt(timeFromPart[1]);
					
					String timeTo = ((Event) occasions.get(x)).getDurationTo().substring(11,16);
					String[] timeToPart = timeTo.split(":");
					int intTimeTo = (Integer.parseInt(timeToPart[0])*100) + Integer.parseInt(timeToPart[1]);
					
					if(intTimeFrom <= compare && intTimeTo > compare) {
						if(((Event) occasions.get(x)).getIsDone()) {
							modelCalendarTable.setValueAt("<html><s>"+((Event)occasions.get(x)).getInfo()+"</s></html>", i, 1);					
						}else {
							modelCalendarTable.setValueAt("<html><font color=\""+occasions.get(x).getColorString()+"\">"+((Event)occasions.get(x)).getInfo(), i, 1);					
						}
					}
					
				}else if(occasions.get(x) instanceof Task) {
					String timeFrom = ((Task) occasions.get(x)).getDurationFrom().substring(11,16);
					String[] timeFromPart = timeFrom.split(":");
					int intTimeFrom = (Integer.parseInt(timeFromPart[0])*100) + Integer.parseInt(timeFromPart[1]);
					
					String timeTo = ((Task) occasions.get(x)).getDurationTo().substring(11,16);
					String[] timeToPart = timeTo.split(":");
					int intTimeTo = (Integer.parseInt(timeToPart[0])*100) + Integer.parseInt(timeToPart[1]);

					if(intTimeFrom <= compare && intTimeTo > compare) {
						if(((Task) occasions.get(x)).getIsDone()) {
							modelCalendarTable.setValueAt("<html><s>"+((Task)occasions.get(x)).getInfo()+"</s></html>", i, 1);
						}else {
							modelCalendarTable.setValueAt("<html><font color=\""+occasions.get(x).getColorString()+"\">"+((Task)occasions.get(x)).getInfo(), i, 1);	
						}
					}
				
				}

			}
			
			if(i % 2 == 0) {
				j += 100;				
			}else {
				k = j + 30;
			}

		}
		
    }
    
	public DayProgramView(CalendarView cv){
		
		setBounds(0,0,620,440);
		
		this.cv = cv;
		
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
        
		modelCalendarTable = new DefaultTableModel()
                {
                    public boolean isCellEditable(int rowIndex, int mColIndex)
                    {
                        return false;
                    }
                };      
                
		calendarTable = new JTable(modelCalendarTable);
                
		scrollCalendarTable = new JScrollPane(calendarTable);
		scrollCalendarTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(20);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollCalendarTable, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollCalendarTable, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		modelCalendarTable.addColumn("Time");
		modelCalendarTable.addColumn("Event / Task");
		
		//calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new InfoTableRenderer(calendarTable.getSelectedRow()));
	}
}
