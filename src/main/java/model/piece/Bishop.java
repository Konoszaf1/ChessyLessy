package model.piece;

public class Bishop extends Piece{
    private static final String blackBishopPath = "bishop_black.png";
    private static final String whiteBishopPath = "bishop_white.png";

    public Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
        this.img = this.loadImage(blackBishopPath, whiteBishopPath);
    }
    @Override
    protected void move() {

    }

    @Override
    protected void getAvailableMoves() {

    }
}
