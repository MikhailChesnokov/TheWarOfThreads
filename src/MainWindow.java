import java.awt.*;
import java.awt.event.*;

class MainWindow extends Frame{
    private static final int
            screenOffset = 8,
            baseX = 8, baseY = 31,
            symbolWidth = 16, symbolHeight = 16;
    private static Font defaultFont = new Font(Font.MONOSPACED, Font.PLAIN, symbolHeight);

    MainWindow(KeyListener keyListener, int screenWidth, int screenHeight){
        setSize(baseX + screenOffset + screenWidth * symbolWidth, baseY + screenOffset + screenHeight * symbolHeight);
        setLayout(null);
        setVisible(true);
        addKeyListener(keyListener);
    }

    private int toPixelCoordinateX(int x){
        return x * symbolWidth + baseX;
    }

    private int toPixelCoordinateY(int y){
        return y * symbolHeight + baseY;
    }

    void setSymbol(int x, int y, char symbol){
        Label label = new Label(String.valueOf(symbol));
        label.setFont(defaultFont);
        label.setBounds(toPixelCoordinateX(x), toPixelCoordinateY(y), symbolWidth, symbolHeight);
        add(label);
    }

    void removeSymbol(int x, int y){
        remove(getComponentAt(toPixelCoordinateX(x),toPixelCoordinateY(y)));
    }

    boolean isFree(int x, int y){
        return getComponentAt(x,y) != null;
    }
}