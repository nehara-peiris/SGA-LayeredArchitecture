package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.PaymentDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBo extends SuperBO {
    ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;

    boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    String getCurrentPaymentId() throws SQLException, ClassNotFoundException;
}