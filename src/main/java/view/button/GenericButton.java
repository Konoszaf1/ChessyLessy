package view.button;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class GenericButton extends JButton {


    private ImageIcon image = null;
    private Color color = null;
    protected GridBagConstraints gbc = new GridBagConstraints();
    public GridBagConstraints getGbc() {
        return this.gbc;
    }
    public ImageIcon getImage() { return image;}
    public Color getColor() { return color;}
    public void setImage(ImageIcon image) { this.image = image;}
    public void setColor(Color color) { this.color = color;}
    protected Image loadImage(String imagePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResource(imagePath)));
        } catch (IOException e) {
            System.out.println("File resource not found.");
            return null;
        }
    }
}
