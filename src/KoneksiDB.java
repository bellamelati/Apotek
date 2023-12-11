import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    private static final String URL = "jdbc:mysql://localhost/aplikasiapotek";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getKoneksi() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}