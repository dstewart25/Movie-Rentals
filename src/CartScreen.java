import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class CartScreen extends JPanel {
    private JFrame frame;

    // Used to view cart information
    private JTable cartTable;
    private JScrollPane scrollPane;
    private static DefaultTableModel tableModel;

    private static Movie[] cartMovies;

    // Used for money computations
    private double total = 0;
    private double tax = 0;
    private double subtotal = 0;
    private final double TAX_PERCENT = .06;
    private String strTotal;
    private String strTax;
    private String strSubtotal;
    private JLabel lblTotal;
    private JLabel lblTax;
    private JLabel lblSubtotal;
    private JPanel bottomRightPanel;
    private JPanel rightPanel;

    public CartScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        initialize();
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    private void getPricesForMovies() {
        for (int i=0; i<cartMovies.length; i++) {
            subtotal += cartMovies[i].getPrice();
        }

        tax = TAX_PERCENT * subtotal;
        total = subtotal + tax;

        strSubtotal = String.format("Subtotal: $%.2f", subtotal);
        strTax = String.format("Tax: $%.2f", tax);
        strTotal = String.format("Total: $%.2f", total);
    }

    private void updatePriceLabels() {
        bottomRightPanel.remove(lblSubtotal);
        bottomRightPanel.remove(lblTax);
        bottomRightPanel.remove(lblTotal);
        getPricesForMovies();
        lblSubtotal.setText(strSubtotal);
        lblTax.setText(strTax);
        lblTotal.setText(strTotal);
        bottomRightPanel.add(lblSubtotal);
        bottomRightPanel.add(lblTax);
        bottomRightPanel.add(lblTotal);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);
    }

    private void populateTable() {
        // Creating list of movies in cart
        cartMovies = null;
        cartMovies = Global.cart.toMovieArray();
        String[] column = new String[] {"Movie", "Price"};
        String[][] data = new String[cartMovies.length][column.length];
        for (int i=0; i<cartMovies.length; i++) {
            data[i][0] = cartMovies[i].getTitle();
            data[i][1] = Double.toString(cartMovies[i].getPrice());
        }
        tableModel = new DefaultTableModel(data, column);
    }

    // Fill the panel
    private void initialize() {
        frame.setTitle("Cart"); // Setting the title of the frame

        populateTable(); // Getting movie information for the movie table
        getPricesForMovies(); // Getting prices for subtotal and total of movies

        // Setting up sub-panels for displaying components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new GridLayout(2,1));
        bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new GridLayout(3,1));

        // Creating "Your Cart" label
        JLabel yourCartLbl = new JLabel("Your Cart");
        yourCartLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        topPanel.add(yourCartLbl, BorderLayout.WEST);

        // Creating back button
        JButton backBtn = new JButton("Back");
        topPanel.add(backBtn, BorderLayout.EAST);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing cart screen and showing movie browse screen
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new MovieBrowseScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Creating movie table
        cartTable = new JTable(tableModel);
        cartTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        scrollPane = new JScrollPane(cartTable,
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
                    Global.cart.removeFromCart(cartTable.getSelectedRow());
                    /*tableModel.removeRow(cartTable.getSelectedRow());
                    cartTable.clearSelection();*/
                    // Refreshing cart screen to show updated price labels
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new CartScreen(frame));
                    frame.pack();
                    frame.getContentPane().setVisible(true);
                } catch (ArrayIndexOutOfBoundsException e1) {}
            }
        });

        // Creating checkout button
        JButton checkoutBtn = new JButton("Checkout");
        topRightPanel.add(checkoutBtn);
        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0; i<cartMovies.length; i++) {
                    Global.history.addToHistory(cartMovies[i]);
                }

                Global.cart.clearCart();

                JOptionPane.showMessageDialog(
                        null,
                        "Your checkout was successful.",
                        "Checkout",
                        JOptionPane.PLAIN_MESSAGE
                );

                // Refreshing cart screen to show updated cart
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new CartScreen(frame));
                frame.pack();
                frame.getContentPane().setVisible(true);
            }
        });

        // Adding labels for total, subtotal, and tax
        lblSubtotal = new JLabel(strSubtotal, SwingConstants.RIGHT);
        bottomRightPanel.add(lblSubtotal);

        lblTax = new JLabel(strTax, SwingConstants.RIGHT);
        bottomRightPanel.add(lblTax);

        lblTotal = new JLabel(strTotal, SwingConstants.RIGHT);
        bottomRightPanel.add(lblTotal);

        // Adding sub-panels to main panel
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
