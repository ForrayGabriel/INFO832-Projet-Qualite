package timer;

/**
 * Class that takes two timers and combine them in a way that, if they both can give a next time,
 * add the time each one give and return the result.
 * This allow to create human like comportements by merging a periodic, date, or one shot timer with a random one.
 */
public class MergedTimer implements Timer{
	
	private Timer timer1;
	private Timer timer2;

	/**
	 * Constructor of the merged Timer that combine the two that are in the parameters
	 * @param timer1 The first of the two timer to be combined
	 * @param timer2 The second of the two timer to be combined 
	 */
	public MergedTimer(Timer timer1, Timer timer2) {
		this.timer1 = timer1;
		this.timer2 = timer2;
	}
	@Override
	public boolean hasNext() {
		return (this.timer1.hasNext() && this.timer2.hasNext());
	}

	@Override
	public Integer next() {
		if(this.hasNext()) {
			return this.timer1.next() + this.timer2.next();
		}
		return null;
	}

}
