package discreteBehaviorSimulator;

/**
 * Interface that has to be implemented by the object that need to be notified by the clock
 *
 */
public interface ClockObserver {
	public void clockChange(int time);
	public void nextClockChange(int nextJump);
}
