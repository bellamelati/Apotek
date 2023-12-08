import javax.swing.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;
public class KoneksiDB {
    private static Connection koneksi;

    public static Connection getKoneksi() {
        try {
            String url = "jdbc:mysql://localhost:3306/aplikasiapotek";
            String user = "root";
            String password = "";

            if (koneksi == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                DriverManager.registerDriver(new Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil !!!", "Report Koneksi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return koneksi;
    }

    private static class Driver implements java.sql.Driver {
        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return null;
        }

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return false;
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return new DriverPropertyInfo[0];
        }

        @Override
        public int getMajorVersion() {
            return 0;
        }

        @Override
        public int getMinorVersion() {
            return 0;
        }

        @Override
        public boolean jdbcCompliant() {
            return false;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }
}