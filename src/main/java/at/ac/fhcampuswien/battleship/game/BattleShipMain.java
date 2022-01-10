package at.ac.fhcampuswien.battleship.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BattleShipMain extends Application {


    public void start(Stage primaryStage){

        Game game = new Game(primaryStage);
        game.createMyGame();

        Scene scene = new Scene(game.getRoot());
        primaryStage.getIcons().add(new Image("file:src/main/resources/image/img.png"));
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
