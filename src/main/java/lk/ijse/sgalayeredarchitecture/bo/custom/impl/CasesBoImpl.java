package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.CasesBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.CasesDAO;
import lk.ijse.sgalayeredarchitecture.dto.CaseDTO;
import lk.ijse.sgalayeredarchitecture.entity.Cases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CasesBoImpl implements CasesBo {

    CasesDAO casesDAO = (CasesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CASES);

    @Override
    public ArrayList<CaseDTO> getAllCases() throws SQLException, ClassNotFoundException {
        ArrayList<Cases> allCases = casesDAO.getAll();
        ArrayList<CaseDTO>caseDTOS = new ArrayList<>();

        for (Cases cases : allCases) {
            CaseDTO caseDTO = new CaseDTO(cases.getCaseId(), cases.getDescription(), cases.getType(), cases.getDate(), cases.getClientId());
            caseDTOS.add(caseDTO);
        }
        return caseDTOS;

    }

    @Override
    public boolean saveCase(CaseDTO dto) throws SQLException, ClassNotFoundException {
        return casesDAO.save(new Cases(dto.getCaseId(), dto.getDescription(), dto.getType(), dto.getDate(), dto.getClientId()));
    }

    @Override
    public boolean updateCase(CaseDTO dto) throws SQLException, ClassNotFoundException {
        return casesDAO.update(new Cases(dto.getCaseId(), dto.getDescription(), dto.getType(), dto.getDate(), dto.getClientId()));
    }

    @Override
    public boolean deleteCase(String id) throws SQLException, ClassNotFoundException {
        return casesDAO.delete(id);
    }

    @Override
    public Map<String, Integer> getAllCasesToChart() throws SQLException, ClassNotFoundException {
        return casesDAO.getAllToChart();
    }
}
