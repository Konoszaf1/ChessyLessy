package model.board;

public class FEN {
    public static final FEN START_POS_WHITE = new FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    public static final FEN START_POS_BLACK = new FEN("RNBQKBNR/PPPPPPPP/8/8/8/8/pppppppp/rnbqkbnr");

    private final String string;

    public FEN(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
