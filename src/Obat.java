import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class Obat extends JFrame {
    private JFrame frame;
    private JLabel tambahLabel;
    private JLabel hapusLabel;
    private JLabel ubahLabel;
    private JLabel bersihLabel;
    private JLabel cariLabel;
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
    private JTextField expDateField;
    private JTextField cariField;
    private DefaultTableModel tableModel;
    private JTable obatTable;
    private JScrollPane tableScrollPane;
    private Color backgroundColor;
    private String hexColor = "#0D3749";
    private Connection connection;

    public Obat() {
        initializeFrame();
        configureFrame();
        initializeComponents();
//        initializeDatabaseConnection();
//        tampilTabel();
    }

//    private void initializeDatabaseConnection() {
//        try {
//            connection = KoneksiDB.getKoneksi();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void initializeFrame() {
        frame = new JFrame("Home - Apotek Kelompok 4");
        backgroundColor = Color.decode(hexColor);
        frame.getContentPane().setBackground(backgroundColor);

        JPanel titlePanel = new JPanel(new GridLayout(1, 1, 5, 5));
        titlePanel.setBackground(backgroundColor);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Atur marg

        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/menucariobat.png")));
        JLabel titleLabel = new JLabel(image);

        titlePanel.add(titleLabel);


        frame.getContentPane().add(titlePanel, BorderLayout.NORTH); // judul
        frame.getContentPane().setBackground(backgroundColor);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    private void configureFrame() {
        frame.setVisible(true);
    }

    private void initializeComponents() {
        JPanel firstPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        firstPanel.setBackground(backgroundColor);
        firstPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0)); // Atur margin bawah

        JPanel secondPanel = new JPanel(new GridLayout(0, 1, 0, 0));
        secondPanel.setBackground(backgroundColor);
        secondPanel.setBorder(BorderFactory.createEmptyBorder(1, 0, 5, 0)); // Atur margin bawah

        Font labelFont = new Font("Rockwell", Font.BOLD, 18);

        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 5, 0)); // Atur margin bawah
        inputPanel.setBackground(backgroundColor);

        kodeObatLabel = new JLabel("Kode Obat");
        kodeObatLabel.setForeground(Color.WHITE);
        kodeObatLabel.setFont(labelFont);

        kodeObatField = new JTextField(10);

        namaObatLabel = new JLabel("Nama Obat");
        namaObatLabel.setForeground(Color.WHITE);
        namaObatLabel.setFont(labelFont);

        namaObatField = new JTextField(10);

        hargaLabel = new JLabel("Harga");
        hargaLabel.setForeground(Color.WHITE);
        hargaLabel.setFont(labelFont);

        hargaField = new JTextField(10);

        stokLabel = new JLabel("Stok");
        stokLabel.setForeground(Color.WHITE);
        stokLabel.setFont(labelFont);

        stokField = new JTextField(10);

        keteranganLabel = new JLabel("Keterangan");
        keteranganLabel.setForeground(Color.WHITE);
        keteranganLabel.setFont(labelFont);

        keteranganField = new JTextField(10);

        expDateLabel = new JLabel("Exp_Date");
        expDateLabel.setForeground(Color.WHITE);
        expDateLabel.setFont(labelFont);

        expDateField = new JTextField(10);
        cariField = new JTextField();

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

        JPanel twoPanel = new JPanel(new GridLayout(2, 3, 0, 0));
        twoPanel.setBackground(backgroundColor);
        twoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Atur margin bawah

        JPanel crudPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        crudPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); // Atur margin bawah
        crudPanel.setBackground(Color.red);

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

        // Add search label and field
        cariLabel = new JLabel("Cari");
        cariLabel.setForeground(Color.WHITE);
        cariLabel.setFont(labelFont);

        cariField = new JTextField(10);

        // Add search button
        ImageIcon searchButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/kcari.png")));
        JButton searchButton = new JButton(searchButtonImage);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setPreferredSize(new Dimension(searchButtonImage.getIconWidth(), searchButtonImage.getIconHeight()));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Atur margin bawah
        searchPanel.setBackground(Color.green);
        searchPanel.add(cariLabel);
        searchPanel.add(cariField);
        searchPanel.add(searchButton);

        crudPanel.add(addButton);
        crudPanel.add(updateButton);
        crudPanel.add(deleteButton);
        crudPanel.add(clearAllButton);

        // Add the table scroll pane to the table panel
        JPanel tablePanel = new JPanel(new GridLayout(6, 0, 0, 0));
        tablePanel.setBackground(backgroundColor);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0)); // Atur margin bawah
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        // Create a table model with columns
        String[] columns = {"Kode Obat", "Nama Obat", "Harga", "Stok", "Keterangan", "Exp_Date"};
        tableModel = new DefaultTableModel(columns, 0);
        obatTable = new JTable(tableModel);

        // Set up sorting for the table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        obatTable.setRowSorter(sorter);

        JScrollPane tableScrollPane = new JScrollPane(obatTable);
        tableScrollPane.add(Box.createVerticalStrut(500));
        tableScrollPane.setBackground(backgroundColor);
        tableScrollPane.setBorder(new EmptyBorder(5, 20, 20, 20));

        tablePanel.add(tableScrollPane);
        twoPanel.add(crudPanel);
        twoPanel.add(searchPanel);

        firstPanel.add(inputPanel);
        firstPanel.add(twoPanel);
        secondPanel.add(tablePanel);
        frame.getContentPane().add(firstPanel, BorderLayout.CENTER);
        frame.getContentPane().add(secondPanel, BorderLayout.SOUTH);
    }

    //    private void tampilTabel() {
//        try {
//            DefaultTableModel model = (DefaultTableModel) obatTable.getModel();
//            model.setRowCount(0);
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM obat");
//
//            while (resultSet.next()) {
//                Vector<Object> rowData = new Vector<>();
//                rowData.add(resultSet.getString("ID_Obat"));
//                rowData.add(resultSet.getString("NamaObat"));
//                rowData.add(resultSet.getString("Harga"));
//                rowData.add(resultSet.getString("Stok"));
//                rowData.add(resultSet.getString("Keterangan"));
//                rowData.add(resultSet.getString("Exp_Date"));
//
//                model.addRow(rowData);
//            }
//
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Obat();
            }
        });
    }
}