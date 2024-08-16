

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OGDataBaseConnection {
    private static final String URL = "jdbc:sqlite:C:\\GOM_VSC\\PRG_II\\ExamenFinal\\OGEcuFauna2K24AO\\dataBase\\EcuFauna.sqlite";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
