package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.JudgeBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.JudgeDAO;
import lk.ijse.sgalayeredarchitecture.dto.JudgeDTO;
import lk.ijse.sgalayeredarchitecture.entity.Judge;

import java.sql.SQLException;
import java.util.ArrayList;

public class JudgeBoImpl implements JudgeBo {

    JudgeDAO judgeDAO = (JudgeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JUDGE);

    @Override
    public ArrayList<JudgeDTO> getAllJudges() throws SQLException, ClassNotFoundException {
        ArrayList<Judge>judges = judgeDAO.getAll();
        ArrayList<JudgeDTO>judgeDTOS = new ArrayList<>();

        for (Judge judge : judges){
            JudgeDTO judgeDTO = new JudgeDTO(judge.getJudgeId(), judge.getName(), judge.getCourtId(), judge.getYrsOfExp());
            judgeDTOS.add(judgeDTO);
        }
        return judgeDTOS;
    }

    @Override
    public boolean saveJudge(JudgeDTO dto) throws SQLException, ClassNotFoundException {
        return judgeDAO.save(new Judge(dto.getJudgeId(), dto.getName(), dto.getCourtId(), dto.getYrsOfExp()));
    }

    @Override
    public boolean updateJudge(JudgeDTO dto) throws SQLException, ClassNotFoundException {
        return judgeDAO.update(new Judge(dto.getJudgeId(), dto.getName(), dto.getCourtId(), dto.getYrsOfExp()));
    }

    @Override
    public boolean deleteJudge(String id) throws SQLException, ClassNotFoundException {
        return judgeDAO.delete(id);
    }
}
