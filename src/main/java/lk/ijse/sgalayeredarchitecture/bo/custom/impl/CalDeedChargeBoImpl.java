package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.BOFactory;
import lk.ijse.sgalayeredarchitecture.bo.custom.CalChargesBo;
import lk.ijse.sgalayeredarchitecture.bo.custom.CaseChargeBO;
import lk.ijse.sgalayeredarchitecture.bo.custom.DeedChargeBo;
import lk.ijse.sgalayeredarchitecture.bo.custom.PaymentBo;
import lk.ijse.sgalayeredarchitecture.dao.custom.DeedChargeDAO;
import lk.ijse.sgalayeredarchitecture.db.DbConnection;
import lk.ijse.sgalayeredarchitecture.dto.DeedChargeDTO;
import lk.ijse.sgalayeredarchitecture.dto.PaymentDTO;
import lk.ijse.sgalayeredarchitecture.entity.CalCharges;
import lk.ijse.sgalayeredarchitecture.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalDeedChargeBoImpl implements CalChargesBo<DeedChargeDAO> {


    PaymentBo paymentBo = (PaymentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    DeedChargeBo deedChargeBo = (DeedChargeBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DEEDCHARGE);


    @Override
    public ArrayList<CalCharges<DeedChargeDAO>> getAllCharges() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveCharges(CalCharges<DeedChargeDAO> dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateCharges(CalCharges<DeedChargeDAO> dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteCharges(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean savePayment(Payment payment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveCharge(CalCharges<DeedChargeDAO> calCharges) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean calCharge(CalCharges<DeedChargeDAO> cc) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPaymentSaved = paymentBo.savePayment(new PaymentDTO(cc.getPayment().getPaymentId(), cc.getPayment().getLawyerId(), cc.getPayment().getDate(), cc.getPayment().getAmount()));
            if (isPaymentSaved) {
                for (int i = 0; i < cc.getChargeList().size(); i++) {
                    boolean save = deedChargeBo.saveDeedCharge((DeedChargeDTO) cc.getChargeList().get(i));
                    if (!save) return false;
                }
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
