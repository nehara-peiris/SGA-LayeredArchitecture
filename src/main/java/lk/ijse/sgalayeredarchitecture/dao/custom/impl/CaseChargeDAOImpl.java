package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.custom.CaseChargeDAO;
import lk.ijse.sgalayeredarchitecture.entity.CaseCharge;

import java.sql.SQLException;
import java.util.ArrayList;

public class CaseChargeDAOImpl implements CaseChargeDAO {
    @Override
    public ArrayList<CaseCharge> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CaseCharge dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CaseCharge dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
