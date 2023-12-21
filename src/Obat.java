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

public class Obat extends JFrame {
    private JFrame frame;
    private JPanel featuresPanel;
    private Home home;
    private JTextField kodeObatField, namaObatField, hargaField, stokField, keteranganField, expDateField, cariField;
    private JTable obatTable;
    private DefaultTableModel obatTableModel;

    public Obat() {
        initializeFrame();
        initializeComponents();
        addActionListeners();
    }

    private void initializeFrame() {
        frame = new JFrame("Home - Apotek Kelompok 4");
        String hexColor = "#0D3749";
        Color backgroundColor = Color.decode(hexColor);
        frame.getContentPane().setBackground(backgroundColor);

        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/menucariobat.png")));
        JLabel label = new JLabel(image);

        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 10));

        frame.getContentPane().add(label, BorderLayout.NORTH);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximalkan frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        // Create a panel to hold your features (Tambah, Hapus, etc.)
        featuresPanel = new JPanel(new GridBagLayout());
        featuresPanel.setBackground(backgroundColor);
        frame.add(featuresPanel, BorderLayout.WEST);

        // Create a table model with columns for Obat
        String[] obatColumns = {"Kode Obat", "Nama Obat", "Harga", "Stok", "Keterangan", "Exp_Date"};
        obatTableModel = new DefaultTableModel(obatColumns, 0);
        obatTable = new JTable(obatTableModel);

        // Set up sorting for the table
        TableRowSorter<DefaultTableModel> obatSorter = new TableRowSorter<>(obatTableModel);
        obatTable.setRowSorter(obatSorter);

        JScrollPane obatTableScrollPane = new JScrollPane(obatTable);
        obatTableScrollPane.setPreferredSize(new Dimension(500, 300));

        // Add the table to the featuresPanel
        featuresPanel.add(obatTableScrollPane);
    }

    private void initializeComponents() {
        String hexColor = "#0D3749";
        Color backgroundColor = Color.decode(hexColor);
        Color fontColor = Color.WHITE;

        // Set layout for the input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(backgroundColor);

        JLabel tambahLabel = new JLabel("Tambah");
        JLabel hapusLabel = new JLabel("Hapus");
        JLabel ubahLabel = new JLabel("Ubah");
        JLabel bersihLabel = new JLabel("Bersih");
        JLabel cariLabel = new JLabel("Cari");
        JLabel logoLabel = new JLabel("Logo");
        JLabel titleLabel = new JLabel("Title");

        // Set font color for labels
        tambahLabel.setForeground(fontColor);
        hapusLabel.setForeground(fontColor);
        ubahLabel.setForeground(fontColor);
        bersihLabel.setForeground(fontColor);
        cariLabel.setForeground(fontColor);
        logoLabel.setForeground(fontColor);
        titleLabel.setForeground(fontColor);

        kodeObatField = new JTextField();
        namaObatField = new JTextField();
        hargaField = new JTextField();
        stokField = new JTextField();
        keteranganField = new JTextField();
        expDateField = new JTextField();
        cariField = new JTextField();

        // Set font size for text fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
        kodeObatField.setFont(fieldFont);
        namaObatField.setFont(fieldFont);
        hargaField.setFont(fieldFont);
        stokField.setFont(fieldFont);
        keteranganField.setFont(fieldFont);
        expDateField.setFont(fieldFont);
        cariField.setFont(fieldFont);

        setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add image icon
        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/menucariobat.png")));
        JLabel label = new JLabel(image);
        gbc.gridwidth = 2;
        inputPanel.add(label, gbc);

        // Reset gridwidth for labels and fields
        gbc.gridwidth = 1;

        gbc.gridy = 1;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Kode Obat"), gbc);
        gbc.gridx = 1;
        inputPanel.add(kodeObatField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Nama Obat"), gbc);
        gbc.gridx = 1;
        inputPanel.add(namaObatField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Harga"), gbc);
        gbc.gridx = 1;
        inputPanel.add(hargaField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Stok"), gbc);
        gbc.gridx = 1;
        inputPanel.add(stokField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Keterangan"), gbc);
        gbc.gridx = 1;
        inputPanel.add(keteranganField, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Exp_Date"), gbc);
        gbc.gridx = 1;
        inputPanel.add(expDateField, gbc);

        frame.add(inputPanel, BorderLayout.CENTER);

        // Add the input fields and labels to the inputPanel
        inputPanel.add(new JLabel("Kode Obat"), gbc);
        inputPanel.add(kodeObatField, gbc);
        inputPanel.add(new JLabel("Nama Obat"), gbc);
        inputPanel.add(namaObatField, gbc);
        inputPanel.add(new JLabel("Harga"), gbc);
        inputPanel.add(hargaField, gbc);
        inputPanel.add(new JLabel("Stok"), gbc);
        inputPanel.add(stokField, gbc);
        inputPanel.add(new JLabel("Keterangan"), gbc);
        inputPanel.add(keteranganField, gbc);
        inputPanel.add(new JLabel("Exp_Date"), gbc);
        inputPanel.add(expDateField, gbc);

        frame.add(inputPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.setBackground(backgroundColor);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        // Add your action listeners here
        // For example, you can add listeners for buttons like tambahButton, hapusButton, etc.
        // Also, add those buttons to the featuresPanel
        JButton tambahButton = new JButton("Tambah");
        JButton hapusButton = new JButton("Hapus");
        JButton ubahButton = new JButton("Ubah");
        JButton bersihButton = new JButton("Bersih");
        JButton cariButton = new JButton("Cari");

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action for the "Tambah" button
            }
        });

        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action for the "Hapus" button
            }
        });

        ubahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action for the "Ubah" button
            }
        });

        bersihButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action for the "Bersih" button
            }
        });

        cariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action for the "Cari" button
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                home = new Home();
            }
        });

        // Add buttons to the featuresPanel
        featuresPanel.add(tambahButton);
        featuresPanel.add(hapusButton);
        featuresPanel.add(ubahButton);
        featuresPanel.add(bersihButton);
        featuresPanel.add(cariButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Obat();
            }
        });
    }
}
