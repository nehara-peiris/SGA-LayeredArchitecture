package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.SummonBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.SummonDAO;
import lk.ijse.sgalayeredarchitecture.dto.SummonDTO;
import lk.ijse.sgalayeredarchitecture.entity.Summon;

import java.sql.SQLException;
import java.util.ArrayList;


public class SummonBoImpl implements SummonBo {

    SummonDAO summonDAO = (SummonDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUMMON);

    @Override
    public ArrayList<SummonDTO> getAllSummons() throws SQLException, ClassNotFoundException {
        ArrayList<Summon>summons = summonDAO.getAll();
        ArrayList<SummonDTO>summonDTOS = new ArrayList<>();

        for (Summon summon : summons){
            SummonDTO summonDTO = new SummonDTO(summon.getSummonId(), summon.getDescription(), summon.getDefendant(), summon.getLawyerId(), summon.getDate());

            summonDTOS.add(summonDTO);
        }
        return summonDTOS;
    }

    @Override
    public boolean saveSummon(SummonDTO dto) throws SQLException, ClassNotFoundException {
        return summonDAO.save(new Summon(dto.getSummonId(), dto.getDescription(), dto.getDefendant(), dto.getLawyerId(), dto.getDate()));
    }

    @Override
    public boolean updateSummon(SummonDTO dto) throws SQLException, ClassNotFoundException {
        return summonDAO.update(new Summon(dto.getSummonId(), dto.getDescription(), dto.getDefendant(), dto.getLawyerId(), dto.getDate()));
    }

    @Override
    public boolean deleteSummon(String id) throws SQLException, ClassNotFoundException {
        return summonDAO.delete(id);
    }
}
