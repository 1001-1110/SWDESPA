package View;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Entry.Event;
import Entry.Occasion;
import Entry.Task;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class DayProgramView extends JPanel implements DayView, ObserverView{

	CalendarView cv;
	
	private int currentSelectedMonth, currentSelectedDay, currentSelectedYear;
	private List<Integer> rowAssignment;
	private int currentRow;	
	
	JScrollPane scrollCalendarTable;    
	
        /**** Calendar Table Components ***/
	public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;

    public void deselect() {
    	
    }
    
    public void updateCurrent(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
    	this.currentSelectedYear = currentSelectedYear;
    	this.currentSelectedMonth = currentSelectedMonth;
    	this.currentSelectedDay = currentSelectedDay;
    	String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    	Calendar c = new GregorianCalendar(currentSelectedYear,currentSelectedMonth,currentSelectedDay);
		this.setBorder(new TitledBorder(null, dayNames[c.get(Calendar.DAY_OF_WEEK)-1], TitledBorder.LEADING, TitledBorder.TOP, null, null));
    }
    
    public void update(List<Occasion>occasions) {
    	
		modelCalendarTable.setColumnCount(2);
		modelCalendarTable.setRowCount(48);
		
		for(int i = 0, j = 0, k = 0 ; i < 48 ; i++) {
			modelCalendarTable.setValueAt(null, i, 1);
			String time;
			
			if(i % 2 == 0) {
				time = Integer.toString(j);
				if(j >= 1000) {
					time = time.substring(0,2) + ":" + time.substring(2,4);
				}else if (j >= 100){
					time = "0" + time.charAt(0) + ":" + time.substring(1,3);				
				}else {
					time = "00:00";
				}	
				modelCalendarTable.setValueAt(time, i, 0);	
			}else {
				time = Integer.toString(k);
				if(k >= 1000) {
					time = time.substring(0,2) + ":" + time.substring(2,4);
				}else if (k >= 100){
					time = "0" + time.charAt(0) + ":" + time.substring(1,3);				
				}else {
					time = "00:30";
				}
			}				

				String[] splitTime = time.split(":");
				Calendar c1 = new GregorianCalendar(currentSelectedYear, currentSelectedMonth, currentSelectedDay, Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));

				for(int x = 0 ; x < occasions.size(); x++) {
					if(occasions.get(x) instanceof Event) {
						String[] durationSplit = ((Event) occasions.get(x)).getDurationFrom().split(" ");
						String[] dateSplit = durationSplit[0].split("-");
						String[] timeSplit = durationSplit[1].split(":");
						int year = Integer.parseInt(dateSplit[0]); 
						int month = Integer.parseInt(dateSplit[1]); 
						int day = Integer.parseInt(dateSplit[2]); 
						int hour = Integer.parseInt(timeSplit[0]); 
						int minute = Integer.parseInt(timeSplit[1]); 
						Calendar c2 = new GregorianCalendar(year,month-1,day,hour,minute);
						durationSplit = ((Event) occasions.get(x)).getDurationTo().split(" ");
						dateSplit = durationSplit[0].split("-");
						timeSplit = durationSplit[1].split(":");
						year = Integer.parseInt(dateSplit[0]); 
						month = Integer.parseInt(dateSplit[1]); 
						day = Integer.parseInt(dateSplit[2]); 
						hour = Integer.parseInt(timeSplit[0]); 
						minute = Integer.parseInt(timeSplit[1]); 
						Calendar c3 = new GregorianCalendar(year,month-1,day,hour,minute);
						if(c1.compareTo(c2) >= 0 && c3.compareTo(c1) > 0)
							if(((Event) occasions.get(x)).getIsDone()) {
								modelCalendarTable.setValueAt("<html><s>"+((Event)occasions.get(x)).getInfo()+"</s></html>", i, 1);					
							}else {
								modelCalendarTable.setValueAt("<html><font color=\""+occasions.get(x).getColorString()+"\">"+((Event)occasions.get(x)).getInfo(), i, 1);					
							}
					}else if(occasions.get(x) instanceof Task) {
						String[] durationSplit = ((Task) occasions.get(x)).getDurationFrom().split(" ");
						String[] dateSplit = durationSplit[0].split("-");
						if(Integer.parseInt(dateSplit[0]) == currentSelectedYear && Integer.parseInt(dateSplit[1]) == (currentSelectedMonth+1) && Integer.parseInt(dateSplit[2]) == currentSelectedDay)
							if(time.equals(durationSplit[1].substring(0,5)))
								if(((Task) occasions.get(x)).getIsDone()) {
									modelCalendarTable.setValueAt("<html><s>"+((Task)occasions.get(x)).getInfo()+"</s></html>", i, 1);
								}else {
									modelCalendarTable.setValueAt("<html><font color=\""+occasions.get(x).getColorString()+"\">"+((Task)occasions.get(x)).getInfo(), i, 1);	
								}
					}
				}

			if(i % 2 == 1)
				j += 100;
			else
				k = j + 30;
				
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
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);
		calendarTable.setRowSelectionAllowed(false);
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
		
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new InfoTableRenderer(calendarTable.getSelectedRow()));
	}
}
