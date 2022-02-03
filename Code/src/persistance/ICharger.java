package persistance;

import javafx.collections.ObservableList;
import modele.gamePlayer.Player;

/**
 * Interface ayant pour méthode le chargement d'une liste de joueurs
 */
public interface ICharger {
    ObservableList<Player> chargerListeJoueurs();
}
