import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NewUser extends JPanel {
    private JFrame frame;

    public NewUser(JFrame frame) {
        this.frame = frame; // Setting main frame equal to this frame
        setLayout(new GridLayout(9,2));
        initialize(); // Initializing the view
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    private void initialize() {
        String path = "bin/UserInfo.txt"; // Path for the new user information

        frame.setTitle("New User"); // Setting the title of the frame

        // Creating first name label and text field
        JLabel firstNameLbl = new JLabel("First Name:");
        add(firstNameLbl);
        JTextField firstNameField = new JTextField();
        firstNameField.setColumns(10);
        add(firstNameField);

        // Creating last name label and text field
        JLabel lastNameLbl = new JLabel("Last Name:");
        add(lastNameLbl);
        JTextField lastNameField = new JTextField();
        lastNameField.setColumns(10);
        add(lastNameField);

        // Creating age label and text field
        JLabel ageLbl = new JLabel("Age:");
        add(ageLbl);
        JTextField ageField = new JTextField();
        ageField.setColumns(10);
        add(ageField);

        // Creating email label and text field
        JLabel emailLbl = new JLabel("Email:");
        add(emailLbl);
        JTextField emailField = new JTextField();
        emailField.setColumns(10);
        add(emailField);

        // Creating password label and text field
        JLabel passwordLbl = new JLabel("Password:");
        add(passwordLbl);
        JTextField passwordField = new JTextField();
        passwordField.setColumns(10);
        add(passwordField);

        // Creating confirm password label and text field
        JLabel confirmPassLbl = new JLabel("Confirm Password:");
        add(confirmPassLbl);
        JTextField confirmPassField = new JTextField();
        confirmPassField.setColumns(10);
        add(confirmPassField);

        // Creating security question label and text field
        JLabel inWhatCityLbl = new JLabel("In what city were you born?");
        JLabel emptyLbl = new JLabel();
        JLabel securityQuestionLbl = new JLabel("Security Question:");
        JTextField securityQuestionField = new JTextField();
        securityQuestionField.setColumns(10);
        add(securityQuestionLbl);
        add(emptyLbl);
        add(inWhatCityLbl);
        add(securityQuestionField);

        // Creating cancel button to take back to main login screen
        JButton cancelBtn = new JButton("Cancel");
        add(cancelBtn);
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

        // Creating register button to write user information to a text file
        JButton registerBtn = new JButton("Register");
        add(registerBtn);
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Writing new user information to a file
                try {
                    writeFile(path, firstNameField, firstNameLbl);
                    writeFile(path, lastNameField, lastNameLbl);
                    writeFile(path, ageField, ageLbl);
                    writeFile(path, emailField, emailLbl);
                    writeFile(path, passwordField, passwordLbl);
                    writeFile(path, securityQuestionField, securityQuestionLbl);

                } catch (IOException ioe) {
                    System.out.println("error");
                    System.exit(0);
                }

                // Removing user login screen and showing main login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LoginScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
    }

    // Write to file
    public void writeFile(String path, JTextField sentField, JLabel sentlbl) throws IOException {
        FileWriter write = new FileWriter(path, true);
        PrintWriter prntLine = new PrintWriter(write);
        prntLine.println(sentlbl.getText() + " " + sentField.getText().trim());
        prntLine.close();
    }
}
