package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.CourtBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.ClientDAO;
import lk.ijse.sgalayeredarchitecture.dao.custom.CourtDAO;
import lk.ijse.sgalayeredarchitecture.dto.CourtDTO;
import lk.ijse.sgalayeredarchitecture.entity.Court;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourtBoImpl implements CourtBo {

    CourtDAO courtDAO = (CourtDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURT);

    @Override
    public ArrayList<CourtDTO> getAllCourts() throws SQLException, ClassNotFoundException {
        ArrayList<Court>courts = courtDAO.getAll();
        ArrayList<CourtDTO>courtDTOS = new ArrayList<>();

        for (Court court : courts){
            CourtDTO courtDTO = new CourtDTO(court.getCourtId(), court.getLocation());

            courtDTOS.add(courtDTO);
        }
        return courtDTOS;
    }

    @Override
    public boolean saveCourt(CourtDTO dto) throws SQLException, ClassNotFoundException {
        return courtDAO.save(new Court(dto.getCourtId(), dto.getLocation()));
    }

    @Override
    public boolean updateCourt(CourtDTO dto) throws SQLException, ClassNotFoundException {
        return courtDAO.update(new Court(dto.getCourtId(), dto.getLocation()));    }

    @Override
    public boolean deleteCourt(String id) throws SQLException, ClassNotFoundException {
        return courtDAO.delete(id);
    }
}
