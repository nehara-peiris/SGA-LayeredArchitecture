package lk.ijse.sgalayeredarchitecture.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDAO{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

}
