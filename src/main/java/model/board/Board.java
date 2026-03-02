package model.board;

import model.piece.*;
import utils.CharUtilFuncs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final Piece[][] grid = new Piece[8][8];
    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();

    public Board(PieceColor playerColor) {
        this.createPieces();
        this.placePiecesStartingPosition(playerColor);
    }

    private void createPieces() {
        for (int i = 0; i < 8; i++) {
            whitePieces.add(new Pawn(PieceColor.WHITE));
            blackPieces.add(new Pawn(PieceColor.BLACK));
        }
        for (int i = 0; i < 2; i++) {
            whitePieces.add(new Rook(PieceColor.WHITE));
            blackPieces.add(new Rook(PieceColor.BLACK));
            whitePieces.add(new Knight(PieceColor.WHITE));
            blackPieces.add(new Knight(PieceColor.BLACK));
            whitePieces.add(new Bishop(PieceColor.WHITE));
            blackPieces.add(new Bishop(PieceColor.BLACK));
        }
        whitePieces.add(new Queen(PieceColor.WHITE));
        blackPieces.add(new Queen(PieceColor.BLACK));
        whitePieces.add(new King(PieceColor.WHITE));
        blackPieces.add(new King(PieceColor.BLACK));
    }

    public List<Piece> getAllPieces() {
        List<Piece> all = new ArrayList<>(whitePieces);
        all.addAll(blackPieces);
        return all;
    }

    public List<Piece> getActivePieces(PieceColor color) {
        List<Piece> source = (color == PieceColor.WHITE) ? whitePieces : blackPieces;
        List<Piece> active = new ArrayList<>();
        for (Piece p : source) {
            if (p.isPlaced()) {
                active.add(p);
            }
        }
        return active;
    }

    public Piece getPieceAt(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return null;
        return grid[row][col];
    }

    public Piece getPieceAt(Position pos) {
        if (pos == null) return null;
        return getPieceAt(pos.getRow(), pos.getColIndex());
    }

    public boolean isEmpty(int row, int col) {
        return getPieceAt(row, col) == null;
    }

    public boolean isEmpty(Position pos) {
        return getPieceAt(pos) == null;
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPieceAt(from);
        if (piece == null) {
            throw new IllegalArgumentException("No piece at " + from);
        }

        // Remove captured piece if any
        Piece captured = getPieceAt(to);
        if (captured != null) {
            removePiece(captured);
        }

        // Update grid
        grid[from.getRow()][from.getColIndex()] = null;
        grid[to.getRow()][to.getColIndex()] = piece;
        piece.setPosition(to.getRow(), to.getCol());
    }

    public void removePiece(Piece piece) {
        if (piece.getRow() != null && piece.getCol() != null) {
            grid[piece.getRow()][piece.getCol().ordinal()] = null;
        }
        piece.setPosition(-1, null); // Mark as captured/off-board
        whitePieces.remove(piece);
        blackPieces.remove(piece);
    }

    public King findKing(PieceColor color) {
        List<Piece> pieces = (color == PieceColor.WHITE) ? whitePieces : blackPieces;
        for (Piece p : pieces) {
            if (p.getType() == PieceType.KING && p.isPlaced()) {
                return (King) p;
            }
        }
        return null;
    }

    // --- FEN parsing (preserved from original) ---

    public void placePiecesStartingPosition(PieceColor playerColor) {
        FEN startingFEN = playerColor == PieceColor.WHITE ? FEN.START_POS_WHITE : FEN.START_POS_BLACK;
        this.parseFEN(startingFEN);
    }

    private void parseFEN(FEN fen) {
        int currentRow = -1;
        List<String> rows = Arrays.asList(fen.getString().split("/"));
        for (String rowStr : rows) {
            BoardColumn currentColumn = BoardColumn.A;
            currentRow += 1;
            for (char character : rowStr.toCharArray()) {
                if (CharUtilFuncs.isNumeric(character)) {
                    currentColumn = currentColumn.incrementColumnByInt(character);
                } else if (CharUtilFuncs.isCapitalAlpha(character)) {
                    placePieceFromFEN(PieceColor.WHITE, character, currentRow, currentColumn);
                    currentColumn = currentColumn.getNextColumn();
                } else if (CharUtilFuncs.isNonCapitalAlpha(character)) {
                    placePieceFromFEN(PieceColor.BLACK, character, currentRow, currentColumn);
                    currentColumn = currentColumn.getNextColumn();
                }
            }
        }
    }

    private void placePieceFromFEN(PieceColor color, char character, int row, BoardColumn col) {
        PieceType type = PieceType.getType(character);
        if (type == null) return;

        List<Piece> pieces = (color == PieceColor.WHITE) ? whitePieces : blackPieces;
        for (Piece piece : pieces) {
            if (piece.getType() == type && !piece.isPlaced()) {
                piece.setPosition(row, col);
                grid[row][col.ordinal()] = piece;
                return;
            }
        }
    }

    public List<Piece> getPieces(PieceColor color, PieceType type) {
        List<Piece> result = new ArrayList<>();
        for (Piece p : getAllPieces()) {
            boolean colorMatch = (color == null || p.getColor() == color);
            boolean typeMatch = (type == null || p.getType() == type);
            if (colorMatch && typeMatch) {
                result.add(p);
            }
        }
        return result;
    }

    public Piece[][] getGrid() {
        return grid;
    }
}
