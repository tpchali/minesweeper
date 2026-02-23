import ui.game;
import ui.main;
import ui.timer;

import java.util.concurrent.FutureTask;

public class test {
    public static void main(String[] args) {
        new game();
        timer timer = new timer();
        FutureTask<Integer> ft=new FutureTask<>(timer);
        Thread t1=new Thread(ft);
        t1.start();

    }
}
