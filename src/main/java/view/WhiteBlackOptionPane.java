package view;

import controller.DialogController;
import model.piece.PieceColor;

import javax.swing.*;
import java.awt.*;

import view.button.DialogButton;


public class WhiteBlackOptionPane extends JOptionPane {


    private final DialogButton blackButton = new DialogButton(PieceColor.BLACK);
    private final DialogButton whiteButton = new DialogButton(PieceColor.WHITE);
    public Component[] options;

    public WhiteBlackOptionPane(DialogController dialogController) {
        this.optionType = YES_NO_OPTION;
        JLabel label = new JLabel("Select a side to play with.");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        this.setMessage(label);
        this.options = configureOptionButtons(dialogController);
        this.setOptions(this.options);
    }

    private Component[] configureOptionButtons(DialogController dialogController){
        Component[] components = new Component[2];
        blackButton.addActionListener(dialogController);
        components[0] = blackButton;
        whiteButton.addActionListener(dialogController);
        components[1] = whiteButton;
        return components;
    }

    public DialogButton getBlackButton() {
        return blackButton;
    }

    public DialogButton getWhiteButton() {
        return whiteButton;
    }
}
