package at.ac.fhcampuswien.battleship.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BattleShipMain extends Application {


    public void start(Stage primaryStage){

        Game game = new Game(primaryStage); //Objekt mit Variablennamen "game" der Klasse Game wird erstellt, mit dem "primaryStage" --> Ist eine Instanzvariable, wird im Konstrukor aufgerufen, wenn ein Spiel gestartet wird
                                            //In primaryStage ist das Spielfenster gespeichert

        game.createMyGame(); //In game wird dann die "createMyGame" Methode aus der Klasse "Game" aufgerufen --> Spieler werden erstellt, wieviel Schiffe man platzieren kann, Erstellung der 10*10 Kästchen usw.

        Scene scene = new Scene(game.getRoot()); //Objekt der Klasse Scene erstellt, welches das den getter "root" aufruft. im "root" haben wir die Buttons, Texte, genaue Positionierung unserer HBoxen in der Klasse "Game"

        primaryStage.getIcons().add(new Image("file:src/main/resources/image/img.png")); //Im primaryStage wird die Funktion getIcons(javafx) aufgerufen, und ein Foto hinzugefügt, dass unser Spielfeld abdeckt nach setzen der Schiffe


        primaryStage.setTitle("Battleship"); //Der Titel des Fensters trägt den Namen "Battleship"

        primaryStage.setScene(scene); // Funktion "setScene" wird aufgerufen --> "scene" wird gesetzt durch "setScene" Funktion, das css stylesheet gehört zum Styling der "scene"

        primaryStage.setResizable(false); //Hier haben wir die "setResizable" Funktion aufgerufen die sagt, dass das Fenster von der Höhe & Breite nicht veränderbar ist --> false

        primaryStage.show(); //Damit sich das Fenster öffnen müssen wir unser PrimaryStage mit "show" aufrufen
    }
}
