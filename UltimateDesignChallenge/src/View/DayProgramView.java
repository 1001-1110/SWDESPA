package View;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Entry.Entries;
import Entry.EntriesIterator;
import Entry.Entry;
import Entry.Slot;

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
    
    // WHERE YOU WILL IMPLEMENT HOW THE DATA IS DISPLAYED
    public void update(Entries slots, Entries books) {	
		
    	//rowAssignment = where the IDs reside for referencing later on
    	
		rowAssignment = new ArrayList<>();
		
		EntriesIterator eiSlots = slots.getIterator();
		
		modelCalendarTable.setColumnCount(2);
		modelCalendarTable.setRowCount(slots.size());
    	
		while(eiSlots.hasNext()) {
			
			Slot slot = (Slot) eiSlots.next();
			
			rowAssignment.add(slot.getId());
			modelCalendarTable.setValueAt("<html>"+slot.getDurationFrom().substring(0,16)+" to "+slot.getDurationTo().substring(0,16)+"</html>", eiSlots.getPos()-1, 0);	
			modelCalendarTable.setValueAt("<html>"+slot.getDoctor()+"</html>", eiSlots.getPos()-1, 1);					

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
