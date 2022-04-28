package me.koba1.tfbbuildffa8.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {

    private String host = "45.140.165.82";
    private String port = "3306";
    private String database = "s17536_theforbiddenbuilders";
    private String username = "u17536_wJigBaH2oo";
    private String password = "EpO^0D=0^iBxF1XdH+VC5PR0";

    private static Connection connection;

    public boolean isConnected() {
        return (connection == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + database + "?useSSL=false&autoReconnect=true",
                    username, password);
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
