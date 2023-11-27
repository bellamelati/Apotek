import java.util.Objects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JFrame frame;
    private JPanel featuresPanel; // Declare featuresPanel as a class member

    public void showGUI() {
        initializeFrame();
        addTitle();
        addFeatureButtons();
        configureFrame();
    }

    private void initializeFrame() {
        frame = new JFrame("Home - Apotek");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void addTitle() {
        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/judul.png")));
        JLabel label = new JLabel(image);
        frame.getContentPane().add(label, BorderLayout.NORTH);
    }

    private void addFeatureButtons() {
        featuresPanel = new JPanel(); // Initialize featuresPanel
        featuresPanel.setLayout(new GridLayout(2, 2));

        addButton("Menu Admin", "/images/menuadmin.png", new MenuAdminListener());
        addButton("Add Obat", "/images/addobat.png", new AddObatListener());
        addButton("Kasir", "/images/kasir.png", new KasirListener());
        addButton("Logout", "/images/logout.png", new LogoutListener());

        frame.getContentPane().add(featuresPanel, BorderLayout.CENTER);
    }

    private void addButton(String label, String imagePath, ActionListener listener) {
        JButton button = createFeatureButton(imagePath);
        button.addActionListener(listener);
        featuresPanel.add(button);
    }

    private JButton createFeatureButton(String imagePath) {
        JButton button = new JButton(new ImageIcon(Objects.requireNonNull(Login.class.getResource(imagePath))));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    private void configureFrame() {
        frame.setVisible(true);
    }

    private abstract class FeatureListener implements ActionListener {
        @Override
        public abstract void actionPerformed(ActionEvent e);
    }

    private class MenuAdminListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAdminManagement();
        }
    }

    private class AddObatListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAddObatForm();
        }
    }

    private class KasirListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openKasirTransaction();
        }
    }

    private class LogoutListener extends FeatureListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logout();
        }
    }

    private void openAdminManagement() {
        // Logika untuk tindakan saat menu admin diklik
        AdminManagement adminManagement = new AdminManagement();
        adminManagement.showGUI();
    }

    private void openAddObatForm() {
        // Logika untuk tindakan saat add obat diklik
        AddObatForm addObatForm = new AddObatForm();
        addObatForm.showGUI();
    }

    private void openKasirTransaction() {
        // Logika untuk tindakan saat kasir diklik
        KasirTransaction kasirTransaction = new KasirTransaction();
        kasirTransaction.showGUI();
    }

    private void logout() {
        // Logika untuk tindakan saat logout diklik
        frame.dispose(); // Menutup frame saat ini
        Login login = new Login();
        login.showGUI();
    }
}
