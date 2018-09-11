package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCHandler {

    private String pass = "";
    private String user = "root";
    private String url = "jdbc:mysql://localhost:3306/wl";
    Connection connection = null;
    Statement statement = null;

    public JDBCHandler() {
    }

    private void createConnection(String dbUrl, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean executeQuery(String query) {
        createConnection(url, user, pass);
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ps.close();
            closeHandler();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCHandler.class.getName()).log(Level.SEVERE, "Error executing query: " + query, ex);
        }
        closeHandler();
        return false;
    }


    public ArrayList<HashMap<String, Object>> queryToHashMapList(String query) {
        createConnection(url, user, pass);
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            final ResultSet r = statement.executeQuery(query);
            rs = r;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs == null) {
            closeHandler();
            return null;
        }

        ArrayList<HashMap<String, Object>> list = null;

        try {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            ArrayList<HashMap<String, Object>> entries = new ArrayList<HashMap<String, Object>>();
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<String, Object>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                entries.add(row);
            }
            list = entries;
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCHandler.class.getName()).log(Level.SEVERE, "Error creating ArrayListHashMap", ex);
        }

        closeHandler();
        return list;
    }

    public void closeHandler() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
