import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class UserLogin extends LoginScreen {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private String username = "", password = "";

	// Run application
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin window = new UserLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Window constructor
	public UserLogin() {
		initialize();
	}

	// Fill window
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 243, 116);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(24, 11, 71, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(24, 39, 71, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(97, 6, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		username = textField.getText();
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(97, 34, 130, 21);
		frame.getContentPane().add(passwordField);
		password = passwordField.getText();
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(passwordField.getText().equals("Pass") && textField.getText().equals("Admin")) {
					System.out.println("Success");
					}
				} catch (Exception loginErr) {
					
				}
			}
		});
		btnNewButton.setBounds(121, 58, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen newLogin = new LoginScreen();
				newLogin.reappear();
				frame.dispose();
			}
		});
		btnCancel.setBounds(6, 58, 117, 29);
		frame.getContentPane().add(btnCancel);
	}
}
