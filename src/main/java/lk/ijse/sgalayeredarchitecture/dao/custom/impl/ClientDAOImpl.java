package lk.ijse.sgalayeredarchitecture.dao.custom.impl;

import lk.ijse.sgalayeredarchitecture.dao.SQLUtil;
import lk.ijse.sgalayeredarchitecture.dao.custom.ClientDAO;
import lk.ijse.sgalayeredarchitecture.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public ArrayList<Client> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Client> allClients = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM client");

        while (rst.next()) {
            Client client = new Client(rst.getString("clientId"), rst.getString("name"), rst.getString("address"), rst.getString("email"), rst.getInt("contact"), rst.getString("lawyerId"));
            allClients.add(client);
        }
        return allClients;
    }

    @Override
    public boolean save(Client dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO client VALUES(?,?,?,?,?,?)", dto.getClientId(), dto.getName(), dto.getAddress(), dto.getEmail(), dto.getContact(), dto.getLawyerId());
    }

    @Override
    public boolean update(Client dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE client SET name = ?, address = ?, email = ?, contact = ?, lawyerId = ? WHERE clientId = ?", dto.getName(), dto.getAddress(),dto.getEmail(), dto.getContact(), dto.getLawyerId(), dto.getClientId());
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM client WHERE clientId = ?",id);
    }

    @Override
    public Client searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM client WHERE clientId = ?", id);
        rst.next();
        return new Client(id + " ", rst.getString("name"), rst.getString("address"), rst.getString("email"), rst.getInt("contact"), rst.getString("lawyerId"));

    }

    @Override
    public String getName(String cid) throws SQLException, ClassNotFoundException {
        //return SQLUtil.execute("SELECT name FROM client WHERE clientId = ?", cid);
        ResultSet rst = SQLUtil.execute("SELECT name FROM client WHERE clientId = ?", cid);
        if (rst.next()) {
            return rst.getString("name");
        } else {
            return null;
        }
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        List<String> allIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT clientId FROM client");

        while (rst.next()) {
            allIds.add(rst.getString("clientId"));
        }
        return allIds;
    }
}
