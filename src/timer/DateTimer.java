package timer;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

/**
 * A DateTimer is a type of Timer that give times based on a list of dates
 * The times are the difference between two dates
 * This Timer is useful when replaying an event with recorded dates
 *
 */
public class DateTimer  implements Timer {
	
	public Vector<Integer> lapsTimes;
	public Iterator<Integer> it;
	
	/**
	 * Constructor of a DateTimer with a list of date
	 * @param dates A list of dates to take the intervals from
	 */
	public DateTimer(TreeSet<Integer> dates) {
		this.lapsTimes = new Vector<Integer>();
		Integer last;
		Integer current=0;
		
		Iterator<Integer> itr = dates.iterator();
		while (itr.hasNext()) {
			last = current;
			current = itr.next();
			this.lapsTimes.add(current-last);
		}
		this.it = this.lapsTimes.iterator();

	}
	
	/**
	 * Constructor of a DateTimer with a list of predefined intervals
	 * @param lapsTimes A list of intervals
	 */
	public DateTimer(Vector<Integer> lapsTimes) {
		this.lapsTimes = new Vector<Integer>(lapsTimes);
		this.it = this.lapsTimes.iterator();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Integer next() {
		return it.next();
	}

}
