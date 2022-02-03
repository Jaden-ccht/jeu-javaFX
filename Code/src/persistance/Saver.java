package persistance;

import javafx.collections.ObservableList;
import modele.gamePlayer.Player;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe qui implemente l'interface ISaver
 */
public class Saver implements ISaver{
    @Override
    public void saveScores(ObservableList<Player> obsl) {
        String ligne = "";
        try {
            FileWriter fw = new FileWriter("/donnees.fxml");
            for (Player p : obsl) {
                ligne = p.getName() + "/" +p.getScore();
                System.out.println(ligne);
                fw.write(ligne);
            }

            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
