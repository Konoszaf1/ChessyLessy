package model.piece;

public class Rook extends Piece {
    private static final String blackRookPath = "rook_black.png";
    private static final String whiteRookPath = "rook_white.png";

    public Rook(PieceColor color) {
        super(color, PieceType.ROOK);
        this.setImage(this.loadImage(blackRookPath, whiteRookPath));
    }
}
