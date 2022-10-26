package model.piece;

public class Pawn extends Piece{

    private static final String blackPawnPath = "pawn_black.png";
    private static final String whitePawnPath = "pawn_white.png";

    public Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
        this.img = this.loadImage(blackPawnPath, whitePawnPath);
    }

    @Override
    protected void move() {
        // TODO: Implement movement functionality
    }

    @Override
    protected void getAvailableMoves() {
        // TODO: Implement checking available moves.
    }
}
