package org.example.gestioncommande.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestioncommande.Main;
import org.example.gestioncommande.Service.AdminServiceI;
import org.example.gestioncommande.Service.AdminServiceImpl;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginbtn;

    private AdminServiceI adminService = new AdminServiceImpl();

    public void login(ActionEvent actionEvent) throws SQLException, IOException {

        if (email.getText().isEmpty() || password.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vides");
            alert.setContentText("Veuillez entrer votre Email et votre Password .");
            alert.showAndWait();
            return;
        }
          if(adminService.isExist(email.getText(),password.getText())){
              FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/MainPage.fxml"));
              Scene scene = new Scene(fxmlLoader.load());
              Stage stage = (Stage) loginbtn.getScene().getWindow();
              stage.setTitle("Commandes");
              stage.setScene(scene);
              stage.show();
          }
          else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Erreur");
              alert.setHeaderText("Champs Erronés");
              alert.setContentText("Veuillez entrer votre Email et votre Password .");
              alert.showAndWait();
              return;
          }
    }
}
