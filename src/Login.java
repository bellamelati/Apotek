import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login {
    public void showGUI(){
        // Membuat frame dan memberi warna background
        JFrame frame = new JFrame("Apotek");
        String hexColor = "#0D3749";
        Color backgroundColor = Color.decode(hexColor);
        frame.getContentPane().setBackground(backgroundColor);

        // Menambahkan judul
        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/judul.png")));
        JLabel label = new JLabel(image);

        // Menambahkan form login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(backgroundColor);

        JLabel usernameLabel = new JLabel("Username :");
        JLabel passwordLabel = new JLabel("Password :");
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();

                // Replace the following condition with your actual login logic
                if (isValidLogin(enteredUsername, enteredPassword)) {
                    // If login is successful, open the Home class
                    frame.dispose(); // Close the login frame
                    Home home = new Home();
                    home.showGUI();
                } else {
                    // If login fails, you can show an error message or take other actions
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Mengatur font, size, warna
        Font labelFont = new Font("Rockwell", Font.BOLD, 17);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        loginButton.setFont(labelFont);

        // Panel Username dan Password
        JPanel usernamePanel = new JPanel(new FlowLayout());
        usernamePanel.setBackground(backgroundColor);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        usernamePanel.setBounds(160, 80, 300, 30);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.setBackground(backgroundColor);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        int yPosition = usernamePanel.getY() + usernamePanel.getHeight() + 10; // Menentukan posisi y untuk passwordPanel
        passwordPanel.setBounds(160, yPosition, 300, 30); // Mengatur posisi dan ukuran passwordPanel

        loginButton.setBounds(160, yPosition + passwordPanel.getHeight() + 10, 300, 30); // Mengatur posisi dan ukuran loginButton

        // Menambahkan komponen form ke panel login
        loginPanel.add(usernamePanel);
        loginPanel.add(passwordPanel);
        loginPanel.add(loginButton);

        // Menambahkan elemen-elemen ke dalam frame
        frame.getContentPane().add(label, BorderLayout.NORTH); // judul
        frame.getContentPane().add(loginPanel, BorderLayout.CENTER); // login

        // Mengatur properti frame
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menempatkan frame di tengah layar
        frame.setLocationRelativeTo(null);

        // Menampilkan frame
        frame.setVisible(true);
    }

    private boolean isValidLogin(String username, char[] password) {
        // Add your authentication logic here
        // For example, you might check against a predefined username and password
        String correctUsername = "admin";
        String correctPassword = "admin123";
        return username.equals(correctUsername) && new String(password).equals(correctPassword);
    }

}
