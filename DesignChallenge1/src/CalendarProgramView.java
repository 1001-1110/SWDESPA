
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class CalendarProgramView implements CalendarView{

	private int yearBound, monthBound, dayBound, yearToday, monthToday, dayToday;
	private int currentSelectedMonth, currentSelectedDay, currentSelectedYear;
	
	CalendarControl cc;
	EventAdderView ea;
	DayView dv;
	AgendaView av;
	
        /**** Swing Components ****/
    public JLabel monthLabel;
	public JButton btnPrev, btnNext;
    public JComboBox<String> cmbYear;
	public JFrame frmMain;
	public Container pane;
	public JScrollPane scrollCalendarTable;
	public JPanel calendarPanel;
        
        /**** Calendar Table Components ***/
	public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;
    private JPanel buttonPanel;
    private JPanel yearPanel;
    private JPanel filterPanel;
    private JPanel panel;
    private JPanel infoBorderPanel;
    private JPanel panel_2;
    private JLabel lblTitle;
    private JButton btnToday;
    private JLabel lblCurrentDate;
    private JButton btnDay;
    private JButton btnAgenda;
    private JButton btnByDay;
    private JButton btnByWeek;
    private JPanel infoPanel;
    private JCheckBox eventFilter;
    private JCheckBox taskFilter;
    
    public void attachController(CalendarControl cc) {
    	this.cc = cc;
    }
    
    public void initialize() {
    	refreshCalendar();
		cc.updateDateTitle(yearToday, monthToday, dayToday);
		refreshCalendar();
		cc.updateViews(currentSelectedYear, currentSelectedMonth, currentSelectedDay, eventFilter.isSelected(), taskFilter.isSelected());
    }
    
    public void updateViews(List<Occasion>occasions) {
    	ea = new EventProgramAdderView(cc);
    	dv = new DayProgramView(cc);
    	av = new AgendaProgramView(cc,occasions);
    	if(infoPanel.getComponentCount() > 0) {
    		Component comp = infoPanel.getComponent(0);
    		infoPanel.removeAll();
    		if(comp instanceof EventAdderView) {
    			infoPanel.add((Component) ea);
    		}if(comp instanceof DayView) {
    			infoPanel.add((Component) dv);
    		}if(comp instanceof AgendaView) {
    			infoPanel.add((Component) av);
    		}
    	}
		infoPanel.revalidate();
		refreshView();
    }
    
	public void updateNotification(boolean success, String type) {
		if(success) {
			JOptionPane.showMessageDialog((Component) ea,type+" added successfully."); 	
			ea.clearInputs();
		}else {
			JOptionPane.showMessageDialog((Component) ea,type+" overlaps with existing schedule.","Inane error", JOptionPane.ERROR_MESSAGE);			
		}
	}
    
    public void showEventAdder() {
    	ea = new EventProgramAdderView(cc);
		infoPanel.removeAll();
		infoPanel.add((Component) ea);
		infoPanel.revalidate();
		refreshView();
    }
    
    public void showDayView() {
		infoPanel.removeAll();
		infoPanel.add((Component) dv);
		infoPanel.revalidate();
		refreshView();    	
    }
    
    public void showAgendaView() {
    	cc.updateViews(currentSelectedYear, currentSelectedMonth, currentSelectedDay, eventFilter.isSelected(), taskFilter.isSelected());
    	infoPanel.removeAll();
		infoPanel.add((Component) av);
		infoPanel.revalidate();
		refreshView();   	
    }
    
    public void refreshView() {
    	frmMain.repaint();
    }
    
    public void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
    	this.currentSelectedYear = currentSelectedYear;
    	this.currentSelectedMonth = currentSelectedMonth;
    	this.currentSelectedDay = currentSelectedDay;
    	String currentDate = new String();
    	currentDate += monthLabel.getText();
    	currentDate += " "+currentSelectedDay;
    	currentDate += ", "+currentSelectedYear;
    	lblCurrentDate.setText(currentDate);
    }

    public void refreshCalendar() {
    	this.refreshCalendar(monthToday, yearToday);
    }    
    
    public void refreshCalendar(int month, int year){
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		
		btnPrev.setContentAreaFilled(false);
		
		if (month == 0 && year <= yearBound-100)
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
			modelCalendarTable.setValueAt(" "+i, row, column);
        }

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer(month, year, currentSelectedMonth, currentSelectedDay, currentSelectedYear));
		calendarTable.clearSelection();
    }
        
	public CalendarProgramView()
        {
		
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
                
		frmMain = new JFrame ("Productivity Tool");
                frmMain.setSize(1000, 600);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modelCalendarTable = new DefaultTableModel()
                {
                    public boolean isCellEditable(int rowIndex, int mColIndex)
                    {
                        return false;
                    }
                };
                
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);
		monthToday = monthBound; 
		yearToday = yearBound;
		dayToday = dayBound;
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 974, 81);
		frmMain.getContentPane().add(panel);
		
		lblTitle = new JLabel("Pr0ductivity t00Lz");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnToday = new JButton("Go to Today");
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monthToday = cal.get(GregorianCalendar.MONTH);
				yearToday = cal.get(GregorianCalendar.YEAR);
				dayToday = cal.get(GregorianCalendar.DATE);
				refreshCalendar(monthToday,yearToday);
				cc.updateDateTitle(yearToday,monthToday,dayToday);
				refreshCalendar(monthToday,yearToday);
				cc.updateViews(currentSelectedYear, currentSelectedMonth, currentSelectedDay, eventFilter.isSelected(), taskFilter.isSelected());
			}
		});
		
		lblCurrentDate = new JLabel();
		lblCurrentDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnDay = new JButton("Day");
		btnDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAgenda = new JButton("Agenda");
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAgendaView();
			}
		});
		
		btnByDay = new JButton("Select by Day");
		btnByDay.setEnabled(false);
		btnByWeek = new JButton("Select by Week");
		btnByWeek.setEnabled(false);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTitle)
							.addGap(18)
							.addComponent(btnToday))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnByDay)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnByWeek)))
					.addGap(48)
					.addComponent(lblCurrentDate, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnDay)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAgenda)
					.addContainerGap(331, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCurrentDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDay)
								.addComponent(btnAgenda)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnToday)
								.addComponent(lblTitle))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnByDay)
								.addComponent(btnByWeek))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		infoBorderPanel = new JPanel();
		infoBorderPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		infoBorderPanel.setBounds(338, 98, 646, 463);
		frmMain.getContentPane().add(infoBorderPanel);
		
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		GroupLayout gl_infoBorderPanel = new GroupLayout(infoBorderPanel);
		gl_infoBorderPanel.setHorizontalGroup(
			gl_infoBorderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoBorderPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_infoBorderPanel.setVerticalGroup(
			gl_infoBorderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoBorderPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		infoBorderPanel.setLayout(gl_infoBorderPanel);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 98, 318, 463);
		frmMain.getContentPane().add(panel_2);
		
		calendarTable = new JTable(modelCalendarTable);
		calendarTable.clearSelection();
		calendarTable.addMouseListener(new MouseAdapter()   
		{  
		    public void mouseClicked(MouseEvent evt)  
		    {  
		        int col = calendarTable.getSelectedColumn();  
		        int row = calendarTable.getSelectedRow(); 
		        if(modelCalendarTable.getValueAt(row, col) != null) {
			        String day =  modelCalendarTable.getValueAt(row, col).toString().trim();
				    refreshCalendar();
			        cc.updateDateTitle(yearToday,monthToday,Integer.parseInt(day));
				    refreshCalendar();		
				    cc.updateViews(currentSelectedYear, currentSelectedMonth, currentSelectedDay, eventFilter.isSelected(), taskFilter.isSelected());
		        }
		    }
		});

		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);
        calendarPanel.setBorder(null);
        
        calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background
        
                calendarTable.getTableHeader().setResizingAllowed(false);
                calendarTable.getTableHeader().setReorderingAllowed(false);
                
                        calendarTable.setColumnSelectionAllowed(true);
                        calendarTable.setRowSelectionAllowed(true);
                        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        
                                calendarTable.setRowHeight(29);
                                
                                JButton btnCreate = new JButton("CREATE");
                                btnCreate.addActionListener(new ActionListener() {
                                	public void actionPerformed(ActionEvent e) {
                                		showEventAdder();
                                	}
                                });
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
		
		filterPanel = new JPanel();
		filterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblView = new JLabel("View");
		lblView.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		eventFilter = new JCheckBox("Event");
		eventFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cc.updateViews(currentSelectedYear, currentSelectedMonth, currentSelectedDay, eventFilter.isSelected(), taskFilter.isSelected());
			}
		});
		eventFilter.setIconTextGap(10);
		
		taskFilter = new JCheckBox("Task");
		taskFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cc.updateViews(currentSelectedYear, currentSelectedMonth, currentSelectedDay, eventFilter.isSelected(), taskFilter.isSelected());
			}
		});
		taskFilter.setIconTextGap(10);
		GroupLayout gl_filterPanel = new GroupLayout(filterPanel);
		gl_filterPanel.setHorizontalGroup(
			gl_filterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filterPanel.createSequentialGroup()
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filterPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblView))
						.addGroup(gl_filterPanel.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_filterPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(taskFilter)
								.addComponent(eventFilter))))
					.addContainerGap(183, Short.MAX_VALUE))
		);
		gl_filterPanel.setVerticalGroup(
			gl_filterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filterPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblView)
					.addGap(7)
					.addComponent(eventFilter)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(taskFilter)
					.addGap(82))
		);
		filterPanel.setLayout(gl_filterPanel);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(calendarPanel, GroupLayout.PREFERRED_SIZE, 301, Short.MAX_VALUE)
							.addGap(5))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(filterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(19, Short.MAX_VALUE))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(calendarPanel, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(filterPanel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
		for (int i = yearBound-100; i <= yearBound+100; i++)
        {
			cmbYear.addItem(String.valueOf(i));
		}

		this.refreshCalendar(monthBound, yearBound);

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
