package at.ac.fhcampuswien.battleship.game;

import javafx.scene.Parent;

import java.util.ArrayList;

class Ship extends Parent {

    private final int shipSize;//Länger der Ships
    private final boolean vertical;//wie Ship plaziert wird
    private final ArrayList<BoardCell> shipBoardCells;//Liste, die die Cell beinhaltet, wo die Ships plaziert wurden
    private int health;//Health vom Ship

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
