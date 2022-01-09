package at.ac.fhcampuswien.battleship.game;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Board extends Parent {
    private final int HEIGHT = 10;
    private final int WIDTH = 10;
    private final String name;
    private boolean covered;
    private Image wave1;
    private ImageView imageView1;
    private final VBox rows = new VBox();


    Board(String name) {
        this.name = name;
        //giving the board shadow effect
        this.setStyle("-fx-effect: dropshadow(three-pass-box, chartreuse, 20, 0, 0, 15);");
        initializeImageView();
        for (int y = 0; y < HEIGHT; y++) {
            HBox row = new HBox();
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

    BoardCell getCell(int x, int y) {                // auf einzelne Zellen zugreifen??
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

    void setImageVisibility(boolean visibility){
        imageView1.setVisible(visibility);
        covered = visibility;
    }

    boolean isCovered() {
        return covered;
    }

    @Override
    public boolean equals(Object o) {
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


