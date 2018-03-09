package edu.zju.cst.AnomalyDetection.dao.impl;

import edu.zju.cst.AnomalyDetection.dao.DatabaseDao;
import edu.zju.cst.AnomalyDetection.model.Database;
import edu.zju.cst.AnomalyDetection.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDaoImpl implements DatabaseDao{

    @Override
    public void GetDataFromDatabase(Database database) {

        try {

            Connection conn = DatabaseConnector.getConnection(database);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(database.getQuery());

        }catch(SQLException e){

            System.out.println(e.getMessage());

        }


    }
}
