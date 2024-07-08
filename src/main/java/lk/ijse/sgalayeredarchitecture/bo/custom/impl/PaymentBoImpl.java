package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.PaymentBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.PaymentDAO;
import lk.ijse.sgalayeredarchitecture.dto.PaymentDTO;
import lk.ijse.sgalayeredarchitecture.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBo {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment>payments = paymentDAO.getAll();
        ArrayList<PaymentDTO>paymentDTOS = new ArrayList<>();

        for (Payment payment : payments){
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPaymentId(), payment.getLawyerId(), payment.getDate(), payment.getAmount());

            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }

    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPaymentId(), dto.getLawyerId(), dto.getDate(), dto.getAmount()));
    }


    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPaymentId(), dto.getLawyerId(), dto.getDate(), dto.getAmount()));
    }

    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    public String getCurrentPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getCurrentId();
    }
}
