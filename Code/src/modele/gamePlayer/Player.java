package modele.gamePlayer;

import javafx.beans.property.*;

/**
 * La classe player permet d'instancier des objets du meme nom
 * Un player possede un pseudo ainsi qu'un score
 */
public class Player {

    /**
     * Propriete correspondante au pseudo d'un joueur
     */
    private StringProperty name = new SimpleStringProperty();
        public String getName() {
        return name.get();
    }
        public StringProperty nameProperty() {
        return name;
    }
        public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Propriete correspondante au score d'un joueur
     */
    private IntegerProperty score = new SimpleIntegerProperty();
        public int getScore() {
            return score.get();
        }
        public IntegerProperty scoreProperty() {
            return score;
        }
        public void setScore(int score) {
            this.score.set(score);
        }

    /**
     * Constructeur
      * @param score le score du joueur
     * @param name le pseudo du joueur
     */
    public Player(int score, String name){
        this.name.set(name);
        this.score.set(score);
    }
}
