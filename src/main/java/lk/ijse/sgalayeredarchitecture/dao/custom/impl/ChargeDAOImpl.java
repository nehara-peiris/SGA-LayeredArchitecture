package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.ChargeDAO;
import lk.ijse.sgalayeredarchitecture.dto.ChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.Charge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChargeDAOImpl implements ChargeDAO {
    @Override
    public ArrayList<Charge> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Charge> allCharges = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM charge");

        while (rst.next()) {
            Charge charge = new Charge(rst.getString("chargeId"), rst.getString("description"), rst.getDouble("amount"));
            allCharges.add(charge);
        }
        return allCharges;
    }

    @Override
    public boolean save(Charge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO charge VALUES(?,?,?,?,?)", dto.getChargeId(), dto.getDescription(), dto.getAmount());
    }

    @Override
    public boolean update(Charge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE charge SET description = ?, amount = ? WHERE chargeId = ?", dto.getDescription(), dto.getAmount(), dto.getChargeId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM charge WHERE ChargeId = ?", id);
    }

    @Override
    public ChargeDTO getData(String chargeId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT description, amount FROM charge WHERE chargeId = ?", chargeId);
    }
}
