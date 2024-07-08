package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.custom.LawCaseDAO;
import lk.ijse.sgalayeredarchitecture.db.DbConnection;
import lk.ijse.sgalayeredarchitecture.entity.LawCase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LawCaseDAOImpl implements LawCaseDAO {
    @Override
    public ArrayList<LawCase> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM lawCase";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<LawCase> lawCaseList = new ArrayList<>();

        while (resultSet.next()) {
            String lawyerId = resultSet.getString(1);
            String caseId = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));

            LawCase lawCase = new LawCase(lawyerId, caseId, date);
            lawCaseList.add(lawCase);
        }
        return lawCaseList;
    }

    @Override
    public boolean save(LawCase dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(LawCase dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
