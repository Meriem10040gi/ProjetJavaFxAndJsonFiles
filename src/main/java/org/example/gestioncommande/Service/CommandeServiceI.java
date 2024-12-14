package org.example.gestioncommande.Service;

import org.example.gestioncommande.Model.Commande;

import java.sql.SQLException;
import java.util.List;

public interface CommandeServiceI {
    void addCommande(Commande c) throws SQLException;
    List<Commande> getAllCommandes() throws SQLException;
}
