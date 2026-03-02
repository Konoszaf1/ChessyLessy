package controller;

import model.piece.PieceColor;
import view.WhiteBlackOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class DialogController implements ActionListener {

    private static final Logger log = Logger.getLogger(DialogController.class.getName());

    private final WhiteBlackOptionPane wbPane;
    private final GameController gameController;
    private final JDialog dialog = new JDialog();

    public DialogController(GameController gameController) {
        this.gameController = gameController;
        this.wbPane = new WhiteBlackOptionPane(this);
        this.initializeDialog(this.dialog);
        this.dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.wbPane.getWhiteButton()) {
            this.gameController.setPlayerColor(PieceColor.WHITE);
            log.info("Player selected WHITE");
            this.dialog.dispose();
        } else if (e.getSource() == this.wbPane.getBlackButton()) {
            this.gameController.setPlayerColor(PieceColor.BLACK);
            log.info("Player selected BLACK");
            this.dialog.dispose();
        }
    }

    private void initializeDialog(JDialog dialog) {
        dialog.setTitle("Choose Side");
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
