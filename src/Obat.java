import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;


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
    private JDateChooser expDateChooser;

    private JTextField cariField;

    private DefaultTableModel tableModel;
    private JTable obatTable;
    private JScrollPane tableScrollPane;

    private LabelTime waktu;

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
    }