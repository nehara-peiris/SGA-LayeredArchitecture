package lk.ijse.sgalayeredarchitecture.dao.custom;

import lk.ijse.sgalayeredarchitecture.dao.CrudDao;
import lk.ijse.sgalayeredarchitecture.dto.ChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.Charge;

import java.sql.SQLException;

public interface ChargeDAO extends CrudDao<Charge> {
    ChargeDTO getData(String chargeId) throws SQLException, ClassNotFoundException;

}
