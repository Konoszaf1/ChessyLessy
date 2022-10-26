package model.piece;

import view.button.Button;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.util.Objects;

abstract public class Piece {
   public Image img;

   private PieceColor color;
   private PieceType type;
   public Button curAssignedButton;

   public Piece(PieceColor color, PieceType type){
      this.color = color;
      this.type = type;
   }
   abstract protected void move();
   abstract protected void getAvailableMoves(); // TODO: Refactor
   public Image getImage(){
      return this.img;
   }
   protected Image loadImage(String blackVariantPath, String whiteVariantPath) {
      String filename = this.color == PieceColor.BLACK ? blackVariantPath : whiteVariantPath;
      try {
         return ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResource(filename)));
      } catch (IOException e) {
         System.out.println("File resource not found.");
         return null;
      }
   }
   public PieceType getType() {
      return type;
   }
   public PieceColor getColor() {
      return color;
   }
}
