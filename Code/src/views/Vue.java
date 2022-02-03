package views;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import launcher.Launch;
import modele.Manager;
import modele.entities.Pirate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Code Behind de la vue utilisee lors d'une partie de jeu
 */
public class Vue{

    /**
     * Le rectangle representant le pirate incarne par le joueur
     */
    @FXML
    private Rectangle rectanglePirate;

    /**
     * Pane contenant le rectanglePirate
     */
    @FXML
    private Pane pane;

    /**
     * Label bind au score du joueur courant
     */
    @FXML
    private Label labelScore;

    /**
     * BooleanProperty permettant de bind la propriete isMort du pirate afin d'arreter la partie et de fermer la scene
     */
    private BooleanProperty isPirateDead = new SimpleBooleanProperty();

    /**
     * Integer property permettant de bind le score du joueur courant au labelScore
     */
    private IntegerProperty leScore = new SimpleIntegerProperty();

    /**
     * Le manager du Launch
     */
    Manager manager = Launch.getManager();

    /**
     * Sprite setter utilise pour modifier l'image du rectangle lors de ses deplacements
     */
    SpriteSetter spriteSetter = new SpriteSetter();

    /**
     * Liste de rectangles qui sera bind au trous visibles sur la scene
     */
    List<Rectangle> rectangleList = new ArrayList<>();

    /**
     * Rectangle qui sera bind a la piece presente sur la scene
     */
    Rectangle rectangleCoin = new Rectangle();

    /** Methode lancee a l'ouverture de la scene "Vue"
     * C'est ici que sont bind tous les elements necessaires, que les EventHandler sont declenches, etc.
     */
    public void initialize() {
        isPirateDead.set(false);
        isPirateDead.bind(((Pirate)manager.getPirate()).mortProperty());
        isPirateDead.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Stage primaryStage = Launch.getPrimaryStage();
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/vuesFXML/EndScene.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        primaryStage.setTitle("End");
                        Scene sc = new Scene(root, 1180, 580);
                        sc.getStylesheets().add("/vuesFXML/style.css");
                        primaryStage.setScene(sc);
                        primaryStage.show();
                    }
                });
            }
        });


        leScore.bind(manager.getActualPlayer().scoreProperty());
        leScore.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        labelScore.setText(String.valueOf(manager.getActualPlayer().getScore()));
                    }
                });
            }
        });

        this.bindAll();


        pane.getChildren().addAll(rectangleList);
        pane.getChildren().add(rectangleCoin);
        rectanglePirate.toFront();

        Image backGround = new Image("/images/boat.png", 1180, 580, true, false);
        pane.setBackground(new Background(new BackgroundImage(backGround, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));


        Launch.getPrimaryStage().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                spriteSetter.setRectangleFill(event.getCode(), rectanglePirate, manager.getPirate());
            }
        });

        manager.startGame();
    }

    /**
     * Méthodes permettant de bind les propriétés des rectangles a celles des Entity du manager
     */
    private void bindAll(){
        this.createAllRectangles();
        this.bindRectangles();

        //Bind collisionneur -> fenetre
        manager.getCollisionneur().hauteurProperty().bind(pane.heightProperty());
        manager.getCollisionneur().largeurProperty().bind(pane.widthProperty());

        //Bind rectangleCoin -> coin
        rectangleCoin.heightProperty().bind(manager.getCoinSpawner().getCoin().hauteurProperty());
        rectangleCoin.widthProperty().bind(manager.getCoinSpawner().getCoin().largeurProperty());
        rectangleCoin.xProperty().bind(manager.getCoinSpawner().getCoin().xProperty());
        rectangleCoin.yProperty().bind(manager.getCoinSpawner().getCoin().yProperty());
        rectangleCoin.visibleProperty().bind(manager.getCoinSpawner().getCoin().isVisibleProperty());
        Image img2 = new Image("/images/coin.png", manager.getCoinSpawner().getCoin().getHauteur(), manager.getCoinSpawner().getCoin().getLargeur(), true, false);
        ImagePattern imp2 = new ImagePattern(img2);
        rectangleCoin.setFill(imp2);
        rectangleCoin.toBack();

        //Bind rectangle -> Pirate
        Image img = new Image("/spritePirate/pirate_avant1.png", manager.getPirate().getHauteur(), manager.getPirate().getLargeur(), true, false);
        ImagePattern imp = new ImagePattern(img);
        rectanglePirate.setFill(imp);
        rectanglePirate.heightProperty().bind(manager.getPirate().hauteurProperty());
        rectanglePirate.widthProperty().bind(manager.getPirate().largeurProperty());
        rectanglePirate.xProperty().bind(manager.getPirate().xProperty());
        rectanglePirate.yProperty().bind(manager.getPirate().yProperty());
    }

    /**
     * Methode permettant de bind les rectangles de la liste au trous qui spawnent avec le HoleSpawner
     */
    private void bindRectangles() {
        for (int i = 0; i < manager.getHoleSpawner().getHoleList().size(); i++) {
            rectangleList.get(i).xProperty().bind(manager.getHoleSpawner().getHoleList().get(i).xProperty());
            rectangleList.get(i).yProperty().bind(manager.getHoleSpawner().getHoleList().get(i).yProperty());
            rectangleList.get(i).widthProperty().bind(manager.getHoleSpawner().getHoleList().get(i).largeurProperty());
            rectangleList.get(i).heightProperty().bind(manager.getHoleSpawner().getHoleList().get(i).hauteurProperty());
            rectangleList.get(i).visibleProperty().bind(manager.getHoleSpawner().getHoleList().get(i).isVisibleProperty());
            Image img = new Image("/images/hole.png", manager.getHoleSpawner().getHoleList().get(i).getHauteur(),manager.getHoleSpawner().getHoleList().get(i).getLargeur(), true, false);
            ImagePattern imp = new ImagePattern(img);
            rectangleList.get(i).setFill(imp);
            rectangleList.get(i).toBack();
        }
    }

    /**
     * Methode permettant de creer une liste de rectangles qui representerons les trous sur la scene
     */
    private void createAllRectangles(){
        Rectangle r;
        for (int i = 0; i < 8; i++){
            r = new Rectangle();
            r.setVisible(false);
            rectangleList.add(r);
        }
    }
}