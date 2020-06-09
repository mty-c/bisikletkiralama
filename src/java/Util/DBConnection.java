package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private Connection c;

    public Connection connect() throws SQLException {

        if (this.c == null || this.c.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bisiklet", "root", "1234");
            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }

        return c;
    }

}
