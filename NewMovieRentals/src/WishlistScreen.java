import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class WishlistScreen extends JPanel {
    private JFrame frame;

    // Used to view wishlist information
    private JTable wishlistTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    private static Movie[] wishlistMovies;

    public WishlistScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        initialize();
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void populateTable() {
        wishlistMovies = Global.wishlist.toMovieArray();
        String[] column = new String[] {"Movie"};
        String[][] data = new String[wishlistMovies.length][column.length];
        for (int i=0; i<wishlistMovies.length; i++) {
            data[i][0] = wishlistMovies[i].getTitle();
        }
        tableModel = new DefaultTableModel(data, column);
    }

    // Fill the panel
    private void initialize() {
        frame.setTitle("Wishlist"); // Setting the title of the frame

        populateTable(); // Getting movie information for the movie table

        // Setting up sub-panels for displaying components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new GridLayout(3,1));

        // Creating "Your Cart" label
        JLabel yourWishlistLbl = new JLabel("Your Wishlist");
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
        wishlistTable = new JTable(tableModel);
        scrollPane = new JScrollPane(wishlistTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(325,225));

        // Creating add to cart button
        JButton addToCartBtn = new JButton("Add to Cart");
        topRightPanel.add(addToCartBtn);
        addToCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Global.wishlist.addToCart(wishlistTable.getSelectedRow());
                    tableModel.removeRow(wishlistTable.getSelectedRow());
                    wishlistTable.clearSelection();
                    populateTable();
                } catch (ArrayIndexOutOfBoundsException e1) {}
            }
        });

        // Creating view cart button
        JButton viewCartBtn = new JButton("View Cart");
        topRightPanel.add(viewCartBtn);
        viewCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing wishlist screen and showing cart screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new CartScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating remove movie button
        JButton removeMovieBtn = new JButton("Remove Movie");
        topRightPanel.add(removeMovieBtn);
        removeMovieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Global.wishlist.removeFromWishlist(wishlistTable.getSelectedRow());
                    tableModel.removeRow(wishlistTable.getSelectedRow());
                    wishlistTable.clearSelection();
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
