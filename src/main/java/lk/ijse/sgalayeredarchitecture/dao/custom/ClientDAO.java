package lk.ijse.sgalayeredarchitecture.dao.custom;

import lk.ijse.sgalayeredarchitecture.dao.CrudDao;
import lk.ijse.sgalayeredarchitecture.entity.Client;

import java.sql.SQLException;

public interface ClientDAO extends CrudDao<Client> {
    Client searchById(String id) throws SQLException, ClassNotFoundException;

    String getName(String cid) throws SQLException, ClassNotFoundException;

}