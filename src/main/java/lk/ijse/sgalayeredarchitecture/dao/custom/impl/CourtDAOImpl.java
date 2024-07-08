package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.CourtDAO;
import lk.ijse.sgalayeredarchitecture.entity.Court;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourtDAOImpl implements CourtDAO {

    @Override
    public ArrayList<Court> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Court> allCourts = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM court");

        while (rst.next()) {
            Court court = new Court(rst.getString("courtId"), rst.getString("location"));
            allCourts.add(court);
        }
        return allCourts;
    }

    @Override
    public boolean save(Court dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO court VALUES(?,?)", dto.getCourtId(), dto.getLocation());
    }

    @Override
    public boolean update(Court dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE court SET location = ? WHERE courtId = ?",dto.getLocation(), dto.getCourtId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM court WHERE courtId = ?", id);
    }
}
