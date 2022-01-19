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

public class BattleShipMain extends Application { //BattleShipMain ist Subklasse der Application
    private Stage primaryStage;

    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Battleship"); //Titel des Spiels
        primaryStage.getIcons().add(new Image("file:src/main/resources/image/img_4.png")); //Das Icon des Spiels
        primaryStage.setScene(scene); // -> "scene" wird gesetzt durch "setScene" Funktion, das css stylesheet gehört zum Styling der "scene"
        primaryStage.setResizable(false); // Das Fenster von der Höhe & Breite nicht veränderbar
        primaryStage.show(); //Damit sich das Fenster öffnen müssen wir unser PrimaryStage mit "show" aufrufen


    }

    private Parent createContent() { //Methode createContent von Parent
        Pane gameMenu = new Pane(); //Erstellen ein UI Element
        gameMenu.setPrefSize(800, 750); //Höhe & Breite des Menu-Fenster

        Image bkimage = new Image("file:src/main/resources/image/battleship1.png"); //Hintergrund des Menus
        ImageView mv = new ImageView(bkimage);
        mv.setFitHeight(750); //Höhe & Breite des Bildes
        mv.setFitWidth(800);


        gameMenu.getChildren().addAll(mv); //Bild zum Hintergrund machen

        Title title = new Title("Battleship"); //Name im Menu
        title.setTranslateX(200); //Positionierung der Überschrift vom Menu
        title.setTranslateY(150);

        VBox box = new VBox( //VerticalBox wird erstellt

                5,
                new MenuItem("START GAME", (() -> { //StartGame Button
                    Game game = new Game(primaryStage); //PrimaryStage nötig damit sich Spielfenster öffnet
                    game.createMyGame(); //Game wird erstellt
                    Scene scene1 = new Scene(game.getRoot());
                    primaryStage.setScene(scene1);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION); //Informationsfenster bevor das Spiel los geht
                    alert.setTitle("Battleships basic rules");
                    alert.setHeaderText(" - Ships can be placed next to each others but cant overlap \n - Every player has 10 ships ");
                    alert.setContentText("Place your 10 ships at the board then try to find enemy's \n - Place 1 Battleship (5 blocks), 2 Cruiser (4 blocks), 3 Destroyer (3 blocks), 4 Submarines (2 blocks)");
                    alert.showAndWait();
                })),

                new MenuItem("Exit Game", () ->{  //Exit Game Button
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exit Game ");
                    alert.setHeaderText("You're about to exit!");
                    alert.setContentText("Are you sure you want to do this?");

                    if (alert.showAndWait().get() == ButtonType.OK) { //Wenn man OK drückt
                        System.out.println(" Exit game successfully, bye ");
                        primaryStage.close(); //schließt sich das Game
                    }
                }));


        // box.setBackground(new Background(new BackgroundFill(Color.web("blue",0.6),null, null)));
        box.setTranslateX(200); //VBOX mit "Start" "Exit Game" mittig platzieren
        box.setTranslateY(500);

        gameMenu.getChildren().addAll(title, box); //"title" & "box" zum gameMenu adden und ausgeben mit return
        return gameMenu;
    }

    private static class Title extends StackPane { //Layout vom StackPane
        public Title(String name) {
            Rectangle bg = new Rectangle(350, 120); //Rechteck erstellen um den Titelnamen
            bg.setStroke(Color.WHITE); //Farbe des Rahmens
            bg.setStrokeWidth(4); //Dicke des Rahmens
            bg.setFill(null); //Keine Füllung

            Text text = new Text(name);
            text.setFill(Color.WHITE); //Farbe des Texts im Rectangle
            text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 100));

            setAlignment(Pos.CENTER); //Im Zentrum positioniert
            getChildren().addAll(bg, text); //Rahmen & Überschrift hinzufügen damit sichtbar ist
        }
    }


    private static class MenuItem extends StackPane {
        public MenuItem(String name, Runnable action) {


            LinearGradient gradient = new LinearGradient(0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.DARKORANGE),
                    new Stop(1.0, Color.BLACK)
            );

            Rectangle bg = new Rectangle(250, 30);// Rectangle von StartGame & Exit Game
            bg.setOpacity(0.4); //Deckkraft der Rechtecke


            Text text = new Text(name);
            text.setFont(Font.font(30.0)); //Schriftgröße der Menu-Buttons
            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.GRAY));
                    //Wenn man über Button hovered --> weiß, sonst grau


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



