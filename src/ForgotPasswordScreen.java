import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ForgotPasswordScreen extends JPanel {
    private JFrame frame;

    JTextField securityQuestionField; // Text field to hold security question answer input

    // Array lists used to store user login info
    private List userEmail = new ArrayList(); // Holds all user emails
    private List userAnswer = new ArrayList(); // Holds all answers to security questions
    private List userPassword = new ArrayList(); // Holds all user passwords

    private static int passIndex; // Holds index where password is stored in the array list

    public ForgotPasswordScreen(JFrame frame) {
        this.frame = frame; // Setting the main frame equal to this frame
        setLayout(new BorderLayout());
        initialize(); // Initializing the view
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    private void initialize() {
        frame.setTitle("Forgot Password");

        importUserInfo(); // Importing email and security question answers

        // Creating sub-panels to hold components
        // Creating panels to hold components
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.X_AXIS));
        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.X_AXIS));
        JPanel questionAndAnswerPanel = new JPanel();
        questionAndAnswerPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));

        // Creating email label and text field
        JLabel emailLbl = new JLabel("Email:");
        emailPanel.add(emailLbl);
        JTextField emailField = new JTextField();
        emailField.setColumns(10);
        emailPanel.add(emailField);
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    // Checking to see if email and security question are correct
                    if (checkUserInfo(emailField.getText(), securityQuestionField.getText())) {
                        // Showing the user their password
                        JOptionPane.showMessageDialog(
                                null,
                                "Your password is " + userPassword.get(passIndex),
                                "Your Password",
                                JOptionPane.PLAIN_MESSAGE
                        );
                        // Removing forgot password screen and showing main login screen
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new LoginScreen(frame));
                        frame.pack();
                        frame.getContentPane().setVisible(true);
                    }
                }
            }
        });

        // Creating security question label and text field
        JLabel inWhatCityLbl = new JLabel("In what city were you born?");
        //JLabel emptyLbl = new JLabel();
        JLabel answerLbl = new JLabel("Answer:");
        securityQuestionField = new JTextField();
        securityQuestionField.setColumns(10);
        questionPanel.add(inWhatCityLbl);
        //add(emptyLbl);
        answerPanel.add(answerLbl);
        answerPanel.add(securityQuestionField);
        securityQuestionField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    // Checking to see if email and security question are correct
                    if (checkUserInfo(emailField.getText(), securityQuestionField.getText())) {
                        // Showing the user their password
                        JOptionPane.showMessageDialog(
                                null,
                                "Your password is " + userPassword.get(passIndex),
                                "Your Password",
                                JOptionPane.PLAIN_MESSAGE
                        );
                        // Removing forgot password screen and showing main login screen
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new LoginScreen(frame));
                        frame.pack();
                        frame.getContentPane().setVisible(true);
                    }
                }
            }
        });

        // Creating cancel button
        JButton cancelBtn = new JButton("Cancel");
        buttonPanel.add(cancelBtn);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing forgot password screen and showing main login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LoginScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating submit button
        JButton submitBtn = new JButton("Submit");
        buttonPanel.add(submitBtn);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checking to see if email and security question are correct
                if (checkUserInfo(emailField.getText(), securityQuestionField.getText())) {
                    // Showing the user their password
                    JOptionPane.showMessageDialog(
                            null,
                            "Your password is " + userPassword.get(passIndex),
                            "Your Password",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    // Removing forgot password screen and showing main login screen
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new LoginScreen(frame));
                    frame.pack();
                    frame.getContentPane().setVisible(true);
                }
            }
        });

        // Creating space between email field and question label
        questionAndAnswerPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        // Adding sub-panels to the main panel
        add(emailPanel, BorderLayout.NORTH);
        questionAndAnswerPanel.add(questionPanel, BorderLayout.NORTH);
        questionAndAnswerPanel.add(answerPanel, BorderLayout.CENTER);
        add(questionAndAnswerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Importing user email and security question answers from a text file
    private void importUserInfo() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Users/UserLoginInfo.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(": ");
                if (splits[0].equals("Email")) {
                    userEmail.add(splits[1]);
                }
                if (splits[0].equals("Security Question")) {
                    userAnswer.add(splits[1].toLowerCase());
                }
                if (splits[0].equals("Password")) {
                    userPassword.add(splits[1]);
                }
            }
        } catch (IOException e) {}
        finally {
            try {
                reader.close();
            } catch (IOException e) {
            } catch (NullPointerException e) {}
        }

        // Printing out array lists to the screen to check if import is correct
        System.out.println("Emails:    " + userEmail);
        System.out.println("Answers:   " + userAnswer);
        System.out.println("Passwords: " + userPassword);
    }

    private boolean checkUserInfo(String email, String answer) {
        // Making sure email and answer are lower case
        email = email.toLowerCase();
        answer = answer.toLowerCase();

        // Checking to see if email or answer fields are empty
        if (email.isEmpty()) {
            // Showing no email entered dialogue
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your email.",
                    "No Email Entered",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        } else if (answer.isEmpty()) {
            // Showing no answer entered dialogue
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your security question answer.",
                    "No Answer Entered",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        }

        // Checking if email is the correct format
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(
                    null,
                    "The email entered is an incorrect format.",
                    "Email",
                    JOptionPane.PLAIN_MESSAGE
            );
            return false;
        }

        // Checking if user email and security question correspond to each other
        for (int i=0; i<userEmail.size(); i++) {
            if (userEmail.get(i).equals(email)) {
                // User email must be registered, check security question
                if (userAnswer.get(i).equals(answer)) {
                    // Security question and user email correspond with each other
                    passIndex = i;
                    return true;
                } else {
                    // Showing incorrect security question dialogue
                    // Showing no password entered dialogue
                    JOptionPane.showMessageDialog(
                            null,
                            "Your security question answer is incorrect.",
                            "Incorrect Answer",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    return false;
                }
            }
        } // End for loop checking email and answers

        // Email was not found so it must not registered
        JOptionPane.showMessageDialog(
                null,
                "The email entered is not registered.",
                "Email",
                JOptionPane.PLAIN_MESSAGE
        );

        return false;
    }
}
