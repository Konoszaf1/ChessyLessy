package model.piece;

public enum PieceType {

    PAWN('p', 'P'),
    ROOK('r', 'R'),
    QUEEN('q', 'Q'),
    KING('k', 'K'),
    BISHOP('b', 'B'),
    KNIGHT('n', 'N');

    char fenNamingBlack;
    char fenNamingWhite;
    PieceType(char fenNamingBlack, char fenNamingWhite) {
        this.fenNamingBlack = fenNamingBlack;
        this.fenNamingWhite = fenNamingWhite;
    }

    public char getFenNamingBlack() {
        return fenNamingBlack;
    }

    public char getFenNamingWhite() {
        return fenNamingWhite;
    }

    public static PieceType getType(char character){
        for (PieceType type : PieceType.values()){
            if (character == type.getFenNamingBlack() || character == type.getFenNamingWhite()){
                return type;
            }
        }
        return null;
    }
}
