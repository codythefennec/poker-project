import com.sun.javafx.geom.RoundRectangle2D;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class TitleScene {
    private final MainApplication program;
    private FontManager font = new FontManager("fonts/Jura-Regular.ttf");
    // scene objects
    Rectangle titleBar;
    Text titleText;
    Rectangle buttonContainerBg;
    VBox buttonContainer;
    Button startButton;
    Button infoButton;
    Button exitButton;

    public TitleScene(MainApplication program) {
        this.program = program;
    }

    public Scene buildScene() {
        // init root
        Group root = new Group();

        // init title bar bg
        titleBar = new Rectangle();
        titleBar.setX(0);
        titleBar.setY(-20);
        titleBar.setWidth(program.getWidth());
        titleBar.setHeight(106.04 + 20);
        titleBar.setArcHeight(50);
        titleBar.setArcWidth(50);
        titleBar.setFill(Color.rgb(83, 66,85, 1));

        // init text field
        titleText = new Text();
        titleText.setX(0);
        titleText.setY(80);
        titleText.setWrappingWidth(program.getWidth());
        titleText.setFont(font.fontOfSize(80));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.WHITE);
        titleText.setText("Furry Poker");

        // button container bg
        buttonContainerBg = new Rectangle(346,158, 400, 569);
        buttonContainerBg.setFill(Color.rgb(44, 44, 44));
        buttonContainerBg.setArcHeight(50);
        buttonContainerBg.setArcWidth(50);

        // vbox
        buttonContainer = new VBox();
        buttonContainer.setLayoutX(346 + 30);
        buttonContainer.setLayoutY(158 + 47.72);
        buttonContainer.setMinWidth(400);
        buttonContainer.setMinHeight(569);
        buttonContainer.setSpacing(45.28);

        // start button
        startButton = new Button("Start");
        startButton.setMinWidth(337);
        startButton.setMinHeight(128.48);
        startButton.getStyleClass().add("title_button");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO send to game screen
                // program.setScene();
            }
        });
        //startButton.setFont(font.fontOfSize(75));
        // info button
        infoButton = new Button("Info");
        infoButton.setMinWidth(337);
        infoButton.setMinHeight(128.48);
        infoButton.getStyleClass().add("title_button");
        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // TODO send to info scene
            }
        });
        // exit button
        exitButton = new Button("Exit");
        exitButton.setMinWidth(337);
        exitButton.setMinHeight(128.48);
        exitButton.getStyleClass().add("title_button");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                javafx.application.Platform.exit();
            }
        });
        // add to vbox
        buttonContainer.getChildren().addAll(startButton, infoButton, exitButton);

        // add to group
        root.getChildren().add(titleBar);
        root.getChildren().add(titleText);
        root.getChildren().add(buttonContainerBg);
        root.getChildren().add(buttonContainer);

        Scene scene = new Scene(root, program.getWidth(), program.getHeight(), Color.BLACK);
        scene.getStylesheets().add("styles/pokerStyle.css");

        return scene;
    }

    public Scene fetchScene() {return buildScene();}
}
