package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.DeedBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.DeedDAO;
import lk.ijse.sgalayeredarchitecture.dto.DeedDTO;
import lk.ijse.sgalayeredarchitecture.entity.Deed;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DeedBoImpl implements DeedBo {

    DeedDAO deedDAO = (DeedDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEED);
    @Override
    public ArrayList<DeedDTO> getAllDeeds() throws SQLException, ClassNotFoundException {
        ArrayList<Deed>deeds = deedDAO.getAll();
        ArrayList<DeedDTO>deedDTOS = new ArrayList<>();

        for (Deed deed : deeds){
            DeedDTO deedDTO = new DeedDTO(deed.getDeedId(), deed.getDescription(), deed.getType(), deed.getDate(), deed.getLawyerId(), deed.getClientId());
            deedDTOS.add(deedDTO);
        }
        return deedDTOS;
    }

    @Override
    public boolean saveDeed(DeedDTO dto) throws SQLException, ClassNotFoundException {
        return deedDAO.save(new Deed(dto.getDeedId(), dto.getDescription(), dto.getType(), dto.getDate(), dto.getLawyerId(), dto.getClientId()));
    }

    @Override
    public boolean updateDeed(DeedDTO dto) throws SQLException, ClassNotFoundException {
        return deedDAO.update(new Deed(dto.getDeedId(), dto.getDescription(), dto.getType(), dto.getDate(), dto.getLawyerId(), dto.getClientId()));
    }

    @Override
    public boolean deleteDeed(String id) throws SQLException, ClassNotFoundException {
        return deedDAO.delete(id);
    }

    @Override
    public Map<String, Integer> getAllDeedToChart() throws SQLException, ClassNotFoundException {
        return deedDAO.getAllToChart();
    }

    @Override
    public ArrayList<String> getAllDeedIds() throws SQLException, ClassNotFoundException {
        return deedDAO.getAllIds();
    }
}
