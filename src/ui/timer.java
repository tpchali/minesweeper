package ui;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class timer implements Callable<Integer> {
    private double time = 0;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public timer(){
    }
    @Override
    public Integer call() throws Exception {
        while(true){
            try{sleep(1);}
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            time=time+0.001;
        }
    }
}
