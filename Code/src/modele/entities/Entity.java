package modele.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/** Les Entites sont des elements etant bind a d'autres elements de la vue
 *
 */
public abstract class Entity {

    private IntegerProperty x = new SimpleIntegerProperty();
    public int getX() {
        return x.get();
    }
    public void setX(int x) {
        this.x.set(x);
    }
    public IntegerProperty xProperty(){return x;}

    private IntegerProperty y = new SimpleIntegerProperty();
    public int getY() {
        return y.get();
    }
    public void setY(int y) {
        this.y.set(y);
    }
    public IntegerProperty yProperty(){return y;}

    private IntegerProperty largeur = new SimpleIntegerProperty();
    public int getLargeur() {
        return largeur.get();
    }
    public void setLargeur(int largeur) {
        this.largeur.set(largeur);
    }
    public IntegerProperty largeurProperty(){return largeur;}

    private IntegerProperty hauteur = new SimpleIntegerProperty();
    public int getHauteur() {
        return hauteur.get();
    }
    public void setHauteur(int hauteur) {
        this.hauteur.set(hauteur);
    }
    public IntegerProperty hauteurProperty(){return hauteur;}
}
