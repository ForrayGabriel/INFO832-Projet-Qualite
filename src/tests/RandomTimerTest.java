package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import junit.framework.Assert;
import timer.RandomTimer.randomDistribution;

class RandomTimerTest {
	private Random r = new Random();
	private randomDistribution distribution;
	private double rate;
	private double mean;
	private double lolim;
	private double hilim; 
	private static Field[] reflexionFields;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP,1);
		Class<?> reflexionRandomTimer = randomTimer.getClass();
		reflexionFields = reflexionRandomTimer.getDeclaredFields();

		for (Field field: reflexionFields) {
			field.setAccessible(true);
		}
	}

	@Test
	void ts1511() {
		RandomTimer randomTimer = null;
		randomDistribution exp = randomTimer.string2Distribution("EXP");
		Assert.assertEquals(randomDistribution.EXP,exp );
	}

	@Test
	void ts1512() {
		String m = RandomTimer.distribution2String(randomDistribution.EXP);
		Assert.assertEquals("EXP",m);
	}

	@Test
	void ts1513() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POISSON,1.1);
		Assert.assertEquals(randomDistribution.POISSON,reflexionFields[1].get(randomTimer));
		Assert.assertEquals(Double.NaN,reflexionFields[2].get(randomTimer));
		Assert.assertEquals(1.1,reflexionFields[3].get(randomTimer));
		Assert.assertEquals(0.0,reflexionFields[4].get(randomTimer));
		Assert.assertEquals(Double.POSITIVE_INFINITY,reflexionFields[5].get(randomTimer));
		
		
	}

	@Test
	void ts1514() throws Exception {
		RandomTimer randomTimer1 = new RandomTimer(randomDistribution.POSIBILIST,1,1);
		Assert.assertEquals(randomDistribution.POSIBILIST,reflexionFields[1].get(randomTimer1));
		Assert.assertEquals(Double.NaN,reflexionFields[2].get(randomTimer1));
		Assert.assertEquals(1.0,reflexionFields[3].get(randomTimer1));
		Assert.assertEquals(1.0,reflexionFields[4].get(randomTimer1));
		Assert.assertEquals(1.0,reflexionFields[5].get(randomTimer1));
		
		
	}

	@Test
	void ts1516() throws Exception {
		RandomTimer randomTimer2 = new RandomTimer(randomDistribution.POSIBILIST,1,1);
		Assert.assertEquals("POSIBILIST",randomTimer2.getDistribution());
	}

	@Test
	void ts1517() throws Exception {
		RandomTimer randomTimer5 = new RandomTimer(randomDistribution.EXP,1.1);
		RandomTimer randomTimer6 = new RandomTimer(randomDistribution.POISSON,1.1);
		RandomTimer randomTimer7 = new RandomTimer(randomDistribution.GAUSSIAN,1,1);
		Assert.assertEquals("rate: 1.1",randomTimer5.getDistributionParam());
		Assert.assertEquals("mean: 1.1",randomTimer6.getDistributionParam());
		Assert.assertEquals("lolim: 1.0 hilim: 1.0",randomTimer7.getDistributionParam());
		
	}

	@Test
	void ts1518() throws Exception {
		RandomTimer randomTimer8 = new RandomTimer(randomDistribution.POISSON,1.1);
		Assert.assertEquals(1.1, randomTimer8.getMean());
	}

	@Test
	void ts1519() throws Exception {
		RandomTimer randomTimer9 = new RandomTimer(randomDistribution.EXP,1);
		RandomTimer randomTimer10 = new RandomTimer(randomDistribution.POISSON,1.1);
		RandomTimer randomTimer11 = new RandomTimer(randomDistribution.GAUSSIAN,1,1);
		RandomTimer randomTimer12 = new RandomTimer(randomDistribution.POSIBILIST,1,1);
		Assert.assertEquals("EXP rate:1.0", randomTimer9.toString()); 
		Assert.assertEquals("POISSON mean:1.1", randomTimer10.toString());
		Assert.assertEquals("GAUSSIAN LoLim:1.0 HiLim:1.0", randomTimer11.toString());
		Assert.assertEquals("POSIBILIST LoLim:1.0 HiLim:1.0", randomTimer12.toString());
		
	}

	@Test
	void ts1520() throws Exception {
		RandomTimer randomTimer13 = new RandomTimer(randomDistribution.POSIBILIST,1,1);
		Assert.assertEquals(true, randomTimer13.hasNext());
	}

}
