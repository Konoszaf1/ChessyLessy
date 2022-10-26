package view;

import controller.ButtonPieceHashMap;
import model.board.BoardColumn;
import model.board.Board;

import model.piece.Piece;
import model.piece.PieceType;
import view.button.Button;

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
    private final ArrayList<ArrayList<Button>> buttons = new ArrayList<>();
    private MainWindow(){
        super();
        this.initialize();
    }

    public static MainWindow getInstance(){
        return INSTANCE;
    }

    private void initialize(){
        this.setLayout(gridBagLayout);
        this.setSize(800,800);
        this.setVisible(true);
        this.setupButtons();

    }
    private void setupButtons(){
        for (int i=0; i<8; i++){
            buttons.add(new ArrayList<>());
            for (BoardColumn col: BoardColumn.values()){
                Button newButton;
                if ((col.ordinal() + i) % 2 == 0){
                    newButton = new Button(Button.LIGHT, i ,col);
                }
                else{
                    newButton = new Button(Button.DARK, i ,col);
                }
                buttons.get(i).add(newButton);
                gridBagLayout.setConstraints(newButton, newButton.getGbc());
                this.add(newButton);
            }
        }
    }
    private void setButtonImage(Button button, Image image){
        button.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
    }

    private ArrayList<Button> getRowButtons(int rowIndex){
        return buttons.get(rowIndex);
    }
    private ArrayList<Button> getColumnButtons(BoardColumn col){
        ArrayList<Button> tempList = new ArrayList<>();
        for (ArrayList<Button> row: buttons){
            for (Button button: row){
                if (button.getColumn() == col ){
                    tempList.add(button);
                }
            }
        }
        return tempList;
    }

    public void setButtonImages(Board board, ButtonPieceHashMap buttonPieceMap){
//        for (Button button: getRowButtons(0)){
//            switch (button.getColumn()){
//                case A -> {
//                    setButtonImage(button,piece);
//                }
//                case H -> {
//                    setButtonImage(button,piece.getImage());}
//            }
//        }
        for (Button button : getRowButtons(1)){
            for (Piece piece : board.getPieces(null,new ArrayList<PieceType>(Arrays.asList(PieceType.KING,PieceType.QUEEN)))){
                setButtonImage(button, piece.getImage());
                buttonPieceMap.setButtonPiecePair(button,piece);
            }
        }
        for (Piece piece: board.getAllPieces()){
            for (ArrayList<Button> row: buttons){
                for (Button button: row){
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
        }
    }
}
