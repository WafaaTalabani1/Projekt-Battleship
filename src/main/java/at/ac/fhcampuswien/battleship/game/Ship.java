package at.ac.fhcampuswien.battleship.game;

import javafx.scene.Parent;

import java.util.ArrayList;

class Ship extends Parent {
    /*length of ship*/
    private final int shipSize;
    /*orientation of ship*/
    private final boolean vertical;
    /*list that holds the cells on which the ship exists*/
    private final ArrayList<BoardCell> shipBoardCells;
    private int health;

    Ship(int shipSize, boolean vertical) {
        this.shipSize = shipSize;
        this.vertical = vertical;
        shipBoardCells = new ArrayList<>();
        health = shipSize;
    }

    int getShipSize() {
        return shipSize;
    }

    boolean isVertical() {
        return vertical;
    }

    ArrayList<BoardCell> getShipCells() {
        return shipBoardCells;
    }

    void hit() {
        health--;
    }

    boolean isAlive() {
        return health > 0;
    }

}
