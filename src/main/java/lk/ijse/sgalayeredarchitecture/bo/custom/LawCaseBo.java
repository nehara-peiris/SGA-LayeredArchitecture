package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.LawCaseDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LawCaseBo extends SuperBO {
    ArrayList<LawCaseDTO> getAllLawCase() throws SQLException, ClassNotFoundException;

    boolean saveLawCase(LawCaseDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateLawCase(LawCaseDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteLawCase(String id) throws SQLException, ClassNotFoundException;
}
