import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GameScene {
    private MainApplication program;
    public GameModel gameModel;
    public GameUI gameUI;

    private AnimationTimer timer;

    public static void command_wait(long millis) {
        Object lock = new Object();

        synchronized (lock) {
            try {
                lock.wait(millis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void force_update() {
        // TODO consider (?)
    }

    // init model && ui
    public GameScene(MainApplication program) {
        this.program = program;
        gameModel = new GameModel();
        gameUI = new GameUI(program, gameModel); // link by connecting in constructor so we can access game model
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //System.out.println(gameModel.getTurnState());

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
                    // display dealingSplash -- setup
                    gameUI.spawnDealingText();

                    // setup bet values
                    gameModel.bet(3); // always bet 3 from start (buy in amount)

                    Platform.runLater(() -> { // everything that runs after start up
                        for (int i = 0; i < 10; i++) {
                            // TODO update loading ticks -> "Dealing." "Dealing.." "Dealing..." repeat
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            gameUI.updateDealingText();
                            System.out.println("Updating dealing text");
                        }

                        // fill game logic's hand
                        gameModel.dealHand();

                        // display hand
                        gameUI.fillPlayerHand(gameModel.getPlayerHand());

                        // now ''loading'' complete remove the splash
                        gameUI.removeDealingText();

                        // change to player's turn
                        gameModel.setTurnState(GameModel.TurnState.PlayerTurn);
                    });
                }

                if (gameModel.getTurnState() == GameModel.TurnState.PlayerTurn) {
                    // do something
                    //System.out.println("Turn state: PLAYER");
                }

                if (gameModel.getTurnState() == GameModel.TurnState.Reset) {
                    // update info like wins losses money (?)

                    // dump hands
                    gameModel.getPlayerHand().clear();
                    gameModel.getDealerHand().clear();

                    // delete cards in display
                    gameUI.emptyPlayerHand();

                    // set to player turn
                    gameModel.setTurnState(GameModel.TurnState.DealerDeals);
                }

                if (gameModel.getTurnState() == GameModel.TurnState.DealerReplaces) {
                    // button code
                    // iterate backwards so we dont have to account for the array shortening
                    // empty selected cards
                    for (int i = gameModel.getPlayerHand().size() - 1; i >= 0; i--) {
                        if (gameModel.getPlayerHand().get(i).isSelected()) {
                            int finalI = i;
                                // THIS FUNCTION SHOULD REPLACE EXACTLY ONE CARD AT A TIME
                                // remove selected card
                                gameModel.getPlayerHand().remove(finalI);

                                // remove that card on display
                                gameUI.removePlayerCard(finalI);

                                // replace card at same location
                                gameModel.getPlayerHand().add(finalI, CardGenerator.getRandCard());

                                // add card that was added back in game model
                                gameUI.addPlayerCard(gameModel.getPlayerHand().get(finalI));

                        }
                    }

                    Platform.runLater(() -> {
                        // program wait here (?)
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        gameModel.setTurnState(GameModel.TurnState.ShowCards);
                    });
                }

                if (gameModel.getTurnState() == GameModel.TurnState.ShowCards) {
                    // we want to wait here before we go to reset
                    gameUI.spawnDealingText();

                    switch (gameModel.checkWin()) {
                        case "win" -> {
                            gameUI.setDealingText("You Won!!");
                        }

                        case "loss" -> {
                            gameUI.setDealingText("You Lost..");
                        }

                        case "tie" -> {
                            gameUI.setDealingText("Tie..");
                        }
                    }

                    Platform.runLater(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        gameUI.removeDealingText();

                        gameModel.setTurnState(GameModel.TurnState.Reset);
                    });
                }

                // every tick
                gameUI.updateMoneyLabel(gameModel.playerMoney);
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
