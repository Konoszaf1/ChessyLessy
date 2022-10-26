package model.board;

import model.piece.*;

import java.util.ArrayList;

public class Board {

    private final ArrayList<Piece> whitePieces = new ArrayList<>();
    private final ArrayList<Piece> blackPieces = new ArrayList<>();

    public Board(){
        setupPieces();
    }
    private void setupPieces(){
        // Create all pieces instances on the board.
        for (int i=0; i<8; i++){
            whitePieces.add(new Pawn(PieceColor.WHITE));
            blackPieces.add(new Pawn(PieceColor.BLACK));
        }
        for (int i=0 ; i<2; i++){
            whitePieces.add(new Rook(PieceColor.WHITE));
            blackPieces.add(new Rook(PieceColor.BLACK));
            whitePieces.add(new Knight(PieceColor.WHITE));
            blackPieces.add(new Knight(PieceColor.BLACK));
            whitePieces.add(new Bishop(PieceColor.WHITE));
            blackPieces.add(new Bishop(PieceColor.BLACK));
        }
        whitePieces.add(new Queen(PieceColor.WHITE));
        blackPieces.add(new Queen(PieceColor.BLACK));
        whitePieces.add(new King(PieceColor.WHITE));
        blackPieces.add(new King(PieceColor.BLACK));
    }
    public ArrayList<Piece> getAllPieces(){
        // Return a list of all pieces on the board
        ArrayList<Piece> allPiecesList = new ArrayList<>(this.getBlackPieces());
        allPiecesList.addAll(this.getWhitePieces());
        return allPiecesList;
    }
    public ArrayList<Piece> getWhitePieces(){
        return this.whitePieces;
    }
    public ArrayList<Piece> getBlackPieces(){
        return this.blackPieces;
    }

    // TODO: Enable one time fetching for assigning to buttons.
    public ArrayList<Piece> getPieces(PieceColor color, PieceType type){
        ArrayList<Piece> tempList = this.getAllPieces();
        if (color == null && type == null){
            throw new NullPointerException("Arguments can't be both null.");
        }
        if (color == null){
            tempList.removeIf((piece) -> (piece.getType() != type));
        }
        else if (type == null){
            tempList.removeIf((piece) -> (piece.getColor() != color));
        }
        else{
            tempList.removeIf((piece) -> (piece.getColor() != color || piece.getType() != type));
        }
        return tempList;
    }
    public ArrayList<Piece> getPieces(PieceColor color, ArrayList<PieceType> typesList){
        ArrayList<Piece> tempList = this.getAllPieces();
        if (color == null && (typesList == null || typesList.equals(new ArrayList<PieceType>()))){
            throw new NullPointerException("Arguments can't be both null.");
        }
        if (color == null){
            tempList.removeIf((piece) -> (!typesList.contains(piece.getType())));
        }
        else if (typesList == null || typesList.equals(new ArrayList<PieceType>())){
            tempList.removeIf((piece) -> (piece.getColor() != color));
        }
        else{
            tempList.removeIf((piece) -> (piece.getColor() != color
                                            || !typesList.contains(piece.getType())));
        }
        return tempList;
    }
}