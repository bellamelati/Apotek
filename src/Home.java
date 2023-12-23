import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Home extends JFrame {
    private final String nama = null;
    private JLabel adminCRUDFrame;
    private JLabel obat;  
    private JLabel kasir;
    private JLabel hoverAdmin;
    private JLabel hoverObat;
    private JLabel hoverKasir;
    private JLabel logoutLabel;
    private JLabel menuLabel;
    private Waktu waktu;
    private JFrame frame;
    private JPanel featuresPanel;
    private Color backgroundColor;

    public void showGUI() {
        initializeFrame();
        addFeatureButtons();
        configureFrame();
        initializeComponents();
    }

    private void initializeFrame() {
        frame = new JFrame("Home - Apotek Kelompok 4");
        String hexColor = "#0D3749";
        backgroundColor = Color.decode(hexColor);

        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/judulmenuadmin.png")));
        JLabel label = new JLabel(image);

        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 10)); // Ganti 18 dengan ukuran font yang diinginkan


        frame.getContentPane().add(label, BorderLayout.NORTH); // judul
        frame.getContentPane().setBackground(backgroundColor);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximize the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }


    private void configureFrame() {
        frame.setVisible(true);
    }

    private void addFeatureButtons() {
        featuresPanel = new JPanel(new GridLayout(2, 3, 5, 0)); // 2 rows, 3 columns, with 10px horizontal and vertical gap
        String hexColor = "#0D3749";
        backgroundColor = Color.decode(hexColor);
        featuresPanel.setBackground(backgroundColor);

        addLabel("Admin", featuresPanel);
        addLabel("Obat", featuresPanel);
        addLabel("Kasir", featuresPanel);

        addButton("Menu Admin", "/images/menuadmin.png", new MenuAdminListener(), featuresPanel);
        addButton("Add Obat", "/images/addobat.png", new ObatListener(), featuresPanel);
        addButton("Kasir", "/images/kasir.png", new KasirListener(), featuresPanel);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoutPanel.setBackground(backgroundColor);
        addButton("Logout", "/images/logout.png", new LogoutListener(), logoutPanel);

        frame.getContentPane().add(featuresPanel, BorderLayout.CENTER);
        frame.getContentPane().add(logoutPanel, BorderLayout.SOUTH);
    }

    private void addLabel(String labelText, JPanel panel) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
    }

    private void addButton(String label, String imagePath, ActionListener listener, JPanel panel) {
        JButton button = createFeatureButton(imagePath);
        button.addActionListener(listener);
        panel.add(button);
    }

    private JButton createFeatureButton(String imagePath) {
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(Login.class.getResource(imagePath))));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
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

//        getContentPane().setBackground(new Color(8, 63, 89));

        menuLabel = new JLabel("");
        menuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/titlemenu.png"))));
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("Nirmala UI", Font.BOLD, 40));
        menuLabel.setBounds(0, 21, 1084, 139);

        getContentPane().add(menuLabel);

        // Create labels for admin, obat, and kasir below the picture
        JLabel adminLabel = createLabel("Admin", "/image/menuadmin.png", 180, 361);
        JLabel obatLabel = createLabel("Obat", "/image/addobat.png", 465, 361);
        JLabel kasirLabel = createLabel("Kasir", "/image/kasir.png", 750, 361);

        getContentPane().add(adminLabel);
        getContentPane().add(obatLabel);
        getContentPane().add(kasirLabel);

        frame.getContentPane();
    }

    private abstract class FeatureListener implements ActionListener {
        @Override
        public abstract void actionPerformed(ActionEvent e);
    }

    private class MenuAdminListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAdminCRUDFrame();
        }
    }

    private class ObatListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openObatGUI();
        }
    }

    private class KasirListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openKasirGUI();
        }
    }

    private class LogoutListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logout();
        }
    }

    private JLabel createLabel(String labelText, String imagePath, int x, int y) {
        JLabel label = new JLabel(labelText, new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))), SwingConstants.CENTER);
        label.setBounds(x, y, 170, 170);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);

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
        frame.setVisible(false);
        new AdminCRUDFrame();
    }

    private void openObatGUI() {
        frame.setVisible(false);
        new Obat();
    }

    private void openKasirGUI() {  
//        new Kasir();
    }

    private void logout() {
        dispose();
        new Login().showGUI();
    }
}
