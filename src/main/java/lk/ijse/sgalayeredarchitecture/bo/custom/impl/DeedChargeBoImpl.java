package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.DeedChargeBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.DeedChargeDAO;
import lk.ijse.sgalayeredarchitecture.dto.DeedChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.DeedCharge;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeedChargeBoImpl implements DeedChargeBo {

    DeedChargeDAO deedChargeDAO = (DeedChargeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEEDCHARGE);


    @Override
    public ArrayList<DeedChargeDTO> getAllDeedCharges() throws SQLException, ClassNotFoundException {
        ArrayList<DeedCharge> deedCharges = deedChargeDAO.getAll();
        ArrayList<DeedChargeDTO> deedChargeDTOS = new ArrayList<>();

        for (DeedCharge dc : deedCharges) {
            DeedChargeDTO deedChargeDTO = new DeedChargeDTO(dc.getDCPayId(), dc.getDeedId(), dc.getLawyerId(), dc.getChargeId(), dc.getDate(), dc.getAmount(), dc.getClientId());
            deedChargeDTOS.add(deedChargeDTO);
        }
        return deedChargeDTOS;
    }

    @Override
    public boolean saveDeedCharge(DeedChargeDTO dto) throws SQLException, ClassNotFoundException {
        return deedChargeDAO.save(new DeedCharge(dto.getDCPayId(), dto.getDeedId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId()));
    }

    @Override
    public boolean updateDeedCharge(DeedChargeDTO dto) throws SQLException, ClassNotFoundException {
        return deedChargeDAO.update(new DeedCharge(dto.getDCPayId(), dto.getDeedId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId()));
    }

    @Override
    public boolean deleteDeedCharge(String id) throws SQLException, ClassNotFoundException {
        return deedChargeDAO.delete(id);
    }
}


