package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.LawyerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LawyerBo extends SuperBO {

    ArrayList<LawyerDTO> getAllLawyers() throws SQLException, ClassNotFoundException;

    boolean saveLawyer(LawyerDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateLawyer(LawyerDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteLawyer(String id) throws SQLException, ClassNotFoundException;

}
