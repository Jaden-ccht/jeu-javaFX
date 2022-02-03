package modele.entities;

import javafx.beans.property.*;

/** La classe Pirate permet l'instanciation d'objets du meme nom
 *  Un Pirate extends une Entity
 */
public class Pirate extends Entity{

    /**Boolean Mort permet de definir la fin de la partie
     *
     */
    private BooleanProperty mort = new SimpleBooleanProperty();
    public boolean isMort() {
        return mort.get();
    }
    public void setMort(boolean mort) {
        this.mort.set(mort);
    }
    public BooleanProperty mortProperty(){return mort;}

    /** Constructeur du pirate
     *  Definit une position par defaut, des dimensions par defaut et le boolean mort = false
     */
    public Pirate() {
        this.setX(500);
        this.setY(180);
        this.setLargeur(40);
        this.setHauteur(40);
        this.mort.set(false);
    }
}
