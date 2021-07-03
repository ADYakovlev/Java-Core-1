package dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 *@author Yakovlev Alexandr
 */
public interface ParamsPreparer {
    void prepare(PreparedStatement ps) throws SQLException;
}
