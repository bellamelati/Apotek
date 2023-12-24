import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class Obat extends JFrame {
    private JFrame frame;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton clearAllButton;
    private JButton searchButton;
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
        initializeDatabaseConnection();
        tampilkanDataDariDatabase();
        addActionListeners();
    }

    private void initializeDatabaseConnection() {
        try {
            connection = KoneksiDB.getKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Panggil method untuk menutup frame
                closeFrame();
            }
        });

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

    // Tambahkan method untuk menutup frame Obat
    private void closeFrame() {
        frame.dispose(); // Tutup frame Obat
        Home home = new Home();
        home.showGUI(); // Tampilkan frame Home
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
        addButton = new JButton(addImage);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setPreferredSize(new Dimension(130, 50));

        // Mengatur ukuran tombol ubah
        ImageIcon updateButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/ubahBtn.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        updateButton = new JButton(updateButtonImage);
        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        updateButton.setPreferredSize(new Dimension(130, 50));

        // Mengatur ukuran tombol hapus
        ImageIcon deleteButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/hapusBtn.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        deleteButton = new JButton(deleteButtonImage);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setPreferredSize(new Dimension(130, 50));

        // Mengatur ukuran tombol bersih
        ImageIcon clearButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/bersihBtn.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        clearAllButton = new JButton(clearButtonImage);
        clearAllButton.setBorderPainted(false);
        clearAllButton.setContentAreaFilled(false);
        clearAllButton.setPreferredSize(new Dimension(130, 50));

        // Search label and field
        cariLabel = new JLabel("Kode Obat");
        cariLabel.setForeground(Color.WHITE);
        cariLabel.setFont(labelFont);

        cariField = new JTextField(10);

        // Search button
        ImageIcon searchButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/kcari.png")));
        searchButton = new JButton(searchButtonImage);
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

        twoPanel.add(crudPanel);;

        firstPanel.add(inputPanel);
        firstPanel.add(twoPanel);
        secondPanel.add(tablePanel);
        frame.getContentPane().add(firstPanel, BorderLayout.CENTER);
        frame.getContentPane().add(secondPanel, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahObatKeDatabase();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateObatDiDatabase();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusObatDariDatabase();
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bersihkanForm();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariObat();
            }
        });

        obatTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    handleTableClick();
                }
            }
        });
    }

    private void handleTableClick() {
        int row = obatTable.getSelectedRow();
        if (row != -1) {
            kodeObatField.setText((String) obatTable.getValueAt(row, 0));
            namaObatField.setText((String) obatTable.getValueAt(row, 1));
            hargaField.setText((String) obatTable.getValueAt(row, 2));
            stokField.setText((String) obatTable.getValueAt(row, 3));
            keteranganField.setText((String) obatTable.getValueAt(row, 4));
            expDateField.setText((String) obatTable.getValueAt(row, 5));
        }
    }

    private void tampilkanDataDariDatabase() {
        try (Connection connection = KoneksiDB.getKoneksi()) {
            String query = "SELECT Kode_Obat, Nama_Obat, Harga_Obat, Stok, Ket_Obat, Exp_Date FROM obat";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Bersihkan data di tabel sebelum menambahkan data baru
                tableModel.setRowCount(0);

                // Tambahkan baris data dari ResultSet ke tabel
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getString("Kode_Obat"),
                            resultSet.getString("Nama_Obat"),
                            resultSet.getString("Harga_Obat"),
                            resultSet.getString("Stok"),
                            resultSet.getString("Ket_Obat"),
                            resultSet.getString("Exp_Date")
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tambahObatKeDatabase() {
        // Implementasi untuk tambah obat ke database
        // Ambil nilai dari field
        String kodeObat = kodeObatField.getText();
        String namaObat = namaObatField.getText();
        String harga = hargaField.getText();
        String stok = stokField.getText();
        String keterangan = keteranganField.getText();
        String expDate = expDateField.getText();

        // Lakukan validasi (pastikan tidak kosong)
        if (!kodeObat.isEmpty() && !namaObat.isEmpty() && !harga.isEmpty() && !stok.isEmpty() && !keterangan.isEmpty() && !expDate.isEmpty()) {
            // Lakukan operasi tambah ke database
            try (Connection connection = KoneksiDB.getKoneksi()) {
                String query = "INSERT INTO obat (Kode_Obat, Nama_Obat, Harga_Obat, Stok, Ket_Obat, Exp_Date) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, kodeObat);
                    preparedStatement.setString(2, namaObat);
                    preparedStatement.setString(3, harga);
                    preparedStatement.setString(4, stok);
                    preparedStatement.setString(5, keterangan);
                    preparedStatement.setString(6, expDate);

                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        // Jika penambahan berhasil, tambahkan juga ke tabel di GUI
                        Object[] rowData = {kodeObat, namaObat, harga, stok, keterangan, expDate};
                        tableModel.addRow(rowData);
                        bersihkanForm();
                        JOptionPane.showMessageDialog(Obat.this, "Data Berhasil Ditambahkan!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding obat to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateObatDiDatabase() {
        // Implementasi untuk update obat di database
        int row = obatTable.getSelectedRow();
        if (row != -1) {
            // Ambil nilai dari field
            String kodeObat = kodeObatField.getText();
            String namaObat = namaObatField.getText();
            String harga = hargaField.getText();
            String stok = stokField.getText();
            String keterangan = keteranganField.getText();
            String expDate = expDateField.getText();

            // Lakukan validasi (pastikan tidak kosong)
            if (!kodeObat.isEmpty() && !namaObat.isEmpty() && !harga.isEmpty() && !stok.isEmpty() && !keterangan.isEmpty() && !expDate.isEmpty()) {
                // Lakukan operasi update ke database
                try (Connection connection = KoneksiDB.getKoneksi()) {
                    String query = "UPDATE obat SET Nama_Obat=?, Harga=?, Stok=?, Keterangan=?, Exp_Date=? WHERE Kode_Obat=?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, namaObat);
                        preparedStatement.setString(2, harga);
                        preparedStatement.setString(3, stok);
                        preparedStatement.setString(4, keterangan);
                        preparedStatement.setString(5, expDate);
                        preparedStatement.setString(6, kodeObat);

                        int affectedRows = preparedStatement.executeUpdate();
                        if (affectedRows > 0) {
                            // Jika update berhasil, update juga di tabel di GUI
                            obatTable.setValueAt(namaObat, row, 1);
                            obatTable.setValueAt(harga, row, 2);
                            obatTable.setValueAt(stok, row, 3);
                            obatTable.setValueAt(keterangan, row, 4);
                            obatTable.setValueAt(expDate, row, 5);
                            bersihkanForm();
                            JOptionPane.showMessageDialog(Obat.this, "Data Berhasil Diubah!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating obat in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusObatDariDatabase() {
        // Implementasi untuk hapus obat dari database
        int row = obatTable.getSelectedRow();
        if (row != -1) {
            // Ambil nilai dari field
            String kodeObat = (String) obatTable.getValueAt(row, 0);

            // Lakukan operasi hapus ke database
            try (Connection connection = KoneksiDB.getKoneksi()) {
                String query = "DELETE FROM obat WHERE Kode_Obat=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, kodeObat);

                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        // Jika hapus berhasil, hapus juga dari tabel di GUI
                        tableModel.removeRow(row);
                        bersihkanForm();
                        JOptionPane.showMessageDialog(Obat.this, "Data Berhasil Dihapus!", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting obat from the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bersihkanForm() {
        // Bersihkan nilai di field
        kodeObatField.setText("");
        namaObatField.setText("");
        hargaField.setText("");
        stokField.setText("");
        keteranganField.setText("");
        expDateField.setText("");
    }

    private void cariObat() {
        // Implementasi untuk mencari obat dari database
        String kodeObat = cariField.getText();
        if (!kodeObat.isEmpty()) {
            TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) obatTable.getRowSorter();
            sorter.setRowFilter(RowFilter.regexFilter(kodeObat, 0));
        } else {
            JOptionPane.showMessageDialog(this, "Enter a Kode Obat to search.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Obat();
            }
        });
    }
}