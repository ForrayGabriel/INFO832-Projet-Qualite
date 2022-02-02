package timer;

import java.util.Iterator;

/**
 * The interface on which every type of timer is based
 * It contains the method next() that return the delay time
 */
public interface Timer extends Iterator<Integer>{
	/**
	 * @return the delay time
	 * @see java.util.Iterator#next()
	 */
	public Integer next();
	/*
	 * return the delay time
	 * @see java.util.Iterator#next()
	 */
	//public Integer next(int now);
}
