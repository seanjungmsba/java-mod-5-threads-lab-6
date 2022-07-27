import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    private final static int NUMBER_OF_THREADS = 1;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // create an executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.print("Enter a number that is to be checked for its primality: ");
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            // submit tasks to your executor
            if (num == 0) {
                System.out.println("STATUS: PROGRAM ENDED");
                break;
            }
            executor.submit(() -> {
                PrimeLogger primeLogger = new PrimeLogger(num);
                primeLogger.run();
            });
            // executor.submit(new PrimeLogger(num)); // can replace line 21-24
        }
        executor.shutdown();
        scanner.close();
    }
}

class PrimeLogger implements Runnable {
    private final int num;
    public PrimeLogger(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        // print num if it is prime
        boolean flag = false;
        int i = 2;
        while (i <= this.num / 2) {
            // condition for nonprime number
            if (this.num % i == 0) {
                flag = true;
                break;
            }
            ++i;
        }
        if (!flag)
            System.out.println(this.num + " is a prime number.");
        else
            System.out.println(this.num + " is not a prime number.");

        System.out.println("Enter [0] to exit out of the program; otherwise, keep entering other numbers");
    }
    
}