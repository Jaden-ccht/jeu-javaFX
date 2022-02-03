package modele.gameLoop;

import java.util.*;

/** Classe correspondante a une boucle de jeu permettant le spawn de differentes entites sur la scene
 *
 */
public class BDJSpawn extends BoucleDeJeu{
    private List<Observateur> observateurList = new ArrayList<>();

    /** Propriete permettant de definir la duree de la boucle de jeu
     *
     */
    private Long sleepTime =  (long) 5500;

    /** Setter de la propriete
     *
     * @param t temps voulue de la boucle de jeu
     */
    public void setSleepTime(int t){
        this.sleepTime = (long)t;
    }

    /** Lance une boucle de jeu de duree egale a la propriete sleepTime
     *
     */
    @Override
    public void run(){
        while(true){
            try
            {
                Thread.sleep(this.sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notifyObservateurs();
        }
    }
}
