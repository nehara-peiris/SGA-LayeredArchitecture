package lk.ijse.sgalayeredarchitecture.dao;

import lk.ijse.sgalayeredarchitecture.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object ...object) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < object.length; i++) {
            statement.setObject(i+1,object[i]);
        }

        boolean SELECT = sql.trim().startsWith("SELECT");
        if (SELECT) {
            return (T)statement.executeQuery();
        } else {
            return (T) (Boolean) (statement.executeUpdate()>0);
        }
    }

}
