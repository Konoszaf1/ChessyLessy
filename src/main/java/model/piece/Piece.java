package model.piece;

import model.board.BoardColumn;
import view.button.ChessBoardButton;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.util.Objects;

abstract public class Piece {
   public Image img;

   private PieceColor color;
   private PieceType type;
   public ChessBoardButton curAssignedButton;
   private Integer row = null;
   private BoardColumn col = null;

   public Piece(PieceColor color, PieceType type){
      this.color = color;
      this.type = type;
   }
   public Piece(PieceColor color, PieceType type, BoardColumn col){
      this.color = color;
      this.type = type;
      this.col = col;
   }
   abstract protected void move();
   abstract protected void getAvailableMoves(); // TODO: Implement
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


}
