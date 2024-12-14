package org.example.gestioncommande.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    String Id;
    String Nom;
    String Prenom;
    String Email;
    String Phone;
}
