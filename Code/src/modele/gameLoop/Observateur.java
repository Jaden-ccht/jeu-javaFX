package modele.gameLoop;

/** Classe abstraite Observateur
 *
 */
public abstract class Observateur {

    /** Tout objet heritant de cette classe dispose de la methode update appelee a chaque tour de boucle par la methode notifyObservateurs
     *
     */
    public abstract void update();
}
