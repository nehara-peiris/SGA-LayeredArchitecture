package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.PaymentDAO;
import lk.ijse.sgalayeredarchitecture.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");

        while (rst.next()) {
            Payment payment = new Payment(rst.getString("payId"), rst.getString("lawyerId"), rst.getDate("date"), rst.getDouble("amount"));
            allPayments.add(payment);
        }
        return allPayments;
    }

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?,?,?,?)", dto.getPaymentId(), dto.getLawyerId(), dto.getDate(), dto.getAmount());
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET lawyerId = ?, date = ?, amount = ? WHERE paymentId = ?", dto.getPaymentId(), dto.getLawyerId(), dto.getDate(), dto.getAmount());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE paymentId = ?", id);
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT payId FROM payment ORDER BY payId DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("payId");
        }
        return null;
    }
}
