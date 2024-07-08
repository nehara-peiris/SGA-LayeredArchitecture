package lk.ijse.sgalayeredarchitecture.dao.custom;

import lk.ijse.sgalayeredarchitecture.dao.CrudDao;
import lk.ijse.sgalayeredarchitecture.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDao<Payment> {
    String getCurrentId() throws SQLException, ClassNotFoundException;

}
