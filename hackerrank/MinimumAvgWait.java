package hackerrank;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimumAvgWait {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	    int n = in.nextInt();
	    PriorityQueue<Long> times = new PriorityQueue<>();
	    HashMap<Long, Long> customers = new HashMap<>();

	    for (int i = 0; i < n; i++){

	        long time = in.nextLong();
	        long pizza = in.nextLong();
	        times.add(time);
	        customers.put(time, pizza);

	    }

	    PriorityQueue<Long> orders = new PriorityQueue<>();

	    long minutesGoneBy = times.poll(); // time is read and 'held' before accepting the customer.
	    long currentPizza = customers.get(minutesGoneBy);
	    long nextTime = times.isEmpty() ? Long.MAX_VALUE : times.poll();
	    long totalManWaitMinutes = 0;
	    long customersArrived = 1;
	    while (currentPizza > 0){

	        Long waitTime = Math.min(nextTime - minutesGoneBy, currentPizza);

	        //1 minute of cooking goes by
	        currentPizza -= waitTime;
	        totalManWaitMinutes +=  waitTime * (orders.size() + 1l);

	        minutesGoneBy += waitTime;

	        if (currentPizza == 0 && orders.isEmpty()
	                && customersArrived < n)
	            minutesGoneBy = nextTime;


	        if (minutesGoneBy == nextTime){
	            // A new customer walks in the door!
	            customersArrived++;
	            long nextPizza = customers.get(nextTime);
	            orders.add(nextPizza);
	            // when the NEXT customer will order.
	            nextTime = times.isEmpty() ? Long.MAX_VALUE : times.poll() ;
	        }

	        if (currentPizza == 0 && !orders.isEmpty())// Pizza is finished.
	            currentPizza = orders.poll();
	    }

	    System.out.println(totalManWaitMinutes / n);
    }


}
