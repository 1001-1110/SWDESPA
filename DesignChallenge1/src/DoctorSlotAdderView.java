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

public class DoctorSlotAdderView extends JFrame {

	private JPanel contentPane;
	private JButton btnSave;
	private JButton btnDiscard;
	private JTextField txtDateStart;
	private JTextField txtDateEnd;
	private JTextField txtTimeStart;
	private JTextField txtTimeEnd;
	private JLabel lblDateMm;
	private JLabel lblNewLabel;
	private JLabel lblToDate;
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
					DoctorSlotAdderView frame = new DoctorSlotAdderView();
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
	public DoctorSlotAdderView() {
		setTitle("Doctor Slot Adder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(132, 183, 67, 23);
		contentPane.add(btnSave);
		
		btnDiscard = new JButton("Discard");
		btnDiscard.setBounds(229, 183, 67, 23);
		contentPane.add(btnDiscard);
		
		txtDateStart = new JTextField();
		txtDateStart.setBounds(132, 77, 67, 20);
		contentPane.add(txtDateStart);
		txtDateStart.setColumns(10);
		
		txtDateEnd = new JTextField();
		txtDateEnd.setEnabled(false);
		txtDateEnd.setBounds(229, 77, 67, 20);
		contentPane.add(txtDateEnd);
		txtDateEnd.setColumns(10);
		
		txtTimeStart = new JTextField();
		txtTimeStart.setBounds(132, 122, 67, 20);
		contentPane.add(txtTimeStart);
		txtTimeStart.setColumns(10);
		
		txtTimeEnd = new JTextField();
		txtTimeEnd.setEnabled(false);
		txtTimeEnd.setBounds(229, 122, 67, 20);
		contentPane.add(txtTimeEnd);
		txtTimeEnd.setColumns(10);
		
		lblDateMm = new JLabel("Date (MM/DD/YYYY):");
		lblDateMm.setBounds(11, 80, 111, 14);
		contentPane.add(lblDateMm);
		
		lblNewLabel = new JLabel("Time (24 hour): ");
		lblNewLabel.setBounds(37, 125, 93, 14);
		contentPane.add(lblNewLabel);
		
		lblToDate = new JLabel("to:");
		lblToDate.setEnabled(false);
		lblToDate.setBounds(209, 80, 20, 14);
		contentPane.add(lblToDate);
		
		lblToTime = new JLabel("to:");
		lblToTime.setEnabled(false);
		lblToTime.setBounds(210, 125, 20, 14);
		contentPane.add(lblToTime);
		
		rdbtnRepeating = new JRadioButton("Repeating");
		rdbtnRepeating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblToDate.setEnabled(true);
				lblToTime.setEnabled(true);
				txtDateEnd.setEnabled(true);
				txtTimeEnd.setEnabled(true);
			}
		});
		rdbtnRepeating.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblToDate.setEnabled(true);
				lblToTime.setEnabled(true);
				txtDateEnd.setEnabled(true);
				txtTimeEnd.setEnabled(true);
				
			}
		});
		buttonGroup.add(rdbtnRepeating);
		rdbtnRepeating.setBounds(229, 47, 109, 23);
		contentPane.add(rdbtnRepeating);
		
		rdbtnSingle = new JRadioButton("Single");
		rdbtnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblToDate.setEnabled(false);
				lblToTime.setEnabled(false);
				txtDateEnd.setEnabled(false);
				txtTimeEnd.setEnabled(false);
			}
		});
		rdbtnSingle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblToDate.setEnabled(false);
				lblToTime.setEnabled(false);
				txtDateEnd.setEnabled(false);
				txtTimeEnd.setEnabled(false);
			}
		});
		rdbtnSingle.setSelected(true);
		buttonGroup.add(rdbtnSingle);
		rdbtnSingle.setBounds(132, 47, 67, 23);
		contentPane.add(rdbtnSingle);
		
		
	}
}
