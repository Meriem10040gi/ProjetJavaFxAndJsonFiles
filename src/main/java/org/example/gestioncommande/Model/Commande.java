package org.example.gestioncommande.Model;
import javafx.fxml.FXML;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Commande {
    @FXML
    String Id;
    Long CommandeDate;
    Double Amount;
    Client Custmer;
    String Statut;
}
