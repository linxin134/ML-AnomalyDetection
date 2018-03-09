package edu.zju.cst.AnomalyDetection.service.impl;

import edu.zju.cst.AnomalyDetection.model.Database;
import edu.zju.cst.AnomalyDetection.dao.DatabaseDao;
import edu.zju.cst.AnomalyDetection.dao.impl.DatabaseDaoImpl;
import edu.zju.cst.AnomalyDetection.service.DatabaseService;

public class DatabaseServiceImpl implements DatabaseService{

    private DatabaseDao databaseDao = new DatabaseDaoImpl();

    @Override
    public void GetDataFromDatabase(Database database) {

        databaseDao.GetDataFromDatabase(database);

    }
}
