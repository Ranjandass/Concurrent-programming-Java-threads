package ConcurrentProgAssignment1;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class TestMain {

    public static void main(String args[]) {
        int nChairs;
        int nBarbers;
        int nCustomers;

        ExecutorService executor = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number of Barbers");
        int n = sc.nextInt();
        nBarbers = n;

        System.out.println("Enter the number of waiting chairs");
        int c = sc.nextInt();
        nChairs = c;

        System.out.println("Enter total number of Customers");
        nCustomers = sc.nextInt();
//......................................................
        System.out.println("Enter Customer entry mean ");
        long arrivalInterval = sc.nextLong();

        System.out.println("Enter the standard deviation of customer entry");
        long stdDivBetweenCustomers = sc.nextLong();

        Random rd = new Random();
        double cusTime = rd.nextGaussian() * arrivalInterval + stdDivBetweenCustomers;
        int cusRound = (int) Math.round(cusTime);
        int cusInterval = Math.abs((cusRound*100)+100);

        System.out.println("Enter the mean time taken by Barber to cut hair");
        long serviceTime = sc.nextLong();

        System.out.println("Enter the standard deviation taken by Barber to cut hair");
        long stdDivServiceTime = sc.nextLong();

        System.out.println("Time taken by customer to leave");
        int custLeave = sc.nextInt();

        double haircutTime = rd.nextGaussian() * serviceTime + stdDivServiceTime;
        int barbRound = (int) Math.round(haircutTime);
        int barbInterval = Math.abs((barbRound*100)+100);
// new/......................

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(
                nChairs);


        BarberShop barberShop = new BarberShop(nBarbers, nCustomers, queue, cusInterval, barbInterval, custLeave);
        barberShop.start(); // Let the simulation begin
    }
}
