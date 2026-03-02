package controller;

import java.util.concurrent.ConcurrentHashMap;
import view.button.ChessBoardButton;
import model.piece.Piece;

public class ButtonPieceHashMap extends ConcurrentHashMap<ChessBoardButton, Piece> {

    public ButtonPieceHashMap() {
        super(64);
    }

    public void setButtonPiecePair(ChessBoardButton button, Piece piece) {
        this.put(button, piece);
    }

    public Piece getPieceByButton(ChessBoardButton button) {
        return this.get(button);
    }

    public ChessBoardButton getButtonByPiece(Piece piece) {
        for (Entry<ChessBoardButton, Piece> entry : this.entrySet()) {
            if (entry.getValue().equals(piece)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void removePieceFromButton(ChessBoardButton button) {
        this.remove(button);
    }
}
