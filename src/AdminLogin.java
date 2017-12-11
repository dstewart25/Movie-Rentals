import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminLogin extends JPanel{
    private JFrame frame;
    private String username, password;
    private JPasswordField passwordField;
    private JPanel usernamePanel;
    private JTextField usernameField;
    private JPanel passwordPanel;
    private JPanel buttonPanel;
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JButton cancelBtn;
    private JButton loginBtn;

    public AdminLogin(JFrame frame) {
        this.frame = frame; // Setting frame equal to this frame
        setLayout(new BorderLayout()); // Setting the layout of the main panel
        initialize(); // Initializing the view
        // Creating empty border around all components
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    public void initialize() {
        frame.setTitle("Admin Login"); // Setting frame title

        // Creating panels to hold components
        usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));

        // Creating username label and text field
        usernameLbl = new JLabel("Username:");
        usernamePanel.add(usernameLbl);
        usernameField = new JTextField();
        usernamePanel.add(usernameField);
        usernameField.setColumns(10);
        username = usernameField.getText();
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        if(passwordField.getText().equals("Pass") && usernameField.getText().equals("Admin")) {
                            // Removing admin login screen and showing movie browse screen
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(new AdminTasks(frame));
                            frame.pack();
                            frame.getContentPane().setVisible(true);
                        }
                    } catch (Exception loginErr) {

                    }
                }
            }
        });

        // Creating password label and text field
        passwordLbl = new JLabel("Password:");
        passwordPanel.add(passwordLbl);
        passwordField = new JPasswordField();
        passwordPanel.add(passwordField);
        password = passwordField.getText();
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        if(passwordField.getText().equals("Pass") && usernameField.getText().equals("Admin")) {
                            // Removing admin login screen and showing movie browse screen
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(new AdminTasks(frame));
                            frame.pack();
                            frame.getContentPane().setVisible(true);
                        }
                    } catch (Exception loginErr) {

                    }
                }
            }
        });

        // Creating cancel button to go back to main login screen
        cancelBtn = new JButton("Cancel");
        buttonPanel.add(cancelBtn);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing admin login screen and showing main login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LoginScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating login button
        loginBtn = new JButton("Login");
        buttonPanel.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(passwordField.getText().equals("Pass") && usernameField.getText().equals("Admin")) {
                        // Remove admin login screen and show admin tasks screen
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new AdminTasks(frame));
                        frame.pack();
                        frame.getContentPane().setVisible(true);
                    }
                } catch (Exception loginErr) {

                }
            }
        });

        // Adding sub-panels to the main panel
        add(usernamePanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
