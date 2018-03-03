import java.awt.EventQueue;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventProgramAdder extends JPanel implements EventAdder{
	
	private JTextField info;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JRadioButton rdbtnEvent;
	private JRadioButton rdbtnTask;

	public EventProgramAdder() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 450, 171);
		
		GregorianCalendar cal = new GregorianCalendar();
		int yearBound = cal.get(GregorianCalendar.YEAR);
		int monthBound = cal.get(GregorianCalendar.MONTH);
		int dayBound = cal.get(GregorianCalendar.DATE);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ep.addEvent(info.getText(),(int)month.getSelectedItem(),(int)day.getSelectedItem(),(int)year.getSelectedItem(),(String)colorselect.getSelectedItem());
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
						.addComponent(inputPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDiscard)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDiscard)
						.addComponent(btnSave))
					.addContainerGap())
		);
		
		JLabel lblInfo = new JLabel("Info:");
		
		JLabel lblDate = new JLabel("MM/DD/YYYY:");
		
		JLabel lblTime = new JLabel("Time:");
		
				
				
				
				JLabel lblColor = new JLabel("Color: ");
		
		info = new JTextField();
		info.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblTo = new JLabel("to");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JComboBox colorselect = new JComboBox();
		colorselect.addItem("Blue");
		colorselect.addItem("Green");
		colorselect.addItem("Red");
		
		rdbtnEvent = new JRadioButton("Event");
		rdbtnEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnTask.isSelected())
					rdbtnTask.setSelected(false);
			}
		});
		
		rdbtnTask = new JRadioButton("Task");
		rdbtnTask.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnEvent.isSelected())
					rdbtnEvent.setSelected(false);
			}
		});
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addComponent(lblTime)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addComponent(lblColor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(colorselect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputPanel.createSequentialGroup()
									.addComponent(lblInfo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(info, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_inputPanel.createSequentialGroup()
									.addComponent(lblDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTask)
								.addComponent(rdbtnEvent))))
					.addContainerGap(66, Short.MAX_VALUE))
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
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnTask))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTime)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTo)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColor)
						.addComponent(colorselect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		inputPanel.setLayout(gl_inputPanel);
		this.setLayout(groupLayout);
		setVisible(true);
	}
}
