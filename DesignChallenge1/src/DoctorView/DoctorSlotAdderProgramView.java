package DoctorView;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorSlotAdderProgramView extends JFrame implements DoctorSlotAdderView{

//	private DoctorProgramModel dpm;
//	private DoctorProgramControl dpc;
	private DoctorMenuProgramView dmv;
	
	private JPanel contentPane;
	private JButton btnSave;
	private JButton btnDiscard;
	private JTextField txtDateStart;
	private JTextField txtTimeStart;
	private JTextField txtTimeEnd;
	private JLabel lblDateMm;
	private JLabel lblNewLabel;
	private JLabel lblToTime;
	private ButtonGroup btnTypeGroup;
	private JRadioButton rdbtnRepeating;
	private JRadioButton rdbtnSingle;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorMenuProgramView testdmv = new DoctorMenuProgramView();
					DoctorSlotAdderProgramView frame = new DoctorSlotAdderProgramView(testdmv);
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
	public DoctorSlotAdderProgramView(DoctorMenuProgramView dmv) {
		this.dmv = dmv;
		initialize();
	}
	
	public void initialize() {
		setTitle("Doctor Slot Adder");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send input to control
				txtDateStart.setText(null);
				txtTimeStart.setText(null);
				txtTimeEnd.setText(null);
				
			}
		});
		btnSave.setBounds(132, 183, 67, 23);
		contentPane.add(btnSave);
		
		btnDiscard = new JButton("Discard");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDateStart.setText(null);
				txtTimeStart.setText(null);
				txtTimeEnd.setText(null);
				setVisible(false);
			}
		});
		btnDiscard.setBounds(229, 183, 79, 23);
		contentPane.add(btnDiscard);
		
		txtDateStart = new JTextField();
		txtDateStart.setBounds(132, 77, 176, 20);
		contentPane.add(txtDateStart);
		txtDateStart.setColumns(10);
		
		txtTimeStart = new JTextField();
		txtTimeStart.setBounds(132, 122, 72, 20);
		contentPane.add(txtTimeStart);
		txtTimeStart.setColumns(10);
		
		txtTimeEnd = new JTextField();
		txtTimeEnd.setBounds(229, 122, 79, 20);
		contentPane.add(txtTimeEnd);
		txtTimeEnd.setColumns(10);
		
		lblDateMm = new JLabel("Date (MM/DD/YYYY):");
		lblDateMm.setBounds(11, 80, 111, 14);
		contentPane.add(lblDateMm);
		
		lblNewLabel = new JLabel("Time (24 hour): ");
		lblNewLabel.setBounds(37, 125, 93, 14);
		contentPane.add(lblNewLabel);
		
		lblToTime = new JLabel("to:");
		lblToTime.setBounds(210, 125, 20, 14);
		contentPane.add(lblToTime);
		
		rdbtnRepeating = new JRadioButton("Repeating");
		rdbtnRepeating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblToTime.setEnabled(true);
				txtTimeEnd.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnRepeating);
		rdbtnRepeating.setBounds(229, 47, 109, 23);
		contentPane.add(rdbtnRepeating);
		
		rdbtnSingle = new JRadioButton("Single");
		rdbtnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		rdbtnSingle.setSelected(true);
		buttonGroup.add(rdbtnSingle);
		rdbtnSingle.setBounds(132, 47, 67, 23);
		contentPane.add(rdbtnSingle);
		
		
	}
	
	public void clearInputs() {
		txtDateStart.setText(null);
		txtTimeStart.setText(null);
		txtTimeEnd.setText(null);
	}
	
	public void updateCurrentDate(int currentSelectedYear,int currentSelectedMonth, int currentSelectedDay) {
		txtDateStart.setText(currentSelectedMonth+1+"/"+currentSelectedDay+"/"+currentSelectedYear);
	}
}
