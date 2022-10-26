import controller.GameController;
import com.intellij.openapi.diagnostic.Logger;



public class Main {
    private static final Logger log = Logger.getInstance("Main.class");

    public static void main(String[] args) {
        // TODO: Find where log file is saved.
        log.info("Application Started.");
        GameController gameController = GameController.getInstance();
        gameController.startGame();
    }
}