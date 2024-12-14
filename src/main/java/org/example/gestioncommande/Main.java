package org.example.gestioncommande;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Scene PageAcceuil;
    @Override
    public void start(Stage stage)  {
        try{FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/LoginPage.fxml"));
        PageAcceuil = new Scene(fxmlLoader.load());
        stage.setTitle("Acceuil");
        stage.setScene(PageAcceuil);
        stage.show();
         } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }}

    public static void main(String[] args) {
        launch();
    }
}
