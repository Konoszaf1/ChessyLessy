package model.piece;

public class King extends Piece{
    private static final String blackKingPath = "king_black.png";
    private static final String whiteKingPath = "king_white.png";

    public King(PieceColor color){
        super(color,PieceType.KING);
        this.img = this.loadImage(blackKingPath, whiteKingPath);

    }
    @Override
    protected void move() {

    }

    @Override
    protected void getAvailableMoves() {

    }
}
