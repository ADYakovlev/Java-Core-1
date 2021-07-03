package service;

import dao.ProductDAO;
import dao.jdbc.SimpleJdbcDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *@author Yakovlev Alexandr
 */
public class JdbcDatabaseInteraction implements DatabaseInteraction {
    public static final int PRODUCT_COUNT =10_000;

    static {
        try{
            Class.forName("org.sqlitc.JDBC");
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Failed to find Jdbc driver", e);
        }
    }

    private String url;
    private Connection connection;
    private ProductDAO dao;

    public JdbcDatabaseInteraction(String url){ this.url = url;}


    @Override
    public void initalize() {
        try{
            this.connection = DriverManager.getConnection(url);
            dao = new SimpleJdbcDAO(connection);
        }catch (SQLException e){
            throw new RuntimeException("Failed to create connection to DB", e);
        }
        prepareDatabase();
    }

    private void prepareDatabase(){
        try{
            Statement statement = connection.createStatement();

            createTableIfRequired(statement);
            clearTable(statement);
            insertProducts(statement);

            statement.close();
        }catch (SQLException e){
            throw new RuntimeException("Failed to prepare database", e);
        }
    }

    private void clearTable(Statement statement) throws SQLException{
        statement.execute("DELETE FROM Product");
    }

    private void createTableIfRequired(Statement statement) throws SQLException{
        statement.execute("CREATE TABLE IF NOT EXIST Product\n" +
        "product_id INTERGER NOT NULL UNIQUE, \n"+
        "title TEXT NOT NULL, \n"+
        "cost INTEGER NOT NULL\n"+
        ");");
    }

    @Override
    public ProductDAO getDAO() {
        checkInitialize();
        return dao;
    }

    @Override
    public void close() throws Exception {
        checkInitialize();
        connection.close();
    }

    private void checkInitialize(){
        if(connection == null){
            throw new IllegalStateException("Service must be required");
        }
    }
}
