package me.koba1.tfbbuildffa8.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLFunctions {

    private SQL sql = new SQL();

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS servers " +
                    "(NAME VARCHAR(100), STATUS VARCHAR(100), PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createServer(String server) {
        try {
            if(!exists(server)) {
                PreparedStatement ps = sql.getConnection().prepareStatement("INSERT IGNORE servers (NAME,STATUS) VALUES (?,?)");
                ps.setString(1, server);
                ps.setString(2, "online");
                ps.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String server) {
        try {
            PreparedStatement ps = sql.getConnection().prepareStatement("SELECT * FROM servers WHERE NAME=?");
            ps.setString(1, server);

            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setStatus(String server, String status) {
        try {
            PreparedStatement ps = sql.getConnection().prepareStatement("UPDATE servers SET STATUS=? WHERE NAME=?");
            ps.setString(2, server);
            ps.setString(1, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getStatus(String server) {
        try {
            PreparedStatement ps = sql.getConnection().prepareStatement("SELECT * FROM servers WHERE NAME=?");
            ps.setString(1, server);
            ResultSet results = ps.executeQuery();
            if(results.next()) {
                return results.getString("STATUS");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
