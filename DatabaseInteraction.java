package service;

import dao.ProductDAO;

/*
 *@author Yakovlev Alexandr
 */
public interface DatabaseInteraction extends AutoCloseable {

    void initalize();

    ProductDAO getDAO();
}
