import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameModel {
    // never visually shown to the player until the flip
    private ArrayList<Card> dealerHand = new ArrayList<Card>();
    private ArrayList<Card> playerHand = new ArrayList<Card>();
    public boolean model_interrupts = false;
    public int playerMoney = 50;
    public int currentBet = 0;

    // scene state handler
    public enum PlayerState {
        VIEWER,
        ACTOR
    }

    public enum TurnState {
        Introductions, // dealer intro
        DealerDeals, // dealer deals, show loading
        PlayerTurn,
        DealerReplaces, // replaces card players wants to replace
        ShowCards, // show each others hands and say which players win, prompt player to click
        Reset; // after player clicks, it will run reset step, so cleaning up before going to player turn
    }

    public enum CameraState {
        UP,
        DOWN
    }

    // states
    private CameraState cameraState;
    private TurnState turnState = TurnState.DealerDeals;
    private PlayerState playerState;

    public GameModel() {
    }

    public void bet(int amount) {
        currentBet += amount;
        playerMoney -= amount;
    }

    public void dealHand() {
        // replaces when redealt, so no worries about clearing arrays
        dealerHand = CardGenerator.generateHand();
        playerHand = CardGenerator.generateHand();
    }

    public String checkWin() {
        // check dealer hand and calc value
        int dealerScore = calculateHandValue(dealerHand);
        System.out.println("Dealer: " + dealerScore);
        // check player hand and calc value
        int playerScore = calculateHandValue(playerHand);
        System.out.println("Player: " + playerScore);

        if (dealerScore < playerScore) {
            return "win";
        } else if (dealerScore == playerScore) {
            return "tie";
        } else {
            return "loss";
        }
    }

    public int calculateHandValue(ArrayList<Card> hand) {
        // check pairs
        ArrayList<Integer> cardCounts = new ArrayList<Integer>();

        // A -> K - Card counts set to 0
        for (int i = 0; i < 13; i++) {
            cardCounts.add(0);
        }

        // fill card counts
        for (Card card : hand) {
            // add 1 to the given integer for the matching card
            int cardNum = card.getCardType();
            cardCounts.set(cardNum, cardCounts.get(cardNum) + 1);
        }

        boolean one_pair = false;
        boolean two_pair = false;
        boolean three_match = false;
        boolean four_match = false;
        boolean five_match = false;

        // check for different hand variations
        for (Integer in : cardCounts) {
            switch (in) {
                case 2 -> {
                    // two of a kind
                    if (one_pair) {
                        two_pair = true;
                        return 2;
                    }

                    one_pair = true;
                }

                case 3 -> {
                    // three of a kind
                    three_match = true;
                }

                case 4 -> {
                    // four of a kind
                    four_match = true;
                    return 7;
                }

                case 5 -> {
                    // five of a kind
                    five_match = true;
                    return 11;
                }

                //default -> throw new IllegalStateException("Unexpected value: " + in);
            }
        }

        if (one_pair && three_match) {
            return 6;
        } else if (three_match) {
            return 3;
        } else if (one_pair){
            return 1;
        }

        //  if we get here we aren't dealing with any pair scenarios
        // checking for all same suit
        boolean sameSuit = true;

        Card.ColorEnum lastColor = hand.get(0).getCardColor();
        for (Card in : hand) {
            if (in.getCardColor() != lastColor) {
                sameSuit = false;
                break;
            }
        }

        // checking for straights
        boolean ascending = true;

        // sort ascending
        hand.sort(Card.cardComparator);

        int firstCardNumber = hand.get(0).getCardType();
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getCardType() != firstCardNumber + 1) {
                ascending = false;
                break;
            }
        }

        // checking for highest rank hands
        boolean royal = false;

        if (sameSuit) {
            // check for royal flush
            royal = true;

            ArrayList<Integer> royalCardTypes = new ArrayList<Integer>();

            // 1 A
            // K 13
            // Q 12
            // J 11
            // 10 10

            royalCardTypes.add(1);
            royalCardTypes.add(13);
            royalCardTypes.add(12);
            royalCardTypes.add(11);
            royalCardTypes.add(10);

            // find ace
            // find K
            // find Q
            // find J
            // find 10

            boolean found = false;
            for (Integer in : royalCardTypes) {
                for (Card inside : hand) {
                    if (inside.getCardType() == in) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    royal = false;
                    break;
                }
            }
        }

        if (royal) {
            return 10;
        } else if (sameSuit && ascending) {
            return 9;
        } else if (sameSuit) {
            return 5;
        } else if (ascending) {
            return 4;
        } else {
            return 1; // high card </3
        }
    }

    // camera state handlers
    public CameraState getCameraState() {
        return cameraState;
    }
    public void setCameraState(CameraState newState) {
        cameraState = newState;
    }

    // turn state handlers
    public TurnState getTurnState() {
        return turnState;
    }
    public void setTurnState(TurnState turnState) {
        this.turnState = turnState;
    }

    // player state handlers
    public PlayerState getPlayerState() {
        return playerState;
    }
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    // get player hand
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    // get dealer's hand
    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }
}
