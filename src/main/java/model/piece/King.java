package model.piece;

public class King extends Piece {
    private static final String blackKingPath = "king_black.png";
    private static final String whiteKingPath = "king_white.png";

    public King(PieceColor color) {
        super(color, PieceType.KING);
        this.setImage(this.loadImage(blackKingPath, whiteKingPath));
    }
}
