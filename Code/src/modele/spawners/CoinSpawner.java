package modele.spawners;

import modele.entities.Coin;
import modele.gameLoop.Observateur;
import modele.movements.CollisionneurSimple;

/** Classe CoinSpawner extends Observateur
 * Permet le spawn de Coin (piece) sur la scene
 */
public class CoinSpawner extends Observateur {

    /**
     * Coin (piece) qui est bind a un rectangle de la vue
     */
    private Coin coin = new Coin();

    /**
     * Collisionneur permettant de verifier la position d'apparition aleatoire du Coin sur la map
     */
    private CollisionneurSimple collisionneurSimple;


    /**
     * Constructeur
     * @param c un collisionneur
     */
    public CoinSpawner(CollisionneurSimple c){
        this.collisionneurSimple = c;
    }

    /**
     *
     * @return this.coin
     */
    public Coin getCoin() {
        return this.coin;
    }

    /**
     * Permet de rendre le coin invisible afin de redemarrer une partie
     */
    public void setCoinInvisible(){this.coin.setIsVisible(false);}

    /** update() heritee de Observateur
     * a chaque tour de boucle (toutes les 5 secondes), si le Coin est visible -> il devient invisible
     * sinon : ses coordonnees sont set aleatoirement et verifiees avec le collisionneur puis il devient visible
     */
    @Override
    public void update() {
        if(this.coin.getIsVisible())
            this.coin.setIsVisible(false);
        else{
            int x = (int)(Math.random() * ( 991 - 198 ));
            int y = (int)(Math.random() * ( 479 - 117 ));
            while(this.collisionneurSimple.isWallCollision(x, y, coin)){
                x = (int)(Math.random() * ( 991 - 198 ));
                y = (int)(Math.random() * ( 479 - 117 ));
            }
            coin.setX(x);
            coin.setY(y);
            coin.setIsVisible(true);
        }
    }
}
