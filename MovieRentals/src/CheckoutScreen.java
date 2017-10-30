import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CheckoutScreen {

	private JFrame frame;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtAddress1;
	private JTextField txtAddress2;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZip;
	private JSeparator separator_1;
	private JLabel lblCreditInformation;
	private JTextField txtCCNum;
	private JTextField txtExpiration;
	private JTextField txtCVV;
	private JSeparator separator_2;
	private JLabel lblCheckout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutScreen window = new CheckoutScreen();
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
	public CheckoutScreen() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblYourInformation = new JLabel("Your Information");
		lblYourInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblYourInformation.setBounds(6, 74, 122, 29);
		frame.getContentPane().add(lblYourInformation);
		
		JLabel lblBillingAddress = new JLabel("Billing Address");
		lblBillingAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBillingAddress.setBounds(6, 159, 106, 29);
		frame.getContentPane().add(lblBillingAddress);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 138, 488, 12);
		frame.getContentPane().add(separator);
		
		txtFirstName = new JTextField();
		txtFirstName.setText("First Name");
		txtFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFirstName.setText("");
			}
		});
		txtFirstName.setBounds(6, 100, 130, 26);
		frame.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("Last Name");
		txtLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtLastName.setText("");
			}
		});
		txtLastName.setColumns(10);
		txtLastName.setBounds(151, 100, 130, 26);
		frame.getContentPane().add(txtLastName);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email");
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEmail.setText("");
			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(293, 100, 130, 26);
		frame.getContentPane().add(txtEmail);
		
		txtAddress1 = new JTextField();
		txtAddress1.setText("Address 1");
		txtAddress1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAddress1.setText("");
			}
		});
		txtAddress1.setBounds(6, 188, 213, 26);
		frame.getContentPane().add(txtAddress1);
		txtAddress1.setColumns(10);
		
		txtAddress2 = new JTextField();
		txtAddress2.setText("Address 2");
		txtAddress2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAddress2.setText("");
			}
		});
		txtAddress2.setColumns(10);
		txtAddress2.setBounds(6, 213, 213, 26);
		frame.getContentPane().add(txtAddress2);
		
		txtCity = new JTextField();
		txtCity.setText("City");
		txtCity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCity.setText("");
			}
		});
		txtCity.setColumns(10);
		txtCity.setBounds(6, 238, 96, 26);
		frame.getContentPane().add(txtCity);
		
		txtState = new JTextField();
		txtState.setText("ST");
		txtState.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtState.setText("");
			}
		});
		txtState.setColumns(10);
		txtState.setBounds(104, 238, 47, 26);
		frame.getContentPane().add(txtState);
		
		txtZip = new JTextField();
		txtZip.setText("Zip");
		txtZip.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtZip.setText("");
			}
		});
		txtZip.setColumns(10);
		txtZip.setBounds(152, 238, 67, 26);
		frame.getContentPane().add(txtZip);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(6, 276, 488, 12);
		frame.getContentPane().add(separator_1);
		
		lblCreditInformation = new JLabel("Credit Card Information");
		lblCreditInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCreditInformation.setBounds(6, 300, 170, 29);
		frame.getContentPane().add(lblCreditInformation);
		
		txtCCNum = new JTextField();
		txtCCNum.setText("Credit Card Number");
		txtCCNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCCNum.setText("");
			}
		});
		txtCCNum.setColumns(10);
		txtCCNum.setBounds(6, 331, 170, 26);
		frame.getContentPane().add(txtCCNum);
		
		txtExpiration = new JTextField();
		txtExpiration.setText("Expiration");
		txtExpiration.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtExpiration.setText("");
			}
		});
		txtExpiration.setColumns(10);
		txtExpiration.setBounds(176, 331, 81, 26);
		frame.getContentPane().add(txtExpiration);
		
		txtCVV = new JTextField();
		txtCVV.setText("CVV");
		txtCVV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCVV.setText("");
			}
		});
		txtCVV.setColumns(10);
		txtCVV.setBounds(257, 331, 81, 26);
		frame.getContentPane().add(txtCVV);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(6, 50, 488, 12);
		frame.getContentPane().add(separator_2);
		
		lblCheckout = new JLabel("Checkout");
		lblCheckout.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblCheckout.setBounds(6, 6, 145, 41);
		frame.getContentPane().add(lblCheckout);
	}
}
