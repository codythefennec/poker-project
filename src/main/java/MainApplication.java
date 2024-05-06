import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import java.io.InputStream;

public class MainApplication extends Application {
    private static Stage primaryStage;
    private SceneManager manager;

    // program sizes
    private final double PROG_WIDTH = 1080;
    private final double PROG_HEIGHT = 768;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        FontManager font = new FontManager("fonts/Jura-Regular.ttf");
        font.LoadFont();
        setPrimaryStage(primaryStage);
        MainApplication.primaryStage = primaryStage;
        primaryStage.setTitle("Poker Project");
        primaryStage.show();
        TitleScene titleScene = new TitleScene(this);

        // start at this scene
        setScene(titleScene.fetchScene());
    }

    // allows static access to primary stage
    private void setPrimaryStage(Stage primaryStage) {
        MainApplication.primaryStage = primaryStage;
    }

    // allows access to primary stage from scene manager
    protected static Stage getPrimaryStage() {
        return primaryStage;
    }

    // used in sceneManager
    protected void setScene(Scene sceneIn) {
        getPrimaryStage().setScene(sceneIn);
    }

    public double getWidth() {return PROG_WIDTH;}

    public double getHeight() {
        return PROG_HEIGHT;
    }

    public void Launch(String[] args) {
        launch(args);
    }
}
