package action;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.TreeSet;

import timer.Timer;

/** Class that create a chain of dependent action that are executed one after another.
 * @author flver
 * 
 */
//TODO Must be refactored to be generic
public class DiscreteActionDependent implements DiscreteActionInterface {
	
	protected DiscreteAction baseAction;
	protected TreeSet<DiscreteAction> depedentActions;
	private Iterator<DiscreteAction> it;
	protected DiscreteAction currentAction;
	
	
	/**
	 * Construct a series of dependent actions, first action (method) called is baseMethodName, then method nextMethod() is called to select the next action. 
	 * 
	 * @param o The object on which the action is performed
	 * @param baseMethodName The method to perform on the object
	 * @param timerBase The base timer 
	 */	
	public DiscreteActionDependent(Object o, String baseMethodName, Timer timerBase){
		this.baseAction = new DiscreteAction(o, baseMethodName, timerBase);
		this.depedentActions = new TreeSet<DiscreteAction>();
		this.it = this.depedentActions.iterator();
		this.currentAction = this.baseAction;
	}
	
	/**
	 * Add an other dependent action
	 * @param o The object on which the new action is performed
	 * @param depentMethodName The method to perform on the dependent object
	 * @param timerDependence Dependent timer
	 */
	public void addDependence(Object o, String depentMethodName, Timer timerDependence) {
		this.depedentActions.add(new DiscreteAction(o, depentMethodName, timerDependence));
	}
	
	/*private void dates2Timalapse(TreeSet<Integer> datesOn, TreeSet<Integer> datesOff, Vector<Integer> timeLapseOn, Vector<Integer> timeLapseOff) {
		Vector<Integer> currentTimeLapse;
		TreeSet<Integer> currentDates;
		Integer date=0;
		if(datesOn.first()<datesOff.first()) {
			currentTimeLapse = timeLapseOn;
			currentDates = datesOn;
		}else {
			currentTimeLapse = timeLapseOff;	
			currentDates = datesOff;		
		}
		Integer nextDate;
		
		while(datesOn.size()>0 || datesOff.size()>0) {
			nextDate = currentDates.first();
		
			currentTimeLapse.add(nextDate - date);
			currentDates.remove(nextDate);
		
			date = nextDate;
			
			if(currentDates == datesOn) {
				currentDates = datesOff;
				currentTimeLapse = timeLapseOff;
			}else {
				currentDates = datesOn;
				currentTimeLapse = timeLapseOn;			
			}
		}
		
	}
	@SuppressWarnings("unchecked")
	public DiscreteActionDependent(Wo o, String on, TreeSet<Integer> datesOn, String off, TreeSet<Integer> datesOff){
		Vector<Integer> timeLapseOn = new Vector<Integer>();
		Vector<Integer> timeLapseOff = new Vector<Integer>();
		
		dates2Timalapse((TreeSet<Integer>)datesOn.clone(), (TreeSet<Integer>)datesOff.clone(), timeLapseOn, timeLapseOff);
		
		this.baseAction = new DiscreteAction(o, on, timeLapseOn);
		this.offAction = new DiscreteAction(o, off, timeLapseOff);
		
		if(datesOn.first() < datesOff.first()){
			this.currentAction = this.baseAction;
		}else{
			this.currentAction = this.offAction;
		}
	}
*/
	private void reInit() {
		//this.baseAction.updateTimeLaps();
		for (Iterator<DiscreteAction> iter = this.depedentActions.iterator(); iter.hasNext(); ) {
		    DiscreteAction element = iter.next();
		    //element.updateTimeLaps();
		}		
	}
	
	/**
	 * Go on the next action to be called
	 */
	public void nextMethod(){
		if (this.currentAction == this.baseAction){
			this.it = this.depedentActions.iterator();
			this.currentAction = this.it.next();
		}else if(this.currentAction == this.depedentActions.last()){
			this.currentAction = this.baseAction;
			this.reInit();
		}else {
			this.currentAction = this.it.next();
		}
	}
	
	/**
	 * Function that spend a specified amount of time
	 * @param t The time that is spent
	 */
	public void spendTime(int t) {
		for (Iterator<DiscreteAction> iter = this.depedentActions.iterator(); iter.hasNext(); ) {
		    DiscreteAction element = iter.next();
		    element.spendTime(t);
		}
	}

	/**
	 * Call the next methode
	 */
	public void updateTimeLaps() {
		// time laps is updated at the re-initialization
		//this.currentAction.updateTimeLaps();	
		this.nextMethod();	
	}

	/**
	 * Getter of the current methode
	 * @return The current methode
	 */
	public Method getMethod() {
		return this.currentAction.getMethod();
	}

	/**
	 * Getter of the current laps of time
	 * @return The current laps of time
	 */
	public Integer getCurrentLapsTime() {
		return this.currentAction.getCurrentLapsTime();
	}

	/**
	 * Getter of the associated object
	 * @return The associated object
	 */
	public Object getObject() {
		return this.currentAction.getObject();
	}

	/**
	 * Compare the current action to an other action 
	 * @param c The action to be compared to
	 * @see DiscreteAction#compareTo(DiscreteActionInterface)
	 */
	public int compareTo(DiscreteActionInterface c) {
		return this.currentAction.compareTo(c);
	}

	/**
	 * Check if there is no more actions
	 * @return True is there is no next action
	 */
	public Boolean isEmpty() {
		return !this.hasNext();
	}

	/**
	 * @see java.util.Iterator#next()
	 */
	public DiscreteActionInterface next() {
		//Integer lapsTime = this.getNextLapsTime();
		Method method = this.getMethod();
		Object object = this.getObject();
		return this;
	}

	/**
	 * @return If their is a next time on the timer
	 */
	public boolean hasNext() {
		return this.baseAction.hasNext() || !this.depedentActions.isEmpty();		
	}

}
