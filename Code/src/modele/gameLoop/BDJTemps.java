package modele.gameLoop;

import java.util.ArrayList;
import java.util.List;

/** Classe correspondante a une boucle de jeu permettant de "chronometrer une partie" et d'incrementer le score du joueur
 *
 */
public class BDJTemps extends BoucleDeJeu {
    private List<Observateur> observateurList = new ArrayList<>();

    /** Lance une boucle de jeu de duree egale a une seconde
     *
     */
    @Override
    public void run() {
        while(true){
            try
            {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notifyObservateurs();
        }
    }
}
