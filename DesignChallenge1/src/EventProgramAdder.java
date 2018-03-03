import java.awt.EventQueue;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class EventProgramAdder extends JFrame implements EventAdder{
	
	private EventParser ep;
	private JTextField info;

	public EventProgramAdder(EventParser ep) {
		this.ep = ep;
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAddEvent = new JLabel("EVENT ADDER");
		
		info = new JTextField();
		info.setColumns(10);
		
		GregorianCalendar cal = new GregorianCalendar();
		int yearBound = cal.get(GregorianCalendar.YEAR);
		int monthBound = cal.get(GregorianCalendar.MONTH);
		int dayBound = cal.get(GregorianCalendar.DATE);
		
		JComboBox day = new JComboBox();
		
		for(int i = 1 ; i < 32 ; i++) {
			day.addItem(i);
		}
		
		JComboBox year = new JComboBox();
		for(int i = yearBound-100 ; i <= yearBound+100 ; i++) {
			year.addItem(i);
		}		
		JComboBox month = new JComboBox();
		for(int i = 1 ; i < 13 ; i++) {
			month.addItem(i);
		}
		
		JComboBox colorselect = new JComboBox();
		colorselect.addItem("Blue");
		colorselect.addItem("Green");
		colorselect.addItem("Red");
		
		day.setSelectedIndex(dayBound-1);
		month.setSelectedIndex(monthBound);
		year.setSelectedIndex(yearBound - (yearBound-100));
		
		JLabel lblInfo = new JLabel("Info:");
		
		JLabel lblDate = new JLabel("MM/DD/YYYY:");
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ep.addEvent(info.getText(),(int)month.getSelectedItem(),(int)day.getSelectedItem(),(int)year.getSelectedItem(),(String)colorselect.getSelectedItem());
			}
		});

		
		
		
		JLabel lblColor = new JLabel("Color: ");
		
		JButton btnAddRecurringEvent = new JButton("Add Recurring Event");
		btnAddRecurringEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ep.addRecurEvent(info.getText(),(int)month.getSelectedItem(),(int)day.getSelectedItem(),(int)year.getSelectedItem(),(String)colorselect.getSelectedItem());
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(173)
					.addComponent(lblAddEvent)
					.addContainerGap(193, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInfo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(info, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDate)
								.addComponent(lblColor))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(colorselect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(month, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(day, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(year, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddEvent)
						.addComponent(btnAddRecurringEvent))
					.addGap(86))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAddEvent)
									.addGap(49))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblInfo)
										.addComponent(info, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDate)
								.addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(colorselect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblColor)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(btnAddEvent)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAddRecurringEvent)))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
