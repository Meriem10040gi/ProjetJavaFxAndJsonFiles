package org.example.gestioncommande.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestioncommande.Main;
import org.example.gestioncommande.Model.*;
import org.example.gestioncommande.Service.*;
import org.example.gestioncommande.Utils.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientController {

    private final ClientServiceI ClientService=new ClientServiceImpl();

    @FXML
    private TableView<Client> ClientTable;

    @FXML
    private TableColumn<Client, String> idColumn;
    @FXML
    private TableColumn<Client, String> nomColumn;
    @FXML
    private TableColumn<Client, String> prenomColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;
    @FXML
    private TableColumn<Client, String> phoneColumn;
    @FXML
    private Button btn;

    public void initialize() throws SQLException {
        List<Client> Clients = ClientService.getAllClients();
        ObservableList<Client> ClientList = FXCollections.observableArrayList(Clients);
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        idColumn.setCellFactory(column -> {
            TableCell<Client, String> cell = new TableCell<Client, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        nomColumn.setCellFactory(column -> {
            TableCell<Client, String> cell = new TableCell<Client, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        TimeConverteser t = new TimeConverteser();
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });

        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        prenomColumn.setCellFactory(column -> {
            TableCell<Client, String> cell = new TableCell<Client, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        phoneColumn.setCellFactory(column -> {
            TableCell<Client, String> cell = new TableCell<Client, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        emailColumn.setCellFactory(column -> {
            TableCell<Client, String> cell = new TableCell<Client, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        ClientTable.setItems(ClientList);
    }



    public void SignOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }


    public void CommandesView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }
}

