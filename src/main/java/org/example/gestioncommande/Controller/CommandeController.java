package org.example.gestioncommande.Controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
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
import org.example.gestioncommande.Model.Commande;
import org.example.gestioncommande.Service.*;
import org.example.gestioncommande.Utils.Browser;
import org.example.gestioncommande.Utils.FileReaderWriter;
import org.example.gestioncommande.Utils.TimeConverteser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommandeController {

    private final CommandeServiceI commandeService=new CommandeServiceImpl();
    private final ClientServiceImpl clientService=new ClientServiceImpl();

    @FXML
    private TableView<Commande> commandeTable;

    @FXML
    private TableColumn<Commande, String> idColumn;
    @FXML
    private TableColumn<Commande, Long> dateColumn;
    @FXML
    private TableColumn<Commande, Double> amountColumn;
    @FXML
    private TableColumn<Commande, String> statutColumn;
    @FXML
    private TableColumn<Commande, String> clientIdColumn;
    @FXML
    private TextField file;
    @FXML
    private TextField file2;
    @FXML
    private TextField file3;
    @FXML
    private Button btn;
    @FXML
    private Button signoutBtn;

    public void initialize() throws SQLException {
        List<Commande> commandes = commandeService.getAllCommandes();
        ObservableList<Commande> commandeList = FXCollections.observableArrayList(commandes);
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        idColumn.setCellFactory(column -> {
            TableCell<Commande, String> cell = new TableCell<Commande, String>() {
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
        dateColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getCommandeDate()).asObject());
        dateColumn.setCellFactory(column -> {
            TableCell<Commande, Long> cell = new TableCell<Commande, Long>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        TimeConverteser t = new TimeConverteser();
                        setText(t.convert(item));
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });

        amountColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
        amountColumn.setCellFactory(column -> {
            TableCell<Commande, Double> cell = new TableCell<Commande, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item != null ? item.toString() : "");
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        statutColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatut()));
        statutColumn.setCellFactory(column -> {
            TableCell<Commande, String> cell = new TableCell<Commande, String>() {
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
        clientIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustmer().getEmail()));
        clientIdColumn.setCellFactory(column -> {
            TableCell<Commande, String> cell = new TableCell<Commande, String>() {
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
        commandeTable.setItems(commandeList);
    }



    public void InsertData(ActionEvent actionEvent) throws IOException, SQLException {
        FileReaderWriter f = new FileReaderWriter();
        String Inputpath=file.getText();
        String Outputpath=file2.getText();
        String Errorpath=file3.getText();
        if (Inputpath.isEmpty() || Outputpath.isEmpty() || Errorpath.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs de chemin vides");
            alert.setContentText("Veuillez entrer des chemins valides pour tous les fichiers.");
            alert.showAndWait();
            return;
        }
        file.clear();
        file2.clear();
        file3.clear();
        List<Commande> commandes = f.JsonReader(Inputpath);
        f.JsonWriter(null,Outputpath,0);
        f.JsonWriter(null,Errorpath,0);
        for(Commande cmd : commandes){
            try{
                clientService.addClient(cmd.getCustmer());
                commandeService.addCommande(cmd);
                f.JsonWriter(cmd,Outputpath,1);
            }
            catch (SQLException e){
                e.printStackTrace();
                f.JsonWriter(cmd,Errorpath,1);
            }
        }
        f.JsonWriter(null,Outputpath,1);
        f.JsonWriter(null,Errorpath,1);
        f.clearFile(Inputpath);
        initialize();
    }
    public void SignOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    public void BrowsInputFile(ActionEvent actionEvent) {
        file.setText(Browser.BrowsFile(actionEvent));
    }

    public void BrowsOutputFile(ActionEvent actionEvent) {
        file2.setText(Browser.BrowsFile(actionEvent));
    }

    public void BrowsErrorFile(ActionEvent actionEvent) {
        file3.setText(Browser.BrowsFile(actionEvent));
    }

    public void CustmersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/MainPage2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

}
