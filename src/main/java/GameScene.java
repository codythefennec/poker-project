import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GameScene {
    private MainApplication program;
    private GameModel gameModel;
    private GameUI gameUI;

    private AnimationTimer timer;

    // init model && ui
    public GameScene(MainApplication program) {
        this.program = program;
        gameModel = new GameModel();
        gameUI = new GameUI(program, gameModel); // link by connecting in constructor so we can access game model
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                System.out.println(gameModel.getTurnState());

                if (gameModel.getCameraState() == GameModel.CameraState.UP) {
                    // do something if the camera is up
                    //System.out.println("Camera Is Up");
                    gameUI.moveCardAreaDown();
                    gameModel.setPlayerState(GameModel.PlayerState.VIEWER);
                }

                if (gameModel.getCameraState() == GameModel.CameraState.DOWN) {
                    // do something else if the camera is down
                    //System.out.println("Camera Is Down");
                    gameUI.moveCardAreaUp();
                    gameModel.setPlayerState(GameModel.PlayerState.ACTOR);
                }

                if (gameModel.getPlayerState() == GameModel.PlayerState.ACTOR) {
                    // if our player is currently an actor we can actually handle actions
                    // TODO ADD FUNCTIONS HERE
                    //System.out.println("I am an actor");
                } else {
                    // our player is a viewer therefore cannot take actions besides interacting with dealer
                    //System.out.println("I am a viewer");
                }

                if (gameModel.getTurnState() == GameModel.TurnState.DealerDeals) {
                    // if it's the turn where the dealer is dealing, fill the game logics "hand"
                    gameModel.dealHand();
                    // display
                    gameUI.fillPlayerHand(gameModel.getPlayerHand());
                    // change to player's turn
                    gameModel.setTurnState(GameModel.TurnState.PlayerTurn);
                }

                if (gameModel.getTurnState() == GameModel.TurnState.PlayerTurn) {
                    // do something
                    //System.out.println("Turn state: PLAYER");
                }
            }
        };
    }

    // build the scene obj
    private Scene buildScene() {
        Scene scene = new Scene(gameUI.getRoot(), program.getWidth(), program.getHeight(), Color.BLACK);
        scene.getStylesheets().add("styles/pokerStyle.css");

        timer.start();
        return scene;
    }

    // fetch the scene obj
    public  Scene fetchScene() {return buildScene();}
}
