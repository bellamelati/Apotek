import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.util.Objects;

public class Kasir extends JFrame {
    private JFrame frame;
    private JButton searchButton;
    private JButton prosesButton;
    private JButton batalButton;
    private JButton okeButton;
    private JButton hitungButton;
    private JButton hapusButton;
    private JButton bersihButton;
    private JLabel kodeObatLabel;
    private JTextField kodeObatField;
    private JLabel cariLabel;
    private JTextField cariField;
    private JLabel merkLabel;
    private JTextField merkField;
    private JLabel hargaLabel;
    private JTextField hargaField;
    private JLabel jumlahitemLabel;
    private JTextField jumlahitemField;
    private JLabel jumlahLabel;
    private JTextField jumlahField;
    private JLabel totalLabel;
    private JTextField totalField;
    private JTextField tunaiField;
    private JLabel tunaiLabel;
    private JTextField kembalianField;
    private JLabel kembalianLabel;
    private DefaultTableModel tableModel;
    private JTable kasirTable;
    private JButton backButton;
    private JScrollPane tableScrollPane;
    private Color backgroundColor;
    private String hexColor = "#0D3749";
    private Connection connection;
    private double totalHarga = 0;

    public Kasir() {
        initializeFrame();
        configureFrame();
        initializeComponents();
        initializeDatabaseConnection();
        Action();
    }

    private void initializeDatabaseConnection() {
        try {
            connection = KoneksiDB.getKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeFrame() {
        frame = new JFrame("Kasir - Apotek Kelompok 4");
        backgroundColor = Color.decode(hexColor);
        frame.getContentPane().setBackground(backgroundColor);

        // Judul Panel menggunakan BorderLayout
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(backgroundColor);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Atur margin

        // Tombol Kembali
        ImageIcon backImages = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/backbutton.png")));
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
        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/menukasir.png")));
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

    private void closeFrame() {
        frame.dispose();
        Home home = new Home();
        home.showGUI();
    }

    private void configureFrame() {
        frame.setVisible(true);
    }

    private void initializeComponents() {
        Font labelFont = new Font("Rockwell", Font.BOLD, 18);

        JPanel firstPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        firstPanel.setBackground(backgroundColor);
        firstPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0)); // Atur margin
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));

        JPanel secondPanel = new JPanel(new GridLayout(4, 2, 0, 0));
        secondPanel.setBackground(backgroundColor);
        secondPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Atur margin
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.PAGE_AXIS));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 3));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Atur margin bawah
        searchPanel.setBackground(backgroundColor);

        ImageIcon searchButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/kcari.png")));
        searchButton = new JButton(searchButtonImage);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setPreferredSize(new Dimension(searchButtonImage.getIconWidth(), searchButtonImage.getIconHeight()));

        kodeObatLabel = new JLabel("Kode Obat");
        kodeObatLabel.setForeground(Color.WHITE);
        kodeObatLabel.setFont(labelFont);

        kodeObatField = new JTextField(10);

        JPanel inputPanel = new JPanel(new GridLayout(2, 6, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(2, 20, 5, 5)); // Atur margin
        inputPanel.setBackground(backgroundColor);

        merkLabel = new JLabel("Merk");
        merkLabel.setForeground(Color.WHITE);
        merkLabel.setFont(labelFont);

        merkField = new JTextField(10);

        hargaLabel = new JLabel("Harga Jual");
        hargaLabel.setForeground(Color.WHITE);
        hargaLabel.setFont(labelFont);

        hargaField = new JTextField(10);

        jumlahitemLabel = new JLabel("Jumlah Item");
        jumlahitemLabel.setForeground(Color.WHITE);
        jumlahitemLabel.setFont(labelFont);

        jumlahitemField = new JTextField(10);

        jumlahLabel = new JLabel("Jumlah");
        jumlahLabel.setForeground(Color.WHITE);
        jumlahLabel.setFont(labelFont);

        jumlahField = new JTextField(10);

        ImageIcon batalButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/kbatal.png")));
        JButton batalButton = new JButton(batalButtonImage);
        batalButton.setBorderPainted(false);
        batalButton.setContentAreaFilled(false);
        batalButton.setPreferredSize(new Dimension(batalButtonImage.getIconWidth(), batalButtonImage.getIconHeight()));

        ImageIcon prosesButtonImage = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/proses.png")));
        JButton prosesButton = new JButton(prosesButtonImage);
        prosesButton.setBorderPainted(false);
        prosesButton.setContentAreaFilled(false);
        prosesButton.setPreferredSize(new Dimension(prosesButtonImage.getIconWidth(), prosesButtonImage.getIconHeight()));

        searchPanel.add(kodeObatLabel);
        searchPanel.add(kodeObatField);
        searchPanel.add(searchButton);

        inputPanel.add(merkLabel);
        inputPanel.add(merkField);
        inputPanel.add(jumlahitemLabel);
        inputPanel.add(jumlahitemField);
        inputPanel.add(prosesButton);
        inputPanel.add(hargaLabel);
        inputPanel.add(hargaField);
        inputPanel.add(jumlahLabel);
        inputPanel.add(jumlahField);
        inputPanel.add(batalButton);

        JPanel twoPanel = new JPanel(new GridLayout(1, 7, 0, 0));
        twoPanel.setBackground(backgroundColor);
        twoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Atur margin

        // Add the table scroll pane to the table panel
        JPanel tablePanel = new JPanel(new GridLayout(1, 0, 0, 0));
        tablePanel.setBackground(backgroundColor);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0)); // Atur margin
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

        // Create a table model with columns
        String[] columns = {"Kode Obat", "Nama Obat", "Harga", "Stok", "Keterangan", "Exp_Date"};
        tableModel = new DefaultTableModel(columns, 0);
        kasirTable = new JTable(tableModel);

        // Set up sorting for the table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        kasirTable.setRowSorter(sorter);

        JScrollPane tableScrollPane = new JScrollPane(kasirTable);
        tableScrollPane.add(Box.createVerticalStrut(50));
        tableScrollPane.setBackground(backgroundColor);
        tableScrollPane.setBorder(new EmptyBorder(5, 20, 3, 20));
        tablePanel.add(tableScrollPane);

        // Mengatur ukuran tombol oke
        ImageIcon okeButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/oke.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        JButton okeButton = new JButton(okeButtonImage);
        okeButton.setBorderPainted(false);
        okeButton.setContentAreaFilled(false);
        okeButton.setPreferredSize(new Dimension(130, 50));

        // Mengatur ukuran tombol hitung
        ImageIcon hitungButtonImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/hitung.png")))
                .getImage().getScaledInstance(130, 30, Image.SCALE_DEFAULT));
        JButton hitungButton = new JButton(hitungButtonImage);
        hitungButton.setBorderPainted(false);
        hitungButton.setContentAreaFilled(false);
        hitungButton.setPreferredSize(new Dimension(130, 50));

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

        JPanel crudPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5)); // Memberikan jarak horizontal 20 dan vertikal 5
        crudPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0)); // Atur margin
        crudPanel.setBackground(backgroundColor);

        totalLabel = new JLabel("Total");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(labelFont);

        totalField = new JTextField(10);

        tunaiLabel = new JLabel("Tunai");
        tunaiLabel.setForeground(Color.WHITE);
        tunaiLabel.setFont(labelFont);

        tunaiField = new JTextField(10);

        kembalianLabel = new JLabel("Kembalian");
        kembalianLabel.setForeground(Color.WHITE);
        kembalianLabel.setFont(labelFont);

        kembalianField = new JTextField(10);

        crudPanel.add(totalLabel);
        crudPanel.add(totalField);
        crudPanel.add(tunaiLabel);
        crudPanel.add(tunaiField);
        crudPanel.add(kembalianLabel);
        crudPanel.add(kembalianField);
        crudPanel.add(okeButton);
        crudPanel.add(hitungButton);
        crudPanel.add(deleteButton);
        crudPanel.add(clearAllButton);

        twoPanel.add(searchPanel);
        firstPanel.add(twoPanel);
        firstPanel.add(inputPanel);

        secondPanel.add(tablePanel);
        secondPanel.add(crudPanel);

        frame.getContentPane().add(firstPanel, BorderLayout.CENTER);
        frame.getContentPane().add(secondPanel, BorderLayout.SOUTH);
    }

    private void Action(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariObatByKode();
            }
        });

        // Tombol untuk memproses penambahan obat ke dalam tabel pembelian
        prosesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahObatKeTabel();
            }
        });

        // Tombol untuk menghapus data obat pada tabel pembelian
        batalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusObatDariTabel();
            }
        });

        // Tombol untuk menghitung total harga dari seluruh obat yang dibeli
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungTotalHarga();
            }
        });

        // Tombol untuk mencetak struk pembelian
        okeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cetakStruk();
            }
        });
    }

    private void cariObatByKode() {
        String kodeObat = kodeObatField.getText();

        try {
            String query = "SELECT merk, harga FROM obat WHERE kode_obat = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, kodeObat);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Populate the corresponding fields with the retrieved data
                merkField.setText(resultSet.getString("merk"));
                hargaField.setText(String.valueOf(resultSet.getDouble("harga")));
                // You can add more fields if needed

            } else {
                JOptionPane.showMessageDialog(this, "Obat tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Metode untuk memproses penambahan obat ke dalam tabel pembelian
    private void tambahObatKeTabel() {
        String kodeObat = kodeObatField.getText();
        String merk = merkField.getText();
        double harga = Double.parseDouble(hargaField.getText());
        int jumlahItem = Integer.parseInt(jumlahitemField.getText());
        int jumlah = Integer.parseInt(jumlahField.getText());

        // Validasi input (misalnya, pastikan jumlah obat yang dimasukkan lebih dari 0)
        if (jumlah <= 0) {
            JOptionPane.showMessageDialog(this, "Jumlah obat harus lebih dari 0", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Update stok obat di database (misalnya, kurangi stok sejumlah obat yang dibeli)
            String updateQuery = "UPDATE obat SET stok = stok - ? WHERE kode_obat = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, jumlah);
            updateStatement.setString(2, kodeObat);
            updateStatement.executeUpdate();

            // Tambahkan data obat ke dalam tabel pembelian
            String[] rowData = {kodeObat, merk, String.valueOf(harga), String.valueOf(jumlahItem), String.valueOf(jumlah)};
            tableModel.addRow(rowData);

            // Hitung dan tambahkan total harga dari obat yang baru ditambahkan
            totalHarga += harga * jumlah;
            totalField.setText(String.valueOf(totalHarga));

            // Setelah ditambahkan ke tabel, bersihkan field-field input
            bersihkanInput();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk menghapus data obat pada tabel pembelian
    private void hapusObatDariTabel() {
        int selectedRow = kasirTable.getSelectedRow();
        if (selectedRow != -1) {
            String kodeObat = tableModel.getValueAt(selectedRow, 0).toString();
            double harga = Double.parseDouble(tableModel.getValueAt(selectedRow, 2).toString());
            int jumlah = Integer.parseInt(tableModel.getValueAt(selectedRow, 4).toString());

            try {
                // Update stok obat di database (misalnya, tambahkan stok sejumlah obat yang dihapus)
                String updateQuery = "UPDATE obat SET stok = stok + ? WHERE kode_obat = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, jumlah);
                updateStatement.setString(2, kodeObat);
                updateStatement.executeUpdate();

                // Hapus baris yang dipilih dari tabel pembelian
                totalHarga -= harga * jumlah;
                totalField.setText(String.valueOf(totalHarga));
                tableModel.removeRow(selectedRow);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Metode untuk menghitung total harga dari seluruh obat yang dibeli
    private void hitungTotalHarga() {
        totalHarga = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            double harga = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
            int jumlah = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            totalHarga += harga * jumlah;
        }
        totalField.setText(String.valueOf(totalHarga));
    }

    // Metode untuk mencetak struk pembelian
    private void cetakStruk() {
        try {
            // Membuat objek PrinterJob
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return Printable.NO_SUCH_PAGE;
                    }

                    // Mendapatkan graphics dari PrinterJob
                    Graphics2D g2d = (Graphics2D) graphics;
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                    // Menyiapkan konten yang akan dicetak (misalnya, isi struk)
                    StringBuilder content = new StringBuilder();
                    content.append("========= Struk Pembelian =========\n");
                    content.append("Kode Obat   : " + kodeObatField.getText() + "\n");
                    content.append("Merk        : " + merkField.getText() + "\n");
                    content.append("Harga       : " + hargaField.getText() + "\n");
                    content.append("Jumlah Item : " + jumlahitemField.getText() + "\n");
                    content.append("Jumlah      : " + jumlahField.getText() + "\n");
                    content.append("Total Harga : " + totalField.getText() + "\n");
                    // Tambahkan data lainnya yang ingin Anda cetak

                    // Menentukan font dan ukuran font untuk mencetak
                    Font font = new Font("Monospaced", Font.PLAIN, 12);
                    g2d.setFont(font);

                    // Memecah konten menjadi beberapa baris dan mencetaknya
                    String[] lines = content.toString().split("\n");
                    int y = 15; // Koordinat y awal
                    for (String line : lines) {
                        g2d.drawString(line, 10, y);
                        y += 15; // Menyesuaikan jarak antar baris
                    }

                    return Printable.PAGE_EXISTS;
                }
            });

            // Menampilkan dialog pencetakan dan mencetak jika pengguna menyetujui
            if (job.printDialog()) {
                job.print();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk membersihkan semua input
    private void bersihkanInput() {
        kodeObatField.setText("");
        merkField.setText("");
        hargaField.setText("");
        jumlahitemField.setText("");
        jumlahField.setText("");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Kasir());
    }
}
