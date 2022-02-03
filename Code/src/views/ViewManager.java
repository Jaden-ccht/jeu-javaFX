package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import launcher.Launch;
import modele.Manager;
import persistance.Stub;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Manager de vues
 */
public class ViewManager {
    /**
     * Bouton permettant le lancement d'une partie
     */
    @FXML
    private Button buttonGame;

    /**
     * Champ de texte a remplir permettant de definir le pseudo du joueur courant
     */
    @FXML
    private TextField textFieldUsername;

    private String username;

    /**
     * Le manager du Launch
     */
    public Manager manager = Launch.getManager();

    /**
     * A l'initialisation, charge les donnees du Stub
     */
    public void initialize(){
        Stub st = new Stub();
        textFieldUsername.textProperty().bindBidirectional(manager.getActualPlayer().nameProperty());
        manager.setList_players_obs(st.chargerListeJoueurs());
        this.button_activator();
    }


    /**
     * Defini la scene de jeu
     * @throws IOException
     */
   public void lancer_jeu() throws IOException {
        Stage stage = Launch.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/vuesFXML/Vue.fxml"));
        stage.setTitle("It's Flows");
        Scene sc = new Scene(root, 1180, 580);
        stage.setScene(sc);
        sc.getStylesheets().add("/vuesFXML/style.css");
        stage.setResizable(false);
       stage.show();
        manager.getActualPlayer().setName(get_username());
        manager.addPlayer(manager.getActualPlayer());
    }

    /**
     * Ouvre une fenetre expliquant les regles du jeu
     * @throws IOException
     */
    public void lancer_rules() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/vuesFXML/Rules.fxml"));
        stage.setTitle("Rules");
        Scene sc = new Scene(root);
        sc.getStylesheets().add("/vuesFXML/style.css");
        stage.setScene(sc);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Permet d'activer le bouton si le textField n'est pas vide
     */
    private void button_activator(){
        buttonGame.disableProperty().bind(textFieldUsername.textProperty().isEmpty());
        }
        
    private String get_username(){
        return this.username = textFieldUsername.getText().trim();
    }

}





