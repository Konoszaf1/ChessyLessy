package view.button;

import model.board.BoardColumn;
import model.piece.Piece;

import javax.swing.*;
import java.awt.*;


public class ChessBoardButton extends GenericButton {
    private ButtonStatus status = ButtonStatus.NORMAL;
    private int row = 0;
    private BoardColumn column = null;
    private Piece piece = null;
    public static final Color LIGHT = new Color(238,238,210);
    public static final Color DARK = new Color(118,150,86);

    public ChessBoardButton(Color color, int row, BoardColumn column) {
        this.setBackground(color);
        this.row = row;
        this.column = column;
        this.setDefaultGridBagConstraints();
    }

    private void setDefaultGridBagConstraints() {
        this.gbc.gridx = this.column.ordinal();
        this.gbc.gridy = this.row;
        this.gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.weightx = 1;
        this.gbc.weighty = 1;
    }

    public int getRow(){
        return this.row;
    }
    public BoardColumn getColumn(){
        return this.column;
    }
}

