package edu.zju.cst.AnomalyDetection.utils;

public enum DatabaseDriver {

    Mysql("com.mysql.jdbc.Driver","mysql");

    private String driver;
    private String type;

    private DatabaseDriver(String driver, String type){

        this.driver = driver;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
