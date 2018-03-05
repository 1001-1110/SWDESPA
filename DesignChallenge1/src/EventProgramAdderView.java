
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventProgramAdderView extends JPanel implements EventAdderView{
	
	private CalendarControl cc;
	
	private JTextField info;
	private JTextField durationFrom;
	private JTextField date;
	private JTextField durationTo;
	private JRadioButton rdbtnEvent;
	private JRadioButton rdbtnTask;

	public EventProgramAdderView(CalendarControl cc) {
		this.cc = cc;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		initialize();
		clearInputs();
	}
	
	public void clearInputs() {
		info.setText(null);
		durationFrom.setText(null);
		date.setText(null);
		durationTo.setText(null);
	}
	
	private void initialize() {
		setBounds(100, 100, 450, 190);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cc.addOccasion(info.getText(), date.getText(), durationFrom.getText(), durationTo.getText(), rdbtnEvent.isSelected(), rdbtnTask.isSelected());
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
		
		JLabel lblTime = new JLabel("Time:");
		
		info = new JTextField();
		info.setColumns(10);
		
		date = new JTextField();
		date.setColumns(10);
		
		durationFrom = new JTextField();
		durationFrom.setColumns(10);
		
		JLabel lblTo = new JLabel("to");
		
		durationTo = new JTextField();
		durationTo.setColumns(10);
		
		ButtonGroup type = new ButtonGroup();
		
		rdbtnEvent = new JRadioButton("Event");
		rdbtnTask = new JRadioButton("Task");
		
		type.add(rdbtnEvent);
		type.add(rdbtnTask);

		rdbtnEvent.setSelected(true);
		
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addComponent(lblTime)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(durationFrom, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(durationTo, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputPanel.createSequentialGroup()
									.addComponent(lblInfo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(info, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_inputPanel.createSequentialGroup()
									.addComponent(lblDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTask)
								.addComponent(rdbtnEvent))))
					.addContainerGap(120, Short.MAX_VALUE))
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
						.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnTask))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTime)
						.addComponent(durationFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTo)
						.addComponent(durationTo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		inputPanel.setLayout(gl_inputPanel);
		this.setLayout(groupLayout);
		setVisible(true);
	}
}
