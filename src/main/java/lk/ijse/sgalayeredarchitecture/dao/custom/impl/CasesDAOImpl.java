package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.CasesDAO;
import lk.ijse.sgalayeredarchitecture.db.DbConnection;
import lk.ijse.sgalayeredarchitecture.entity.Cases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CasesDAOImpl implements CasesDAO {
    @Override
    public ArrayList<Cases> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Cases> allCases = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM cases");

        while (rst.next()) {
            Cases cases = new Cases(rst.getString("caseId"), rst.getString("description"), rst.getString("type"), rst.getDate("date"), rst.getString("clientId"));
            allCases.add(cases);
        }
        return allCases;

    }

    @Override
    public boolean save(Cases dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO cases VALUES(?,?,?,?,?)", dto.getCaseId(),dto.getDescription(),dto.getType(), dto.getDate(), dto.getClientId());
    }

    @Override
    public boolean update(Cases dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE cases SET description = ?, type = ?, date = ?, clientId = ? WHERE caseId = ?",dto.getDescription(),dto.getType(), dto.getDate(), dto.getClientId(),dto.getCaseId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM cases WHERE caseId = ?", id);
    }

    @Override
    public Map<String, Integer> getAllToChart() throws SQLException, ClassNotFoundException {
        String sql = "SELECT type, COUNT(*) AS count FROM cases WHERE type IS NOT NULL GROUP BY type";

        Map<String, Integer> caseTypeCounts = new HashMap<>();
        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery(sql)) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int count = resultSet.getInt("count");
                caseTypeCounts.put(type, count);
            }
        }
        return caseTypeCounts;
    }

}
