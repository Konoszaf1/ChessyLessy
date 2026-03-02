package controller;

import model.GamePhase;
import model.GameState;
import model.board.Board;
import model.board.MoveValidator;
import model.board.Position;
import model.piece.Piece;
import model.piece.PieceColor;
import view.MainWindow;
import view.button.ButtonStatus;
import view.button.ChessBoardButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GameController implements ActionListener {

    private static final Logger log = Logger.getLogger(GameController.class.getName());
    private static GameController instance;

    private MainWindow mainWindow;
    private Board board;
    private GameState gameState;
    private MoveValidator moveValidator;
    private ButtonPieceHashMap buttonPiecesMap;

    // Selection state
    private ChessBoardButton selectedButton = null;
    private Piece selectedPiece = null;
    private List<Position> currentLegalMoves = new ArrayList<>();

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void startGame() {
        // Step 1: Ask the player to pick a color (modal, blocks until chosen)
        PieceColor playerColor = askPlayerColor();

        // Step 2: Build the board model
        this.board = new Board(playerColor);
        this.moveValidator = new MoveValidator(board);
        this.gameState = new GameState(board, playerColor);
        this.buttonPiecesMap = new ButtonPieceHashMap();

        // Step 3: Create window, render pieces, attach click listeners
        this.mainWindow = MainWindow.getInstance();
        this.mainWindow.setButtonImages(this.board, this.buttonPiecesMap);
        attachButtonListeners();
        this.mainWindow.setVisible(true);

        log.info("Game started. Player is " + playerColor + ". White moves first.");
    }

    private PieceColor askPlayerColor() {
        new DialogController(this);
        return this.playerColor;
    }

    private PieceColor playerColor;

    public void setPlayerColor(PieceColor playerColor) {
        this.playerColor = playerColor;
    }

    private void attachButtonListeners() {
        ChessBoardButton[][] buttons = mainWindow.getAllButtons();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                buttons[row][col].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState.isGameOver()) return;

        ChessBoardButton clickedButton = (ChessBoardButton) e.getSource();
        Piece clickedPiece = buttonPiecesMap.getPieceByButton(clickedButton);

        if (selectedButton == null) {
            // No piece selected yet — try to select one
            handleSelection(clickedButton, clickedPiece);
        } else {
            // A piece is already selected
            Position clickedPos = new Position(clickedButton.getRow(), clickedButton.getColumn());

            if (currentLegalMoves.contains(clickedPos)) {
                // Execute the move
                executeMove(clickedButton, clickedPos);
            } else if (clickedPiece != null && clickedPiece.getColor() == gameState.getCurrentTurn()) {
                // Clicked a different friendly piece — switch selection
                clearHighlights();
                handleSelection(clickedButton, clickedPiece);
            } else {
                // Clicked an invalid square — deselect
                clearHighlights();
                clearSelection();
            }
        }
    }

    private void handleSelection(ChessBoardButton button, Piece piece) {
        if (piece == null || piece.getColor() != gameState.getCurrentTurn()) {
            return; // Can only select own pieces on own turn
        }

        List<Position> legalMoves = moveValidator.getLegalMoves(piece);
        if (legalMoves.isEmpty() && piece.getType() != model.piece.PieceType.KING) {
            // Piece has no legal moves — still select it but show no highlights
        }

        selectedButton = button;
        selectedPiece = piece;
        currentLegalMoves = legalMoves;

        // Highlight selected square
        button.highlight(ChessBoardButton.HIGHLIGHT_SELECTED);
        button.setStatus(ButtonStatus.CLICKED);

        // Highlight legal move targets
        for (Position pos : legalMoves) {
            ChessBoardButton targetBtn = mainWindow.getButton(pos.getRow(), pos.getCol());
            Piece targetPiece = board.getPieceAt(pos);
            if (targetPiece != null && targetPiece.getColor() != piece.getColor()) {
                targetBtn.highlight(ChessBoardButton.HIGHLIGHT_CAPTURE);
                targetBtn.setStatus(ButtonStatus.CAPTURABLE);
            } else {
                targetBtn.highlight(ChessBoardButton.HIGHLIGHT_MOVE);
                targetBtn.setStatus(ButtonStatus.TARGETED);
            }
        }
    }

    private void executeMove(ChessBoardButton targetButton, Position targetPos) {
        Position fromPos = new Position(selectedPiece.getRow(), selectedPiece.getCol());
        Piece captured = board.getPieceAt(targetPos);

        // Record the move
        gameState.recordMove(selectedPiece, fromPos, targetPos, captured);

        // Update button-piece map
        buttonPiecesMap.removePieceFromButton(selectedButton);
        if (captured != null) {
            buttonPiecesMap.removePieceFromButton(targetButton);
        }

        // Move on the board model
        board.movePiece(fromPos, targetPos);

        // Update UI
        clearHighlights();
        mainWindow.clearButtonImage(selectedButton);
        mainWindow.setButtonImage(targetButton, selectedPiece.getImage());
        buttonPiecesMap.setButtonPiecePair(targetButton, selectedPiece);

        // Check for pawn promotion (auto-promote to queen for MVP)
        handlePawnPromotion(selectedPiece, targetPos);

        clearSelection();

        // Switch turns
        gameState.switchTurn();
        log.info("Move: " + fromPos + " -> " + targetPos
                + (captured != null ? " (captured " + captured.getType() + ")" : "")
                + ". Turn: " + gameState.getCurrentTurn());

        // Check game state
        checkGameState();
    }

    private void handlePawnPromotion(Piece piece, Position pos) {
        if (piece.getType() != model.piece.PieceType.PAWN) return;
        int promotionRow = (piece.getColor() == PieceColor.WHITE) ? 0 : 7;
        if (pos.getRow() == promotionRow) {
            // Auto-promote to queen for MVP
            Piece queen = new model.piece.Queen(piece.getColor());
            board.getGrid()[pos.getRow()][pos.getColIndex()] = queen;
            queen.setPosition(pos.getRow(), pos.getCol());
            board.removePiece(piece);

            // Update UI
            ChessBoardButton button = mainWindow.getButton(pos.getRow(), pos.getCol());
            mainWindow.setButtonImage(button, queen.getImage());
            buttonPiecesMap.setButtonPiecePair(button, queen);

            log.info("Pawn promoted to Queen at " + pos);
        }
    }

    private void checkGameState() {
        PieceColor currentTurn = gameState.getCurrentTurn();

        if (moveValidator.isCheckmate(currentTurn)) {
            gameState.setPhase(GamePhase.CHECKMATE);
            PieceColor winner = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
            log.info("CHECKMATE! " + winner + " wins!");
            highlightKingInCheck(currentTurn);
            JOptionPane.showMessageDialog(mainWindow,
                    "Checkmate! " + winner + " wins!",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (moveValidator.isStalemate(currentTurn)) {
            gameState.setPhase(GamePhase.STALEMATE);
            log.info("STALEMATE! Game is a draw.");
            JOptionPane.showMessageDialog(mainWindow,
                    "Stalemate! The game is a draw.",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (moveValidator.isInCheck(currentTurn)) {
            gameState.setPhase(GamePhase.CHECK);
            log.info(currentTurn + " is in CHECK!");
            highlightKingInCheck(currentTurn);
        } else {
            gameState.setPhase(GamePhase.PLAYING);
        }
    }

    private void highlightKingInCheck(PieceColor color) {
        Piece king = board.findKing(color);
        if (king != null && king.isPlaced()) {
            ChessBoardButton kingButton = mainWindow.getButton(king.getRow(), king.getCol());
            kingButton.highlight(ChessBoardButton.HIGHLIGHT_CHECK);
        }
    }

    private void clearHighlights() {
        ChessBoardButton[][] buttons = mainWindow.getAllButtons();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                buttons[row][col].resetColor();
            }
        }
    }

    private void clearSelection() {
        selectedButton = null;
        selectedPiece = null;
        currentLegalMoves.clear();
    }
}
