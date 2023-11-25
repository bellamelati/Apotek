import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminManagement {
    private DefaultListModel<String> adminListModel;

    public AdminManagement() {
        adminListModel = new DefaultListModel<>();
        adminListModel.addElement("Admin1");
        adminListModel.addElement("Admin2");
        adminListModel.addElement("Admin3");
    }

    public void showGUI() {
        JFrame adminFrame = new JFrame("Admin Management");

        JList<String> adminJList = new JList<>(adminListModel);
        JScrollPane scrollPane = new JScrollPane(adminJList);

        adminFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        adminFrame.setSize(400, 300);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }
}
