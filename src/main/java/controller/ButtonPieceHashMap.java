package controller;

import java.util.concurrent.ConcurrentHashMap;
import view.button.ChessBoardButton;
import model.piece.Piece;

public class ButtonPieceHashMap extends ConcurrentHashMap<ChessBoardButton,Piece> {


    public ButtonPieceHashMap(){
        super(64); // Number of Chessboard tiles
    }
    public boolean setButtonPiecePair(ChessBoardButton button, Piece piece){
        if (this.containsKey(button)){
            return false;
        }
        this.put(button,piece);
        return true;
    }
    public Piece getPieceByButton(ChessBoardButton button){
        return this.get(button);
    }
    public ChessBoardButton getButtonByPiece(Piece piece){
        for (Entry<ChessBoardButton, Piece> entry : this.entrySet()){
            if (entry.equals(piece)){
                return entry.getKey();
            }
        }
        return null;
    }
}
