import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AdminCRUDFrame extends JFrame {
    private DefaultTableModel tableModel;
    private JTable adminTable;
    private JTextField codeField, usernameField, passwordField, searchField;

    public AdminCRUDFrame() {
        setTitle("Admin CRUD Application");
        String hexColor = "#083F59";
        Color backgroundColor = Color.decode(hexColor);
        getContentPane().setBackground(backgroundColor);

        setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon(Objects.requireNonNull(LoginFrame.class.getResource("/images/Menuaddadmin_r.png")));
        JLabel titleLabel = new JLabel(image);
        JPanel titlePanel = new JPanel(new GridLayout(1, 1, 10, 10));

        titlePanel.setBackground(backgroundColor);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.PAGE_START);

        // Create input panel with labels
        Font labelFont = new Font("Rockwell", Font.BOLD, 18);
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBackground(backgroundColor);

        JLabel codeLabel = new JLabel("Kode Admin");
        codeLabel.setForeground(Color.WHITE);
        codeLabel.setFont(labelFont);

        codeField = new JTextField();
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(labelFont);

        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(labelFont);
        passwordField = new JTextField();

        inputPanel.add(codeLabel);
        inputPanel.add(codeField);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);


        JPanel crudPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        crudPanel.setBackground(backgroundColor);

        ImageIcon addImage = new ImageIcon(Objects.requireNonNull(LoginFrame.class.getResource("/images/fiturtambah.png")));
        JButton addButton = new JButton(addImage);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));

        
        ImageIcon updateButtonImage = new ImageIcon(Objects.requireNonNull(LoginFrame.class.getResource("/images/fiturubah.png")));
        JButton updateButton = new JButton(updateButtonImage);
        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        updateButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));


        ImageIcon deleteButtonImage = new ImageIcon(Objects.requireNonNull(LoginFrame.class.getResource("/images/fiturhapus.png")));
        JButton deleteButton = new JButton(deleteButtonImage);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));

        ImageIcon clearButtonImage = new ImageIcon(Objects.requireNonNull(LoginFrame.class.getResource("/images/bersih.png")));
        JButton clearAllButton = new JButton(clearButtonImage);
        clearAllButton.setBorderPainted(false);
        clearAllButton.setContentAreaFilled(false);
        clearAllButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));        

        crudPanel.add(addButton);
        crudPanel.add(updateButton);
        crudPanel.add(deleteButton);
        crudPanel.add(clearAllButton);

        inputPanel.add(crudPanel);
        add(inputPanel, BorderLayout.CENTER);

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(backgroundColor);

        JLabel searchLabel = new JLabel("ID_ADMIN");
        searchLabel.setFont(labelFont);
        searchLabel.setForeground(Color.WHITE);
        searchField = new JTextField(15);


        ImageIcon searchButtonImage = new ImageIcon(Objects.requireNonNull(LoginFrame.class.getResource("/images/cariadmin.png")));
        JButton searchButton = new JButton(searchButtonImage);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));        

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        inputPanel.add(searchPanel);
        // add(searchPanel, BorderLayout.CENTER);

        // Create a table model with columns
        String[] columns = {"Kode Admin", "Username", "Password"};
        tableModel = new DefaultTableModel(columns, 0);
        adminTable = new JTable(tableModel);

        // Set up sorting for the table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        adminTable.setRowSorter(sorter);

        JScrollPane tableScrollPane = new JScrollPane(adminTable);
        add(tableScrollPane, BorderLayout.AFTER_LAST_LINE);

        dummy();

        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAdmin();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAdmin();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAdmin();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAdmin();
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    private void dummy(){
        Object[] dummy1 = {"ADM001", "Admin", "admin"};
        Object[] dummy2 = {"ADM002", "Romi", "dummyPass2"};
        Object[] dummy3 = {"ADM003", "Mina", "dummyPass2"};


        tableModel.addRow(dummy1);
        tableModel.addRow(dummy2);
        tableModel.addRow(dummy3);
    }

    private void addAdmin() {
        String code = codeField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!code.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
            Object[] rowData = {code, username, password};
            tableModel.addRow(rowData);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAdmin() {
        int selectedRow = adminTable.getSelectedRow();

        if (selectedRow != -1) {
            String code = codeField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!code.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                adminTable.setValueAt(code, selectedRow, 0);
                adminTable.setValueAt(username, selectedRow, 1);
                adminTable.setValueAt(password, selectedRow, 2);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAdmin() {
        int selectedRow = adminTable.getSelectedRow();

        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchAdmin() {
        String searchText = searchField.getText();
        if (!searchText.isEmpty()) {
            TableRowSorter<?> sorter = (TableRowSorter<?>) adminTable.getRowSorter();
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0));
        } else {
            // If the search field is empty, reset the row filter
            TableRowSorter<?> sorter = (TableRowSorter<?>) adminTable.getRowSorter();
            sorter.setRowFilter(null);
        }
    }

    private void clearFields() {
        codeField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        searchField.setText("");
        adminTable.clearSelection();
    }

    private void clearAll() {
        // Clear all rows in the table
        tableModel.setRowCount(0);
    
        // Clear all fields
        clearFields();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminCRUDFrame();
            }
        });
    }
}
