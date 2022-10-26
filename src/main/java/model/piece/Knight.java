package model.piece;

public class Knight extends Piece{
    private static final String blackKnightPath = "knight_black.png";
    private static final String whiteKnightPath = "knight_white.png";

    public Knight(PieceColor color){
        super(color,PieceType.KNIGHT);
        this.img = this.loadImage(blackKnightPath, whiteKnightPath);

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
