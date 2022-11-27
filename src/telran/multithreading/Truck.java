package telran.multithreading;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Truck extends Thread{
	private int load;
	private int nLoads;
	private static int elevator1;
	private static int elevator2;
	public final static Lock lock = new ReentrantLock(false);
	public static int waitingCounter;
	
	public Truck(int load, int nLoads) {
		this.load = load;
		this.nLoads = nLoads;
	}
	@Override
	public void run() {
		for (int i = 0; i < nLoads; i++) {
			try {
				if (lock.tryLock(30, TimeUnit.MILLISECONDS)) {
				  try {
				loadElevator1(load);
				loadElevator2(load);

} finally {
				          lock.unlock();
				      }
				  } else {
					  System.out.println(Thread.currentThread().getName());
					  waitingCounter++;
				  }
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}          
			
		}
	}
	 static private void loadElevator2(int load) {
		 	elevator2+=load;			
	}
	static private void loadElevator1(int load) {
		elevator1 +=load;
		
	}
	public static long getElevator1() {
		return elevator1;
	}
	public static long getElevator2() {
		return elevator2;
	}
	public static long getWaitingCounter() {
		return waitingCounter;
	}
}
