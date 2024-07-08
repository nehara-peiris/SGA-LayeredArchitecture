package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.DeedDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface DeedBo extends SuperBO {

    ArrayList<DeedDTO> getAllDeeds() throws SQLException, ClassNotFoundException;

    boolean saveDeed(DeedDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateDeed(DeedDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteDeed(String id) throws SQLException, ClassNotFoundException;

    Map<String, Integer> getAllDeedToChart() throws SQLException, ClassNotFoundException;

}
