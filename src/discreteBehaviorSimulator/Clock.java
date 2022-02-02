package discreteBehaviorSimulator;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Class that manage a clock, constructed as a singleton pattern
 *
 */
public class Clock {
	private static Clock instance = null;
	
	private int time;
	private int nextJump;
	private ReentrantReadWriteLock lock;
	private boolean virtual;
	
	
	private Set<ClockObserver> observers;
	
	/**
	 * Constructor of a new Clock
	 */
	private Clock() {
		this.time = 0;
		this.nextJump=0;
		this.lock = new ReentrantReadWriteLock();
		this.virtual = true;
		this.observers = new HashSet<ClockObserver>();
	}
	
	/**
	 * Get the instance of the current clock, create one if it doesn't exist
	 * @return The clock instance
	 */
	public static Clock getInstance() {
		if (Clock.instance == null) {
			Clock.instance = new Clock();
		}
		return Clock.instance;
	}
	
	/**
	 * Add an observer to notify
	 * @param o The observer that is added
	 */
	public void addObserver(ClockObserver o) {
		this.observers.add(o);
	}
	/**
	 * Remove an Observer
	 * @param o The observer that is removed
	 */
	public void removeObserver(ClockObserver o) {
		this.observers.remove(o);
	}
	/**
	 * Change the type of the clock
	 * @param virtual A boolean if the clock is virtual or not
	 */
	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}
	/**
	 * Check if the clock is virtual
	 * @return True if the clock is virtual, else False
	 */
	public boolean isVirtual() {
		return this.virtual;
	}
	
	/**
	 * Set the next jump in time and notify the observer
	 * @param nextJump The time to jump
	 */
	public void setNextJump(int nextJump) {
		this.nextJump = nextJump;
		for(ClockObserver o:this.observers) {
			o.nextClockChange(this.nextJump);
		}
	}
	/*public void setTime(int time) throws IllegalAccessException {
		this.lock.lock();
		if (this.time < time) {
			this.time = time;
			for(ClockObserver o:this.observers) {
				o.ClockChange();
			}
		}else{
			this.lock.unlock();
			throw new IllegalAccessException("Set time error, cannot go back to the past !!!");
		}
		this.lock.unlock();
	}*/
	/**
	 * Increase the time of the clock
	 * @param time The time to be added
	 * @throws Exception
	 */
	public void increase(int time) throws Exception {

		this.lockWriteAccess();

		if(time != this.nextJump) {
			throw new Exception("Unexpected time change");
		}
		this.time += time;
		for(ClockObserver o:this.observers) {
			o.clockChange(this.time);
		}
		this.unlockWriteAccess();
	}
	/**
	 * Get the current time
	 * @return The current time
	 */
	public long getTime() {
		if(this.virtual) {
			return this.time;
		}else {
			return new Date().getTime();
		}
	}
	
	/**
	 * Lock the read access
	 */
	public void lockReadAccess() {
		this.lock.readLock().lock();
	}
	/**
	 * Unlock the read access
	 */
	public void unlockReadAccess() {
		this.lock.readLock().unlock();
	}
	
	/**
	 * Lock the write access
	 */
	public void lockWriteAccess() {
		this.lock.writeLock().lock();
	}
	/**
	 * Unlock the write access
	 */
	public void unlockWriteAccess() {
		this.lock.writeLock().unlock();		
	}
	
	public String toString() {
		return ""+this.time;
	}
}
