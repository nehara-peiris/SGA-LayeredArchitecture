package lk.ijse.sgalayeredarchitecture.dao.custom;

import lk.ijse.sgalayeredarchitecture.dao.CrudDao;
import lk.ijse.sgalayeredarchitecture.entity.Deed;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface DeedDAO extends CrudDao<Deed> {

    public Map<String, Integer> getAllToChart() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException;
}
