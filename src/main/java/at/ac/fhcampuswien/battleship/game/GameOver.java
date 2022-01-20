package at.ac.fhcampuswien.battleship.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

class GameOver extends Stage {
    private final Stage primaryStage;
    private final VBox root = new VBox();
    private final Scene scene = new Scene(root);
    private final Text winnerTxt;
    private final Text rematch = new Text("Rematch?");
    private final Button yesBtn = new Button("Yes");
    private final Button noBtn = new Button("No");

    GameOver(String winner, Stage primaryStage){
        scene.getStylesheets().add("file:src/main/resources/style/style.css");// CSS File is used for the styling

        /*das Fenster vom Gameover geht auf auf mein Spielfenster*/
        this.initModality(Modality.APPLICATION_MODAL);

        this.initStyle(StageStyle.UNDECORATED);
        winnerTxt = new Text(winner + " WINS");
        this.primaryStage = primaryStage;
    }

    /**Function that draws the Game Over window*/
    void drawDialog(Game game){
        root.setAlignment(Pos.CENTER);

        winnerTxt.getStyleClass().add("winnerText"); // Die CSS Klassen werden den Text-Objekt zugewiesen
        rematch.getStyleClass().add("rematch");

        root.getChildren().add(winnerTxt);
        root.getChildren().add(rematch);

        winnerTxt.setFont(new Font(10));
        rematch.setFont(new Font(10));

        HBox buttons = new HBox();

        buttons.getChildren().add(yesBtn);
        buttons.getChildren().add(noBtn);

        buttons.setSpacing(50);

        root.setPadding(new Insets(20));
        root.getChildren().add(buttons);
        buttons.setAlignment(Pos.CENTER);

        /*Funktion die eintritt wenn man den "Yes" Button drückt*/
        yesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getThis().close(); // schließt das Gameoverfenster
                game.redrawContent(); // zeichnet das Spielfenster neu
            }
        });

        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                endGame();
            }
        });
        this.setScene(scene);
    }

    /**Funktion die das Gameover- und Spielfenster schließt*/
    private void endGame() {
        primaryStage.close();
        this.close();
    }

    /*Wird benötig da man innerhalb des Eventhandlers nicht auf "this" zugreifen kann*/
    private GameOver getThis(){
        return this;
    }

















}
