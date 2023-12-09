import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Home extends JFrame {
    private JLabel adminCRUDFrame;
    private JLabel obat;  
    private JLabel kasir;
    private JLabel hoverAdmin;
    private JLabel hoverObat;
    private JLabel hoverKasir;
    private JLabel logoutLabel;
    private JLabel menuLabel;
    private Waktu waktu;

    public Home(String nama) {
        initializeFrame();
        initializeComponents();
        addActionListeners();

        JOptionPane.showMessageDialog(null, "Selamat Datang " + nama, "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);

        userInterface();

        setVisible(true);
    }

    private void initializeFrame() {
        setLocation(120, 50);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Admin Apotek Unjani Kelompok 4");
        setLocationRelativeTo(null);
        setResizable(false);

        JOptionPane.showMessageDialog(null, "Login Berhasil", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
    }

    private void userInterface() {
        waktu = new Waktu();
        waktu.setForeground(Color.WHITE);
        waktu.setSize(200, 100);
        waktu.setLocation(800, 551);
        waktu.setFont(new Font("Arial", Font.BOLD, 24));
        waktu.setHorizontalAlignment(Waktu.RIGHT);

        getContentPane().add(waktu);
        waktu.setBounds(750, 520, 300, 40);
    }

    private void initializeComponents() {
        getContentPane().setLayout(null);

        adminCRUDFrame = createLabel("/image/menuadmin.png", 180, 180);
        hoverAdmin = createLabel("/image/hoveradmin.png", 0, 361);
        obat = createLabel("/image/addobat.png", 465, 180);
        hoverObat = createLabel("/image/hoverobat.png", 0, 361);
        kasir = createLabel("/image/kasir.png", 750, 180);
        hoverKasir = createLabel("/image/hoverkasir.png", 0, 361);
        logoutLabel = createLabel("/image/logout.png", 30, 530);

        getContentPane().add(adminCRUDFrame);
        getContentPane().add(hoverAdmin);
        getContentPane().add(obat);  
        getContentPane().add(hoverObat);
        getContentPane().add(kasir);
        getContentPane().add(hoverKasir);
        getContentPane().add(logoutLabel);

        getContentPane().setBackground(new Color(8, 63, 89));

        menuLabel = new JLabel("");
        menuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/titlemenu.png"))));
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("Nirmala UI", Font.BOLD, 40));
        menuLabel.setBounds(0, 21, 1084, 139);

        getContentPane().add(menuLabel);
    }

    private JLabel createLabel(String imagePath, int x, int y) {
        JLabel label = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))));
        label.setBounds(x, y, 170, 170);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private void addActionListeners() {
        logoutLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                logout();
            }
        });

        adminCRUDFrame.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                handleMouseEnter(adminCRUDFrame, "/image/menuadmin.png", hoverAdmin, true);
            }

            public void mouseExited(MouseEvent e) {
                handleMouseEnter(adminCRUDFrame, "/image/menuadmin.png", hoverAdmin, false);
            }

            public void mouseClicked(MouseEvent e) {
                openAdminCRUDFrame();
            }
        });

        obat.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                handleMouseEnter(obat, "/image/addobat.png", hoverObat, true);
            }

            public void mouseExited(MouseEvent e) {
                handleMouseEnter(obat, "/image/addobat.png", hoverObat, false);
            }

            public void mouseClicked(MouseEvent e) {
                openObatGUI();
            }
        });

        kasir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                handleMouseEnter(kasir, "/image/kasir.png", hoverKasir, true);
            }

            public void mouseExited(MouseEvent e) {
                handleMouseEnter(kasir, "/image/kasir.png", hoverKasir, false);
            }

            public void mouseClicked(MouseEvent e) {
                openKasirGUI();  
            }
        });
    }

    private void handleMouseEnter(JLabel label, String imagePath, JLabel hoverLabel, boolean showHover) {
        label.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))));
        hoverLabel.setVisible(showHover);
    }

    private void openAdminCRUDFrame() {
        new AdminCRUDFrame();
    }

    private void openObatGUI() {  
        new Obat();
    }

    private void openKasirGUI() {  
        new Kasir();
    }

    private void logout() {
        dispose();
        new Login().showGUI();
    }
}
