import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen {

	private JFrame frame;

	// Run application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Window constructor
	public LoginScreen() {
		initialize();
		frame.setVisible(false);
	}

	// Fill window
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 150, 142);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton_2 = new JButton("Forgot Password");
		btnNewButton_2.setBounds(6, 59, 137, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton = new JButton("New User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUser.main();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(29, 35, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin.main();
				frame.dispose();
			}
		});

		btnNewButton_1.setBounds(29, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		btnNewButton_3.setBounds(44, 80, 59, 29);
		frame.getContentPane().add(btnNewButton_3);
	}

	public void reappear() {
		frame.setVisible(true);
	}

}
