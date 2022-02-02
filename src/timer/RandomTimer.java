package timer;

import java.util.Random;
import java.util.Vector;

/**
 * @author Flavien Vernier
 * A RandomTimer is a Timer that give random timings.  
 * The timings can be generated from multiple random laws.
 * The laws can be Exponential, Poisson, Gaussian or Posibilist
 */
public class RandomTimer implements Timer {
	
	public static enum randomDistribution {
		POISSON, EXP, POSIBILIST, GAUSSIAN;
	}
	
	//private static String randomDistributionString[] = {"POISSON", "EXP", "POSIBILIST", "GAUSSIAN"};
	
	private Random r = new Random();
	private randomDistribution distribution;
	private double rate;
	private double mean;
	private double lolim;
	private double hilim; 
	//private int width; 
	
	
	public static randomDistribution string2Distribution(String distributionName){
		return RandomTimer.randomDistribution.valueOf(RandomTimer.randomDistribution.class, distributionName.toUpperCase());
	}	
	public static String distribution2String(randomDistribution distribution){
		return distribution.name();
	}
	
	/**
	 * Constructor of a RandomTimer for an Exponential or Poisson law
	 * @param distribution The type of random distribution
	 * @param Parameter of the distribution
	 * @throws Exception 
	 */
	public RandomTimer(randomDistribution distribution, double param) throws Exception{
		if(distribution == randomDistribution.EXP ){
			this.distribution = distribution;
			this.rate = param;
			this.mean = 1/param;
			this.lolim = 0;
			this.hilim = Double.POSITIVE_INFINITY;
		}else if(distribution == randomDistribution.POISSON){
			this.distribution = distribution;
			this.rate = Double.NaN;
			this.mean = param;
			this.lolim = 0;
			this.hilim = Double.POSITIVE_INFINITY;
		}else{
			throw new Exception("Bad Timer constructor for selected distribution");
		}
	}
	
	/**
	 * Constructor of a RandomTimer for a Posibilist or Gaussian law
	 * @param distribution The type of random distribution
	 * @param lolim The lower limit
	 * @param hilim The higher limit
	 * @throws Exception 
	 */
	public RandomTimer(randomDistribution distribution, int lolim, int hilim) throws Exception{
		if(distribution == randomDistribution.POSIBILIST || distribution == randomDistribution.GAUSSIAN){
			this.distribution = distribution;
			this.mean = lolim + (hilim - lolim)/2;
			this.rate = Double.NaN;
			this.lolim = lolim;
			this.hilim = hilim;
		}else{
			throw new Exception("Bad Timer constructor for selected distribution");
		}
	}
	
	/**
	 * Getter for the distribution
	 * @return the type of distribution
	 */
	public String getDistribution(){
		return this.distribution.name();
	}
	
	/**
	 * Getter for the distribution's parameters 
	 * @return the parameters of the distribution as a string composed of the type of parameter with its value
	 */
	public String getDistributionParam() {
		if(distribution == randomDistribution.EXP ){
			return "rate: " + this.rate;
		}else if(distribution == randomDistribution.POISSON){
			return "mean: " + this.mean;
		}else if(distribution == randomDistribution.POSIBILIST || distribution == randomDistribution.GAUSSIAN){
			return "lolim: " + this.lolim + " hilim: " + this.hilim;
		}
		
		return "null";
	}
	
	/**
	 * Getter of the mean
	 * @return The mean
	 */
	public double getMean(){
		return this.mean;
	}
	
	public String toString(){
		String s = this.getDistribution();
		switch (this.distribution){
		case POSIBILIST :
			s += " LoLim:" + this.lolim + " HiLim:" + this.hilim;
			break;
		case POISSON :
			s += " mean:" + this.mean;
			break;
		case EXP :
			s += " rate:" + this.rate;
			break;
		case GAUSSIAN :
			s += " LoLim:" + this.lolim + " HiLim:" + this.hilim;
			break;
		}
		
		return s;
	}
	

	/* (non-Javadoc)
	 * @see methodInvocator.Timer#next()
	 */
	@Override
	public Integer next(){
		switch (this.distribution){
		case POSIBILIST :
			return this.nextTimePosibilist();
		case POISSON :
			return this.nextTimePoisson();
		case EXP :
			return this.nextTimeExp();
		case GAUSSIAN :
			return this.nextTimeGaussian();
		}
		return -1; // Theoretically impossible !!!
	}
	
	/*
	 * Equivalent to methodInvocator.RandomTimer#next()
	 * 
	 * @param since has no effect
	 * 
	 * @see methodInvocator.RandomTimer#next(int)
	 */
	/*@Override
	public Integer next(int since){
		return this.next();
	}*/
	
	/**
	 * Function to get the next time using a Posibilist law
	 * Give good mean
	 * Give wrong variance
	 * @return The next time 
	 */
	private int nextTimePosibilist(){
	    return (int)this.lolim + (int)(this.r.nextDouble() * (this.hilim - this.lolim));
	}
	
	/**
	 * Function to get the next time using a Exponential law
	 * Give good mean
	 * Give wrong variance
	 * @return The next time 
	 */
	private int nextTimeExp(){
	    return (int)(-Math.log(1.0 - this.r.nextDouble()) / this.rate);
	}
	

	/**
	 * Function to get the next time using a Poisson law
	 * Give good mean
	 * Give good variance
	 * @return The next time
	 */
	private int nextTimePoisson() {
	    
	    double L = Math.exp(-this.mean);
	    int k = 0;
	    double p = 1.0;
	    do {
	        p = p * this.r.nextDouble();
	        k++;
	    } while (p > L);
	    return k - 1;
	}   		
	    
	/**
	 * Function to get the next time using a Gaussian law
	 * @return The next time
	 */
	private int nextTimeGaussian(){
		return (int)this.lolim + (int)((this.r.nextGaussian() + 1.0)/2.0 * (this.hilim - this.lolim));
	}
	
	
	@Override
	public boolean hasNext() {
		return true;
	}
}
