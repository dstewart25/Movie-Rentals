import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {
    private JFrame frame;

    public LoginScreen(JFrame frame) {
        this.frame = frame; // Setting main frame equal to this frame
        setLayout(new GridLayout(4,1)); // Setting layout of login screen
        initialize(); // Initializing the view
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    public void initialize() {
        frame.setTitle("Login"); // Setting frame title

        JButton loginBtn = new JButton("Login");
        add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing login screen and showing user login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new UserLogin(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        JButton newUserBtn = new JButton("New User");
        add(newUserBtn);
        newUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing login screen and showing new user screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new NewUser(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        JButton forgotPasswordBtn = new JButton("Forgot Password");
        add(forgotPasswordBtn);
        forgotPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing login screen and showing forgot password screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new ForgotPasswordScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        JButton exitBtn = new JButton("Exit");
        add(exitBtn);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exiting the program
                System.exit(1);
            }
        });
    }
}
