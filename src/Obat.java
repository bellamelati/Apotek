import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class Obat extends JFrame {

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

    private Connection connection;

    public Obat() {
        initializeFrame();
        initializeComponents();
        addActionListeners();
        initializeDatabaseConnection();
        tampilTabel();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initializeDatabaseConnection() {
        try {
            connection = KoneksiDB.getKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeFrame() {
        setLocation(220, 10);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Database Obat");
    }

    private void initializeComponents() {
        tambahLabel = new JLabel("Tambah");
        hapusLabel = new JLabel("Hapus");
        ubahLabel = new JLabel("Ubah");
        bersihLabel = new JLabel("Bersih");
        cariLabel = new JLabel("Cari");

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
        expDateField = new JTextField();

        cariField = new JTextField();

        // Initialize the table and table model
        String[] columnNames = {"Kode Obat", "Nama Obat", "Harga", "Stok", "Keterangan", "Exp_Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        obatTable = new JTable(tableModel);
        tableScrollPane = new JScrollPane(obatTable);

        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(7, 2));

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
        tambahLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openObatGUI();
            }

            private void openObatGUI() {
                new Obat();
            }
        });

        hapusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = obatTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmDialog = JOptionPane.showConfirmDialog(
                            null,
                            "Apakah Anda yakin ingin menghapus data ini?",
                            "Konfirmasi Hapus Data",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmDialog == JOptionPane.YES_OPTION) {
                        hapusData(selectedRow);
                        tampilTabel();
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Pilih baris yang akan dihapus terlebih dahulu.",
                            "Peringatan",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        ubahLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = obatTable.getSelectedRow();
                if (selectedRow != -1) {
                    Vector<Object> rowData = new Vector<>();
                    for (int i = 0; i < tableModel.getColumnCount(); i++) {
                        rowData.add(obatTable.getValueAt(selectedRow, i));
                    }
                    openUbahObatGUI(rowData);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Pilih baris yang akan diubah terlebih dahulu.",
                            "Peringatan",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

            private void openUbahObatGUI(Vector<Object> rowData) {
                // Implementasi untuk membuka jendela ubah data dengan data terpilih
                // Gunakan data dari rowData untuk mengisi form pada jendela ubah data
                // Anda dapat membuat kelas baru untuk jendela ubah data atau
                // menambahkan logika ubah data di sini.
            }
        });

        cariLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = cariField.getText().trim();
                TableRowSorter<DefaultTableModel> sorter =
                        new TableRowSorter<>((DefaultTableModel) obatTable.getModel());
                obatTable.setRowSorter(sorter);

                if (searchText.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void tampilTabel() {
        try {
            DefaultTableModel model = (DefaultTableModel) obatTable.getModel();
            model.setRowCount(0);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM obat");

            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getString("ID_Obat"));
                rowData.add(resultSet.getString("NamaObat"));
                rowData.add(resultSet.getString("Harga"));
                rowData.add(resultSet.getString("Stok"));
                rowData.add(resultSet.getString("Keterangan"));
                rowData.add(resultSet.getString("Exp_Date"));

                model.addRow(rowData);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusData(int selectedRow) {
        try {
            DefaultTableModel model = (DefaultTableModel) obatTable.getModel();
            String kodeObat = model.getValueAt(selectedRow, 0).toString();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM obat WHERE Kode_Obat = ?");
            preparedStatement.setString(1, kodeObat);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
