package view.button;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class GenericButton extends JButton {

    private static final Logger log = Logger.getLogger(GenericButton.class.getName());

    protected GridBagConstraints gbc = new GridBagConstraints();

    public GridBagConstraints getGbc() {
        return this.gbc;
    }

    protected Image loadImage(String imagePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResource(imagePath)));
        } catch (IOException e) {
            log.severe("Failed to load image resource: " + imagePath);
            throw new RuntimeException("Missing image resource: " + imagePath, e);
        }
    }
}
