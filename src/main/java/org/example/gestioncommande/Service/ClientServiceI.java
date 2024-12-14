package org.example.gestioncommande.Service;

import org.example.gestioncommande.Model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientServiceI {
    void addClient(Client c) throws SQLException;
    List<Client> getAllClients() throws SQLException;
}
