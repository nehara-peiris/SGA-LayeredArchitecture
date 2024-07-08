package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.JudgeDAO;
import lk.ijse.sgalayeredarchitecture.entity.Judge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JudgeDAOImpl implements JudgeDAO {
    @Override
    public ArrayList<Judge> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Judge> allJudges = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM summon");

        while (rst.next()) {
            Judge judge = new Judge(rst.getString("judgeId"), rst.getString("Name"), rst.getString("courtId"), rst.getInt("yrsOfExp"));
            allJudges.add(judge);
        }
        return allJudges;
    }

    @Override
    public boolean save(Judge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO judge VALUES(?,?,?,?)", dto.getJudgeId(), dto.getName(), dto.getCourtId(), dto.getYrsOfExp());
    }

    @Override
    public boolean update(Judge dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE judge SET name = ?, courtId = ?, yrsOfExp = ? WHERE judgeId = ?", dto.getName(), dto.getCourtId(),dto.getYrsOfExp(), dto.getJudgeId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM judge WHERE judgeId = ?", id);
    }
}
