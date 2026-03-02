package model.piece;

import model.board.BoardColumn;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class Piece {

   private static final Logger log = Logger.getLogger(Piece.class.getName());

   private final PieceColor color;
   private final PieceType type;
   private Image img;
   private Integer row = null;
   private BoardColumn col = null;

   public Piece(PieceColor color, PieceType type) {
      this.color = color;
      this.type = type;
   }

   public Image getImage() {
      return this.img;
   }

   protected void setImage(Image img) {
      this.img = img;
   }

   protected Image loadImage(String blackVariantPath, String whiteVariantPath) {
      String filename = this.color == PieceColor.BLACK ? blackVariantPath : whiteVariantPath;
      try {
         return ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResource(filename)));
      } catch (IOException e) {
         log.severe("Failed to load image resource: " + filename);
         throw new RuntimeException("Missing image resource: " + filename, e);
      }
   }

   public PieceType getType() {
      return type;
   }

   public PieceColor getColor() {
      return color;
   }

   public Integer getRow() {
      return row;
   }

   public BoardColumn getCol() {
      return col;
   }

   public void setRow(int row) {
      this.row = row;
   }

   public void setCol(BoardColumn col) {
      this.col = col;
   }

   public void setPosition(int row, BoardColumn col) {
      this.row = row;
      this.col = col;
   }

   public boolean isPlaced() {
      return this.row != null && this.col != null;
   }
}
