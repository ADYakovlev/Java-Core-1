package dao.jdbc;

import model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/*
 *@author Yakovlev Alexandr
 */
public class IterableProductConverter {

    private ResultSet resultSet;

    public IterableProductConverter(ResultSet resultSet){
        this.resultSet =resultSet;
    }

    @Override
    public Iterator<Product> iterator(){
        return new ResultSetIterator();
    }

    private final class ResultSetIterator implements Iterator<Product>{

        @Override
        public boolean hasNext(){
            try{
                return resultSet.next();
            }catch (SQLException e){
                throw new RuntimeException("Failed to get next element from resultSet", e);
            }
        }

        @Override
        public Product next(){
            try{
                long id = resultSet.getLong(1);
                long productId = resultSet.getLong(2);
                String title =resultSet.getString(3);
                BigDecimal cost = new BigDecimal(resultSet.getInt(4));
                return new Product(id, productId, title,cost);
            }catch (SQLException e){
                throw new RuntimeException("Failed to get column value from resultSet", e);
            }
        }
    }
}
