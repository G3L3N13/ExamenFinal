package OGDataAccess.OGDataHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OGDataHelper {
    private static final String ogDBURL = "jdbc:sqlite:dataBase//EcuFauna.sqlite";
    private static Connection ogConnection = null;

    public static synchronized Connection ogConection() {
        try {
            if (ogConnection == null || ogConnection.isClosed()) {
                ogConnection = DriverManager.getConnection(ogDBURL);
            }
            System.out.println("Se conecto a la base de datos");
            return ogConnection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static synchronized void ogDesconection() {
        try {
            if (ogConnection != null && !ogConnection.isClosed()) {
                ogConnection.close();
                System.out.println("Se desconecto a la base de datos");
                ogConnection = null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
