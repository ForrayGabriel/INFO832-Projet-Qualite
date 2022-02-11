package timer;

import java.util.Iterator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PeriodicTimer periodic = new PeriodicTimer(10);
		OneShotTimer on = new OneShotTimer(2);
		TimeBoundedTimer mgTimer = new TimeBoundedTimer(periodic, 5,15);
		for (int i = 0; i < 15; i++) {
			System.out.println(mgTimer.next());
			System.out.println(mgTimer.hasNext());
		}
	}

}
