import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Home extends JFrame {
    private final String username = null;
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

    private static final String MENU_ADMIN_IMAGE_PATH = "/images/menuadmin.png";

    public void showGUI() {
        initializeFrame();
        addFeatureButtons();
        configureFrame();
        initializeComponents();
    }

    private void initializeFrame() {
        frame = new JFrame("Home - Apotek Kelompok 4");
        backgroundColor = Color.decode("#0D3749");

        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/judulmenuadmin.png")));
        JLabel label = new JLabel(image);

        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 10));

        frame.getContentPane().add(label, BorderLayout.NORTH);
        frame.getContentPane().setBackground(backgroundColor);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    private void configureFrame() {
        frame.setVisible(true);
    }

    private void addFeatureButtons() {
        featuresPanel = new JPanel(new GridLayout(2, 3, 5, 0));
        featuresPanel.setBackground(backgroundColor);

        addButton("Menu Admin", MENU_ADMIN_IMAGE_PATH, new MenuAdminListener(), featuresPanel);
        addButton("Add Obat", "/images/addobat.png", new ObatListener(), featuresPanel);
        addButton("Kasir", "/images/kasir.png", new KasirListener(), featuresPanel);

        addLabel("Admin", featuresPanel);
        addLabel("Obat", featuresPanel);
        addLabel("Kasir", featuresPanel);

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

        Font originalFont = label.getFont();
        Font newFont = new Font(originalFont.getName(), Font.BOLD, 20); // Sesuaikan ukuran font yang diinginkan
        label.setFont(newFont);

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

    private void initializeComponents() {
        adminCRUDFrame = createLabel("", MENU_ADMIN_IMAGE_PATH, 180, 180);
        hoverAdmin = createLabel("", "/image/hoveradmin.png", 0, 361);
        obat = createLabel("", "/image/addobat.png", 465, 180);
        hoverObat = createLabel("", "/image/hoverobat.png", 0, 361);
        kasir = createLabel("", "/image/kasir.png", 750, 180);
        hoverKasir = createLabel("", "/image/hoverkasir.png", 0, 361);
        logoutLabel = createLabel("", "/image/logout.png", 30, 530);

        getContentPane().add(adminCRUDFrame);
        getContentPane().add(hoverAdmin);
        getContentPane().add(obat);
        getContentPane().add(hoverObat);
        getContentPane().add(kasir);
        getContentPane().add(hoverKasir);
        getContentPane().add(logoutLabel);

        menuLabel = new JLabel("");
        menuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/titlemenu.png"))));
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("Nirmala UI", Font.BOLD, 40));
        menuLabel.setBounds(0, 21, 1084, 139);

        getContentPane().add(menuLabel);

        JLabel adminLabel = createLabel("Admin", MENU_ADMIN_IMAGE_PATH, 180, 361);
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
                handleMouseEnter(adminCRUDFrame, MENU_ADMIN_IMAGE_PATH, hoverAdmin, true);
            }

            public void mouseExited(MouseEvent e) {
                handleMouseEnter(adminCRUDFrame, MENU_ADMIN_IMAGE_PATH, hoverAdmin, false);
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
