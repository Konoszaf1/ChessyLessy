package model.piece;

public class Queen extends Piece{
    private static final String blackQueenPath = "queen_black.png";
    private static final String whiteQueenPath = "queen_white.png";

    public Queen(PieceColor color){
        super(color,PieceType.QUEEN);
        this.img = this.loadImage(blackQueenPath, whiteQueenPath);

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
