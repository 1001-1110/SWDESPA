import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class DoctorMenuView extends JFrame {

	private JPanel contentPane;
	
	
    private JRadioButton rdbtnDay;
    private JRadioButton rdbtnWeek;
    private JPanel panelButtons;
    private JPanel panelCalendar;
    private JPanel panelInfo;
    private JPanel panelNotifs;
    
    private JLabel lblName;
    private JButton btnAddNewTimeSlot;
    private JButton btnGoToToday;
    private JButton btnDay;
    private JButton btnTime;
    private JButton btnAgenda;
    private JButton btnDelete;
    private JButton btnEdit;
    private JScrollPane scrollCalendarTable;
    private JTable calendarTable;
    private JPanel panelYear;
    private JPanel panelMonth;
    private JLabel labelMonth;
    private JComboBox cmbYear;
    private JPanel panelMonthButton;
    private JButton btnPrevMonth;
    private JButton btnNextMonth;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorMenuView frame = new DoctorMenuView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DoctorMenuView() {
		setTitle("Doctor Menu");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelButtons  = new JPanel();
		panelButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panelCalendar = new JPanel();
		panelCalendar.setBorder(new LineBorder(Color.BLACK));
		
		panelInfo 	 = new JPanel();
		panelInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panelNotifs   = new JPanel();
		panelNotifs.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panelNotifs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelCalendar, GroupLayout.PREFERRED_SIZE, 259, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panelButtons, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 913, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panelCalendar, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelNotifs, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
						.addComponent(panelInfo, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)))
		);
		GroupLayout gl_panelInfo = new GroupLayout(panelInfo);
		gl_panelInfo.setHorizontalGroup(
			gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addGap(0, 563, Short.MAX_VALUE)
		);
		gl_panelInfo.setVerticalGroup(
			gl_panelInfo.createParallelGroup(Alignment.LEADING)
				.addGap(0, 385, Short.MAX_VALUE)
		);
		panelInfo.setLayout(gl_panelInfo);
		GroupLayout gl_panelNotifs = new GroupLayout(panelNotifs);
		gl_panelNotifs.setHorizontalGroup(
			gl_panelNotifs.createParallelGroup(Alignment.LEADING)
				.addGap(0, 259, Short.MAX_VALUE)
		);
		gl_panelNotifs.setVerticalGroup(
			gl_panelNotifs.createParallelGroup(Alignment.LEADING)
				.addGap(0, 135, Short.MAX_VALUE)
		);
		panelNotifs.setLayout(gl_panelNotifs);
		
		btnAddNewTimeSlot = new JButton("Add New Time Slot");
		btnAddNewTimeSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		scrollCalendarTable = new JScrollPane();
		
		panelYear = new JPanel();
		
		panelMonth = new JPanel();
		
		labelMonth = new JLabel("January");
		GroupLayout gl_panelMonth = new GroupLayout(panelMonth);
		gl_panelMonth.setHorizontalGroup(
			gl_panelMonth.createParallelGroup(Alignment.LEADING)
				.addGap(0, 67, Short.MAX_VALUE)
				.addGroup(gl_panelMonth.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelMonth, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
		);
		gl_panelMonth.setVerticalGroup(
			gl_panelMonth.createParallelGroup(Alignment.LEADING)
				.addGap(0, 17, Short.MAX_VALUE)
				.addGroup(gl_panelMonth.createSequentialGroup()
					.addComponent(labelMonth)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelMonth.setLayout(gl_panelMonth);
		
		cmbYear = new JComboBox();
		GroupLayout gl_panelYear = new GroupLayout(panelYear);
		gl_panelYear.setHorizontalGroup(
			gl_panelYear.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelYear.createSequentialGroup()
					.addComponent(panelMonth, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbYear, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelYear.setVerticalGroup(
			gl_panelYear.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelYear.createSequentialGroup()
					.addGroup(gl_panelYear.createParallelGroup(Alignment.LEADING)
						.addComponent(panelMonth, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbYear, GroupLayout.PREFERRED_SIZE, 17, Short.MAX_VALUE))
					.addContainerGap())
		);
		panelYear.setLayout(gl_panelYear);
		
		panelMonthButton = new JPanel();
		
		btnPrevMonth = new JButton("<");
		btnPrevMonth.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		btnNextMonth = new JButton(">");
		btnNextMonth.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_panelMonthButton = new GroupLayout(panelMonthButton);
		gl_panelMonthButton.setHorizontalGroup(
			gl_panelMonthButton.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelMonthButton.createSequentialGroup()
					.addComponent(btnPrevMonth, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNextMonth, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelMonthButton.setVerticalGroup(
			gl_panelMonthButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMonthButton.createSequentialGroup()
					.addGroup(gl_panelMonthButton.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrevMonth, GroupLayout.PREFERRED_SIZE, 15, Short.MAX_VALUE)
						.addComponent(btnNextMonth, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelMonthButton.setLayout(gl_panelMonthButton);
		GroupLayout gl_panelCalendar = new GroupLayout(panelCalendar);
		gl_panelCalendar.setHorizontalGroup(
			gl_panelCalendar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCalendar.createSequentialGroup()
					.addGroup(gl_panelCalendar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCalendar.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelCalendar.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollCalendarTable, 0, 0, Short.MAX_VALUE)
								.addGroup(gl_panelCalendar.createSequentialGroup()
									.addComponent(panelYear, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panelMonthButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelCalendar.createSequentialGroup()
							.addGap(60)
							.addComponent(btnAddNewTimeSlot, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelCalendar.setVerticalGroup(
			gl_panelCalendar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCalendar.createSequentialGroup()
					.addComponent(btnAddNewTimeSlot, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCalendar.createParallelGroup(Alignment.LEADING)
						.addComponent(panelYear, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelMonthButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollCalendarTable, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		calendarTable = new JTable();
		scrollCalendarTable.setViewportView(calendarTable);
		panelCalendar.setLayout(gl_panelCalendar);
		
		lblName 		= new JLabel("");
		
		btnGoToToday = new JButton("Go To Today");
		
		btnTime 	= new JButton("Time");
		btnTime.setMinimumSize(new Dimension(69, 23));
		btnTime.setMaximumSize(new Dimension(69, 23));
		btnTime.setPreferredSize(new Dimension(69, 23));
		
		btnAgenda = new JButton("Agenda");
		
		btnDelete = new JButton("Delete");
		
		btnEdit = new JButton("Edit");
		GroupLayout gl_panelButtons = new GroupLayout(panelButtons);
		gl_panelButtons.setHorizontalGroup(
			gl_panelButtons.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelButtons.createSequentialGroup()
					.addGroup(gl_panelButtons.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelButtons.createSequentialGroup()
							.addComponent(lblName)
							.addGap(52)
							.addComponent(btnGoToToday)
							.addPreferredGap(ComponentPlacement.RELATED, 627, Short.MAX_VALUE))
						.addGroup(gl_panelButtons.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnEdit)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panelButtons.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelButtons.createSequentialGroup()
							.addComponent(btnDelete)
							.addGap(34)
							.addComponent(btnAgenda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(btnTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelButtons.setVerticalGroup(
			gl_panelButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelButtons.createSequentialGroup()
					.addGroup(gl_panelButtons.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelButtons.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblName))
						.addGroup(gl_panelButtons.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnGoToToday)
							.addComponent(btnTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAgenda)
					.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelButtons.createSequentialGroup()
					.addContainerGap(49, Short.MAX_VALUE)
					.addGroup(gl_panelButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDelete)
						.addComponent(btnEdit))
					.addContainerGap())
		);
		panelButtons.setLayout(gl_panelButtons);
		contentPane.setLayout(gl_contentPane);
		setVisible(false);
		
		
		
		
		
	}
}
