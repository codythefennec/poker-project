import javafx.scene.Group;
import javafx.scene.Scene;

public class SceneManager {
    private final MainApplication app;
    private Scene curScene;

    // when constructed indicates the start of the program
    public SceneManager(MainApplication app, Scene startScene) {
        this.app = app;

        switchTo(startScene);
    }

    // allows scenes to change from other scenes
    public void switchTo(Scene sceneIn) {
        app.setScene(sceneIn);
    }
}
