package lk.ijse.sgalayeredarchitecture.bo.custom;

import lk.ijse.sgalayeredarchitecture.bo.SuperBO;
import lk.ijse.sgalayeredarchitecture.dto.ClientDTO;
import lk.ijse.sgalayeredarchitecture.entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientBo extends SuperBO {
    ArrayList<ClientDTO> getAllClients() throws SQLException, ClassNotFoundException;

    boolean saveClient(Client dto) throws SQLException, ClassNotFoundException;

    boolean updateClient(Client dto) throws SQLException, ClassNotFoundException;

    boolean deleteClient(String id) throws SQLException, ClassNotFoundException;

    Client searchClientById(String id) throws SQLException, ClassNotFoundException;

    String getClientName(String cid) throws SQLException, ClassNotFoundException;
}
