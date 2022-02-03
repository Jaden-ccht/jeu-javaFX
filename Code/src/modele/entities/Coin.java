package modele.entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/** La classe Coin (piece) permet l'instanciation d'objets du meme nom
 *  Un Coin extends une Entity
 */
public class Coin extends Entity{

    private BooleanProperty isVisible = new SimpleBooleanProperty();
    public boolean getIsVisible() {
        return isVisible.get();
    }
    public void setIsVisible(boolean isVisible) {
        this.isVisible.set(isVisible);
    }
    public BooleanProperty isVisibleProperty(){return isVisible;}

    private int value = 20;
    public int getValue() {
        return this.value;
    }
    public void setValue(int v) {this.value = v;}


    public Coin() {
        this.setX((int)(Math.random() * ( 991 - 198 )));
        this.setY((int)(Math.random() * ( 479 - 117 )));
        this.setLargeur(40);
        this.setHauteur(40);
        this.setIsVisible(false);
    }
}
