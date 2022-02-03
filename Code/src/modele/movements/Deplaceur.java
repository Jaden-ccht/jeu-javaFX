package modele.movements;

import modele.entities.Entity;

/**
 * Classe déplaceur
 */
public class Deplaceur {

    /**
     * longueur du deplacement
     */
    private final int STEP = 7;

    /**
     * permet de deplacer une Entity en verifiant les eventuelles collisions
     * @param entity Entity a deplacer
     * @param direction int permettant de definir la direction du deplacement
     * @param collisionneur collisionneur permettant la verification d'eventuelles collisions
     */
    public void deplacement(Entity entity, int direction, CollisionneurSimple collisionneur){
        switch (direction) {
            //Déplacement vers le haut
            case 1 :
                if(!collisionneur.isWallCollision(entity.getX(), entity.getY() - STEP, entity))
                    entity.setY(entity.getY() - STEP);
                break;
            //Déplacement vers la droite
            case 2 :
                if(!collisionneur.isWallCollision(entity.getX() + STEP, entity.getY(), entity))
                    entity.setX(entity.getX() + STEP);
                break;
            //Déplacement vers le bas
            case 3 :
                if(!collisionneur.isWallCollision(entity.getX(), entity.getY() + STEP, entity))
                    entity.setY(entity.getY() + STEP);
                break;
            //Déplacement vers la gauche
            case 4 :
                if(!collisionneur.isWallCollision(entity.getX() - STEP, entity.getY(), entity))
                    entity.setX(entity.getX() - STEP);
                break;
        }
    }

}
