package org.example.gestioncommande.Service;

import org.example.gestioncommande.Model.Client;
import org.example.gestioncommande.Model.Commande;
import org.example.gestioncommande.Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeServiceImpl implements CommandeServiceI{

    DBConnection Connexion = new DBConnection ();
    @Override
    public void addCommande(Commande c) throws SQLException {

        Connexion.connecte("CommandeClient","root","");
        String sql = "INSERT INTO Commande (id,amount,client_id,statut,dateAjout) VALUES (?, ?, ?,?,?)";
        PreparedStatement stmt = Connexion.connexion.prepareStatement(sql);
        String checkid = "SELECT COUNT(*) FROM Commande WHERE id = ?";
        PreparedStatement checkId = Connexion.connexion.prepareStatement(checkid);
        checkId.setString(1, c.getId());
        ResultSet rs = checkId.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            stmt.setString(1, c.getId());
            stmt.setDouble(2, c.getAmount());
            stmt.setString(3, c.getCustmer().getId());
            stmt.setString(4, c.getStatut());
            stmt.setLong(5, c.getCommandeDate());
            //stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            stmt.executeUpdate();}
        Connexion.close();
    }

    @Override
    public List<Commande> getAllCommandes() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        Connexion.connecte("CommandeClient", "root", "");
        String sql = "SELECT * FROM Commande";
        PreparedStatement stmt = Connexion.connexion.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Commande cmd = new Commande(
                    rs.getString("id"),
                    rs.getLong("dateAjout"),
                    rs.getDouble("amount"),
                    null,
                    rs.getString("statut")
            );
            String clientSql = "SELECT * FROM Client WHERE id = ?";
            PreparedStatement clientStmt = Connexion.connexion.prepareStatement(clientSql);
            clientStmt.setString(1, rs.getString("client_id"));
            ResultSet clientRs = clientStmt.executeQuery();
            if (clientRs.next()) {
                Client client = new Client(
                        clientRs.getString("id"),
                        clientRs.getString("nom"),
                        clientRs.getString("prenom"),
                        clientRs.getString("email"),
                        clientRs.getString("phone")
                );
                cmd.setCustmer(client);
            }
            commandes.add(cmd);
            clientRs.close();
            clientStmt.close();
        }
        rs.close();
        stmt.close();
        Connexion.close();
        return commandes;
    }


}
