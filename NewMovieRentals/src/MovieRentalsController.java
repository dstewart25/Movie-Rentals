import javax.swing.*;
import java.awt.*;

public class MovieRentalsController extends JFrame {
    public static void main(String[] args) {
        MovieRentalsController frame = new MovieRentalsController();
        frame.setVisible(true);
    }

    public MovieRentalsController() {
        // Setting up the frame
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        JFrame myFrame = this;

        // Adding login screen to show when program begins
        getContentPane().removeAll();
        getContentPane().add(new MovieBrowseScreen(myFrame), BorderLayout.CENTER);
        pack();
        getContentPane().setVisible(true);
    }
}
