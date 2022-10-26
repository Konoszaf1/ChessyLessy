package model.piece;

public class Rook extends Piece{

    private static final String blackRookPath = "rook_black.png";
    private static final String whiteRookPath = "rook_white.png";

    public Rook(PieceColor color) {
        super(color, PieceType.ROOK);
        this.img = this.loadImage(blackRookPath, whiteRookPath);
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
