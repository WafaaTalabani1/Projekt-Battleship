package at.ac.fhcampuswien.battleship.game;

import java.util.ArrayList;

class Player {

    private final String name;
    private final Board placingBoard;
    private final Board attackingBoard;
    private boolean turn;
    private final ArrayList<Ship> ships;
    private int shipsToPlace;//schaut wie viele Ships von den PLayern schon gesetzt werden
    private int shipSize;//schaut auf die Größe der platziereten Ships
    private boolean vertical = true;

    Player(String name, int shipsToPlace) { //Konstruktor der die Instanzvariablen den Parameter zuweist
        this.name = name;
        if (shipsToPlace > 0) {
            this.shipsToPlace = shipsToPlace;
        } else {
            throw new IllegalArgumentException("Player has to place at least one Ship!");
        }
        placingBoard = new Board(name); //Das Board wo man die Schiffe platziert
        attackingBoard = new Board(name);//Das Board wo rauf man die Schiffe sucht
        ships = new ArrayList<>(); //die Liste die unsere Ships beinhaltet
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
