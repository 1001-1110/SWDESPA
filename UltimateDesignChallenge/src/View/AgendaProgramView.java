package View;

import javax.swing.*;
import javax.swing.table.*;

import Entry.Entries;
import Entry.EntriesIterator;
import Entry.Slot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AgendaProgramView extends JPanel implements AgendaView, ObserverView{
	
	CalendarView cv;
	
	List<Integer> rowAssignment;
	int currentRow;
	
	JScrollPane scrollCalendarTable;    
	
        /**** Calendar Table Components ***/
	public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;
    
    public void deselect() {
    	currentRow = -1;
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new InfoTableRenderer(calendarTable.getSelectedRow()));    	
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
    
    public int getSelectedOccasion() {
    	return rowAssignment.get(currentRow);
    }
    
	public AgendaProgramView(CalendarView cv){
		
		setBounds(0,0,620,440);
		
		this.cv =  cv;
		
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
		calendarTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentRow = (int) calendarTable.getSelectedRow();
				calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new InfoTableRenderer(calendarTable.getSelectedRow()));
				cv.enableSelectButtons();
				calendarTable.clearSelection();
				calendarTable.repaint();
			}
		});
                
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

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new InfoTableRenderer(calendarTable.getSelectedRow()));
	}
}
