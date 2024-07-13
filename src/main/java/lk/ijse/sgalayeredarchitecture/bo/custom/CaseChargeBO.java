package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.CaseChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.CaseCharge;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CaseChargeBO extends SuperBO {
    ArrayList<CaseChargeDTO> getAllCaseCharge() throws SQLException, ClassNotFoundException;;

    boolean saveCaseCharge(CaseChargeDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCaseCharge(CaseChargeDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCaseCharge(String id) throws SQLException, ClassNotFoundException;
}
