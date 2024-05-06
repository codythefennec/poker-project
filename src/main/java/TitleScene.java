import com.sun.javafx.geom.RoundRectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TitleScene {
    private final MainApplication program;
    // scene objects
    Rectangle titleBar;

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

        // add to group
        root.getChildren().add(titleBar);

        return new Scene(root, program.getWidth(), program.getHeight(), Color.BLACK);
    }

    public Scene fetchScene() {return buildScene();}
}
