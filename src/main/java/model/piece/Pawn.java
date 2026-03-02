package model.piece;

public class Pawn extends Piece {
    private static final String blackPawnPath = "pawn_black.png";
    private static final String whitePawnPath = "pawn_white.png";

    public Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
        this.setImage(this.loadImage(blackPawnPath, whitePawnPath));
    }
}
