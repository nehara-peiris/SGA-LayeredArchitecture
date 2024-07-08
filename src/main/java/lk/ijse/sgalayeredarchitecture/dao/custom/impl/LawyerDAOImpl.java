package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.LawyerDAO;
import lk.ijse.sgalayeredarchitecture.entity.Lawyer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LawyerDAOImpl implements LawyerDAO {

    @Override
    public ArrayList<Lawyer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Lawyer> allLawyers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM lawyer");

        while (rst.next()) {
            Lawyer lawyer = new Lawyer(rst.getString("lawyerId"), rst.getString("name"), rst.getInt("yrsOfExp"),  rst.getString("address"), rst.getString("email"), rst.getInt("contact"));
            allLawyers.add(lawyer);
        }
        return allLawyers;
    }

    @Override
    public boolean save(Lawyer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO lawyer VALUES(?,?,?,?,?,?)", dto.getLawyerId(), dto.getName(), dto.getYrsOfExp(), dto.getAddress(), dto.getEmail(), dto.getContact());
    }

    @Override
    public boolean update(Lawyer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE lawyer SET name = ?, yrsOfExp = ?, address = ?, email = ?, contact = ? WHERE lawyerId = ?", dto.getName(), dto.getYrsOfExp(), dto.getAddress(), dto.getEmail(), dto.getContact(), dto.getLawyerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM lawyer WHERE lawyerId = ?",id);
    }
}
