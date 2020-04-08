package Database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

public class DbUtil {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties pro = new Properties();
        ClassLoader classLoader = DbUtil.class.getClassLoader();
        java.net.URL url = classLoader.getResource("jdbc.properties");
        String path = Objects.requireNonNull(url).getPath();
        try {
            pro.load(new FileReader(path));
            URL = pro.getProperty("URL");
            USER = pro.getProperty("USER");
            PASSWORD = pro.getProperty("PASSWORD");
            Class.forName(pro.getProperty("DRIVER"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectDB() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
