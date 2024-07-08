package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.LawyerBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.LawyerDAO;
import lk.ijse.sgalayeredarchitecture.dto.LawyerDTO;
import lk.ijse.sgalayeredarchitecture.entity.Lawyer;

import java.sql.SQLException;
import java.util.ArrayList;

public class LawyerBoImpl implements LawyerBo {

    LawyerDAO lawyerDAO = (LawyerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LAWYER);

    @Override
    public ArrayList<LawyerDTO> getAllLawyers() throws SQLException, ClassNotFoundException {
        ArrayList<Lawyer>lawyers = lawyerDAO.getAll();
        ArrayList<LawyerDTO>lawyerDTOS = new ArrayList<>();

        for (Lawyer lawyer : lawyers){
            LawyerDTO lawyerDTO = new LawyerDTO(lawyer.getLawyerId(), lawyer.getName(), lawyer.getYrsOfExp(), lawyer.getAddress(), lawyer.getEmail(), lawyer.getContact());
            lawyerDTOS.add(lawyerDTO);
        }
        return lawyerDTOS;
    }

    @Override
    public boolean saveLawyer(LawyerDTO dto) throws SQLException, ClassNotFoundException {
        return lawyerDAO.save(new Lawyer(dto.getLawyerId(), dto.getName(), dto.getYrsOfExp(), dto.getAddress(), dto.getEmail(), dto.getContact()));
    }

    @Override
    public boolean updateLawyer(LawyerDTO dto) throws SQLException, ClassNotFoundException {
        return lawyerDAO.update(new Lawyer(dto.getLawyerId(), dto.getName(), dto.getYrsOfExp(), dto.getAddress(), dto.getEmail(), dto.getContact()));
    }

    @Override
    public boolean deleteLawyer(String id) throws SQLException, ClassNotFoundException {
        return lawyerDAO.delete(id);
    }
}
