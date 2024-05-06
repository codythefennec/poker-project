import javafx.scene.text.Font;

import java.io.InputStream;

public class FontManager {
    private final InputStream inStream;
    
    public FontManager(String fontPath) {
        inStream =  Object.class.getClassLoader().getResourceAsStream(fontPath);
    }

    public Font fontOfSize(int size) {
        return Font.loadFont(inStream, size);
    }
}
