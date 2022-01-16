package at.ac.fhcampuswien.battleship.game;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

    public class Board extends Parent { // Wo ist Parent, was steht in Parent drinnen ?
        private final int HEIGHT = 10; //nicht änderbare Höhe definiert --> 10*10 Feld
        private final int WIDTH = 10;  //nicht änderbare definierte Breite
        private final String name; //Wenn name eingegeben wird nicht änderbar mitten in der Ausführung
        private boolean covered; //eine boolean Variable "covered" für das Bild, welches das Spielfeld überdecken wird
        private Image wave1; //Eine Variable für das Foto
        private ImageView imageView1; //Eine ImageView Variable wo wave1 aufgerufen wird
        private final VBox rows = new VBox(); //Neues Objekt wird erstellt der Klasse VBox mit Namen "rows"


    Board(String name) { //Konstruktor "default"
        this.name = name; //Name wird übergeben
        //giving the board shadow effect
        this.setStyle("-fx-effect: dropshadow(three-pass-box, chartreuse, 20, 0, 0, 15);"); //shadow vom Spielfeld
        initializeImageView(); //Methode wird aufgerufen von weiter unten
        for (int y = 0; y < HEIGHT; y++) {
            HBox row = new HBox(); //HBox = Horizontale Boxen
            for (int x = 0; x < WIDTH; x++) {
                BoardCell c = new BoardCell(x, y, this);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);

    }

    private void initializeImageView(){
        wave1 = new Image("file:src/main/resources/image/wave.jpg");
        imageView1 = new ImageView(wave1);
        imageView1.setVisible(false);
        imageView1.setStyle("-fx-effect: dropshadow(three-pass-box, chocolate, 30, 0, 0, 15);");
    }

    BoardCell getCell(int x, int y) {                //auf einzelne Zellen zugreifen
        return (BoardCell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);

    }

    boolean isValidPoint(double x, double y){
        return x >= 0 && x < HEIGHT && y >= 0 && y < WIDTH;
    }

    int getHEIGHT(){
        return HEIGHT;
    }

    String getName() {
        return name;
    }

    ImageView getImageView1() {
        return imageView1;
    }

    void setImageVisibility(boolean visibility){ //Damit Bild nicht direkt angezeigt wird, erst später dann
        imageView1.setVisible(visibility);
        covered = visibility;
    }

    boolean isCovered() {
        return covered;
    }

    @Override
    public boolean equals(Object o) { //Vergleich zweier Objekte
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return covered == board.covered && name.equals(board.name) && rows.equals(board.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(HEIGHT, WIDTH, name, covered, wave1, imageView1, rows);
    }
}


