package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.SummonDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SummonBo extends SuperBO {
    ArrayList<SummonDTO> getAllSummons() throws SQLException, ClassNotFoundException;

    boolean saveSummon(SummonDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSummon(SummonDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteSummon(String id) throws SQLException, ClassNotFoundException;
}
