
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AgendaProgramView extends JPanel implements AgendaView{
	
	CalendarControl cc;
	
	int currentRow;
	
	JScrollPane scrollCalendarTable;    
	
        /**** Calendar Table Components ***/
	public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;
    
    private void refreshInfoTable(List<Occasion>occasions) {
    	calendarTable.repaint();
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new InfoTableRenderer(calendarTable.getSelectedRow(),occasions));
    }
    
    public String getSelectedOccasion() {
		String dateFrom = (String) calendarTable.getValueAt(currentRow,0);
    	dateFrom = dateFrom.replaceAll("<html>", "");
    	dateFrom = dateFrom.replaceAll("</html>", "");
    	dateFrom = dateFrom.replaceAll("<s>", "");
    	dateFrom = dateFrom.replaceAll("</s>", "");
    	return dateFrom.substring(0,18);
    }
    
	public AgendaProgramView(CalendarControl cc, List<Occasion>occasions){
		
		setBounds(0,0,620,440);
		
		this.cc = cc;
		
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
				refreshInfoTable(occasions);
				
				currentRow = (int) calendarTable.getSelectedRow();
				calendarTable.clearSelection();
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
		
		modelCalendarTable.setColumnCount(2);
		modelCalendarTable.setRowCount(occasions.size());
		
		for(int i = 0 ; i < occasions.size() ; i++) {
			if(occasions.get(i) instanceof Event) {
				if(((Event) occasions.get(i)).getIsDone()) {
					modelCalendarTable.setValueAt("<html><s>"+((Event)occasions.get(i)).getDurationFrom()+" to "+((Event)occasions.get(i)).getDurationTo()+"</s></html>", i, 0);	
				}else {
					modelCalendarTable.setValueAt(((Event)occasions.get(i)).getDurationFrom()+" to "+((Event)occasions.get(i)).getDurationTo(), i, 0);
				}
				
				if(((Event) occasions.get(i)).getIsDone()) {
					modelCalendarTable.setValueAt("<html><s>"+((Event)occasions.get(i)).getInfo()+"</s></html>", i, 1);					
				}else {
					modelCalendarTable.setValueAt(((Event)occasions.get(i)).getInfo(), i, 1);					
				}
			}else if (occasions.get(i) instanceof Task) {
				if(((Task) occasions.get(i)).getIsDone()) {
					modelCalendarTable.setValueAt("<html><s>"+((Task)occasions.get(i)).getDurationFrom()+"</s></html>", i, 0);
				}else {
					modelCalendarTable.setValueAt(((Task)occasions.get(i)).getDurationFrom(), i, 0);
				}
				
				if(((Task) occasions.get(i)).getIsDone()) {
					modelCalendarTable.setValueAt("<html><s>"+((Task)occasions.get(i)).getInfo()+"</s></html>", i, 1);
				}else {
					modelCalendarTable.setValueAt(((Task)occasions.get(i)).getInfo(), i, 1);	
				}
			}
		}
		
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new InfoTableRenderer(calendarTable.getSelectedRow(), occasions));
	}
}
