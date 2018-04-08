import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private DoctorMenuView dmv;
//	private DoctorProgramModel dpm;
//	private DoctorProgramControl dpc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorMenuView dv = new DoctorMenuView();
					DoctorLoginView frame = new DoctorLoginView(dv);
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
	public DoctorLoginView(DoctorMenuView dmv) {
		super("Doctor Login");
		this.dmv = dmv;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
//		usernameField.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if("DoctorPau".equalsIgnoreCase(usernameField.getText()) || "DoctorJoanne".equalsIgnoreCase(usernameField.getText())) {
//					//login
//					System.out.println("Tst");
//					JOptionPane.showMessageDialog(null, "Login Successful");
//				}else {
//					JOptionPane.showMessageDialog(null, "Doctor not identified, please try again.");
//				}
//			}
//		});
		usernameField.setBounds(101, 55, 237, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("password".equalsIgnoreCase(new String(passwordField.getPassword()))) {
					//login
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Login Successful");
					dmv.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Wrong Password, please try again.");
				}
			}
		});
		passwordField.setBounds(101, 102, 237, 20);
		contentPane.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(10, 57, 81, 17);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 105, 81, 14);
		contentPane.add(lblPassword);
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("password".equalsIgnoreCase(new String(passwordField.getPassword()))) {
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Login Successful");
					dmv.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Wrong Password. Please Try Again.");
				}
			}
		});
		btnLogin.setBounds(136, 161, 89, 23);
		contentPane.add(btnLogin);
		
		setVisible(true);
	}
}
