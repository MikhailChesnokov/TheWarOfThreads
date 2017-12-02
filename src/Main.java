import java.util.concurrent.Semaphore;

public class Main {
    private static final int screenWidth = 80, screenHeight = 25;
    static MainWindow window = new MainWindow(new RightLeftSpaceListener(), screenWidth, screenHeight);
    static int gunX = screenWidth / 2, gunY = screenHeight - 1;
    private static final char gunSymbol = '|';
    private static final int maxBulletsPerQueue = 3;

    public static Semaphore bulletSemaphore = new Semaphore(maxBulletsPerQueue, true);

    public static void main(String[] args) {
        window.setSymbol(gunX, gunY, gunSymbol);
    }

    public static void stepLeft() {
        if (gunX - 1 > 0) {
            window.removeSymbol(gunX, gunY);
            window.setSymbol(--gunX, gunY, gunSymbol);
        }
    }

    public static void stepRight() {
        if (gunX + 1 < screenWidth) {
            window.removeSymbol(gunX, gunY);
            window.setSymbol(++gunX, gunY, gunSymbol);
        }
    }

    public static void fire(){
        new BulletThread().start();
    }
}