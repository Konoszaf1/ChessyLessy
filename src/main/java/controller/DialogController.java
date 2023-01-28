package controller;

import model.piece.PieceColor;
import view.WhiteBlackOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DialogController implements ActionListener {

    private final WhiteBlackOptionPane wbPane;
    private final GameController gameController;
    private JDialog tempDialog = new JDialog();

    public DialogController(GameController gameController){
        super();
        this.gameController = gameController;
        this.wbPane = new WhiteBlackOptionPane(this);
        this.initializeDialog(this.tempDialog);
        //this.tempDialog = this.wbPane.createDialog("pitsi");

        this.tempDialog.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.wbPane.getWhiteButton()){
            this.gameController.setPlayerColor(PieceColor.WHITE);
            this.tempDialog.dispose();
            System.out.println("white");
        }
        else if (e.getSource() == this.wbPane.getBlackButton()){
            this.gameController.setPlayerColor(PieceColor.BLACK);
            this.tempDialog.dispose();
            System.out.println("black");
        }
    }
    private void initializeDialog(JDialog dialog){
        dialog.setComponentOrientation(this.wbPane.getComponentOrientation());
        Container contentPane = dialog.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this.wbPane, BorderLayout.CENTER);
        dialog.setResizable(false);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
    }
}
