import javax.swing.*;
public class Login {
    public void showGUI(){
        // Membuat frame
        JFrame frame = new JFrame("Apotek");

        // Mengatur properti frame
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menempatkan frame di tengah layar
        frame.setLocationRelativeTo(null);

        // Menampilkan frame
        frame.setVisible(true);
    }

    
}
