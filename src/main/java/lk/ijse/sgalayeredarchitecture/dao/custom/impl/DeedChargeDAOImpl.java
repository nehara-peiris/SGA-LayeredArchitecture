package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.DeedChargeDAO;
import lk.ijse.sgalayeredarchitecture.entity.DeedCharge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeedChargeDAOImpl implements DeedChargeDAO {
    @Override
    public ArrayList<DeedCharge> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<DeedCharge> allDeedCharges = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM deedCharge");

        while (rst.next()) {
            DeedCharge deedCharge = new DeedCharge(rst.getString("DCPayId"), rst.getString("deedId"), rst.getString("lawyerId"), rst.getString("chargeId"), rst.getDate("date"), rst.getDouble("amount"), rst.getString("clientId"));
            allDeedCharges.add(deedCharge);
        }
        return allDeedCharges;
    }

    @Override
    public boolean save(DeedCharge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO deedCharge VALUES(?,?,?,?,?,?,?)", dto.getDCPayId(), dto.getDeedId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId());
    }

    @Override
    public boolean update(DeedCharge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE deedCharge SET deedId = ?, lawyerId = ?, chargeId = ?, date = ?, amount = ?, clientId = ? WHERE DCPayId = ?", dto.getDeedId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId(), dto.getDCPayId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM deedCharge WHERE DCPayId = ?", id);
    }
}
