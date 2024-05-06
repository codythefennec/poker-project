import javafx.scene.text.Font;

import java.io.InputStream;

public class FontManager {
    private final InputStream inStream;
    
    public FontManager(String fontPath) {
        inStream =  this.getClass().getResourceAsStream(fontPath);
    }

    public Font fontOfSize(int size) {
        return Font.loadFont(inStream, size);
    }

    public void LoadFont() {
        Font.loadFont(inStream, 10);
    }
}
