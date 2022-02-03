package launcher;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.Manager;


public class Launch extends Application {
    /**
     * Stage principale
     */
    private static Stage primaryStage;

    /**
     * Manager utilise par la suite
     */
    private static Manager manager;

    /**
     * Au demarrage, permet de creer un manager qui sera par la suite reutiliser dans les code behind
     * @param primaryStage la stage principale
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Launch.primaryStage = primaryStage;
        Launch.manager = new Manager();
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(manager.getIsPlaying())
                    manager.doPirateEvents(event.getCode());
            }
        });
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if(!manager.getIsPlaying())
                    manager.stopGame();
            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("/vuesFXML/EcranLancement.fxml"));
        primaryStage.setTitle("It's Flows");
        Scene sc= new Scene(root);
        sc.getStylesheets().add("/vuesFXML/style.css");
        primaryStage.setScene(sc);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     *
     * @return primaryStage
     */
    public static Stage getPrimaryStage(){return primaryStage;}

    /**
     *
     * @return manager
     */
    public static Manager getManager(){return manager;}

}


