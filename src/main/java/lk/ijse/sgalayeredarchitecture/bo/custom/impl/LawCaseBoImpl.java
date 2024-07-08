package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.LawCaseBo;
import lk.ijse.sgalayeredarchitecture.dto.LawCaseDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LawCaseBoImpl implements LawCaseBo {

    @Override
    public ArrayList<LawCaseDTO> getAllLawCase() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveLawCase(LawCaseDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateLawCase(LawCaseDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteLawCase(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
