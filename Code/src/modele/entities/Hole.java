package modele.entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/** La classe Hole (trou) permet l'instanciation d'objets du meme nom
 *  Un Hole extends une Entity
 */
public class Hole extends Entity{

    /** Boolean permettant de definir si un trou est visible ou non
     *
     */
    private BooleanProperty isVisible = new SimpleBooleanProperty();
    public boolean getIsVisible() {
        return isVisible.get();
    }
    public void setIsVisible(boolean isVisible) {
        this.isVisible.set(isVisible);
    }
    public BooleanProperty isVisibleProperty(){return isVisible;}

    /** Constructeur
     * definit des coordonnees aleatoires par defaut dans la zone de jeu, des dimensions et la visibilite a false
     */
    public Hole() {
        this.setX((int)(Math.random() * ( 991 - 198 )));
        this.setY((int)(Math.random() * ( 479 - 117 )));
        this.setLargeur(48);
        this.setHauteur(48);
        this.setIsVisible(false);
    }
}
