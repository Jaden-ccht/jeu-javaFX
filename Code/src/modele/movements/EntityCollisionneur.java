package modele.movements;

import modele.entities.Coin;
import modele.spawners.CoinSpawner;
import modele.spawners.HoleSpawner;
import modele.entities.Entity;
import modele.entities.Hole;

/**
 * Permet la verification de collision entre 2 Entity
 */
public class EntityCollisionneur extends Collisionneur{

    /**
     * Methode de verification de collision entre 2 Entity
     * @param x coordonnees x ou verifier une collision
     * @param y coordonnees y ou verifier une collision
     * @param e Entity e necessaire a l'obtention de la largeur et de la hauteur de la zone de collision a verifier
     * @param holeSpawner permet d'obtenir la Liste de Hole presents sur la map afin de verifier une collision
     * @param coinSpawner permet d'obtenir le Coin present sur la map afin de verifier une collision
     * @return une Entity de type Coin ou Hole ou null
     */
    public Entity isEntityCollision(double x, double y, Entity e, HoleSpawner holeSpawner, CoinSpawner coinSpawner){
        Coin c = coinSpawner.getCoin();
        if(((x > c.getX() && x < c.getX() + c.getLargeur()) || (x + e.getLargeur()> c.getX() && x + e.getLargeur() < c.getX() + c.getLargeur())) && ((y > c.getY() && y < c.getY() + c.getHauteur()) || (y + e.getHauteur()> c.getY() && y + e.getHauteur() < c.getY() + c.getHauteur()))){
            if(c.getIsVisible())
                return c;
        }
        for (Hole  h : holeSpawner.getHoleList()) {
            if(((x > h.getX() && x < h.getX() + h.getLargeur()) || (x + e.getLargeur()> h.getX() && x + e.getLargeur() < h.getX() + h.getLargeur())) && ((y > h.getY() && y < h.getY() + h.getHauteur()) || (y + e.getHauteur()> h.getY() && y + e.getHauteur() < h.getY() + h.getHauteur()))){
                if(h.getIsVisible())
                    return h;
            }
        }
        return null;
    }

}
