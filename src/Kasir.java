import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Objects;

public class Kasir extends JFrame {
    private JFrame frame;
    private JLabel kodeObatLabel;
    private JTextField kodeObatField;
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

    public Kasir() {
        initializeFrame();
        configureFrame();
        initializeComponents();
        initializeDatabaseConnection();
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

    private void configureFrame() {
        frame.setVisible(true);
    }

    private void initializeComponents() {
        JPanel firstPanel = new JPanel(new BorderLayout());
        firstPanel.setBackground(Color.green);
        firstPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0)); // Atur margin

        JPanel secondPanel = new JPanel(new GridLayout(1, 1, 0, 0));
        secondPanel.setBackground(Color.red);
        secondPanel.setBorder(BorderFactory.createEmptyBorder(1, 0, 5, 0)); // Atur margin

        Font labelFont = new Font("Rockwell", Font.BOLD, 18);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0)); // Atur margin
        searchPanel.setBackground(backgroundColor);

        // Add search label and field
        kodeObatLabel = new JLabel("Kode Obat");
        kodeObatLabel.setForeground(Color.WHITE);
        kodeObatLabel.setFont(labelFont);

        kodeObatField = new JTextField(5);

        ImageIcon searchButtonImage = new ImageIcon(getClass().getResource("/images/kcari.png"));
        JButton searchButton = new JButton(searchButtonImage);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);

        searchPanel.add(kodeObatLabel);
        searchPanel.add(kodeObatField);
        searchPanel.add(searchButton);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 0, 0)); // Mengubah ke 3 kolom agar lebih mudah dilihat
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Atur margin
        inputPanel.setBackground(Color.blue);

        merkLabel = new JLabel("Merk");
        merkLabel.setForeground(Color.WHITE);
        merkLabel.setFont(labelFont);

        merkField = new JTextField(10);

        hargaLabel = new JLabel("Harga");
        hargaLabel.setForeground(Color.WHITE);
        hargaLabel.setFont(labelFont);

        hargaField = new JTextField(10);

        jumlahitemLabel = new JLabel("jumlah item");
        jumlahitemLabel.setForeground(Color.WHITE);
        jumlahitemLabel.setFont(labelFont);

        jumlahitemField = new JTextField(10);

        jumlahLabel = new JLabel("jumlah");
        jumlahLabel.setForeground(Color.WHITE);
        jumlahLabel.setFont(labelFont);

        jumlahField = new JTextField(10);

        inputPanel.add(merkLabel);
        inputPanel.add(merkField);
        inputPanel.add(hargaLabel);
        inputPanel.add(hargaField);
        inputPanel.add(jumlahitemLabel);
        inputPanel.add(jumlahitemField);
        inputPanel.add(jumlahLabel);
        inputPanel.add(jumlahField);

        //inputPanel.add(totalLabel);
//        inputPanel.add(totalField);
//        inputPanel.add(tunaiLabel);
//        inputPanel.add(tunaiField);
//        inputPanel.add(kembalianLabel);
//        inputPanel.add(kembalianField);

        firstPanel.add(searchPanel, BorderLayout.NORTH);
        firstPanel.add(inputPanel, BorderLayout.CENTER);
        frame.getContentPane().add(firstPanel, BorderLayout.CENTER);
        frame.getContentPane().add(secondPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Kasir());
    }
}
