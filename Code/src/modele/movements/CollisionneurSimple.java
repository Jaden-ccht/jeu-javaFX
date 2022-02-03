package modele.movements;

import modele.entities.Entity;

/** CollisionneurSimple extends Collisionneur
 *
 */
public class CollisionneurSimple extends Collisionneur{

    /**
     *Methode permettant la verification d'une eventuelle collision avec un mur
     * @param x coordonnees x ou verifier une collision
     * @param y coordonnees y ou verifier une collision
     * @param e Entity e necessaire a l'obtention la largeur et de la hauteur de la zone de collision a verifier
     * @return un boolean : true en cas de collision, false sinon
     */
    public Boolean isWallCollision(double x, double y, Entity e){
        if( this.isInZone(x, y, 198, 410, 238, 365, e)
                || this.isInZone(x, y, 361, 474, 117, 479, e)
                || this.isInZone(x, y, 362, 829, 117, 283, e)
                || this.isInZone(x, y, 501, 693, 303, 479, e)
                || this.isInZone(x, y, 618, 693, 117, 479, e)
                || this.isInZone(x, y, 644, 829, 117, 283, e)
                || this.isInZone(x, y, 720, 829, 117, 479, e)
                || this.isInZone(x, y, 720, 991, 345, 417, e)
                || this.isInZone(x, y, 856, 991, 193, 417, e)){
            return false;
        }
        return true;
    }

    /**
     * Permet de verifier si une Entity se situe dans une zone de collision ou non
     * @param x coordonnees x ou verifier une collision
     * @param y coordonnees y ou verifier une collision
     * @param x1 angle superieur gauche de la zone
     * @param x2 angle superieur droit de la zone
     * @param y1 angle inferieur gauche de la zone
     * @param y2 angle inferieur droit de la zone
     * @param e Entity e necessaire a l'obtention de la largeur et de la hauteur de la zone de collision a verifier
     * @return un boolean : true en cas de collision, false sinon
     */
    private Boolean isInZone(double x, double y, double x1, double x2, double y1, double y2, Entity e){
        if(x+8 < x1 || x + (e.getLargeur()) - 9 > x2 || y+9 < y1 || y + (e.getHauteur()) > y2)
            return false;
        return true;
    }
}