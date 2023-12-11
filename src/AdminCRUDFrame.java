import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminCRUDFrame extends JFrame {
    private DefaultTableModel tableModel;
    private JTable adminTable;
    private JTextField codeField, usernameField, passwordField, searchField;

    public AdminCRUDFrame() {
        setTitle("Admin CRUD Application");
        String hexColor = "#083F59";
        Color backgroundColor = Color.decode(hexColor);
        getContentPane().setBackground(backgroundColor);

        setLayout(new GridLayout(2, 1, 20 ,20));

        int paddingSize = 20; // Sesuaikan dengan ukuran padding yang diinginkan

        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/Menuaddadmin_r.png")));
        JLabel titleLabel = new JLabel(image);
        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 0));

        JPanel firstPanel = new JPanel(new GridLayout(3, 1, 0, 0));

        firstPanel.setBackground(backgroundColor);
        firstPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Atur margin bawah
        JPanel secondPanel = new JPanel(new GridLayout(1, 1, 0, 0));
        secondPanel.setBackground(backgroundColor);
        secondPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Atur margin bawah


        
        ImageIcon backImages = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/backbutton.png")));
        JButton backButton = new JButton(backImages);
        backButton.setPreferredSize(new Dimension(10,10));

        backButton.setHorizontalAlignment(SwingConstants.LEFT);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false); 
        backButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        backButton.setContentAreaFilled(false);
        backButton.setPreferredSize(new Dimension(backImages.getIconWidth(), backImages.getIconHeight()));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminCRUDFrame();
                dispose();
            }
        });

        titlePanel.setBackground(backgroundColor);

        titlePanel.add(backButton);
        titlePanel.add(titleLabel);

        // add(titlePanel);
        firstPanel.add(titlePanel);
        // Create input panel with labels
        Font labelFont = new Font("Rockwell", Font.BOLD, 18);
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
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


        inputPanel.setBorder(new EmptyBorder(paddingSize, paddingSize, -3, paddingSize));

        JPanel twoPanel = new JPanel(new GridLayout(2, 3, 0, 0));
        JPanel crudPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        crudPanel.setBackground(backgroundColor);

        ImageIcon addImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/fiturtambah.png")));
        JButton addButton = new JButton(addImage);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));

        
        ImageIcon updateButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/fiturubah.png")));
        JButton updateButton = new JButton(updateButtonImage);
        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        updateButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));


        ImageIcon deleteButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/fiturhapus.png")));
        JButton deleteButton = new JButton(deleteButtonImage);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));

        ImageIcon clearButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/bersih.png")));
        JButton clearAllButton = new JButton(clearButtonImage);
        clearAllButton.setBorderPainted(false);
        clearAllButton.setContentAreaFilled(false);
        clearAllButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));        

        crudPanel.add(addButton);
        crudPanel.add(updateButton);
        crudPanel.add(deleteButton);
        crudPanel.add(clearAllButton);

        twoPanel.add(crudPanel);
        // inputPanel.add(crudPanel);
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paginationPanel.setBackground(backgroundColor);


        titlePanel.add(inputPanel);
        // add(inputPanel);
        // add(twoPanel);

        firstPanel.add(inputPanel);
        firstPanel.add(twoPanel);
        // add(titlePanel, BorderLayout.PAGE_START);

        add(firstPanel, BorderLayout.BEFORE_FIRST_LINE);

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        
        searchPanel.setBackground(backgroundColor);

        JLabel searchLabel = new JLabel("ID_ADMIN");
        searchLabel.setFont(labelFont);
        searchLabel.setForeground(Color.WHITE);
        searchField = new JTextField(20);


        ImageIcon searchButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/cariadmin.png")));
        JButton searchButton = new JButton(searchButtonImage);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setPreferredSize(new Dimension(addImage.getIconWidth(), addImage.getIconHeight()));        

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        twoPanel.add(searchPanel);
        // inputPanel.add(searchPanel);
        // add(searchPanel, BorderLayout.CENTER);

        // Create a table model with columns
        String[] columns = {"Kode Admin", "Username", "Password"};
        tableModel = new DefaultTableModel(columns, 0);
        adminTable = new JTable(tableModel);

        // Set up sorting for the table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        adminTable.setRowSorter(sorter);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setBackground(backgroundColor);

        JScrollPane tableScrollPane = new JScrollPane(adminTable);
        tableScrollPane.add(Box.createVerticalStrut(600));
        // tablePanel.add(tableScrollPane);
        tablePanel.add(tableScrollPane);

        // add(tablePanel);
        // add(paginationPanel);

        secondPanel.add(tablePanel);
        // secondPanel.add(paginationPanel);
        add(secondPanel, BorderLayout.PAGE_END);
        tableScrollPane.setBackground(backgroundColor);

        tableScrollPane.setBorder(new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize));



        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAdminToDatabase();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAdminInDatabase();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAdminFromDatabase();
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

        loadDataFromDatabase();
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    private void addAdminToDatabase() {

        String code = codeField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!code.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
            try (Connection connection = KoneksiDB.getKoneksi()) {
            String query = "INSERT INTO admin (Id_Admin, Username, Password) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, code);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, password);
        
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        // Jika penambahan berhasil, tambahkan juga ke tabel di GUI
                        Object[] rowData = {code, username, password};
                        tableModel.addRow(rowData);
                        clearFields();
                        JOptionPane.showMessageDialog(AdminCRUDFrame.this, "Data Berhasil Disimpan!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding admin to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }


    private void updateAdminInDatabase() {
        int row = adminTable.getSelectedRow();
        // JOptionPane.showMessageDialog(this, row, "Error", JOptionPane.ERROR_MESSAGE);

        if (row != -1) {
            String code = (String) tableModel.getValueAt(row, 0);
            codeField.setText(code);
            // String code = codeField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!code.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                try (Connection connection = KoneksiDB.getKoneksi()) {
                    String query = "UPDATE admin SET Username=?, Password=? WHERE Id_Admin=?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        preparedStatement.setString(3, code);
            
                        int affectedRows = preparedStatement.executeUpdate();
                        if (affectedRows > 0) {
                            // Jika update berhasil, update juga di tabel di GUI
                            adminTable.setValueAt(username, row, 1);
                            adminTable.setValueAt(password, row, 2);
                            clearFields();
                            JOptionPane.showMessageDialog(AdminCRUDFrame.this, "Data Berhasil Diubah!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating admin in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void deleteAdminFromDatabase() {
        int row = adminTable.getSelectedRow();

        if (row != -1) {
            String code = (String) tableModel.getValueAt(row, 0);
            try (Connection connection = KoneksiDB.getKoneksi()) {
                String query = "DELETE FROM admin WHERE Id_Admin=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, code);
        
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        // Jika delete berhasil, hapus juga dari tabel di GUI
                        tableModel.removeRow(row);
                        clearFields();
                        JOptionPane.showMessageDialog(AdminCRUDFrame.this, "Data Berhasil Dihapus!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
                        loadDataFromDatabase();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting admin from the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void searchAdmin() {
        String searchText = searchField.getText();
        if (!searchText.isEmpty()) {
            TableRowSorter<?> sorter = (TableRowSorter<?>) adminTable.getRowSorter();
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0,1));
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
    

    // private int getEndIndex() {
    //     int endIndex = getStartIndex() + pageSize - 1;
    //     return Math.min(endIndex, tableModel.getRowCount() - 1);
    // }

    private void loadDataFromDatabase() {
        tableModel.setRowCount(0); // Clear existing data

        try (Connection connection = KoneksiDB.getKoneksi()) {
            String query = "SELECT Id_Admin, Username, Password FROM admin";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
               

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String code = resultSet.getString("Id_Admin");
                        String username = resultSet.getString("Username");
                        String password = resultSet.getString("Password");

                        Object[] rowData = {code, username, password};
                        tableModel.addRow(rowData);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminCRUDFrame();
            }
        });
    }
}
