package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.SummonDAO;
import lk.ijse.sgalayeredarchitecture.entity.Summon;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummonDAOImpl implements SummonDAO {

    @Override
    public ArrayList<Summon> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Summon> allSummons = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM summon");

        while (rst.next()) {
            Summon summon = new Summon(rst.getString("summonID"), rst.getString("description"), rst.getString("defendant"), rst.getString("lawyerId"), rst.getDate("date"));
            allSummons.add(summon);
        }
        return allSummons;
    }

    @Override
    public boolean save(Summon dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO summon VALUES(?,?,?,?,?)", dto.getSummonId(), dto.getDescription(), dto.getDefendant(), dto.getLawyerId(),dto.getDate());

    }

    @Override
    public boolean update(Summon dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE summon SET description = ?, defendant = ?, lawyerId = ?, date = ? WHERE summonId = ?", dto.getDescription(), dto.getDefendant(), dto.getLawyerId(), dto.getDate(), dto.getSummonId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM summon WHERE summonId = ?", id);
    }
}
