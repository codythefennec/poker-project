import java.io.InputStream;
import javafx.scene.image.Image;

public class Card {
    // color of card - Translates to suit
    private final ColorEnum cardColor;
    // L or R - Translates to red or black
    private final SideEnum cardSide;
    // card type 1 - 13
    private final int cardType;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private boolean selected;

    public Card(ColorEnum cardColor, SideEnum cardSide, int cardType) {
        this.cardColor = cardColor;
        this.cardSide = cardSide;
        this.cardType = cardType;
        this.selected = false;
    }

    public Image getCardImage() {
        String path = "cards/" + getCardTypeText() + "_" + cardColor.name().toLowerCase() + "Card.png";
        return new Image(getInputStream(path));
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

    public String getCardTypeText() {
        return switch (cardType) {
            case 1 -> "A";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10 -> Integer.toString(cardType);
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> "ERROR";
        };
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
                case RED, GREEN -> SideEnum.R;
                case BLUE, YELLOW -> SideEnum.L;
            };
        }
    }

    private InputStream getInputStream(String path) {
        return this.getClass().getClassLoader().getResourceAsStream(path);
    }

    public static void main(String[] args) {
        Card card = new Card(ColorEnum.RED, SideEnum.getFromColor(ColorEnum.RED), 1);
        card.getCardImage();
    }
}