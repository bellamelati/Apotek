import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Home extends JFrame {

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
        featuresPanel = new JPanel(null); // Menggunakan layout null
        featuresPanel.setBackground(backgroundColor);

        addButton("Menu Admin", MENU_ADMIN_IMAGE_PATH, new MenuAdminListener(), featuresPanel, 323, 190);
        addButton("Add Obat", "/images/addobat.png", new ObatListener(), featuresPanel, 646, 190);
        addButton("Kasir", "/images/kasir.png", new KasirListener(), featuresPanel, 969, 190);

        addLabel("Admin", featuresPanel, 330, 400);
        addLabel("Obat", featuresPanel, 655, 400);
        addLabel("Kasir", featuresPanel, 980, 400);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoutPanel.setBackground(backgroundColor);
        addButton("Logout", "/images/logout.png", new LogoutListener(), logoutPanel, 30, 530);

        frame.getContentPane().add(featuresPanel, BorderLayout.CENTER);
        frame.getContentPane().add(logoutPanel, BorderLayout.SOUTH);
    }

    private void addLabel(String labelText, JPanel panel, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        Font originalFont = label.getFont();
        Font newFont = new Font(originalFont.getName(), Font.BOLD, 30); // Sesuaikan ukuran font yang diinginkan
        label.setFont(newFont);


        label.setBounds(x, y, 150, 30);

        panel.add(label);
    }

    private void addButton(String label, String imagePath, ActionListener listener, JPanel panel, int x, int y) {
        JButton button = createFeatureButton(imagePath);
        button.addActionListener(listener);
        panel.add(button);
        button.setBounds(x, y, 170, 170);
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

    private void openAdminCRUDFrame() {
        frame.setVisible(false);
        new AdminCRUDFrame();
    }

    private void openObatGUI() {
        frame.setVisible(false);
        new Obat();
    }

    private void openKasirGUI() {
        frame.setVisible(false);
        new Kasir();
    }

    private void logout() {
        dispose();
        new Login().showGUI();
    }
}
