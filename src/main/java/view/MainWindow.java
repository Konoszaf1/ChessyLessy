package view;

import controller.ButtonPieceHashMap;
import model.board.BoardColumn;
import model.board.Board;

import model.piece.Piece;
import model.piece.PieceColor;
import model.piece.PieceType;
import view.button.ChessBoardButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainWindow extends JFrame {
    /**
     * Singleton class where main user interaction takes place.
     */
    private static final MainWindow INSTANCE = new MainWindow();
    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final ArrayList<ArrayList<ChessBoardButton>> buttons = new ArrayList<>();
    private MainWindow(){
        super();
        this.initialize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static MainWindow getInstance(){
        return INSTANCE;
    }

    private void initialize(){
        this.setLayout(gridBagLayout);
        this.setSize(800,800);
        this.setResizable(true);
        this.setVisible(true);
        this.setupButtons();

    }
    private void setupButtons(){
        for (int i=0; i<8; i++){
            buttons.add(new ArrayList<>());
            for (BoardColumn col: BoardColumn.values()){
                ChessBoardButton newButton;
                if ((col.ordinal() + i) % 2 == 0){
                    newButton = new ChessBoardButton(ChessBoardButton.LIGHT, i ,col);
                }
                else{
                    newButton = new ChessBoardButton(ChessBoardButton.DARK, i ,col);
                }
                buttons.get(i).add(newButton);
                gridBagLayout.setConstraints(newButton, newButton.getGbc());
                this.add(newButton);
            }
        }
    }
    private void setButtonImage(ChessBoardButton button, Image image){
        button.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
    }

    private ArrayList<ChessBoardButton> getRowButtons(int rowIndex){
        return buttons.get(rowIndex);
    }
    private ArrayList<ChessBoardButton> getColumnButtons(BoardColumn col){
        ArrayList<ChessBoardButton> tempList = new ArrayList<>();
        for (ArrayList<ChessBoardButton> row: buttons){
            for (ChessBoardButton button: row){
                if (button.getColumn() == col ){
                    tempList.add(button);
                }
            }
        }
        return tempList;
    }

    public void setButtonImages(Board board, ButtonPieceHashMap buttonPieceMap){
        for (int row =0; row<8; row++){
            for (ChessBoardButton button : this.getRowButtons(row))
                for (Piece piece : board.getAllPieces()) {
                    if (button.getRow() == piece.getRow() && button.getColumn() == piece.getCol()) {
                        setButtonImage(button, piece.getImage());
                        buttonPieceMap.setButtonPiecePair(button, piece);
                    }
                }
        }
        /*for (ChessBoardButton button : getRowButtons(1)){
            for (Piece piece : board.getPieces(PieceColor.BLACK,new ArrayList<PieceType>(Arrays.asList(PieceType.KING,PieceType.QUEEN)))){
                setButtonImage(button, piece.getImage());
                buttonPieceMap.setButtonPiecePair(button,piece);
            }
        }*/
        /*for (Piece piece: board.getAllPieces()){
            for (ArrayList<ChessBoardButton> row: buttons){
                for (ChessBoardButton button: row){
                    if (button.getRow() == 6 && piece.getType() == PieceType.PAWN) {
                        button.setIcon(new ImageIcon(piece.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                    }
                    else if (button.getRow() == 7 ){
                        if (piece.getType()==PieceType.ROOK && (button.getColumn() == BoardColumn.A
                                || button.getColumn() == BoardColumn.H)){
                            button.setIcon(new ImageIcon(piece.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                        }
                        else if (piece.getType()==PieceType.KNIGHT && (button.getColumn() == BoardColumn.B || button.getColumn() == BoardColumn.G)){
                            button.setIcon(new ImageIcon(piece.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                        }
                        else if (piece.getType()==PieceType.BISHOP && (button.getColumn() == BoardColumn.C || button.getColumn() == BoardColumn.F)){
                            button.setIcon(new ImageIcon(piece.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                        }
                        else if (piece.getType()==PieceType.QUEEN && button.getColumn() == BoardColumn.D ){
                            button.setIcon(new ImageIcon(piece.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                        }
                        else if (piece.getType()==PieceType.KING && button.getColumn() == BoardColumn.E ){
                            button.setIcon(new ImageIcon(piece.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
                        }
                    }
                }
            }
        }*/
    }
}
