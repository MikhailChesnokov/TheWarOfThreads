import java.util.Iterator;

public class BadguyThread extends Thread {
    private static final int sleepBadguyTime = 40;

    @Override
    public void run() {
        Iterator<Character> body = new BadguyBodyGenerator().iterator();
        int badguyY = Main.getNextRandomInt(Main.screenHeight - 1);
        int increment, boundX, badguyX;

        if (badguyY % 2 == 0) {
            increment = 1;
            boundX = Main.screenWidth - 1;
            badguyX = 1;
        } else {
            increment = -1;
            boundX = 0;
            badguyX = Main.screenWidth - 1;
        }

        while (badguyX != boundX){
            Main.window.setSymbol(badguyX, badguyY, body.next());
            try {
                sleep(sleepBadguyTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.window.removeSymbol(badguyX, badguyY);

            badguyX += increment;
        }
    }
}