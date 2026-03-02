package view;

import controller.ButtonPieceHashMap;
import model.board.BoardColumn;
import model.board.Board;
import model.piece.Piece;
import view.button.ChessBoardButton;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static MainWindow instance;
    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final ChessBoardButton[][] buttons = new ChessBoardButton[8][8];

    private MainWindow() {
        super("ChessyLessy");
        this.initialize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    private void initialize() {
        this.setLayout(gridBagLayout);
        this.setSize(800, 800);
        this.setResizable(true);
        this.setupButtons();
    }

    private void setupButtons() {
        for (int row = 0; row < 8; row++) {
            for (BoardColumn col : BoardColumn.values()) {
                Color color = (col.ordinal() + row) % 2 == 0
                        ? ChessBoardButton.LIGHT
                        : ChessBoardButton.DARK;
                ChessBoardButton button = new ChessBoardButton(color, row, col);
                buttons[row][col.ordinal()] = button;
                gridBagLayout.setConstraints(button, button.getGbc());
                this.add(button);
            }
        }
    }

    public void setButtonImages(Board board, ButtonPieceHashMap buttonPieceMap) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessBoardButton button = buttons[row][col];
                for (Piece piece : board.getAllPieces()) {
                    if (piece.getRow() != null && piece.getCol() != null
                            && button.getRow() == piece.getRow()
                            && button.getColumn() == piece.getCol()) {
                        setButtonImage(button, piece.getImage());
                        buttonPieceMap.setButtonPiecePair(button, piece);
                    }
                }
            }
        }
    }

    public void setButtonImage(ChessBoardButton button, Image image) {
        if (image != null) {
            button.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
        } else {
            button.setIcon(null);
        }
    }

    public void clearButtonImage(ChessBoardButton button) {
        button.setIcon(null);
    }

    public ChessBoardButton getButton(int row, BoardColumn col) {
        return buttons[row][col.ordinal()];
    }

    public ChessBoardButton[][] getAllButtons() {
        return buttons;
    }
}
