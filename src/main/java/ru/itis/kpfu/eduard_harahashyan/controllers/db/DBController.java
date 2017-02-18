package ru.itis.kpfu.eduard_harahashyan.controllers.db;

import java.sql.*;

public class DBController {

    private Connection con = null;
    private final String USER = "postgres";
    private final String PASS = "admin";
    private final String URL = "jdbc:postgresql://localhost:5432/postgres";

    public DBController(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public void closeCon(){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet executeQuery(String s){
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery(s);
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

}
