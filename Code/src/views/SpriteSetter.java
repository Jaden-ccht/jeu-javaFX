package views;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import modele.entities.Entity;

/**
 * Classe permettant de definir l'image du pirate en fonction de ses mouvements
 */
public class SpriteSetter {
    /**
     * Boolean permettant de definir l'image courante du pirate
     */
    private boolean isImg1, isImg2;

    /**
     * int permettant de definir le nombre de Sprites max avant changement d'image
     */
    private final int NBSPRITEMAX = 5;

    /**
     * Un compteur qui s'increment au cours du temps
     */
    private int spriteCounter = 0;

    /**
     * Les liens des images
     */
    private String avant1 = "/spritePirate/pirate_avant1.png",
            avant2 = "/spritePirate/pirate_avant2.png",
            arriere1 = "/spritePirate/pirate_arriere1.png",
            arriere2 = "/spritePirate/pirate_arriere2.png",
            gauche1 = "/spritePirate/pirate_gauche1.png",
            gauche2 = "/spritePirate/pirate_gauche2.png",
            droite1 = "/spritePirate/pirate_droite1.png",
            droite2 = "/spritePirate/pirate_droite2.png";

    /**
     * Constructeur
     */
    public SpriteSetter(){
        this.isImg1 = true;
        this.isImg2 = false;
    }

    /**
     * Methode permettant de definir l'image du rectangle representant le pirate
     * @param c le code de la touche pressee sur le clavier (permet de definir quelle image choisir)
     * @param rectanglePirate le rectangle pour lequel definir l'image
     * @param entity l'entity permettant de definir la taille de l'image en fonction de sa largeur
     */
    public void setRectangleFill(KeyCode c, Rectangle rectanglePirate, Entity entity){
        Image img;
        ImagePattern imp;
        if(this.spriteCounter > NBSPRITEMAX){
            if(this.isImg1){
                this.isImg2 = true;
                this.isImg1 = false;
            }
            else{
                this.isImg2 = false;
                this.isImg1 = true;
            }
            this.spriteCounter = 0;        }
        switch (c) {
            //Déplacement vers le haut
            case Z :
                if(isImg1) {
                    img = new Image(arriere1, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                else{
                    img = new Image(arriere2, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                imp = new ImagePattern(img);
                rectanglePirate.setFill(imp);
                this.spriteCounter++;
                break;
            //Déplacement vers la droite
            case D :
                if(isImg1) {
                    img = new Image(droite1, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                else{
                    img = new Image(droite2, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                imp = new ImagePattern(img);
                rectanglePirate.setFill(imp);
                this.spriteCounter++;
                break;
            //Déplacement vers le bas
            case S :
                if(isImg1) {
                    img = new Image(avant1, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                else{
                    img = new Image(avant2, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                imp = new ImagePattern(img);
                rectanglePirate.setFill(imp);
                this.spriteCounter++;
                break;
            //Déplacement vers la gauche
            case Q :
                if(isImg1) {
                    img = new Image(gauche1, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                else{
                    img = new Image(gauche2, entity.getHauteur(), entity.getLargeur(), true, false);
                }
                imp = new ImagePattern(img);
                rectanglePirate.setFill(imp);
                this.spriteCounter++;
                break;
        }
    }
}
