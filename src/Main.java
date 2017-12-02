import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static Random rnd = new Random();
    static final int screenWidth = 80, screenHeight = 25;
    static MainWindow window = new MainWindow(new RightLeftSpaceListener(), screenWidth, screenHeight);
    static int gunX = screenWidth / 2, gunY = screenHeight - 1;
    private static final char gunSymbol = '|';
    private static final int maxBulletsPerQueue = 3;
    static AtomicInteger hit = new AtomicInteger(0), miss = new AtomicInteger(0);

    public static Semaphore bulletSemaphore = new Semaphore(maxBulletsPerQueue, true);
    private static ReentrantLock randomLock = new ReentrantLock();

    public static void main(String[] args) {
        window.setSymbol(gunX, gunY, gunSymbol);
        new BadguysFactoryThread().start();
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

    public static int getNextRandomInt(int limit){
        randomLock.lock();
        int randomInt = rnd.nextInt(limit);
        randomLock.unlock();
        return randomInt;
    }
}