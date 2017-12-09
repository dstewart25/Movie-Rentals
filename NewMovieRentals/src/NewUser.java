import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NewUser extends JPanel {
    private JFrame frame;

    // Path for the text file to save new user information
    private String path = "Users/UserLoginInfo.txt";

    // Text field components for new user
    private static JTextField firstNameField;
    private static JTextField lastNameField;
    private static JTextField ageField;
    private static JTextField emailField;
    private static JTextField passwordField;
    private static JTextField confirmPassField;
    private static JTextField securityQuestionField;

    // Label components for new user
    private static JLabel firstNameLbl;
    private static JLabel lastNameLbl;
    private static JLabel ageLbl;
    private static JLabel emailLbl;
    private static JLabel passwordLbl;
    private static JLabel confirmPassLbl;
    private static JLabel securityQuestionLbl;

    // String variables for checking correct information
    private static String firstName, lastName, age, email;

    public NewUser(JFrame frame) {
        this.frame = frame; // Setting main frame equal to this frame
        setLayout(new GridLayout(9,2));
        initialize(); // Initializing the view
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    private void initialize() {
        frame.setTitle("New User"); // Setting the title of the frame

        // Creating first name label and text field
        firstNameLbl = new JLabel("First Name:");
        add(firstNameLbl);
        firstNameField = new JTextField();
        firstNameField.setColumns(10);
        add(firstNameField);
        firstNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendNewUserInfo(); // Checking text field info and writing to a file
                }
            }
        });

        // Creating last name label and text field
        lastNameLbl = new JLabel("Last Name:");
        add(lastNameLbl);
        lastNameField = new JTextField();
        lastNameField.setColumns(10);
        add(lastNameField);
        lastNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendNewUserInfo(); // Checking text field info and writing to a file
                }
            }
        });

        // Creating age label and text field
        ageLbl = new JLabel("Age:");
        add(ageLbl);
        ageField = new JTextField();
        ageField.setColumns(10);
        add(ageField);
        ageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendNewUserInfo(); // Checking text field info and writing to a file
                }
            }
        });

        // Creating email label and text field
        emailLbl = new JLabel("Email:");
        add(emailLbl);
        emailField = new JTextField();
        emailField.setColumns(10);
        add(emailField);
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendNewUserInfo(); // Checking text field info and writing to a file
                }
            }
        });

        // Creating password label and text field
        passwordLbl = new JLabel("Password:");
        add(passwordLbl);
        passwordField = new JTextField();
        passwordField.setColumns(10);
        add(passwordField);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendNewUserInfo(); // Checking text field info and writing to a file
                }
            }
        });

        // Creating confirm password label and text field
        confirmPassLbl = new JLabel("Confirm Password:");
        add(confirmPassLbl);
        confirmPassField = new JTextField();
        confirmPassField.setColumns(10);
        add(confirmPassField);
        confirmPassField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendNewUserInfo(); // Checking text field info and writing to a file
                }
            }
        });

        // Creating security question label and text field
        JLabel inWhatCityLbl = new JLabel("In what city were you born?");
        JLabel emptyLbl = new JLabel();
        securityQuestionLbl = new JLabel("Security Question:");
        securityQuestionField = new JTextField();
        securityQuestionField.setColumns(10);
        add(securityQuestionLbl);
        add(emptyLbl);
        add(inWhatCityLbl);
        add(securityQuestionField);
        securityQuestionField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    sendNewUserInfo(); // Checking text field info and writing to a file
                }
            }
        });

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
                sendNewUserInfo(); // Checking text field info and writing to file
            }
        });
    }

    private void sendNewUserInfo() {
        if (textFieldsFilled()) { // Check if all of the text fields have been filled
            if (textFieldsCorrect()) { // Check if all of the text fields have correct info
                // Writing new user information to a file
                try {
                    writeFile(path, firstNameField, firstNameLbl);
                    writeFile(path, lastNameField, lastNameLbl);
                    writeFile(path, ageField, ageLbl);
                    writeFile(path, emailField, emailLbl);
                    writeFile(path, passwordField, passwordLbl);
                    writeFile(path, securityQuestionField, securityQuestionLbl);
                } catch (IOException ioe) {
                    System.out.println("Unable to write to file");
                }

                JOptionPane.showMessageDialog(
                        null,
                        "You are now registered!",
                        "Registered",
                        JOptionPane.PLAIN_MESSAGE
                );

                // Removing user login screen and showing main login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LoginScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        }
    }

    private boolean textFieldsCorrect() {
        getTextFromInput(); // Getting the input from text fields

        // Converting age to a number
        int numAge = 0;
        try {
            numAge = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your age as a number.",
                    "Age",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        }

        if (!firstName.matches("[a-zA-z]*")) {
            JOptionPane.showMessageDialog(
                    null,
                    "First name is an incorrect format.",
                    "First Name",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (!lastName.matches("[a-zA-z]*")) {
            JOptionPane.showMessageDialog(
                    null,
                    "Last name is an incorrect format.",
                    "Last Name",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (!(numAge > 0 && numAge < 200)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your age as a number between 1-199.",
                    "Age",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your email is an incorrect format.",
                    "Email",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } // End if else conditional

        // All information entered is the correct format
        return true;
    }

    // Setting text field input to string values
    private void getTextFromInput() {
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        age = ageField.getText();
        email = emailField.getText().toLowerCase();
    }

    private boolean textFieldsFilled() {
        if (firstNameField.getText().isEmpty()) { // No first name entered
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your first name.",
                    "First Name",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (lastNameField.getText().isEmpty()) { // No last name entered
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your last name.",
                    "Last Name",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (ageField.getText().isEmpty()) { // No age entered
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your age.",
                    "Age",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (emailField.getText().isEmpty()) { // No email entered
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your email.",
                    "Email",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (passwordField.getText().isEmpty()) { // No password entered
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter a password.",
                    "Password",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (confirmPassField.getText().isEmpty()) { // No confirm password entered
            JOptionPane.showMessageDialog(
                    null,
                    "Please confirm your password.",
                    "Password",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        // Passwords do not match
        } else if (!(passwordField.getText().equals(confirmPassField.getText()))) {
            JOptionPane.showMessageDialog(
                    null,
                    "Passwords do not match. Please reenter your password.",
                    "Password",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (securityQuestionField.getText().isEmpty()) { // No security question entered
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your answer to the security question.",
                    "Security Question",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        }
        return true;
    }

    // Write to file
    private void writeFile(String path, JTextField sentField, JLabel sentlbl) throws IOException {
        // Variables to write to the user info file
        FileWriter write = new FileWriter(path, true);
        PrintWriter printWriter = new PrintWriter(write);

        // Checking to see if email is being written to file
        if (sentField.equals(emailField)) {
            String email = sentField.getText().toLowerCase(); // Making email not case sensitive
            // Writing email to user info file
            printWriter.println(sentlbl.getText() + " " + email.trim());
        } else {
            // Writing to user info file
            printWriter.println(sentlbl.getText() + " " + sentField.getText().trim());
        }

        // If it is the last piece of info, add a separator for ease of reading
        if (sentlbl.getText().equals("Security Question:")) {
            printWriter.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        // Closing variables
        printWriter.close();
    }
}
