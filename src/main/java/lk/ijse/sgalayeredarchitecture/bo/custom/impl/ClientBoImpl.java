package lk.ijse.sgalayeredarchitecture.bo.custom.impl;

import lk.ijse.sgalayeredarchitecture.bo.custom.ClientBo;
import lk.ijse.sgalayeredarchitecture.dao.DAOFactory;
import lk.ijse.sgalayeredarchitecture.dao.custom.ClientDAO;
import lk.ijse.sgalayeredarchitecture.dto.ClientDTO;
import lk.ijse.sgalayeredarchitecture.entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientBoImpl implements ClientBo {

    ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CLIENT);

    public ArrayList<ClientDTO> getAllClients() throws SQLException, ClassNotFoundException {
        ArrayList<Client>clients = clientDAO.getAll();
        ArrayList<ClientDTO>clientDTOS = new ArrayList<>();

        for (Client client : clients){
            ClientDTO clientDTO = new ClientDTO(client.getClientId(), client.getName(), client.getAddress(), client.getEmail(), client.getContact(), client.getLawyerId());
            clientDTOS.add(clientDTO);
        }
        return clientDTOS;
    }

    public boolean saveClient(ClientDTO dto) throws SQLException, ClassNotFoundException {
        return clientDAO.save(new Client(dto.getClientId(), dto.getName(), dto.getAddress(), dto.getEmail(), dto.getContact(), dto.getLawyerId()));
    }

    public boolean updateClient(ClientDTO dto) throws SQLException, ClassNotFoundException {
        return clientDAO.update(new Client(dto.getClientId(), dto.getName(), dto.getAddress(), dto.getEmail(), dto.getContact(), dto.getLawyerId()));
    }

    public boolean deleteClient(String id) throws SQLException, ClassNotFoundException {
        return clientDAO.delete(id);
    }

    public Client searchClientById(String id) throws SQLException, ClassNotFoundException {
        return clientDAO.searchById(id);
    }

    public String getClientName(String cid) throws SQLException, ClassNotFoundException {
        return clientDAO.getName(cid);
    }
}
