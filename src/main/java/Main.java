import controller.GameController;
import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;


public class Main {
    private static final Logger log = Logger.getInstance("Main.class");

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.setProperty("sun.java2d.uiScale", "1.0");
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        // TODO: Find where log file is saved.
        log.info("Application Started.");
        GameController gameController = GameController.getInstance();
        gameController.startGame();
    }
}