package modele.spawners;

import modele.entities.Hole;
import modele.gameLoop.Observateur;
import modele.movements.CollisionneurSimple;

import java.util.*;

/**
 * Classe HoleSpawner extends Observateur
 */
public class HoleSpawner extends Observateur {

    /**
     * Liste de Hole (trous) qui seront chacun bind a un rectangle sur la vue
     */
    private List<Hole> holeList = new ArrayList<>();

    /**
     * Collisionneur permettant de verifier la position d'apparition aleatoire des Hole(trous) sur la map
     */
    private CollisionneurSimple collisionneurSimple;


    /**
     * Constructeur
     * cree une liste de Hole (trous) non visibles
     * @param c un collisionneur
     */
    public HoleSpawner(CollisionneurSimple c){
        Hole hole;
        for (int i = 0; i < 8; i++){
            hole = new Hole();
            holeList.add(hole);
        }
        this.collisionneurSimple = c;
    }

    /**
     * getHoleList()
     * @return this.holeList
     */
    public List<Hole> getHoleList() {
        return this.holeList;
    }

    /**
     * Permet de rendre l'ensemble des trous de la liste invisibles afin de commencer une partie
     */
    public void setAllInvisible(){
        for (Hole h : this.holeList) {
            h.setIsVisible(false);
        }
    }

    /**
     * getNbVisible()
     * @return le nombre de Hole visibiles dans le jeu et permet ainsi de declencher la fin du jeu si ce nombre est trop important
     */
    public int getNbVisible(){
        int nb = 0;
        for (Hole h : this.holeList) {
            if(h.getIsVisible())
                nb++;
        }
        return nb;
    }

    /** update() heritee de Observateur
     * a chaque tour de boucle correspondante, rend visible le premier Hole non visible de la liste en lui attribuant des coordonnees aleatoires
     */
    @Override
    public void update() {
        for (Hole h : this.holeList) {
            if(!h.getIsVisible()) {
                h.setIsVisible(true);
                int x = (int)(Math.random() * ( 991 - 198 ));
                int y = (int)(Math.random() * ( 479 - 117 ));
                while(this.collisionneurSimple.isWallCollision(x, y, h)){
                    x = (int)(Math.random() * ( 991 - 198 ));
                    y = (int)(Math.random() * ( 479 - 117 ));
                }
                h.setX(x);
                h.setY(y);
                return;
            }
        }
    }
}
