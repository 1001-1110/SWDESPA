
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class CalendarProgramView implements CalendarView{

	public int yearBound, monthBound, dayBound, yearToday, monthToday;
	
	CalendarControl cc;
	
        /**** Swing Components ****/
    public JLabel monthLabel;
	public JButton btnPrev, btnNext;
    public JComboBox cmbYear;
	public JFrame frmMain;
	public Container pane;
	public JScrollPane scrollCalendarTable;
	public JPanel calendarPanel;
        
        /**** Calendar Table Components ***/
	public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;
    private JPanel buttonPanel;
    private JPanel yearPanel;
    
    public void refreshCalendar() {
    	refreshCalendar(monthToday, yearToday);
    }
    
    public void refreshCalendar(int month, int year){
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		
		btnPrev.setContentAreaFilled(false);
		
		if (month == 0 && year <= yearBound-10)
                    btnPrev.setEnabled(false);
		if (month == 11 && year >= yearBound+100)
                    btnNext.setEnabled(false);
                
		monthLabel.setText(months[month]);
                
		cmbYear.setSelectedItem(""+year);
		
		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		for (i = 1; i <= nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;	
        	String eventText = "<html>"+i+"<br>";
        	try {
    			ArrayList<Event> datedevents = cc.checkDateEvent(month, i, year); 
                if(datedevents.size() > 0) {
                	for(int e = 0 ; e < datedevents.size() ; e++) {
                		eventText += "<font color=\""+datedevents.get(e).getColorString()+"\""+">"+datedevents.get(e).getInfo() +"</font>"+ "<br>";
                	}
                }    			
        	}catch(NullPointerException e) {}
        	eventText += "</html>";
			modelCalendarTable.setValueAt(eventText, row, column);
        }

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
	}
        
	public CalendarProgramView(CalendarControl cc)
        {
		
		this.cc = cc;
		
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
                
		frmMain = new JFrame ("Calendar Application");
                frmMain.setSize(660, 700);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modelCalendarTable = new DefaultTableModel()
                {
                    public boolean isCellEditable(int rowIndex, int mColIndex)
                    {
                        return true;
                    }
                };
                
		calendarTable = new JTable(modelCalendarTable);
                calendarTable.addMouseListener(new MouseAdapter()   
                {  
                    public void mouseClicked(MouseEvent evt)  
                    {  
                        int col = calendarTable.getSelectedColumn();  
                        int row = calendarTable.getSelectedRow();  
                    }
                });
                
		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		calendarPanel.setBorder(null);
		
		pane.add(calendarPanel);
		
                calendarPanel.setBounds(10, 11, 285, 276);
                
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);
		monthToday = monthBound; 
		yearToday = yearBound;
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(29);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.setForeground(Color.RED);
		btnCreate.setBackground(Color.RED);
		
		buttonPanel = new JPanel();
		
		yearPanel = new JPanel();
		GroupLayout gl_calendarPanel = new GroupLayout(calendarPanel);
		gl_calendarPanel.setHorizontalGroup(
			gl_calendarPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_calendarPanel.createSequentialGroup()
					.addGap(36)
					.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(42, Short.MAX_VALUE))
				.addGroup(gl_calendarPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_calendarPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollCalendarTable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addGroup(gl_calendarPanel.createSequentialGroup()
							.addComponent(yearPanel, GroupLayout.PREFERRED_SIZE, 143, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
					.addGap(31))
		);
		gl_calendarPanel.setVerticalGroup(
			gl_calendarPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_calendarPanel.createSequentialGroup()
					.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_calendarPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(buttonPanel, 0, 0, Short.MAX_VALUE)
						.addComponent(yearPanel, GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollCalendarTable, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		cmbYear = new JComboBox();
		
		JPanel monthPanel = new JPanel();
		
				monthLabel = new JLabel ("January");
				GroupLayout gl_monthPanel = new GroupLayout(monthPanel);
				gl_monthPanel.setHorizontalGroup(
					gl_monthPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_monthPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(monthLabel, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
				);
				gl_monthPanel.setVerticalGroup(
					gl_monthPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_monthPanel.createSequentialGroup()
							.addComponent(monthLabel)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				monthPanel.setLayout(gl_monthPanel);
		GroupLayout gl_yearPanel = new GroupLayout(yearPanel);
		gl_yearPanel.setHorizontalGroup(
			gl_yearPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_yearPanel.createSequentialGroup()
					.addComponent(monthPanel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbYear, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_yearPanel.setVerticalGroup(
			gl_yearPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_yearPanel.createSequentialGroup()
					.addGroup(gl_yearPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(monthPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(cmbYear, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 17, Short.MAX_VALUE))
					.addContainerGap())
		);
		yearPanel.setLayout(gl_yearPanel);
		cmbYear.addActionListener(new cmbYear_Action());
		btnPrev = new JButton ("<");
		btnPrev.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext = new JButton (">");
		btnNext.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNext.setContentAreaFilled(false);
		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addGap(2)
					.addComponent(btnPrev, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_buttonPanel.setVerticalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_buttonPanel.createSequentialGroup()
					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrev, GroupLayout.PREFERRED_SIZE, 15, Short.MAX_VALUE)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		buttonPanel.setLayout(gl_buttonPanel);
		btnNext.addActionListener(new btnNext_Action());
		calendarPanel.setLayout(gl_calendarPanel);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
		for (int i = yearBound-100; i <= yearBound+100; i++)
                {
			cmbYear.addItem(String.valueOf(i));
		}
		
		refreshCalendar (monthBound, yearBound); //Refresh calendar
	}
	

	class btnPrev_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (monthToday == 0)
                        {
				monthToday = 11;
				yearToday -= 1;
			}
			else
                        {
				monthToday -= 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	class btnNext_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (monthToday == 11)
                        {
				monthToday = 0;
				yearToday += 1;
			}
			else
                        {
				monthToday += 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	class cmbYear_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (cmbYear.getSelectedItem() != null)
                        {
				String b = cmbYear.getSelectedItem().toString();
				yearToday = Integer.parseInt(b);
				refreshCalendar(monthToday, yearToday);
			}
		}
	}
}
