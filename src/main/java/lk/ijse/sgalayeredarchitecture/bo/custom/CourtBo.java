package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.CourtDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourtBo extends SuperBO {

    ArrayList<CourtDTO> getAllCourts() throws SQLException, ClassNotFoundException;

    boolean saveCourt(CourtDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCourt(CourtDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCourt(String id) throws SQLException, ClassNotFoundException;
}
