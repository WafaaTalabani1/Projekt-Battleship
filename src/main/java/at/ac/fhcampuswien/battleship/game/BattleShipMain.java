package at.ac.fhcampuswien.battleship.game;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BattleShipMain extends Application {
    private Stage primaryStage; //BatleShipMain ist Subklasse der Application

    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Battleship");
        primaryStage.getIcons().add(new Image("file:src/main/resources/image/img_4.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();



       /* Game game = new Game(primaryStage); //Objekt mit Variablennamen "game" der Klasse Game wird erstellt, mit dem "primaryStage"
        // --> Ist eine Instanzvariable, wird im Konstrukor aufgerufen, wenn ein Spiel gestartet wird
                                            //In primaryStage ist das Spielfenster gespeichert

        game.createMyGame(); //In game wird dann die "createMyGame" Methode aus der Klasse "Game" aufgerufen
        // --> Spieler werden erstellt, wieviel Schiffe man platzieren kann, Erstellung der 10*10 Kästchen usw.

        Scene scene = new Scene(game.getRoot()); //Objekt der Klasse Scene erstellt, welches das den getter "root" aufruft.
        // im "root" haben wir die Buttons, Texte, genaue Positionierung unserer HBoxen in der Klasse "Game"

        primaryStage.getIcons().add(new Image("file:src/main/resources/image/img_4.png")); //Im primaryStage wird die Funktion getIcons(javafx) aufgerufen,
        // und ein Foto hinzugefügt, dass unser Spielfeld abdeckt nach setzen der Schiffe


        primaryStage.setTitle("Battleship"); //Der Titel des Fensters trägt den Namen "Battleship"

        primaryStage.setScene(scene); // Funktion "setScene" wird aufgerufen -
        // -> "scene" wird gesetzt durch "setScene" Funktion, das css stylesheet gehört zum Styling der "scene"

        primaryStage.setResizable(false); //Hier haben wir die "setResizable" Funktion aufgerufen die sagt,
        // dass das Fenster von der Höhe & Breite nicht veränderbar ist --> false

        primaryStage.show(); //Damit sich das Fenster öffnen müssen wir unser PrimaryStage mit "show" aufrufen
    }*/
    }

    private Parent createContent() {
        Pane gameMenu = new Pane();
        gameMenu.setPrefSize(800, 750);

        Image bkimage = new Image("file:src/main/resources/image/battleship1.png");
        ImageView mv = new ImageView(bkimage);
        mv.setFitHeight(750);
        mv.setFitWidth(800);


        gameMenu.getChildren().addAll(mv);

        Title title = new Title("Battleship");
        title.setTranslateX(200);
        title.setTranslateY(150);

        VBox box = new VBox(

                5,
                new MenuItem("START GAME", (() -> {
                    Game game = new Game(primaryStage);
                    game.createMyGame();
                    Scene scene1 = new Scene(game.getRoot());
                    primaryStage.setScene(scene1);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Battleships basic rules");
                    alert.setHeaderText(" -ships can be placed next to each others but cant overlap \n -every player has 10 ships ");
                    alert.setContentText("Place your 10 ships at the board then try to find enemy's");
                    alert.showAndWait();
                })),

                new MenuItem("Exit Game", () ->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exit Game ");
                    alert.setHeaderText("You're about to exit!");
                    alert.setContentText("Are you sure you want to do this?");

                    if (alert.showAndWait().get() == ButtonType.OK) {
                        System.out.println(" Exit game successfully, bye ");
                        primaryStage.close();
                    }
                }));


        // box.setBackground(new Background(new BackgroundFill(Color.web("blue",0.6),null, null)));
        box.setTranslateX(200);
        box.setTranslateY(500);

        gameMenu.getChildren().addAll(title, box);
        return gameMenu;
    }

    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(350, 100);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(4);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 100));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }


    private static class MenuItem extends StackPane {
        public MenuItem(String name, Runnable action) {


            LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.DARKORANGE),
                    new Stop(1.0, Color.BLACK)
            );

            Rectangle bg = new Rectangle(250, 30);// background
            bg.setOpacity(0.4);


            Text text = new Text(name);
            text.setFont(Font.font(30.0));
            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY));


            setOnMouseClicked(e -> action.run());

            setOnMousePressed(e -> bg.setFill(Color.WHITE));

            setOnMouseReleased(event -> bg.setFill(gradient));

            setAlignment(Pos.CENTER);

            getChildren().addAll(bg, text);


        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}



