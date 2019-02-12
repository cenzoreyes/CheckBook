package CheckBook.DataAccess.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckBookDB {
    // TODO: Make singleton!
    /*
     * Create a private class variable for the database connection
     */
    private Connection conn;

    public CheckBookDB() {

        //TODO I added '&serverTimezone=UTC' to end of conn url because that's what it needed to work
        String connectionUrl = "jdbc:mysql://software-engineering-checkbook.mysql.database.azure.com:3306/checkbook_dev?useSSL=true&requireSSL=false&serverTimezone=UTC";
        String userName = "checkmate@software-engineering-checkbook";
        String password = "database1!";

        //Commented this out so I can connect to db
//        String connectionUrl = "jdbc:mysql://localhost:3306/checkbook_dev?characterEncoding=latin1";
//        String userName = "root";
//        String password = "reyzo14";


        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.print("unable to connect to database!");
            }
            conn = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("unable to connect to database!");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     * Returns the current database connection, if exists.
     *
     * @return - the current database connection object
     */
    public Connection openConnection() {
        return conn;
    }

    /**
     * Closes the connection to the database.
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
