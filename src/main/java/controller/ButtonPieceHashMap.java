package controller;

import java.util.concurrent.ConcurrentHashMap;
import view.button.Button;
import model.piece.Piece;

public class ButtonPieceHashMap extends ConcurrentHashMap<Button,Piece> {


    public ButtonPieceHashMap(){
        super(64); // Number of Chessboard tiles
    }
    public boolean setButtonPiecePair(Button button, Piece piece){
        if (this.containsKey(button)){
            return false;
        }
        this.put(button,piece);
        return true;
    }
    public Piece getPieceByButton(Button button){
        return this.get(button);
    }
    public Button getButtonByPiece(Piece piece){
        for (Entry<Button, Piece> entry : this.entrySet()){
            if (entry.equals(piece)){
                return entry.getKey();
            }
        }
        return null;
    }
}
