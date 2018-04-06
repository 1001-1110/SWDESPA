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

public class DoctorLogin extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorLogin frame = new DoctorLogin();
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
	public DoctorLogin() {
		super("Doctor Login");
		String password = "1234";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(101, 55, 237, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("password".equalsIgnoreCase(new String(passwordField.getPassword()))) {
					//login
					JOptionPane.showMessageDialog(null, "Login Successful");
				}else {
					JOptionPane.showMessageDialog(null, "Login Unsuccessful");
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
					//login
					JOptionPane.showMessageDialog(null, "Login Successful");
				}else {
					JOptionPane.showMessageDialog(null, "Login Unsuccessful");
				}
			}
		});
		btnLogin.setBounds(136, 161, 89, 23);
		contentPane.add(btnLogin);
	}
}
