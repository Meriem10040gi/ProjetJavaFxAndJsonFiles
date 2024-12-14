package org.example.gestioncommande.Service;

import org.example.gestioncommande.Model.Client;
import org.example.gestioncommande.Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientServiceI {
    DBConnection Connexion = new DBConnection ();
    @Override
    public void addClient(Client c) throws SQLException {

        Connexion.connecte("CommandeClient","root","");
        String sql = "INSERT INTO Client (id,nom, prenom, email, phone) VALUES (?, ?, ?, ?,?)";
        PreparedStatement stmt = Connexion.connexion.prepareStatement(sql);
        String checkid = "SELECT COUNT(*) FROM Client WHERE id = ?";
        PreparedStatement checkId = Connexion.connexion.prepareStatement(checkid);
        checkId.setString(1, c.getId());
        ResultSet rs = checkId.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            stmt.setString(1, c.getId());
            stmt.setString(3, c.getPrenom());
            stmt.setString(2, c.getNom());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getPhone());
            stmt.executeUpdate();}
        Connexion.close();
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        Connexion.connecte("CommandeClient","root","");
        List<Client> clients = new ArrayList<Client>();
        String clientSql = "SELECT * FROM Client ";
        PreparedStatement clientStmt = Connexion.connexion.prepareStatement(clientSql);
        ResultSet clientRs = clientStmt.executeQuery();
        while(clientRs.next()) {
            Client client = new Client(
                    clientRs.getString("id"),
                    clientRs.getString("nom"),
                    clientRs.getString("prenom"),
                    clientRs.getString("email"),
                    clientRs.getString("phone")
            );
            clients.add(client);
        }
        clientRs.close();
        clientStmt.close();
        Connexion.close();
        return clients;
    }

}
