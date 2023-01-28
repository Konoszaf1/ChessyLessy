package model.board;

public class FEN {
    public static final FEN START_POS_WHITE = new FEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    //public static final FEN START_POS_WHITE = new FEN("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R");
    public static final FEN START_POS_BLACK = new FEN("RNBQKBNR/PPPPPPPP/8/8/8/8/pppppppp/rnbqkbnr");
    String string = new String();

    public FEN(String newString){
        this.string = newString;
    }
    public String getString(){
        return this.string;
    }
}
