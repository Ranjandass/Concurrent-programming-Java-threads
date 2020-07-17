package ConcurrentProgAssignment1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


class Barber extends Thread {
    private final int cutTime;
    private final int TimeTakenToLeave;
    BlockingQueue<Integer> queue = null;
    private String name;
    long OFFICE_CLOSE=100000;     //mAximum time till the shop is active

    public Barber(BlockingQueue<Integer> queue, String name,int cutInterval, int leaveCustTime) {
        this.name = name;
        this.queue = queue;
        this.cutTime = cutInterval;
        this.TimeTakenToLeave = leaveCustTime;
    }


    public void run() {
        while (true) { // runs in an infinite loop

            try {
                Integer i = this.queue.poll(OFFICE_CLOSE,
                        TimeUnit.MILLISECONDS);
                if (i == null)
                    break; // if no customer in the loop, barbers sleeps

                this.cutHair(i); // cutting...
                Thread.sleep(TimeTakenToLeave); //for customer to leave the barber shop
            } catch (InterruptedException e) {
            }
        }
    }

    public void cutHair(Integer i) {
        System.out.println("The barber " + this.name
                + " is cutting hair for customer #" + i);
        try {
            sleep(cutTime);
        } catch (InterruptedException ex) {
        }
    }
}