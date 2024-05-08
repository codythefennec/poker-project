import java.awt.*;

public class Card {
    // color of card - Translates to suit
    private final ColorEnum cardColor;
    // L or R - Translates to red or black
    private final SideEnum cardSide;
    // card type 1 - 13
    private final int cardType;

    public Card(ColorEnum cardColor, SideEnum cardSide, int cardType) {
        this.cardColor = cardColor;
        this.cardSide = cardSide;
        this.cardType = cardType;
    }

    public ColorEnum getCardColor() {
        return cardColor;
    }

    public SideEnum getCardSide() {
        return cardSide;
    }

    public int getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return "[cardColor = " + cardColor + " ] " +
                "[cardSide = " + cardSide + " ] " +
                "[ cardType = " + cardType + " ] \n";
    }

    // denotes card color
    public enum ColorEnum {
        RED,
        GREEN,
        BLUE,
        YELLOW;

        public static ColorEnum getFromIndex(int index) {
            if ((index  > 3) || (index < 0)) {
                System.out.println("Invalid Index Provided : COLOR_ENUM");
                return null;
            }

            return switch (index) {
                case 0 -> ColorEnum.RED;
                case 1 -> ColorEnum.BLUE;
                case 2 -> ColorEnum.GREEN;
                case 3 -> ColorEnum.YELLOW;
                default -> null;
            };
        }
    }

    // denotes side of the card, L or R
    public enum SideEnum {
        L,
        R;

        public static SideEnum getFromColor(ColorEnum color) {
            return switch (color) {
                case RED -> SideEnum.R;
                case BLUE -> SideEnum.L;
                case GREEN -> SideEnum.R;
                case YELLOW -> SideEnum.L;
                default -> null;
            };
        }
    }
}
