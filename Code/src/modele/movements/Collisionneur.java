package modele.movements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import modele.entities.Entity;

/** Classe abstraite Collisionneur
 *
 */
public abstract class Collisionneur {

        /**
         * int correspondant a un decalage
         */
        private final int OFFSET = 8;

        /** Propriete largeur : est bind a la largeur de la fenetre dans le code behind de la vue
         *
         */
        private DoubleProperty largeur = new SimpleDoubleProperty();
        public double getlargeur() {
                return largeur.get();
        }
        public void setlargeur(double largeur) {
                this.largeur.set(largeur);
        }
        public DoubleProperty largeurProperty() {
                return largeur;
        }

        /** Propriete hauteur : est bind a la hauteur de la fenetre dans le code behind de la vue
         *
         */
        private DoubleProperty hauteur = new SimpleDoubleProperty();
        public double getHauteur() {
                return hauteur.get();
        }
        public void setHauteur(double hauteur) {
                this.hauteur.set(hauteur);
        }
        public DoubleProperty hauteurProperty() {
                return hauteur;
        }

        /** Methode isCollision
         * Verification de collision sur les bords de la fenetre
         * @param x coordonnees x ou verifier une collision
         * @param y coordonnees y ou verifier une collision
         * @param e Entity e necessaire a l'obtention de la largeur et de la hauteur de la zone de collision a verifier
         * @return un boolean : true en cas de collision, false sinon
         */
        public Boolean isCollision(double x, double y, Entity e) {
                if(x < 0 || x > this.getlargeur() - (e.getLargeur()) || y < 0 || y > this.getHauteur()-(e.getHauteur() - OFFSET))
                        return true;
                return false;
        }
}