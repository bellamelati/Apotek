import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class KoneksiDB {
    private static Connection koneksi;

    public static Connection getKoneksi() {
        try {
            String url = "jdbc:mysql://localhost:3306/aplikasiapotek";
            String user = "root";
            String password = "";

            if (koneksi == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil !!!", "Report Koneksi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return koneksi;
    }
}
