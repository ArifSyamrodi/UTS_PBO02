
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Koneksi {
    // Path ke file database SQLite
    private static final String DB_URL = "jdbc:sqlite:AlamatDB.db";

    /**
     * Method untuk membuat koneksi ke SQLite dan membuat database serta tabel jika belum ada.
     *
     * @return Connection object
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // Membuat koneksi ke SQLite
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Koneksi ke SQLite berhasil!");

            // Membuat tabel jika belum ada
            createTable(conn);

        } catch (SQLException e) {
            System.err.println("Koneksi ke SQLite gagal: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Method untuk membuat tabel Alamat jika belum ada.
     *
     * @param conn Connection object
     */
    private static void createTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS Alamat ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "nama TEXT NOT NULL, "
                   + "alamat TEXT NOT NULL, "
                   + "hubungan TEXT NOT NULL, "
                   + "telepon TEXT NOT NULL"
                   + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel 'Alamat' berhasil dibuat atau sudah ada.");
        } catch (SQLException e) {
            System.err.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

    /**
     * Method utama untuk menjalankan program dan membuat database serta tabel.
     */
    public static void main(String[] args) {
        // Membuat koneksi dan tabel
        connect();
    }
}
