package at.ac.fhcampuswien.battleship.game;

import java.util.ArrayList;

class Player {

    private String name;
    private Board placingBoard;
    private Board attackingBoard;
    private boolean turn;

    private ArrayList<Ship> ships;

    /*to keep track of how many ships each player has already placed*/
    private int shipsToPlace;

    /*to keep track of the size of each ship the player places*/
    private int shipSize;
    private boolean vertical = true;

    Player(String name, int shipsToPlace){
        this.name  = name;
        if (shipsToPlace > 0){
            this.shipsToPlace = shipsToPlace;
        }else{
            throw new IllegalArgumentException("Player has to place at least one Ship!");
        }
        placingBoard = new Board(name);
        attackingBoard = new Board(name);
        ships = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    Board getPlacingBoard() {
        return placingBoard;
    }

    Board getAttackingBoard() {
        return attackingBoard;
    }

    ArrayList<Ship> getShips() {
        return ships;
    }

    int getShipsToPlace() {
        return shipsToPlace;
    }

    void setShipsToPlace(int shipsToPlace) {
        this.shipsToPlace = shipsToPlace;
    }

    boolean isTurn() {
        return turn;
    }

    void setTurn(boolean turn) {
        this.turn = turn;
    }


    int getShipSize() {
        return shipSize;
    }

    void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    boolean isVertical() {
        return vertical;
    }

    void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
}
