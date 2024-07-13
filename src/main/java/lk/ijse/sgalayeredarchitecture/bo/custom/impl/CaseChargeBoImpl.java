package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.CaseChargeBO;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.CaseChargeDAO;
import lk.ijse.sgalayeredarchitecture.dto.CaseChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.CaseCharge;

import java.sql.SQLException;
import java.util.ArrayList;

public class CaseChargeBoImpl implements CaseChargeBO {

    CaseChargeDAO caseChargeDAO = (CaseChargeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CASECHARGE);

    @Override
    public ArrayList<CaseChargeDTO> getAllCaseCharge() throws SQLException, ClassNotFoundException {
        ArrayList<CaseCharge>caseCharges = caseChargeDAO.getAll();
        ArrayList<CaseChargeDTO>caseChargeDTOS = new ArrayList<>();

        for (CaseCharge cc : caseCharges){
            CaseChargeDTO caseChargeDTO = new CaseChargeDTO(cc.getCCPayId(), cc.getCaseId(), cc.getLawyerId(), cc.getChargeId(), cc.getDate(), cc.getAmount(), cc.getClientId());
            caseChargeDTOS.add(caseChargeDTO);
        }
        return caseChargeDTOS;
    }

    @Override
    public boolean saveCaseCharge(CaseChargeDTO dto) throws SQLException, ClassNotFoundException {
        return caseChargeDAO.save(new CaseCharge(dto.getCCPayId(), dto.getCaseId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId()));
    }

    @Override
    public boolean updateCaseCharge(CaseChargeDTO dto) throws SQLException, ClassNotFoundException {
        return caseChargeDAO.update(new CaseCharge(dto.getCCPayId(), dto.getCaseId(), dto.getLawyerId(), dto.getChargeId(), dto.getDate(), dto.getAmount(), dto.getClientId()));
    }

    @Override
    public boolean deleteCaseCharge(String id) throws SQLException, ClassNotFoundException {
        return caseChargeDAO.delete(id);
    }
}
