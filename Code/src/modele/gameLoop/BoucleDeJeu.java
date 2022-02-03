package modele.gameLoop;

import java.util.ArrayList;
import java.util.List;

/** Classe abstraite qui implemente Runnable afin de creer des Threads pour une boucle de jeu
 *
 */
public abstract class BoucleDeJeu implements Runnable{

    /** Liste d'observateurs de la boucle de jeu
     *
     */
    private List<Observateur> observateurList = new ArrayList<>();

    @Override
    public abstract void run();

    /** Permet de notifier les observateurs de la liste
     * Methode utilisee a chaque tour d'une boucle de jeu
     */
    public void notifyObservateurs(){
        for (Observateur o: observateurList) {
            o.update();
        }
    }

    /** Permet d'ajouter un observateur a la liste
     * @param o  un objet de type Observateur
     */
    public void addObservateur(Observateur o) {
        observateurList.add(o);
    }
}
