import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewUser {

	private JFrame frame;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField ageField;
	private JTextField userField;
	private JTextField passField;
	private JTextField confirmPass;
	private JTextField secQuestion;
	private JButton btnReg;
	private JTextField blankField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser window = new NewUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewUser() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String path = "bin/UserInfo.txt";
		
		frame = new JFrame();
		frame.setBounds(100, 100, 280, 277);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(6, 6, 80, 16);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(6, 34, 80, 16);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblDateOfBirth = new JLabel("Age:");
		lblDateOfBirth.setBounds(6, 62, 35, 16);
		frame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(6, 90, 80, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(6, 118, 80, 16);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(6, 146, 127, 16);
		frame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblSecurityQuestion = new JLabel("Security Answer:");
		lblSecurityQuestion.setBounds(6, 196, 127, 16);
		frame.getContentPane().add(lblSecurityQuestion);
		
		firstNameField = new JTextField();
		firstNameField.setBounds(80, 1, 130, 26);
		frame.getContentPane().add(firstNameField);
		firstNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(80, 29, 130, 26);
		frame.getContentPane().add(lastNameField);
		lastNameField.setColumns(10);
		
		ageField = new JTextField();
		ageField.setBounds(37, 57, 130, 26);
		frame.getContentPane().add(ageField);
		ageField.setColumns(10);
		
		userField = new JTextField();
		userField.setBounds(80, 85, 130, 26);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passField = new JTextField();
		passField.setBounds(80, 113, 130, 26);
		frame.getContentPane().add(passField);
		passField.setColumns(10);
		
		confirmPass = new JTextField();
		confirmPass.setBounds(131, 141, 130, 26);
		frame.getContentPane().add(confirmPass);
		confirmPass.setColumns(10);
		
		secQuestion = new JTextField();
		secQuestion.setBounds(116, 191, 130, 26);
		frame.getContentPane().add(secQuestion);
		secQuestion.setColumns(10);
		
		JButton btnReg = new JButton("Register");
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					writeFile(path, firstNameField);
					writeFile(path, lastNameField);
					writeFile(path, ageField);
					writeFile(path, userField);
					writeFile(path, passField);
					writeFile(path, secQuestion);
					writeFile(path, blankField);
					
				} catch (IOException ioe) {
					System.out.println("error");
					System.exit(0);
				}
				LoginScreen newLogin = new LoginScreen();
				newLogin.reappear();
				frame.dispose();
			}
		});
		btnReg.setBounds(126, 220, 117, 29);
		frame.getContentPane().add(btnReg);
		
		JLabel lblInWhatCity = new JLabel("In what city were you born?");
		lblInWhatCity.setBounds(6, 171, 178, 16);
		frame.getContentPane().add(lblInWhatCity);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen newLogin = new LoginScreen();
				newLogin.reappear();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(16, 220, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		blankField = new JTextField();
		blankField.setBounds(239, 57, 35, 26);
		frame.getContentPane().add(blankField);
		blankField.setColumns(10);
		blankField.setVisible(false);
	}
	
	// Write to file
	public void writeFile(String path, JTextField sentField) throws IOException {
		FileWriter write = new FileWriter(path, true);
		PrintWriter prntLine = new PrintWriter(write);
		prntLine.println(sentField.getText().trim());
		prntLine.close();
	}
}
