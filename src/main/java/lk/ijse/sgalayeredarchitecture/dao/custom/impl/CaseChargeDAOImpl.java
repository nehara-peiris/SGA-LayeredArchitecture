package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.CaseChargeDAO;
import lk.ijse.sgalayeredarchitecture.entity.CaseCharge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaseChargeDAOImpl implements CaseChargeDAO {
    @Override
    public ArrayList<CaseCharge> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CaseCharge> allCaseCharges = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM caseCharge");

        while (rst.next()) {
            CaseCharge caseCharge = new CaseCharge(rst.getString("CCPayId"), rst.getString("caseId"), rst.getString("lawyerId"), rst.getString("chargeId"), rst.getDate("date"), rst.getDouble("amount"), rst.getString("clientId"));
            allCaseCharges.add(caseCharge);
        }
        return allCaseCharges;
    }

    @Override
    public boolean save(CaseCharge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO caseCharge VALUES(?,?,?,?,?,?,?)", dto.getCCPayId(), dto.getCaseId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId());
    }

    @Override
    public boolean update(CaseCharge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE caseCharge SET caseId = ?, lawyerId = ?, chargeId = ?, date = ?, amount = ?, clientId = ? WHERE CCPayId = ?", dto.getCaseId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId(), dto.getCCPayId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM caseCharge WHERE CCPayId = ?", id);
    }
}
