package com.salmon.test.framework.helpers;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class DatabaseHelper {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);
//    private static final String jdbcUrl;
    private static final String jdbcDriver;
    private static final String jdbcUser;
    private static final String jdbcPwd;
    
    
    private static final String b2BJdbcUrl;
    private static final String b2CJdbcUrl;
    
    private static Connection conn = null;

    static {
//        jdbcUrl = LoadProperties.getProps().getProperty("jdbcUrl");
        jdbcDriver = LoadProperties.getProps().getProperty("jdbcDriver");
        jdbcUser = LoadProperties.getProps().getProperty("jdbcUser");
        jdbcPwd = LoadProperties.getProps().getProperty("jdbcPwd");
        b2CJdbcUrl=LoadProperties.getProps().getProperty("b2c.jdbcUrl");
        b2BJdbcUrl = LoadProperties.getProps().getProperty("b2b.jdbcUrl");
    }

    /**
     * Executes the sql Query and returns the results in list format
     *
     * @param sqlQuery Specify sql query in String format
     */
    public static List executeQuery(String sqlQuery, String testEnv) throws SQLException {
        conn = setUpConnection(testEnv);
        QueryRunner run = new QueryRunner();
        return run.query(conn, sqlQuery, new MapListHandler());
    }

//    private static Connection setUpConnection() {
//        try {
//            DbUtils.loadDriver(jdbcDriver);
//            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
//        } catch (SQLException se) {
//            LOG.info(se.getMessage());
//        } finally {
//            DbUtils.closeQuietly(conn);
//        }
//        return conn;
//    }
    
    private static Connection setUpConnection(String testEnv) {
        try {
            DbUtils.loadDriver(jdbcDriver);
            if(testEnv.equalsIgnoreCase("b2C")){
              return DriverManager.getConnection(b2CJdbcUrl, jdbcUser, jdbcPwd);	
            }
            else if(testEnv.equalsIgnoreCase("b2B")){
            	return DriverManager.getConnection(b2BJdbcUrl, jdbcUser, jdbcPwd);
            }
//            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPwd);
        } catch (SQLException se) {
            LOG.info(se.getMessage());
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return conn;
    }
    

	public String selectRecordsFromTable(String tableName, String column, String email, String testEnv) {
        Connection dbConnection;
        PreparedStatement preparedStatement;
        String emailId = null;
        // query to check the email is stored in database
        String selectSQL = "SELECT  *  FROM " + tableName + " WHERE Email = ?";
        try {
            dbConnection = setUpConnection(testEnv);
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                emailId = rs.getString("EMAIL");
                LOG.info(" \n DataBase Email Id value:::-->" + emailId);
            }
        } catch (SQLException e) {

        }
        return emailId;
    }
    
	public void executeQuery(String tableName, String sqlQuery, String testEnv){
		Connection dbConnection;
        
        try {
            dbConnection = setUpConnection(testEnv);
            dbConnection.prepareStatement(sqlQuery).executeQuery();
        } catch (SQLException se) {
        	LOG.error(se.getMessage());
        }
	}
	
    public void deleteRecordsFromTable(String tableName, String description, String testEnv){
    	Connection dbConnection;
        String deleteSQL = "DELETE FROM " + tableName + " WHERE "+description;
        try {
            dbConnection = setUpConnection(testEnv);
            dbConnection.prepareStatement(deleteSQL);  
            ResultSet rs=dbConnection.prepareStatement(deleteSQL).executeQuery();
        } catch (SQLException se) {
        	LOG.error(se.getMessage());
        }
    }
}