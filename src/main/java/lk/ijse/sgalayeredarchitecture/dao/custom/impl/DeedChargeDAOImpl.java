package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.custom.DeedChargeDAO;
import lk.ijse.sgalayeredarchitecture.entity.DeedCharge;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeedChargeDAOImpl implements DeedChargeDAO {
    @Override
    public ArrayList<DeedCharge> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(DeedCharge dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(DeedCharge dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
