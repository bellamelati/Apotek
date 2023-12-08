import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Home extends JFrame {
    private JLabel adduser;
    private JLabel addobat;
    private JLabel kasir;
    private JLabel hoveruser;
    private JLabel dbobat_hover;
    private JLabel kasir_hover;
    private JLabel home_jl_keluar;
    private JLabel menuLabel;
    private LabelTime waktu;

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
        setSize(1100, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu Admin Apotek Unjani");
        setLocationRelativeTo(null);
        setResizable(false);

        JOptionPane.showMessageDialog(null, "Login Berhasil", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
    }

<<<<<<< HEAD
    private void userInterface() {
        waktu = new LabelTime();
        waktu.setForeground(Color.WHITE);
        waktu.setSize(200, 100);
        waktu.setLocation(800, 551);
        waktu.setFont(new Font("Arial", Font.BOLD, 24));
        waktu.setHorizontalAlignment(LabelTime.RIGHT);

        getContentPane().add(waktu);
        waktu.setBounds(750, 520, 300, 40);
=======
    private void addTitle() {
//        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/judul.png")));
//        JLabel label = new JLabel(image);
//        frame.getContentPane().add(label, BorderLayout.NORTH);
>>>>>>> bfba401c6b0f2a27329955078a093f66fe1298c8
    }

    private void initializeComponents() {
        getContentPane().setLayout(null);

        adduser = createLabel("/image/menuadmin.png", 180, 180);
        hoveruser = createLabel("/image/hoveradmin.png", 0, 361);
        addobat = createLabel("/image/addobat.png", 465, 180);
        dbobat_hover = createLabel("/image/hoverobat.png", 0, 361);
        kasir = createLabel("/image/kasir.png", 750, 180);
        kasir_hover = createLabel("/image/hoverkasir.png", 0, 361);
        home_jl_keluar = createLabel("/image/logout.png", 30, 530);

        getContentPane().add(adduser);
        getContentPane().add(hoveruser);
        getContentPane().add(addobat);
        getContentPane().add(dbobat_hover);
        getContentPane().add(kasir);
        getContentPane().add(kasir_hover);
        getContentPane().add(home_jl_keluar);

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
        home_jl_keluar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                logout();
            }
        });

        adduser.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                handleMouseEnter(adduser, "/image/menuadmin.png", hoveruser, true);
            }

            public void mouseExited(MouseEvent e) {
                handleMouseEnter(adduser, "/image/menuadmin.png", hoveruser, false);
            }

            public void mouseClicked(MouseEvent e) {
                openAddAdminGUI();
            }
        });

        addobat.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                handleMouseEnter(addobat, "/image/addobat.png", dbobat_hover, true);
            }

            public void mouseExited(MouseEvent e) {
                handleMouseEnter(addobat, "/image/addobat.png", dbobat_hover, false);
            }

            public void mouseClicked(MouseEvent e) {
                openAddObatGUI();
            }
        });

        kasir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                handleMouseEnter(kasir, "/image/kasir.png", kasir_hover, true);
            }

            public void mouseExited(MouseEvent e) {
                handleMouseEnter(kasir, "/image/kasir.png", kasir_hover, false);
            }

            public void mouseClicked(MouseEvent e) {
                openBayarKasirGUI();
            }
        });
    }

    private void handleMouseEnter(JLabel label, String imagePath, JLabel hoverLabel, boolean showHover) {
        label.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))));
        hoverLabel.setVisible(showHover);
    }

    private void openAddAdminGUI() {
        new AddAdminGUI();
    }

<<<<<<< HEAD
    private void openAddObatGUI() {
        new AddObatGUI();
    }

    private void openBayarKasirGUI() {
        new BayarKasirGUI();
=======
    private class MenuAdminListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAdminCRUDFrame();
        }
    }

    private class AddObatListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            openAddObatForm();
        }
    }

    private class KasirListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            openKasirTransaction();
        }
    }

    private class LogoutListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logout();
        }
    }

    private void openAdminCRUDFrame() {
        // Create an instance of AdminCRUDFrame and show it
        AdminCRUDFrame adminCRUDFrame = new AdminCRUDFrame();
        adminCRUDFrame.setVisible(true);
>>>>>>> bfba401c6b0f2a27329955078a093f66fe1298c8
    }
//
//    private void openAddObatForm() {
//        // Logika untuk tindakan saat add obat diklik
//        AddObatForm addObatForm = new AddObatForm();
//        addObatForm.showGUI();
//    }
//
//    private void openKasirTransaction() {
//        // Logika untuk tindakan saat kasir diklik
//        KasirTransaction kasirTransaction = new KasirTransaction();
//        kasirTransaction.showGUI();
//    }

    private void logout() {
        dispose();
        new Login().showGUI();
    }

