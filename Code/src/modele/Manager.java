package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import modele.entities.*;
import modele.gameLoop.BDJTemps;
import modele.gameLoop.Observateur;
import modele.gamePlayer.Player;
import modele.movements.Collisionneur;
import modele.movements.CollisionneurSimple;
import modele.movements.Deplaceur;
import modele.gameLoop.BoucleDeJeu;
import modele.gameLoop.BDJSpawn;
import modele.movements.EntityCollisionneur;
import modele.spawners.CoinSpawner;
import modele.spawners.HoleSpawner;
import persistance.Saver;

/** Classe Manager extends Observateur
 * Cette classe permet la gestion globale du jeu
 */
public class Manager extends Observateur {

    /**
     * actualPlayer est le joueur courrant lors du demarrage d'une partie
     */
    private Player actualPlayer = new Player(0, "Player");
        public Player getActualPlayer() {
        return actualPlayer;
    }
        public void setActualPlayer(Player actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    /**
     * Liste de joueurs Observable
     */
    private ObservableList<Player> list_players_obs = FXCollections.observableArrayList();
    private final ListProperty<Player> list_players = new SimpleListProperty<>(list_players_obs);
        public ObservableList<Player> getList_players_obs(){
        return list_players.get();
    }
        public void setList_players_obs(ObservableList<Player> value){
        list_players.set(value);
    }
        public ListProperty<Player> playerListProperty(){
        return list_players;
    }
        public void addPlayer(Player player){
            this.getList_players_obs().add(player);
    }

    /** Entity bind a un rectangle sur la vue
     * Il s'agit du personnage que le joueur controle
     */
    private final Entity prt = new Pirate();
        public Entity getPirate(){
        return this.prt;
    }

    //--------------------------------------------------------------

    //Déclaration de toutes les boucles de jeu nécessaires

    /** Boucle de jeu de spawn de Hole (trous) sur la scene
     *
     */
    private final BoucleDeJeu bdjHoleSpawn = new BDJSpawn();
        public BoucleDeJeu getBdjHoleSpawn(){return this.bdjHoleSpawn;}

    /** Boucle de jeu de spawn de Coin (piece) sur la scene
     *
     */
    private final BoucleDeJeu bdjCoinSpawn = new BDJSpawn();
        public BoucleDeJeu getBdjCoinSpawn() {
        return this.bdjCoinSpawn;
    }

    /** Boucle de jeu permettant l'incrémentation du score au fil de la partie
     *
     */
    private final BoucleDeJeu bdjTime = new BDJTemps();
        public BoucleDeJeu getBdjTime() { return this.bdjTime; }

    /** Différents Threads permettant de lancer les différentes boucles de jeu
     *
     */
    private Thread td1, td2, td3;

    //------------------------------------------------------------------------------

    /** Déplaceur permettant les déplacements du pirate
     *
     */
    private final Deplaceur dpl = new Deplaceur();

    /** un Collisionneur simple
     *
     */
    private final CollisionneurSimple collisionneur = new CollisionneurSimple();
        public Collisionneur getCollisionneur(){
        return this.collisionneur;
    }

    /** Un Collisionneur d'Entity
     *
     */
    private final EntityCollisionneur entityCollisionneur = new EntityCollisionneur();

    //--------------------------------------------------------------------------------

    /** HoleSpawner permet l'apparition de Hole (trous) sur la scene
     *
     */
    private final HoleSpawner holeSpawner = new HoleSpawner(this.collisionneur);
        public HoleSpawner getHoleSpawner(){return this.holeSpawner;}

    /** CoinSpawner permet l'apparition de Coin (pièce) sur la scene
     *
     */
    private final CoinSpawner coinSpawner = new CoinSpawner(this.collisionneur);
        public CoinSpawner getCoinSpawner(){return this.coinSpawner;}

    //--------------------------------------------------------------------------

    /**
     * Permet la sauvegarde des donnees de jeu dans un fichier
     */
    private final Saver saver = new Saver();
        public Saver getSaver(){return this.saver;}

    /**
     * int correspondant au temps ecoule depuis le lancement d'une partie
     */
    private int temps;

    private boolean isPlaying = false;
    public boolean getIsPlaying(){return this.isPlaying;}


    /** Constructeur du manager
     * Ajoute les observateurs necessaires aux differentes boucles de jeu
     */
    public Manager(){
        this.bdjCoinSpawn.addObservateur(this.coinSpawner);
        ((BDJSpawn)this.bdjCoinSpawn).setSleepTime(5000);

        this.bdjHoleSpawn.addObservateur(this.holeSpawner);

        this.bdjTime.addObservateur(this);
    }

    /**
     * Demarre toutes les boucles de jeu
     */
    public void startGame(){
        this.isPlaying = true;
        this.td1 = new Thread(this.bdjHoleSpawn);
        this.td1.start();

        this.td2 = new Thread(this.bdjTime);
        this.td2.start();

        this.td3 = new Thread(this.bdjCoinSpawn);
        this.td3.start();
    }

    /**
     * Arrete toutes les boucles de jeu
     */
    public void stopGame(){
        this.isPlaying = false;
        ((Pirate)this.prt).setMort(false);
        this.coinSpawner.setCoinInvisible();
        this.holeSpawner.setAllInvisible();
        this.td1.stop();
        this.td2.stop();
        this.td3.stop();
    }

    /**
     * Methode appelee par le keyListener de la scene
     * Switch appelant soit les deplacements soit la methode interraction en fonction du KeyCode
     * @param c  le KeyCode
     */
    public void doPirateEvents(KeyCode c){
        switch (c) {
            case Z -> this.dpl.deplacement(this.prt, 1, this.collisionneur);
            case S -> this.dpl.deplacement(this.prt, 3, this.collisionneur);
            case Q -> this.dpl.deplacement(this.prt, 4, this.collisionneur);
            case D -> this.dpl.deplacement(this.prt, 2, this.collisionneur);
            case P -> this.interract();
        }
    }

    /** interract
     * Si la touche d'interaction est pressee, effectue l'action correspondante dans le cas ou une Entity est proche du pirate
     * Soit "boucher un trou" rendre un Hole invisible
     * Soit "ramasser une piece" rendre un coin invisible
     */
    public void interract(){
        Entity e;
        if((e = this.entityCollisionneur.isEntityCollision(this.prt.getX(), this.prt.getY(), this.prt, this.holeSpawner, this.coinSpawner)) != null){
            if(e.getClass() == Hole.class){
                ((Hole)e).setIsVisible(false);
            }
            if(e.getClass() == Coin.class){
                ((Coin)e).setIsVisible(false);
                this.actualPlayer.setScore(actualPlayer.getScore()+10);
            }
        }
    }

    /** update() heritee de Observateur
     * a chaque tour de boucle, verifie si le nombre de Hole visibles n'est pas superieur a la limite
     * incremente le score du joueur au fil du temps
     * definie la duree des boucles de jeu de Spawn de trous afin d'augmenter la difficulte au cours du temps
     */
    @Override
    public void update() {
        if(this.holeSpawner.getNbVisible() >= 8){
            ((Pirate)this.prt).setMort(true);
            this.stopGame();
        }
        this.actualPlayer.setScore(actualPlayer.getScore()+1);
        this.temps += 1 ;
        switch (this.temps){
            case 20 -> ((BDJSpawn)this.bdjHoleSpawn).setSleepTime(4500);
            case 30 -> ((BDJSpawn)this.bdjHoleSpawn).setSleepTime(3500);
            case 45 -> ((BDJSpawn)this.bdjHoleSpawn).setSleepTime(2500);
            case 60 -> ((BDJSpawn)this.bdjHoleSpawn).setSleepTime(2000);
        }
    }

    /**
     * Méthode de sauvegarde des donnees dans un fichier .txt (non utilisée pour l'instant)
     */
    public void sauvegarder(){
        this.saver.saveScores(this.list_players_obs);
    }
}
