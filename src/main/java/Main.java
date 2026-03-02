import controller.GameController;

import javax.swing.*;
import java.util.logging.Logger;


public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.setProperty("sun.java2d.uiScale", "1.0");
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        log.info("Application Started.");
        GameController gameController = GameController.getInstance();
        gameController.startGame();
    }
}