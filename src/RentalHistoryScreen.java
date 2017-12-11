import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentalHistoryScreen extends JPanel {
    private JFrame frame;

    // Used to view wishlist information
    private JTable historyTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    private static Movie[] historyMovies;

    public RentalHistoryScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        initialize();
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void populateTable() {
        historyMovies = Global.history.toMovieArray();
        String[] column = new String[] {"Movie"};
        String[][] data = new String[historyMovies.length][column.length];
        for (int i=0; i<historyMovies.length; i++) {
            data[i][0] = historyMovies[i].getTitle();
        }
        tableModel = new DefaultTableModel(data, column);
    }

    // Fill the panel
    private void initialize() {
        frame.setTitle("User Rental History"); // Setting the title of the frame

        populateTable(); // Getting movie information for the movie table

        // Setting up sub-panels for displaying components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new GridLayout(3,1));

        // Creating "Your Rental History" label
        JLabel yourWishlistLbl = new JLabel("Your Rental History");
        yourWishlistLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        topPanel.add(yourWishlistLbl, BorderLayout.WEST);

        // Creating back button
        JButton backBtn = new JButton("Back");
        topPanel.add(backBtn, BorderLayout.EAST);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing wishlist screen and showing movie browse screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new MovieBrowseScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating movie table
        historyTable = new JTable(tableModel);
        scrollPane = new JScrollPane(historyTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(325,225));

        // Creating remove movie button
        JButton removeMovieBtn = new JButton("Remove Movie");
        topRightPanel.add(removeMovieBtn);
        removeMovieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Global.history.removeFromHistory(historyTable.getSelectedRow());
                    tableModel.removeRow(historyTable.getSelectedRow());
                    historyTable.clearSelection();
                    populateTable();
                } catch (ArrayIndexOutOfBoundsException e1) {}
            }
        });

        // Adding sub-panels to main panel
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
