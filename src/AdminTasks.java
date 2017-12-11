import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminTasks extends JPanel {
    private JFrame frame;
    private JButton addNewMovieBtn;
    private JButton editMovieBtn;
    private JButton exitBtn;

    public AdminTasks(JFrame frame) {
        this.frame = frame; // Setting main frame equal to this frame
        setLayout(new GridLayout(3,1)); // Setting layout of admin tasks screen
        initialize(); // Initializing the view
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // Fill the panel
    public void initialize() {
        frame.setTitle("Admin Tasks"); // Set frame title

        addNewMovieBtn = new JButton("Add a New Movie");
        add(addNewMovieBtn);
        addNewMovieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove admin tasks and show add new movie screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new NewMovie(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
        
        editMovieBtn = new JButton("Edit a Movie");
        add(editMovieBtn);
        editMovieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove admin tasks screen and show edit movie browse screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new EditMovieBrowseScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        exitBtn = new JButton("Back to Login");
        add(exitBtn);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove admin tasks screen and show login screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new LoginScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });
    }
}
