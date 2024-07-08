package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.SalaryDAO;
import lk.ijse.sgalayeredarchitecture.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> allSalary = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary");

        while (rst.next()) {
            Salary salary = new Salary(rst.getString("salaryId"), rst.getDouble("totalSalary"));
            allSalary.add(salary);
        }
        return allSalary;
    }

    @Override
    public boolean save(Salary dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Salary dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
