package utils;

public class CharUtilFuncs {

    public static boolean isNumeric(char character) {
        return Character.isDigit(character);
    }

    public static boolean isCapitalAlpha(char character) {
        return Character.isUpperCase(character);
    }

    public static boolean isNonCapitalAlpha(char character) {
        return Character.isLowerCase(character);
    }
}
