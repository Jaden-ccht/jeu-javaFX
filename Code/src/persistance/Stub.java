package persistance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.gamePlayer.Player;

/** Classe qui implement l'interface ICharger
 * Permet le chargement de fausses donnees de test
 */
public class Stub implements ICharger{
    @Override
    public ObservableList<Player> chargerListeJoueurs() {
        ObservableList<Player> l = FXCollections.observableArrayList();
        l.add(new Player(122, "Jaden"));
        l.add(new Player(153, "Matthieu"));
        return l;
    }
}
