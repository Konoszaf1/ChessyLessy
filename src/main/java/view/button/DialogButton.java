package view.button;

import model.piece.PieceColor;
import javax.swing.*;
import java.awt.*;


public class DialogButton extends GenericButton{

    private final Image black_image = this.loadImage("king_black.png");
    private final Image white_image = this.loadImage("king_white.png");

    public DialogButton(PieceColor color) {
        super();
        Image image = color == PieceColor.BLACK ? black_image: white_image;
        this.setIcon(new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
        this.setDefaultGridBagConstraints(color);
    }

    private void setDefaultGridBagConstraints(PieceColor color) {
        this.gbc.gridx = 0;
        this.gbc.gridy = color == PieceColor.BLACK ? 0: 1;
        this.gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.weightx = 1;
        this.gbc.weighty = 1;
    }
}
