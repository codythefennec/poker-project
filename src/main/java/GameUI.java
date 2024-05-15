import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class GameUI {
    private MainApplication program;
    private GameModel model;
    private Group returnRoot;
    private FontManager font = new FontManager("fonts/Jura-Regular.ttf");
    private Font label_font = font.fontOfSize(75);
    // Consts
    private final Color grey3 = Color.rgb(18, 18, 18, 1);
    private final Color grey2 = Color.rgb(44, 44, 44, 1);
    private final Color grey1 = Color.rgb(112, 112, 112, 1);

    // numbers
    private int betDisplay = 0;

    // groups
    Group cardAreaGroup;
    Group buttonAreaGroup;

    // hboxes
    HBox cardAreaHBox;

    // Control Objects
    private Node cameraMonitor;

    // UI Objects
    private Image dealerImage;
    private ImageView dealerIV;
    private Rectangle buttonArea;
    private Rectangle cardArea;

    private HBox buttonHBox;
    private Button holdButton;
    private Button callButton;
    private Button betButton;
    private Button foldButton;

    // bet group
    private Rectangle betBg1;
    private Rectangle betBg2;
    private Button addBetButton;
    private Button subBetButton;
    private Button submitBetButton;
    private Button nahButton;
    private Label betAmountLabel;
    private Rectangle betAmountBg;

    public GameUI(MainApplication program, GameModel model) {
        this.program = program;
        this.model = model;
        returnRoot = buildUI();
    }

    private Group buildUI() {
        Group root = new Group();

        // dealer image here
        dealerIV = new ImageView();
        dealerImage = new Image(
                this.getClass().getClassLoader().getResourceAsStream("images/codyDealer_idle.png")
        );
        dealerIV.setImage(dealerImage);
        dealerIV.setX(0);
        dealerIV.setY(-270);
        dealerIV.setFitWidth(1080);
        dealerIV.setFitHeight(768);

        // button area group
        buttonAreaGroup = new Group();

        buttonArea = new Rectangle(0, 645, 1080, 246);
        buttonArea.setFill(grey3);
        buttonArea.setArcHeight(50);
        buttonArea.setArcWidth(50);
        buttonAreaGroup.getChildren().add(buttonArea);

        // buttons added here
        buttonHBox = new HBox();
        buttonHBox.setSpacing(19);
        buttonHBox.setLayoutX(25);
        buttonHBox.setLayoutY(659);
        buttonHBox.setPrefHeight(80);

        callButton = new Button("Call");
        callButton.getStyleClass().add("game_button");
        callButton.setMinSize(244.35, 80);
        callButton.setOnAction(e -> {
            // button code
            model.setTurnState(GameModel.TurnState.ShowCards);
        });

        holdButton = new Button("Hold");
        holdButton.setMinWidth(244.35);
        holdButton.setMinHeight(89);
        holdButton.getStyleClass().add("game_button");
        holdButton.setOnAction(e -> {
            // button code
        });

        betButton = new Button("Bet");
        betButton.setMinWidth(244.35);
        betButton.setMinHeight(89);
        betButton.getStyleClass().add("game_button");
        betButton.setOnAction(e -> {
            // button code
            // entering this body means we are currently betting
            //      bet interface opens.
            spawnBetGroup(root);
        });

        foldButton = new Button("Fold");
        foldButton.getStyleClass().add("game_button");
        foldButton.setMinWidth(244.35);
        foldButton.setMinHeight(89);
        foldButton.setOnAction(e -> {
            // button code
            model.setTurnState(GameModel.TurnState.Reset);
        });

        // add to hbox
        buttonHBox.getChildren().addAll(callButton, holdButton, betButton, foldButton);

        // card area group
        cardAreaGroup = new Group();

        cardArea = new Rectangle(124,384, 831, 246);
        cardArea.setFill(grey2);
        cardArea.setArcHeight(50);
        cardArea.setArcWidth(50);

        cardAreaHBox = new HBox();
        cardAreaHBox.setTranslateX(cardArea.getX() + 32);
        cardAreaHBox.setTranslateY(cardArea.getY() + 25);
        cardAreaHBox.setSpacing(16);

        // create regions camera up / down
        cameraMonitor = new Canvas(program.getWidth(), program.getHeight() / 2);
        cameraMonitor.setLayoutY(0);
        cameraMonitor.setLayoutX(0);
        cameraMonitor.setOnMouseEntered(e -> {
            model.setCameraState(GameModel.CameraState.UP);
        });

        cameraMonitor.setOnMouseExited(e -> {
            model.setCameraState(GameModel.CameraState.DOWN);
        });

        // add to group
        root.getChildren().add(dealerIV);
        root.getChildren().add(cardAreaGroup);
        root.getChildren().add(cardArea);
        root.getChildren().add(cardAreaHBox);
        root.getChildren().add(buttonAreaGroup);
        root.getChildren().add(buttonArea);
        root.getChildren().add(buttonHBox);
        root.getChildren().add(cameraMonitor);

        return root;
    }

    private void spawnBetGroup(Group root) {
        // create the bg
        betBg1 = new Rectangle(540, 471, 261, 297);
        betBg1.setFill(grey3);
        betBg1.setArcHeight(50);
        betBg1.setArcWidth(50);

        // create the bg pt2
        betBg2 = new Rectangle(550,481, 241.45, 277);
        betBg2.setFill(grey1);
        betBg2.setArcHeight(50);
        betBg2.setArcWidth(50);

        // image for buttons
        Image img = new Image(this.getClass().getClassLoader().getResourceAsStream("images/trisArrow.png"));

        // up button
        addBetButton = new Button();
        addBetButton.getStyleClass().add("graphic_button");
        addBetButton.setLayoutX(569);
        addBetButton.setLayoutY(491 - 7);
        addBetButton.setPrefSize(78,79);
        addBetButton.setGraphic(new ImageView(img));
        addBetButton.setOnMouseClicked(e -> {
            // ++ to display
            betDisplay++;
            betAmountLabel.setText(String.format("%02d", betDisplay));
        });

        // down button
        subBetButton = new Button();
        subBetButton.getStyleClass().add("graphic_button");
        subBetButton.setLayoutX(569);
        subBetButton.setLayoutY(659 + 10);
        subBetButton.setPrefSize(70, 70);
        subBetButton.setRotate(180);
        subBetButton.setGraphic(new ImageView(img));
        subBetButton.setMaxWidth(70);
        subBetButton.setOnMouseClicked(e -> {
            // -- to display
            betDisplay--;
            betAmountLabel.setText(String.format("%02d", betDisplay));
        });

        // nah button
        nahButton = new Button("Nah");
        nahButton.getStyleClass().add("bet_button");
        nahButton.setLayoutX(657);
        nahButton.setLayoutY(496);
        nahButton.setPrefSize(115, 60);
        nahButton.setOnAction(e -> {
            removeBetGroup(root);
            betDisplay = 0;
        });

        // bet button
        submitBetButton = new Button("Bet");
        submitBetButton.getStyleClass().add("bet_button");
        submitBetButton.setLayoutX(654);
        submitBetButton.setLayoutY(678);
        submitBetButton.setMinHeight(60);
        submitBetButton.setMinWidth(115);
        submitBetButton.setOnAction(e -> {
            // submit bet
            // or sumn
            betDisplay = 0;
        });

        betAmountBg = new Rectangle(570, 569, 203, 89);
        betAmountBg.setArcWidth(50);
        betAmountBg.setArcHeight(50);
        betAmountBg.setFill(grey3);

        betAmountLabel = new Label();
        // updatable label
        betAmountLabel.setLayoutX(626);
        betAmountLabel.setLayoutY(569);
        betAmountLabel.setTextFill(Color.WHITE);
        betAmountLabel.setFont(label_font);
        betAmountLabel.setText(String.format("%02d", betDisplay));

        root.getChildren().addAll(
                betBg1,
                betBg2,
                addBetButton,
                subBetButton,
                nahButton,
                submitBetButton,
                betAmountBg,
                betAmountLabel
        );
    }

    private void removeBetGroup(Group root) {
        root.getChildren().removeAll(
                betBg1,
                betBg2,
                addBetButton,
                subBetButton,
                nahButton,
                submitBetButton,
                betAmountBg,
                betAmountLabel
        );
    }

    private ImageView makeCardUI(Card card) {
        ImageView iv = new ImageView();
        iv.setImage(card.getCardImage());
        iv.setFitWidth(140);
        iv.setFitHeight(196);
        iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // code for when the card is clicked
                for (int i = 0; i < cardAreaHBox.getChildren().size(); i++) {
                    if (iv.equals(cardAreaHBox.getChildren().get(i))) {
                        // if the card selected matches in hbox
                        // we use that integer to determine its position

                        if (model.getPlayerHand().get(i).isSelected()) {
                            System.out.println("unselected");
                            // if the matching card is selected unselect it +100
                            iv.setTranslateY(iv.getTranslateY() + 100);
                            model.getPlayerHand().get(i).setSelected(false);
                        } else {
                            System.out.println("selected");
                            // otherwise select it and move -100
                            iv.setTranslateY(iv.getTranslateY() - 100);
                            model.getPlayerHand().get(i).setSelected(true);
                        }
                    }
                }

                boolean temp = false;

                for (Card in : model.getPlayerHand()) {
                    if (in.isSelected()) {
                        temp = true;
                    }

                    System.out.println(temp);
                }

                if (temp) {
                    holdButton.setText("Trade");
                } else {
                    holdButton.setText("Hold");
                }
            }
        });
        return iv;
    }

    public void fillPlayerHand(ArrayList<Card> arr) {
        for (Card in : arr) {
            cardAreaHBox.getChildren().add(makeCardUI(in));
        }
    }

    public void emptyPlayerHand() {
        cardAreaHBox.getChildren().clear();
    }

    public void removePlayerCard(int index) {
        cardAreaHBox.getChildren().remove(index);
        // deletes instance / removes from screen
    }

    public void moveCardAreaDown() {
        if (cardArea.getTranslateY() < 156) {
            cardArea.setTranslateY(cardArea.getTranslateY() + 1);
            cardAreaHBox.setTranslateY(cardAreaHBox.getTranslateY() + 1);
        }
        if (dealerIV.getTranslateY() < 270) {
            dealerIV.setTranslateY(dealerIV.getTranslateY() + 1.577);
        }
    }

    public void moveCardAreaUp() {
        if (cardArea.getTranslateY() > 0) {
            cardArea.setTranslateY(cardArea.getTranslateY() - 1);
            cardAreaHBox.setTranslateY(cardAreaHBox.getTranslateY() - 1);

        }
        if (dealerIV.getTranslateY() > 0) {
            dealerIV.setTranslateY(dealerIV.getTranslateY() - 1.577);
        }

    }

    public Group getRoot() {
        return returnRoot;
    }
}
