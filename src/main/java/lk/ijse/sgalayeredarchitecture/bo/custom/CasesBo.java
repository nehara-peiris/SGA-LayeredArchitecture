package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.CaseDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface CasesBo extends SuperBO {
    ArrayList<CaseDTO> getAllCases() throws SQLException, ClassNotFoundException;

    boolean saveCase(CaseDTO dto) throws SQLException, ClassNotFoundException;

   boolean updateCase(CaseDTO dto) throws SQLException, ClassNotFoundException;

   boolean deleteCase(String id) throws SQLException, ClassNotFoundException;

   Map<String, Integer> getAllCasesToChart() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllCaseIds() throws SQLException, ClassNotFoundException;
}
