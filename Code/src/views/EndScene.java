package views;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import launcher.Launch;
import modele.Manager;
import modele.gamePlayer.Player;

import java.io.IOException;

public class EndScene {

    /**
     * liste de joueurs a afficher
     */
    @FXML
    public ListView laListe;

    @FXML
    public Button button;
    /**
     * Le manager du launch
     */
    private Manager manager = Launch.getManager();

    /**
     * Permet de creer une CellFactory a l'initialisation
     */
    @FXML
    public void initialize() {
        laListe.itemsProperty().bind(manager.playerListProperty());

        laListe.setCellFactory(__ ->
                new ListCell<Player>(){
                    @Override
                    protected void updateItem(Player item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            textProperty().bind(Bindings.format("%s : %s points", item.nameProperty(), Bindings.convert(item.scoreProperty())));
                        } else {
                            textProperty().unbind();
                            setText("");
                        }
                    }
                }
        );
    }
}
