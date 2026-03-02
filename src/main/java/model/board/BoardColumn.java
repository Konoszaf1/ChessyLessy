package model.board;

public enum BoardColumn {
    A, B, C, D, E, F, G, H;

    public BoardColumn getNextColumn() {
        int next = ordinal() + 1;
        if (next >= values().length) {
            return null;
        }
        return values()[next];
    }

    public BoardColumn incrementColumnByInt(char increment) {
        int offset = increment - '0';
        int target = ordinal() + offset;
        if (target >= values().length) {
            return null;
        }
        return values()[target];
    }

    public static BoardColumn fromIndex(int index) {
        if (index < 0 || index >= values().length) {
            return null;
        }
        return values()[index];
    }
}
