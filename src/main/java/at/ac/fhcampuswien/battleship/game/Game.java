package at.ac.fhcampuswien.battleship.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Game {

    private final BorderPane root = new BorderPane();
    private final Stage primaryStage;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private int gameCount;
    private Text player1Text;
    private Text player2Text;
    private RadioButton vertical;
    private HBox hbox;

    public Game(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public void createMyGame() {
        root.setStyle("-fx-background-color: #021C1E");
        player1 = new Player("Player 1", 10);
        player2 = new Player("Player 2", 10);

        Board player1PlacingBoard = player1.getPlacingBoard();
        Board player1AttackingBoard = player1.getAttackingBoard();

        Board player2PlacingBoard = player2.getPlacingBoard();
        Board player2AttackingBoard = player2.getAttackingBoard();

        StackPane coverBoard1 = coveredBoard(player1.getPlacingBoard());
        StackPane coverBoard2 = coveredBoard(player2.getPlacingBoard());


        /*Adding the event handler to all cells of both players' boards*/
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                player1PlacingBoard.getCell(i, j).clickDetectOnPlacingBoard(this);
                player1PlacingBoard.getCell(i, j).enteredDetectPlacingBoard(this);
                player1PlacingBoard.getCell(i, j).exitedDetectPlacingBoard(this);
                player1AttackingBoard.getCell(i, j).clickDetectOnAttackingBoard(this);

                player2PlacingBoard.getCell(i, j).clickDetectOnPlacingBoard(this);
                player2PlacingBoard.getCell(i, j).enteredDetectPlacingBoard(this);
                player2PlacingBoard.getCell(i, j).exitedDetectPlacingBoard(this);
                player2AttackingBoard.getCell(i, j).clickDetectOnAttackingBoard(this);
            }
        }

        if (gameCount % 2 == 0) {        // Bedingung die bestimmt, welcher Spieler nach dem Neustart dran ist
            activePlayer = player1;     // (immer abwechseld player1 dann player2)
            player1.setTurn(true);
        } else {
            activePlayer = player2;
            player2.setTurn(true);
        }


        hbox = new HBox(60, shipSizeSlider(), radioButtons());
        hbox.setAlignment(Pos.CENTER);
        root.setCenter(hbox);

        player1Text = new Text(player1.getName());
        player1Text.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        player2Text = new Text(player2.getName());
        player2Text.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        HBox hbox1 = new HBox(20, coverBoard2, player1.getAttackingBoard(), player2Text);
        hbox1.setAlignment(Pos.TOP_CENTER);
        hbox1.setPadding(new Insets(50, 100, 20, 50));

        HBox hbox2 = new HBox(20, coverBoard1, player2.getAttackingBoard(), player1Text);
        hbox2.setAlignment(Pos.TOP_CENTER);
        hbox2.setPadding(new Insets(10, 100, 20, 50));

        root.setPrefSize(500, 750);
        root.setTop(hbox1);
        root.setBottom(hbox2);
        showTurn();

    }

    private VBox radioButtons() {
        vertical = new RadioButton("Vertical");
        vertical.setStyle("-fx-padding: 5; -fx-text-fill:#6FB98F; -fx-font-size: 12");

        /*making the ships vertical when the game starts*/
        vertical.setSelected(true);
        activePlayer.setVertical(true);

        RadioButton horizontal = new RadioButton("Horizontal");
        horizontal.setStyle("-fx-padding: 5; -fx-text-fill:#6FB98F; -fx-font-size: 12");

        VBox vBox = new VBox(10, vertical, horizontal);
        ToggleGroup toggleGroup = new ToggleGroup();

        // adding the radio buttons to the toggle group
        vertical.setToggleGroup(toggleGroup);
        horizontal.setToggleGroup(toggleGroup);

        //adding the listener ?
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> activePlayer.setVertical(!newVal.toString().contains("Horizontal")));
        return vBox;
    }

    /**
     * Method that creates the slider and adds a listener to assign
     * the changes to shipSize variable in the Player Object
     */
    private Slider shipSizeSlider() {
        Slider slider = new Slider();
        slider.setMin(1);
        slider.setMax(5);
        slider.setValue(3);
        slider.setMajorTickUnit(1);//abstand zwischen tick Marks
        slider.setShowTickLabels(true);

        slider.setStyle(" -fx-padding: 10px;-fx-font-size: 15;-fx-text-fill:#6FB98F ");

        slider.setBlockIncrement(1);
        player1.setShipSize((int) slider.getValue());
        player2.setShipSize((int) slider.getValue());
        //
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.doubleValue() >= 1.0 && newValue.doubleValue() <= 1.4) {//mit 1,5 wird aufgerundet
                player1.setShipSize(1);
                player2.setShipSize(1);
                System.out.println(newValue);
                System.out.println(player1.getShipSize());
            } else if (newValue.doubleValue() >= 1.5 && newValue.doubleValue() <= 2.4) {
                player1.setShipSize(2);
                player2.setShipSize(2);
                System.out.println(newValue);
                System.out.println(player1.getShipSize());
            } else if (newValue.doubleValue() >= 2.5 && newValue.doubleValue() <= 3.4) {
                player1.setShipSize(3);
                player2.setShipSize(3);
                System.out.println(newValue);
                System.out.println(player1.getShipSize());
            } else if (newValue.doubleValue() >= 3.5 && newValue.doubleValue() <= 4.4) {
                player1.setShipSize(4);
                player2.setShipSize(4);
                System.out.println(newValue);
                System.out.println(player1.getShipSize());
            } else if (newValue.doubleValue() >= 4.5 && newValue.doubleValue() <= 5) {
                player1.setShipSize(5);
                player2.setShipSize(5);
                System.out.println(newValue);
                System.out.println(player1.getShipSize());
            }

        });

        return slider;
    }


    private StackPane coveredBoard(Board boardToCover) {
        ImageView imageView1 = boardToCover.getImageView1();
        return new StackPane(boardToCover, imageView1);
    }

    void redrawContent() {
        root.getChildren().clear();
        gameCount++;
        createMyGame();
    }

    boolean canPlaceShip__(Player player, Ship ship, BoardCell cell) {
        int col = cell.getx();
        int row = cell.gety();

        /*Check to keep the ships inside the board*/
        if (row + ship.getShipSize() > player.getPlacingBoard().getHEIGHT() && ship.isVertical() ||
                (col + ship.getShipSize() > player.getPlacingBoard().getHEIGHT() && !ship.isVertical())) {
            return false;
        }
        if (ship.isVertical())
            return checkBasedOnOrientation(player, ship, col, row);
        if (!ship.isVertical())
            return checkBasedOnOrientation(player, ship, row, col);

        return false;
    }

    /**
     * This method is called to check if the cells are occupied or not
     */
    private boolean checkBasedOnOrientation(Player player, Ship ship, int x, int y) {
        for (int i = y; i < (y + ship.getShipSize()); i++) {
            if (player.getPlacingBoard().isValidPoint(x, i)) {
                BoardCell currentBoardCell;
                if (ship.isVertical()) currentBoardCell = player.getPlacingBoard().getCell(x, i);
                else currentBoardCell = player.getPlacingBoard().getCell(i, x);

                if (currentBoardCell.isOccupied())
                    return false;
            }
        }
        return true;
    }

    Ship createShip(int shipSize, boolean vertical) {
        return new Ship(shipSize, vertical);
    }

    Ship createShipForPlayer(Player player, int shipSize, boolean vertical) {
        Ship ship = new Ship(shipSize, vertical);
        player.getShips().add(ship);
        return ship;
    }


    void placeShipForPlayer(Player player, Ship ship, BoardCell cell) {
        int row = cell.getx();
        int col = cell.gety();

        if (ship.isVertical()) {
            for (int i = col; i < (col + ship.getShipSize()); i++) {
                managePlacing(player, ship, i, row);
            }
        } else {
            for (int i = row; i < (row + ship.getShipSize()); i++) {
                managePlacing(player, ship, col, i);
            }
        }
        player.setShipsToPlace(player.getShipsToPlace() - 1);
    }

    private void managePlacing(Player player, Ship ship, int col, int row) {
        BoardCell currentCell = player.getPlacingBoard().getCell(row, col);
        BoardCell currentCellOnAttackingBoard = player.getAttackingBoard().getCell(row, col);
        currentCell.setOccupied(true);
        currentCellOnAttackingBoard.setOccupied(true);
        ship.getShipCells().add(currentCellOnAttackingBoard);
        currentCell.setFill(Color.DARKGREEN);
    }


    /**
     * Method that gets called when a player the attacking board of the second player
     * if the cell is occupied the cell will be black otherwise cell will be gold
     * and the corresponding ship that contains this cell will be fetched from this
     * player ships!
     */
    boolean hit(Player player, BoardCell cell) {
        cell.setWasShot(true);
        if (!cell.isOccupied()) {
            cell.setFill(Color.CORNFLOWERBLUE);
            return false;
        } else {
            for (Ship ship : player.getShips()) {
                for (BoardCell currentCell : ship.getShipCells()) {
                    if (currentCell.equals(cell)) {
                        cell.setFill(Color.GOLDENROD);
                        ship.hit();
                        if (!ship.isAlive()) {
                            shipDestroyed(player, ship);
                        }
                        return true;
                    }
                }
            }
            return true;
        }
    }

    /**
     * Method that removes the destroyed ship from the player ships and
     * calls the game over function if all the ships of the player are destroyed
     *
     * @param player : the player whose ship got hit
     * @param ship   : the ship that got destroyed
     */
    private void shipDestroyed(Player player, Ship ship) {
        player.getShips().remove(ship);
        if (player.getShips().size() == 0) {
            gameOver(player);
        }
    }


    void newActivePlayer() {
        if (player1.isTurn()) {
            activePlayer = player2;
            player1.setTurn(false);
            player2.setTurn(true);
        } else {
            activePlayer = player1;
            player1.setTurn(true);
            player2.setTurn(false);
        }
    }

    /**
     * Method that makes the name of the current player green!
     */
    public void showTurn() {
        if (player1.isTurn()) {
            changeTextColor(player1Text, player2Text);
        } else {
            changeTextColor(player2Text, player1Text);
        }
    }

    private void changeTextColor(Text player1Text, Text player2Text) {
        player1Text.setFill(Color.GREEN);
        player1Text.setStyle("-fx-effect: dropshadow(three-pass-box, green, 30, 0, 0, 15);");
        player1Text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        player2Text.setFill(Color.BLACK);
        player2Text.setStyle("-fx-effect: dropshadow(three-pass-box, green, 0, 0, 0, 0);");
        player2Text.setFont(Font.font("Arial" , FontWeight.BOLD, 20));
    }


    private void gameOver(Player loser) {
        GameOver dialog;
        if (loser == player1) {
            dialog = new GameOver(player2.getName(), primaryStage);
        } else {
            dialog = new GameOver(player1.getName(), primaryStage);
        }
        dialog.drawDialog(this);
        dialog.showAndWait();
    }


    void changeColorOnHover(Player player, Ship ship, BoardCell cell, String color, String color1) {

        int col = cell.getx();
        int row = cell.gety();

        /*check to see if the ship fits in the specified place!
         * if so .. check to see if the ship is vertical or horizontal*/
        if (canPlaceShip__(player, ship, cell)) {
            // if the ship is vertical start counting from its starting row and down!
            if (ship.isVertical()) {
                for (int i = row; i < (row + ship.getShipSize()); i++) {
                    BoardCell currentCell = player.getPlacingBoard().getCell(col, i);
                    currentCell.setStyle(color);
                }
            }
            //if the ship is horizontal, start counting from its starting column and right!
            else {
                for (int i = col; i < (col + ship.getShipSize()); i++) {
                    BoardCell currentCell = player.getPlacingBoard().getCell(i, row);
                    currentCell.setStyle(color);
                }
            }
        }
        /*same process in case the ship does not fit on the board...
         * check if the ship is vertical or horizontal and give the cells red color accordingly  */
        else {
            if (ship.isVertical()) {
                for (int i = row; i < (row + ship.getShipSize()); i++) {
                    // try-catch block for the case when the cell is out of the board (>9)
                    try {
                        BoardCell currentCell = player.getPlacingBoard().getCell(col, i);
                        if (!currentCell.isOccupied())
                            currentCell.setStyle(color1);
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            } else {
                for (int i = col; i < (col + ship.getShipSize()); i++) {
                    try {
                        BoardCell currentCell = player.getPlacingBoard().getCell(i, row);
                        if (!currentCell.isOccupied())
                            currentCell.setStyle(color1);
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            }
        }
    }

    Player getActivePlayer() {
        return activePlayer;
    }


    public BorderPane getRoot() {
        return root;
    }

    Player getTheSecondPlayer() {
        if (activePlayer == player1) return player2;
        else return player1;
    }

    public void removeButtons() {
        root.getChildren().remove(hbox);
    }


    public Player getPlayer1() {
        return player1;
    }

    //
    public void setButtonToVertical() {
        vertical.setSelected(true);
    }
}
