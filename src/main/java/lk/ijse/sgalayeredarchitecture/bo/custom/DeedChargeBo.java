package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.DeedChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.DeedCharge;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeedChargeBo extends SuperBO {
    ArrayList<DeedChargeDTO> getAllDeedCharges() throws SQLException, ClassNotFoundException;

    boolean saveDeedCharge(DeedChargeDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateDeedCharge(DeedChargeDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteDeedCharge(String id) throws SQLException, ClassNotFoundException;
}
