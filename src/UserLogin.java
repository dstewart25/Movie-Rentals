import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class UserLogin extends JPanel{
    private JFrame frame;
    private String username, password;
    private JPasswordField passwordField;

    // Array lists used to store user login info
    private List userEmail = new ArrayList();
    private List userPass = new ArrayList();

    public UserLogin(JFrame frame) {
        this.frame = frame; // Setting frame equal to this frame
        setLayout(new BorderLayout()); // Setting the layout of the main panel
        initialize(); // Initializing the view
        // Creating empty border around all components
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    private void initialize() {
        frame.setTitle("Login"); // Setting frame title

        importUserLoginInfo(); // Importing email and password information for login

        // Creating panels to hold components
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));

        // Creating username label and text field
        JLabel usernameLbl = new JLabel("Email:");
        usernamePanel.add(usernameLbl);
        JTextField usernameField = new JTextField();
        usernamePanel.add(usernameField);
        usernameField.setColumns(10);
        username = usernameField.getText();
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    // If 'ENTER' key is pressed then try to login
                    try {
                        if (checkLogin(usernameField.getText(), passwordField.getText())) {
                            // Removing user login screen and showing movie browse screen
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(new MovieBrowseScreen(frame));
                            frame.pack();
                            frame.getContentPane().setVisible(true);
                        } else {
                            return;
                        }
                    } catch (Exception loginErr) {

                    }
                }
            }
        });

        // Creating password label and text field
        JLabel passwordLbl = new JLabel("Password:");
        passwordPanel.add(passwordLbl);
        passwordField = new JPasswordField();
        passwordPanel.add(passwordField);
        password = passwordField.getText();
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        if (checkLogin(usernameField.getText(), passwordField.getText())) {
                            // Removing user login screen and showing movie browse screen
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(new MovieBrowseScreen(frame));
                            frame.pack();
                            frame.getContentPane().setVisible(true);
                        } else {
                            return;
                        }
                    } catch (Exception loginErr) {

                    }
                }
            }
        });

        // Creating cancel button to go back to main login screen
        JButton cancelBtn = new JButton("Cancel");
        buttonPanel.add(cancelBtn);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing user login screen and showing main login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LoginScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating login button
        JButton loginBtn = new JButton("Login");
        buttonPanel.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (checkLogin(usernameField.getText(), passwordField.getText())) {
                        // Removing user login screen and showing movie browse screen
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new MovieBrowseScreen(frame));
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

    private void importUserLoginInfo() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Users/UserLoginInfo.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(": ");
                if (splits[0].equals("Email")) {
                    userEmail.add(splits[1]);
                }
                if (splits[0].equals("Password")) {
                    userPass.add(splits[1]);
                }
            }
        } catch (IOException e) {}
        finally {
            try {
                reader.close();
            } catch (IOException e) {
            } catch (NullPointerException e) {}
        }
        System.out.println("Emails:    " + userEmail);
        System.out.println("Passwords: " + userPass);
    }

    private boolean checkLogin(String email, String password) {
        email = email.toLowerCase(); // Making the email not case sensitive

        // Checking if email field is empty
        if (email.isEmpty()) {
            // Showing no email entered dialogue
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your email.",
                    "No Email Entered",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        }

        for (int i=0; i<userEmail.size(); i++) {
            if (userEmail.get(i).equals(email)) { // If user email is one registered in the array
                if (userPass.get(i).equals(password)) { // If password corresponds with the email
                    return true;
                } else if (password.isEmpty()) {
                    // Showing no password entered dialogue
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter your password.",
                            "No Password Entered",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    return false;
                } else {
                    // Showing incorrect password dialogue
                    JOptionPane.showMessageDialog(
                            null,
                            "Your password is incorrect.",
                            "Incorrect Password",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    return false;
                }
            }
        } // End for loop for checking emails and passwords

        // Email is not registered
        JOptionPane.showMessageDialog(
                null,
                "The email entered is not registered.",
                "Email",
                JOptionPane.PLAIN_MESSAGE
        );

        return false;
    }
}
