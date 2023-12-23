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
    private JButton backButton;
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

        // Judul Panel menggunakan BorderLayout
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(backgroundColor);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Atur margin

        // Tombol Kembali
        ImageIcon backImages = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/backbutton.png")));
        backButton = new JButton(backImages);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        backButton.setContentAreaFilled(false);
        backButton.setPreferredSize(new Dimension(backImages.getIconWidth(), backImages.getIconHeight()));

        // Letakkan tombol kembali di sebelah kiri
        titlePanel.add(backButton, BorderLayout.WEST);

        // Label Judul
        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/menucariobat.png")));
        JLabel titleLabel = new JLabel(image);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Letakkan judul di tengah
        titlePanel.add(titleLabel, BorderLayout.CENTER);

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
        firstPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0)); // Atur margin

        JPanel secondPanel = new JPanel(new GridLayout(0, 1, 0, 0));
        secondPanel.setBackground(backgroundColor);
        secondPanel.setBorder(BorderFactory.createEmptyBorder(1, 0, 5, 0)); // Atur margin

        Font labelFont = new Font("Rockwell", Font.BOLD, 18);

        JPanel inputPanel = new JPanel(new GridLayout(3, 7, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 5, 0)); // Atur margin
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

        JPanel twoPanel = new JPanel(new GridLayout(1, 7, 0, 0));
        twoPanel.setBackground(backgroundColor);
        twoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Atur margin

        JPanel crudPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        crudPanel.setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100)); // Atur margin
        crudPanel.setBackground(backgroundColor);

        // Mengatur ukuran tombol tambah
        ImageIcon addImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/tambahBtn.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        JButton addButton = new JButton(addImage);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setPreferredSize(new Dimension(130, 50));

        // Mengatur ukuran tombol ubah
        ImageIcon updateButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/ubahBtn.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        JButton updateButton = new JButton(updateButtonImage);
        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        updateButton.setPreferredSize(new Dimension(130, 50));

        // Mengatur ukuran tombol hapus
        ImageIcon deleteButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/hapusBtn.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        JButton deleteButton = new JButton(deleteButtonImage);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setPreferredSize(new Dimension(130, 50));

        // Mengatur ukuran tombol bersih
        ImageIcon clearButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/bersihBtn.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        JButton clearAllButton = new JButton(clearButtonImage);
        clearAllButton.setBorderPainted(false);
        clearAllButton.setContentAreaFilled(false);
        clearAllButton.setPreferredSize(new Dimension(130, 50));

        // Add search label and field
        cariLabel = new JLabel("Kode Obat");
        cariLabel.setForeground(Color.WHITE);
        cariLabel.setFont(labelFont);

        cariField = new JTextField(10);

        // Add search button
        ImageIcon searchButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/kcari.png")));
        JButton searchButton = new JButton(searchButtonImage);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setPreferredSize(new Dimension(searchButtonImage.getIconWidth(), searchButtonImage.getIconHeight()));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 170, 5, 0)); // Atur margin bawah
        searchPanel.setBackground(backgroundColor);
        searchPanel.add(cariLabel);
        searchPanel.add(cariField);
        searchPanel.add(searchButton);

        crudPanel.add(addButton);
        crudPanel.add(updateButton);
        crudPanel.add(deleteButton);
        crudPanel.add(clearAllButton);
        crudPanel.add(searchPanel);

        // Add the table scroll pane to the table panel
        JPanel tablePanel = new JPanel(new GridLayout(6, 0, 0, 0));
        tablePanel.setBackground(backgroundColor);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0)); // Atur margin
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
//        twoPanel.add(searchPanel);

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