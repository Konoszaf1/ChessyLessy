package controller;

import model.board.Board;
import view.MainWindow;

public class GameController {

    private MainWindow mainWindow;
    private Board board;
    private static final GameController INSTANCE = new GameController();
    private ButtonPieceHashMap buttonPiecesMap;

    private GameController(){
        // Disable windows application scaling
        System.setProperty("sun.java2d.uiScale", "1.0");
        this.initialize();
    }
    public static GameController getInstance(){
        return INSTANCE;
    }

    private void initialize(){
        this.board = new Board();
        this.buttonPiecesMap = new ButtonPieceHashMap();
        this.mainWindow = MainWindow.getInstance();
        this.mainWindow.setButtonImages(this.board, this.buttonPiecesMap);
    }

    public void startGame(){
        this.mainWindow.setVisible(true);
    }
}
