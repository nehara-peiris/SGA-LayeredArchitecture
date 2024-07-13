package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.entity.CalCharges;
import lk.ijse.sgalayeredarchitecture.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CalChargesBo <T> extends SuperBO {

    ArrayList<CalCharges<T>> getAllCharges() throws SQLException, ClassNotFoundException;

    boolean saveCharges(CalCharges<T> dto) throws SQLException, ClassNotFoundException;

    boolean updateCharges(CalCharges<T> dto) throws SQLException, ClassNotFoundException;

    boolean deleteCharges(String id) throws SQLException, ClassNotFoundException;

    boolean savePayment(Payment payment) throws SQLException, ClassNotFoundException;

    boolean saveCharge(CalCharges<T> calCharges) throws SQLException, ClassNotFoundException;

    boolean calCharge(CalCharges<T> cc) throws SQLException, ClassNotFoundException;

}
