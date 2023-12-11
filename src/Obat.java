import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Obat extends JFrame {

    // Komponen GUI
    private JLabel tambahLabel;
    private JLabel hapusLabel;
    private JLabel ubahLabel;
    private JLabel bersihLabel;
    private JLabel cariLabel;
    private JLabel logoLabel;
    private JLabel titleLabel;

    private JLabel kodeObatLabel;
    private JTextField kodeObatField;

    private JLabel namaObatLabel;
    private JTextField namaObatField;

    private JLabel hargaLabel;
    private JTextField hargaField;

    private JLabel stokLabel;
    private JTextField stokField;

    private JLabel keteranganLabel;
    private JTextField keteranganField;

    private JLabel expDateLabel;
    private JTextField expDateField; // Use JTextField instead of JDateChooser

    private JTextField cariField;

    private DefaultTableModel tableModel;
    private JTable obatTable;
    private JScrollPane tableScrollPane;

    public Obat() {
        initializeFrame();
        initializeComponents();
        addActionListeners();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initializeFrame() {
        setLocation(220, 10);
        setSize(833, 689);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Database Obat");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initializeComponents() {
        // Initialize your components here

        // Example:
        tambahLabel = new JLabel("Tambah");
        hapusLabel = new JLabel("Hapus");
        ubahLabel = new JLabel("Ubah");
        bersihLabel = new JLabel("Bersih");
        cariLabel = new JLabel("Cari");
        logoLabel = new JLabel("Logo");
        titleLabel = new JLabel("Title");

        kodeObatLabel = new JLabel("Kode Obat");
        kodeObatField = new JTextField();

        namaObatLabel = new JLabel("Nama Obat");
        namaObatField = new JTextField();

        hargaLabel = new JLabel("Harga");
        hargaField = new JTextField();

        stokLabel = new JLabel("Stok");
        stokField = new JTextField();

        keteranganLabel = new JLabel("Keterangan");
        keteranganField = new JTextField();

        expDateLabel = new JLabel("Exp_Date");
        expDateField = new JTextField(); // Use JTextField instead of JDateChooser

        cariField = new JTextField();

        // Initialize the table and table model
        String[] columnNames = {"Kode Obat", "Nama Obat", "Harga", "Stok", "Keterangan", "Exp_Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        obatTable = new JTable(tableModel);
        tableScrollPane = new JScrollPane(obatTable);

        // Add components to the frame
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(7, 2));

        // Add your components to the inputPanel
        // Example:
        inputPanel.add(kodeObatLabel);
        inputPanel.add(kodeObatField);
        inputPanel.add(namaObatLabel);
        inputPanel.add(namaObatField);
        inputPanel.add(hargaLabel);
        inputPanel.add(hargaField);
        inputPanel.add(stokLabel);
        inputPanel.add(stokField);
        inputPanel.add(keteranganLabel);
        inputPanel.add(keteranganField);
        inputPanel.add(expDateLabel);
        inputPanel.add(expDateField);

        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private void addActionListeners() {
        // Add action listeners for buttons or labels if needed
        // Example:
        tambahLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openObatGUI();
            }

            private void openObatGUI() {
                new Obat();
            }
        });

        // Add more action listeners as needed
    }

}
