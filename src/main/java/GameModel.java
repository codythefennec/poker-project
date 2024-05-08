import java.util.ArrayList;

public class GameModel {
    // never visually shown to the player until the flip
    private ArrayList<Card> dealerHand = new ArrayList<Card>();
    private ArrayList<Card> playerHand = new ArrayList<Card>();

    // scene state handler
    public enum PlayerState {
        VIEWER,
        ACTOR
    }

    public enum TurnState {
        DealerDeals,
        PlayerTurn,
        DealerReplaces,
        ShowCards,
        Reset
    }

    public enum CameraState {
        UP,
        DOWN
    }

    // states
    private CameraState cameraState;
    private TurnState turnState;
    private PlayerState playerState;

    public GameModel() {

    }

    private void dealHand() {
        // replaces when redealt, so no worries about clearing arrays
        dealerHand = CardGenerator.generateHand();
        playerHand = CardGenerator.generateHand();
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
}
