package ConcurrentProgAssignment1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarberShop extends Thread{

    private static BlockingQueue<Integer> queue;
    private final int custInterval;
    private final int barbCutInterval;
    private final int leaveCustTime;
    private ExecutorService executor;
    private final int nBarbers;
    private final int nCustomers;

    public BarberShop(int nBarbers, int nCustomers, BlockingQueue<Integer> queue, int customerInterval, int barbInterval, int custLeave){
    this.nBarbers = nBarbers;
    this.nCustomers = nCustomers;
    this.queue = queue;
    this.custInterval = customerInterval;
    this.barbCutInterval = barbInterval;
    this.leaveCustTime = custLeave;
    }


    public void run() {

        executor = Executors.newFixedThreadPool(nBarbers);
        for (int i = 1; i <= nBarbers; i++) {
            executor.submit(new Barber(
                    BarberShop.queue, Integer.toString(i), barbCutInterval, leaveCustTime));

        }
        // create new customers

        for (int i = 1; i <= nCustomers; i++) {
            Customer aCustomer = new Customer(i, BarberShop.queue, custInterval);
            aCustomer.start();
            try {
                sleep(custInterval);
            } catch (InterruptedException ex) {
            }
            ;
        }
    }
}






