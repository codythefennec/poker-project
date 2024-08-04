import java.util.ArrayList;
import java.util.Random;

public class CardGenerator {
    public static ArrayList<Card> generateHand() {
        ArrayList<Card> hand = new ArrayList<Card>();

        for (int i = 0; i < 5; i++) {
            Card generatedCard = getRandCard();
            hand.add(generatedCard);
        }

        return hand;
    }
    public static Card getRandCard() {
        Random rand = new Random();

        // R B G Y or Hearts Spades Clubs Diamonds
        Card.ColorEnum color = Card.ColorEnum.getFromIndex(rand.nextInt(0,3));

        // L or R / Red or Black
        assert color != null;
        Card.SideEnum side = Card.SideEnum.getFromColor(color);

        // A - K
        int type = rand.nextInt(1, 13);

        return new Card(color, side, type);
    }

    public static void main(String[] args) {
        CardGenerator cardGen = new CardGenerator();

        System.out.println("hand 1: \n");
        cardGen.generateHand();

        System.out.println("hand 2: \n");
        cardGen.generateHand();

        System.out.println("hand 3: \n");
        cardGen.generateHand();
    }
}
