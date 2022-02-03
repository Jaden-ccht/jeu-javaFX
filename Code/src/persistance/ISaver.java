package persistance;

import javafx.collections.ObservableList;
import modele.gamePlayer.Player;

/**
 * Interface ayant pour méthode la sauvegarde d'une liste de joueurs
 */
public interface ISaver {
    void saveScores(ObservableList<Player> obsl);
}
