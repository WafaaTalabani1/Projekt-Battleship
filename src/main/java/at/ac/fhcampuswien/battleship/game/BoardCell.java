package at.ac.fhcampuswien.battleship.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

class BoardCell extends Rectangle {
    private final int x;
    private final int y;
    private boolean wasShot = false;
    private boolean occupied;
    private final Board board; //parent of Cell

    BoardCell(int x, int y, Board board) {   // Cell Constructor
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setStyle("-fx-fill: #317f43");
        setStroke(Color.BLACK);
    }

    /**
     * method will be called when the mouse pointer enters a cell on the placing board.
     * if player can place a ship starting from this cell the ship color will be green
     * otherwise red
     * */
    void enteredDetectPlacingBoard(Game game){
        this.setOnMouseEntered(mouseEvent -> {
            if (game.getActivePlayer() != null) {
                Player currentPlayer = game.getActivePlayer();
                if (currentPlayer.getShipsToPlace() > 0){
                    BoardCell currentBoardCell = (BoardCell) mouseEvent.getSource();
                    /*if statement to allow each player to place ships only on his placing board*/
                    if (currentBoardCell.board.getName().equals(currentPlayer.getPlacingBoard().getName())){
                        Ship ship = game.createShip(currentPlayer.getShipSize(), currentPlayer.isVertical());
                        /*change the color only for the unoccupied cells*/
                        if (!currentBoardCell.isOccupied())
                            //First color is light green, second is Red
                            game.changeColorOnHover(currentPlayer, ship, this, "-fx-fill: #6FB98F", "-fx-fill: #8D230F");
                    }
                }
            }
        });
    }

    /**
     * method will be called when the mouse pointer exits a cell on the placing board.
     * this is needed to bring the board its original color back (Aqua) after it was changed by
     * the enteredDetectPlacingBoard
     * */
    void exitedDetectPlacingBoard(Game game){
        this.setOnMouseExited(mouseEvent -> {
            if (game.getActivePlayer() != null) {
                Player currentPlayer = game.getActivePlayer();
                if (currentPlayer.getShipsToPlace() > 0){
                    BoardCell currentBoardCell = (BoardCell) mouseEvent.getSource();
                    if (currentBoardCell.board.getName().equals(currentPlayer.getPlacingBoard().getName())){
                        Ship ship = game.createShip(currentPlayer.getShipSize(), currentPlayer.isVertical()); // das sollte dynamisch sein!
                        game.changeColorOnHover(currentPlayer, ship, currentBoardCell, "-fx-fill: #317f43", "-fx-fill: #317f43");
                    }
                }
            }
        });
    }

    /**
     * method will be called when the mouse pointer clicks on a cell on the placing board.
     *
     * */
    void clickDetectOnPlacingBoard(Game game){
        this.setOnMouseClicked(mouseEvent -> {
            if (game.getActivePlayer() != null){
                Player currentPlayer = game.getActivePlayer();
                Player secondPlayer = game.getTheSecondPlayer();
                if (currentPlayer.getShipsToPlace() > 0 ){
                    BoardCell currentBoardCell = (BoardCell) mouseEvent.getSource();
                    if (currentBoardCell.board.getName().equals(currentPlayer.getPlacingBoard().getName())){//?
                        Ship ship = game.createShipForPlayer(currentPlayer,currentPlayer.getShipSize(), currentPlayer.isVertical());
                        if (game.canPlaceShip__(currentPlayer,ship, currentBoardCell)){
                            game.placeShipForPlayer(currentPlayer,ship, currentBoardCell);
                        }
                        if (currentPlayer.getShipsToPlace() == 0){//??
                            if (secondPlayer.getShipsToPlace() == 0){
                                game.removeButtons();
                            }
                            currentPlayer.getPlacingBoard().setImageVisibility(true);
                            game.newActivePlayer();
                            game.showTurn();
                            game.setButtonToVertical();
                        }
                    }
                }
            }
        });
    }

    void clickDetectOnAttackingBoard(Game game){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (game.getActivePlayer() != null){
                    Player currentPlayer = game.getActivePlayer();
                    Player secondPlayer = game.getTheSecondPlayer();
                    if (currentPlayer.getPlacingBoard().isCovered() && game.getTheSecondPlayer().getPlacingBoard().isCovered()){
                        BoardCell currentBoardCell = (BoardCell) mouseEvent.getSource();
                        // if statement ist negiert weil wenn player 1 dran ist, player2 board muss angegrifen werden
                        if (!currentBoardCell.board.getName().equals(currentPlayer.getPlacingBoard().getName())){
                            if (!currentBoardCell.isWasShot()){
                                boolean b = game.hit(secondPlayer, currentBoardCell);
                                System.out.println(currentPlayer.getName() + " ist dran");
                                if (!b) {
                                    game.newActivePlayer();
                                    game.showTurn();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    int getx(){
        return x;
    }

    int gety(){
        return y;
    }

    boolean isOccupied() {
        return occupied;
    }

    void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    private boolean isWasShot() {
        return wasShot;
    }

    void setWasShot(boolean wasShot) {
        this.wasShot = wasShot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardCell boardCell = (BoardCell) o;
        return x == boardCell.x &&
                y == boardCell.y &&
                wasShot == boardCell.wasShot &&
                occupied == boardCell.occupied &&
                board.equals(boardCell.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, wasShot, occupied, board);
    }
}
