import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class GameUI {
    private MainApplication program;
    private GameModel model;
    private Group returnRoot;
    // Consts
    private final Color grey3 = Color.rgb(18, 18, 18, 1);
    private final Color grey2 = Color.rgb(44, 44, 44, 1);
    private final Color grey1 = Color.rgb(112, 112, 112, 1);

    // groups
    Group cardAreaGroup;
    Group buttonAreaGroup;

    // hboxes
    HBox cardAreaHBox;

    // Control Objects
    private Node upCameraArea;
    private Node downCameraArea;
    private Label tempLabel;

    // UI Objects
    private Image dealerImage;
    private ImageView dealerIV;
    private Rectangle buttonArea;
    private Rectangle cardArea;


    public GameUI(MainApplication program, GameModel model) {
        this.program = program;
        this.model = model;
        returnRoot = buildUI();
    }

    private Group buildUI() {
        Group root = new Group();

        tempLabel = new Label("und");

        // dealer image here
        dealerIV = new ImageView();
        dealerImage = new Image(this.getClass().getClassLoader().getResourceAsStream("images/codyDealer_idle.png"));
        dealerIV.setImage(dealerImage);
        dealerIV.setX(0);
        dealerIV.setY(-270);
        dealerIV.setFitWidth(1080);
        dealerIV.setFitHeight(768);

        // button area group
        buttonAreaGroup = new Group();
        buttonAreaGroup.setTranslateX(0);
        buttonAreaGroup.setTranslateY(645);

        buttonArea = new Rectangle(0, 0, 1080, 246);
        buttonArea.setFill(grey3);
        buttonArea.setArcHeight(50);
        buttonArea.setArcWidth(50);
        buttonAreaGroup.getChildren().add(buttonArea);

        //      buttons added here

        // card area group
        cardAreaGroup = new Group();
        cardAreaGroup.setTranslateX(124);
        cardAreaGroup.setTranslateY(384);

        cardArea = new Rectangle(0,0, 831, 246);
        cardArea.setFill(grey2);
        cardArea.setArcHeight(50);
        cardArea.setArcWidth(50);
        cardAreaGroup.getChildren().add(cardArea);

        cardAreaHBox = new HBox();
        cardAreaGroup.getChildren().add(cardAreaHBox);
        cardAreaHBox.setTranslateX(32);
        cardAreaHBox.setTranslateY(25);
        cardAreaHBox.setSpacing(16);

        // create regions camera up / down
        upCameraArea = new Canvas(program.getWidth(), program.getHeight() / 2);
        upCameraArea.setOnMouseEntered(e -> {
            // update states
            model.setCameraState(GameModel.CameraState.UP);
        });

        downCameraArea = new Canvas(program.getWidth(), program.getHeight()/2);
        downCameraArea.setLayoutY(program.getHeight()/2);
        downCameraArea.setOnMouseEntered(e -> {
            // update states
            model.setCameraState(GameModel.CameraState.DOWN);
        });

        // add to group
        root.getChildren().add(dealerIV);
        root.getChildren().add(cardAreaGroup);
        root.getChildren().add(cardArea);
        root.getChildren().add(cardAreaHBox);
        root.getChildren().add(buttonAreaGroup);
        root.getChildren().add(buttonArea);
        root.getChildren().add(tempLabel);
        root.getChildren().add(upCameraArea);
        root.getChildren().add(downCameraArea);

        return root;
    }

    private ImageView makeCardUI(Card card) {
        ImageView iv = new ImageView();
        iv.setImage(card.getCardImage());
        return iv;
    }

    public void fillPlayerHand(ArrayList<Card> arr) {
        for (Card in : arr) {
            cardAreaHBox.getChildren().add(makeCardUI(in));
        }
    }

    public void moveCardAreaDown() {
        System.out.println(cardArea.getTranslateY());
        if (cardArea.getTranslateY() < 156) {
            cardArea.setTranslateY(cardArea.getTranslateY() + 1);
        }
        if (dealerIV.getTranslateY() < 270) {
            dealerIV.setTranslateY(dealerIV.getTranslateY() + 1.577);
        }
    }

    public void moveCardAreaUp() {
        System.out.println(cardArea.getY());
        if (cardArea.getTranslateY() > 0) {
            cardArea.setTranslateY(cardArea.getTranslateY() - 1);
        }
        if (dealerIV.getTranslateY() > 0) {
            dealerIV.setTranslateY(dealerIV.getTranslateY() - 1.577);
        }

    }

    public Group getRoot() {
        return returnRoot;
    }
}
