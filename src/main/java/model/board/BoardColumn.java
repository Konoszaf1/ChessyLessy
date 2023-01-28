package model.board;

public enum BoardColumn {
    A, B, C, D, E, F, G, H;

    public BoardColumn getNextColumn() {
        try {
            return values()[ordinal() + 1];
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }
    public BoardColumn incrementColumnByInt(char increment) {
        try{
            return values()[ordinal() + increment - 49];
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }
}
