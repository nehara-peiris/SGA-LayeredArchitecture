package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.DeedDAO;
import lk.ijse.sgalayeredarchitecture.db.DbConnection;
import lk.ijse.sgalayeredarchitecture.entity.Deed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeedDAOImpl implements DeedDAO {
    @Override
    public ArrayList<Deed> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Deed> allDeeds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM deed");

        while (rst.next()) {
            Deed deed = new Deed(rst.getString("deedId"), rst.getString("description"), rst.getString("type"), rst.getDate("date"), rst.getString("lawyerId"), rst.getString("clientId"));
            allDeeds.add(deed);
        }
        return allDeeds;
    }

    @Override
    public boolean save(Deed dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO deed VALUES(?,?,?,?,?,?)", dto.getDeedId(), dto.getDescription(), dto.getType(), dto.getDate(), dto.getLawyerId(), dto.getClientId());
    }

    @Override
    public boolean update(Deed dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE deed SET description = ?, type = ?, date = ?, lawyerId = ?, clientId = ? WHERE deedId = ?", dto.getDescription(), dto.getType(), dto.getDate(), dto.getLawyerId(), dto.getClientId(), dto.getDeedId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM deed WHERE deedId = ?", id);
    }

    @Override
    public Map<String, Integer> getAllToChart() throws SQLException, ClassNotFoundException {
        String sql = "SELECT type, COUNT(*) AS count FROM deed WHERE type IS NOT NULL GROUP BY type";

        Map<String, Integer> deedTypeCounts = new HashMap<>();
        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery(sql)) {
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int count = resultSet.getInt("count");
                deedTypeCounts.put(type, count);
            }
        }
        return deedTypeCounts;
    }
}
