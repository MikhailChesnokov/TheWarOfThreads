public class BulletThread extends Thread {
    private static final int bulletSleepPeriod = 100;
    private static final char bullet = '*';

    @Override
    public void run() {
        if (!Main.bulletSemaphore.tryAcquire()) return;

        int bulletX = Main.gunX, bulletY = Main.gunY;

        while(bulletY >= 0) {
            Main.window.setSymbol(bulletX, --bulletY, bullet);
            try {
                sleep(bulletSleepPeriod);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.window.removeSymbol(bulletX, bulletY);
        }

        Main.bulletSemaphore.release();
    }
}