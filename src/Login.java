import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Login {
    public void showGUI(){
        // Membuat frame
        JFrame frame = new JFrame("Apotek");

        // Menambahkan judul
        ImageIcon image = new ImageIcon(Objects.requireNonNull(Login.class.getResource("/images/judul.png")));
        JLabel label = new JLabel(image);

        // Menambahkan elemen judul ke dalam frame
        frame.getContentPane().add(label, BorderLayout.NORTH);

        // Mengatur properti frame
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menempatkan frame di tengah layar
        frame.setLocationRelativeTo(null);

        // Menampilkan frame
        frame.setVisible(true);
    }


}
