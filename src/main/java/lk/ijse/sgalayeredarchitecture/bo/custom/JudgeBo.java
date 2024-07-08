package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.JudgeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface JudgeBo extends SuperBO {
    ArrayList<JudgeDTO> getAllJudges() throws SQLException, ClassNotFoundException;

    boolean saveJudge(JudgeDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateJudge(JudgeDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteJudge(String id) throws SQLException, ClassNotFoundException;

}
