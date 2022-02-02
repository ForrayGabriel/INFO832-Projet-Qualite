package timer;

/**
 * A OneShotTimer is a type of timer that give only one value and stops
 */
public class OneShotTimer  implements Timer {
	
	private Integer at;
	private boolean hasNext;
	
	/**
	 * Create a OneShotTimer
	 * @param at The time value that will be returned
	 */
	public OneShotTimer(int at) {
		this.at = at;
		this.hasNext = true;
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

	@Override
	public Integer next() {
		Integer next=this.at;
		this.at=null;
		this.hasNext = false;
		return next;
	}

}
