package dao;

import model.Id;
import model.Product;

import java.math.BigDecimal;
import java.util.List;

/*
 *@author Yakovlev Alexandr
 */
public interface ProductDAO {
    Product getById(Long id) throws UnknownProductException;
    List<Product> getAll();
    Product getByProductId(Id<Long> productId) throws UnknownProductException;
    BigDecimal getProductCostByTitle(String title) throws UnknownProductException;
    void changeProductCost (String productTitle, BigDecimal newCost)throws UnknownProductException;
    List<Product> getProducts(String productAlias, String whereClause);
    void executeQuery(String query);
}
