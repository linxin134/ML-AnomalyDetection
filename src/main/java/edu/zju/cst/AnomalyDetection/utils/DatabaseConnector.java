package edu.zju.cst.AnomalyDetection.utils;

import edu.zju.cst.AnomalyDetection.model.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {


    public static Connection getConnection(Database database){

        Connection conn = null;
        try {
            Class.forName(DatabaseDriver.valueOf(database.getDatabaseType()).getDriver()).newInstance();
            conn = DriverManager.getConnection(database.getUrl(),database.getUsername(),database.getPassword());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return conn;

    }

}
