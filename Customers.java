package ConcurrentProgAssignment1;

import java.util.concurrent.BlockingQueue;


class Customer extends Thread {
    private final int customerInterval;
    int iD;
    boolean notCut = true;

    // Constructor for the Customer
    BlockingQueue<Integer> queue = null;

    public Customer(int i, BlockingQueue<Integer> queue, int custInterval) {
        iD = i;
        this.queue = queue;
        this.customerInterval = custInterval;
    }

    public void run() {
        while (true) { // Customer will stay in the queue till he hasn't gotten a haircut
            // If the queue is full, the customer leaves
            try {
                //Thread.sleep(customerInterval);
                this.queue.add(this.iD);
                this.getHaircut(); // take a sit
            } catch (IllegalStateException e) {

                System.out.println("There are no free seats. Customer "
                        + this.iD + " has left the barbershop.");
            }
            break;
        }
    }

    // take a seat
    public void getHaircut() {
        System.out.println("Customer " + this.iD + " took a chair");
    }
}