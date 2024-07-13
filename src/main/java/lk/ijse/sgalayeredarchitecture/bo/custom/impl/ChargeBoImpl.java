package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.ChargeBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.ChargeDAO;
import lk.ijse.sgalayeredarchitecture.dto.ChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.Charge;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChargeBoImpl implements ChargeBo {

    ChargeDAO chargeDAO = (ChargeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CHARGE);

    @Override
    public ArrayList<ChargeDTO> getAllCharges() throws SQLException, ClassNotFoundException {
        ArrayList<Charge>charges = chargeDAO.getAll();
        ArrayList<ChargeDTO>chargeDTOS = new ArrayList<>();

        for (Charge charge : charges){
            ChargeDTO chargeDTO = new ChargeDTO(charge.getChargeId(), charge.getDescription(), charge.getAmount());

            chargeDTOS.add(chargeDTO);
        }
        return chargeDTOS;
    }

    @Override
    public boolean saveCharge(ChargeDTO dto) throws SQLException, ClassNotFoundException {
        return chargeDAO.save(new Charge(dto.getChargeId(),dto.getDescription(),dto.getAmount()));
    }

    @Override
    public boolean updateCharge(ChargeDTO dto) throws SQLException, ClassNotFoundException {
        return chargeDAO.update(new Charge(dto.getChargeId(),dto.getDescription(),dto.getAmount()));
    }

    @Override
    public boolean deleteCharge(String id) throws SQLException, ClassNotFoundException {
        return chargeDAO.delete(id);
    }

    @Override
    public ChargeDTO getChargesData(String chargeId) throws SQLException, ClassNotFoundException {
        return chargeDAO.getData(chargeId);
    }

    @Override
    public ChargeDTO searchByDesc(String desc) throws SQLException, ClassNotFoundException {
        return chargeDAO.searchByDesc(desc);
    }
}
