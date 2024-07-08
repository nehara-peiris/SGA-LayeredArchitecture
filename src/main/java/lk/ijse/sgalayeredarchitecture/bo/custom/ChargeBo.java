package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.ChargeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ChargeBo extends SuperBO {
    ArrayList<ChargeDTO> getAllCharges() throws SQLException, ClassNotFoundException;

    boolean saveCharge(ChargeDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateCharge(ChargeDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCharge(String id) throws SQLException, ClassNotFoundException ;

    ChargeDTO getChargesData(String chargeId) throws SQLException, ClassNotFoundException;
}
