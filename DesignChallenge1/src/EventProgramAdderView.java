
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class EventProgramAdderView extends JPanel implements EventAdderView{
	
	private CalendarControl cc;
	
	private JTextField info;
	private JTextField durationFrom;
	private JTextField dateFrom;
	private JTextField durationTo;
	private JRadioButton rdbtnEvent;
	private JRadioButton rdbtnTask;
	private JTextField dateTo;
	private JLabel lblDateTo;
	private JLabel lblTimeTo;

	public EventProgramAdderView(CalendarControl cc) {
		this.cc = cc;
		setBorder(new LineBorder(new Color(0, 0, 0)));
	}
	
	public void updateCurrentDate(int currentSelectedYear,int currentSelectedMonth, int currentSelectedDay) {
		dateFrom.setText(currentSelectedMonth+1+"/"+currentSelectedDay+"/"+currentSelectedYear);
		dateTo.setText(currentSelectedMonth+1+"/"+currentSelectedDay+"/"+currentSelectedYear);		
	}
	
	public void updateInvalidInput() {
		JOptionPane.showMessageDialog(this,"Invalid input.","Invalid", JOptionPane.ERROR_MESSAGE);
	}
	
	public void clearInputs() {
		info.setText(null);
		durationFrom.setText(null);
		durationTo.setText(null);
	}
	
	public void initialize() {
		
		setBounds(100, 100, 450, 190);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cc.addOccasion(info.getText(), dateFrom.getText(), dateTo.getText(), durationFrom.getText(), durationTo.getText(), rdbtnEvent.isSelected(), rdbtnTask.isSelected())) {
					updateInvalidInput();
				}else {
					cc.refreshCalendarDays();
				}
			}
		});
		
		JButton btnDiscard = new JButton("Discard");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JPanel inputPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDiscard))
						.addComponent(inputPanel, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDiscard)
						.addComponent(btnSave))
					.addContainerGap())
		);
		
		JLabel lblInfo = new JLabel("Info:");
		
		JLabel lblDate = new JLabel("MM/DD/YYYY:");
		
		JLabel lblTime = new JLabel("Time (24-Hour):");
		
		info = new JTextField();
		info.setColumns(10);
		
		dateFrom = new JTextField();
		dateFrom.setColumns(10);
		
		durationFrom = new JTextField();
		durationFrom.setColumns(10);
		
		lblTimeTo = new JLabel("to");
		
		durationTo = new JTextField();
		durationTo.setColumns(10);
		
		ButtonGroup type = new ButtonGroup();
		
		rdbtnEvent = new JRadioButton("Event");
		rdbtnEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDateTo.setEnabled(true);
				dateTo.setEnabled(true);
				lblTimeTo.setEnabled(true);
				durationTo.setEnabled(true);
			}
		});
		
		rdbtnTask = new JRadioButton("Task");
		rdbtnTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDateTo.setEnabled(false);
				dateTo.setEnabled(false);
				lblTimeTo.setEnabled(false);
				durationTo.setEnabled(false);
			}
		});
		
		
		type.add(rdbtnEvent);
		type.add(rdbtnTask);

		rdbtnEvent.setSelected(true);
		
		lblDateTo = new JLabel("to");
		
		dateTo = new JTextField();
		dateTo.setText("1/0/0");
		dateTo.setColumns(10);
		
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_inputPanel.createSequentialGroup()
									.addComponent(lblInfo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(info))
								.addGroup(gl_inputPanel.createSequentialGroup()
									.addComponent(lblDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblDateTo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateTo, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
							.addGap(47)
							.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTask)
								.addComponent(rdbtnEvent))
							.addGap(52))
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addComponent(lblTime)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(durationFrom, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTimeTo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(durationTo, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(228, Short.MAX_VALUE))))
		);
		gl_inputPanel.setVerticalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInfo)
						.addComponent(info, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnEvent))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate)
						.addComponent(dateFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDateTo)
						.addComponent(dateTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnTask))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTime)
						.addComponent(durationFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTimeTo)
						.addComponent(durationTo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		inputPanel.setLayout(gl_inputPanel);
		this.setLayout(groupLayout);
		
		setVisible(true);
	}
}
