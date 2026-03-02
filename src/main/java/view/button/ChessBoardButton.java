package view.button;

import model.board.BoardColumn;

import java.awt.*;

public class ChessBoardButton extends GenericButton {

    public static final Color LIGHT = new Color(238, 238, 210);
    public static final Color DARK = new Color(118, 150, 86);
    public static final Color HIGHLIGHT_MOVE = new Color(186, 202, 68);
    public static final Color HIGHLIGHT_CAPTURE = new Color(235, 97, 80);
    public static final Color HIGHLIGHT_SELECTED = new Color(246, 246, 105);
    public static final Color HIGHLIGHT_CHECK = new Color(255, 50, 50);

    private final int row;
    private final BoardColumn column;
    private final Color originalColor;
    private ButtonStatus status = ButtonStatus.NORMAL;

    public ChessBoardButton(Color color, int row, BoardColumn column) {
        this.originalColor = color;
        this.setBackground(color);
        this.row = row;
        this.column = column;
        this.setDefaultGridBagConstraints();
        this.setFocusPainted(false);
        this.setBorderPainted(false);
    }

    private void setDefaultGridBagConstraints() {
        this.gbc.gridx = this.column.ordinal();
        this.gbc.gridy = this.row;
        this.gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.weightx = 1;
        this.gbc.weighty = 1;
    }

    public int getRow() {
        return this.row;
    }

    public BoardColumn getColumn() {
        return this.column;
    }

    public ButtonStatus getStatus() {
        return status;
    }

    public void setStatus(ButtonStatus status) {
        this.status = status;
    }

    public void highlight(Color highlightColor) {
        this.setBackground(highlightColor);
    }

    public void resetColor() {
        this.setBackground(originalColor);
        this.status = ButtonStatus.NORMAL;
    }

    public Color getOriginalColor() {
        return originalColor;
    }
}
