package timer;
import java.util.Random;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PeriodicTimerTest2 {
	private RandomTimer mol ;
	private PeriodicTimer ptI;
	private PeriodicTimer ptIE;
	private PeriodicTimer ptIP;
	private PeriodicTimer ptIPOS;
	private PeriodicTimer ptIG;
	private PeriodicTimer ptII;
	private PeriodicTimer ptIIE;
	private PeriodicTimer ptIIP;
	private PeriodicTimer ptIIPOS;
	private PeriodicTimer ptIIG;
	private RandomTimer timer;
	private RandomTimer POISSON ;
	private RandomTimer EXP ;
	private RandomTimer POSIBILIST ;
	private RandomTimer GAUSSIAN ;
	
	

	@Before
	public void setUp() throws Exception {
		 
		 POISSON = new RandomTimer(timer.string2Distribution("exp"), 1.1);
		 EXP = new RandomTimer(timer.string2Distribution("exp"), 2.2);
		 POSIBILIST = new RandomTimer(timer.string2Distribution("exp"), 3);
		 GAUSSIAN = new RandomTimer(timer.string2Distribution("exp"), 2.1);
		 
		 ptI = new PeriodicTimer(1);
		 
		 ptIE=new PeriodicTimer(1,EXP);
		 ptIP=new PeriodicTimer(1,POISSON);
		 ptIPOS=new PeriodicTimer(1,POSIBILIST);
		 ptIG=new PeriodicTimer(1,GAUSSIAN);
		 
		 ptII= new PeriodicTimer(1,2);
		 
		 ptIIE=new PeriodicTimer(1,2,EXP);
		 ptIIP=new PeriodicTimer(1,3,POISSON);
		 ptIIPOS=new PeriodicTimer(1,3,POSIBILIST);
		 ptIIG=new PeriodicTimer(1,2,GAUSSIAN);
		

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPeriodicTimerInt() {
		assertEquals(ptI.getPeriod(), 1);
		
				}

	@Test
	public void testPeriodicTimerIntRandomTimer() {
		assertEquals(ptIE.getPeriod(),1);
		assertEquals(ptIP.getPeriod(),1);
		assertEquals(ptIPOS.getPeriod(),1);
		assertEquals(ptIG.getPeriod(),1);
	}

	@Test
	public void testPeriodicTimerIntInt() {
		assertEquals(ptII.getPeriod(),1);
	}

	@Test
	public void testPeriodicTimerIntIntRandomTimer() {
		assertEquals(ptIIE.getPeriod(),1);
		assertEquals(ptIIP.getPeriod(),1);
		assertEquals(ptIIPOS.getPeriod(),1);
		assertEquals(ptIIG.getPeriod(),1);
	}
	@Test
	public void testGetPeriod() {
		assertEquals(ptI.getPeriod(),1);
		assertEquals(ptIE.getPeriod(),1);
		assertEquals(ptIP.getPeriod(),1);
		assertEquals(ptIPOS.getPeriod(),1);
		assertEquals(ptIG.getPeriod(),1);
		assertEquals(ptII.getPeriod(),1);
		assertEquals(ptIIE.getPeriod(),1);
		assertEquals(ptIIP.getPeriod(),1);
		assertEquals(ptIIPOS.getPeriod(),1);
		assertEquals(ptIIG.getPeriod(),1);
		
		
	}

	@Test
	public void testNext() {
		assertTrue(ptI.next() == 1);
		assertTrue(ptIP.next() == 1);
		
	}

	@Test
	public void testHasNext() {
		assertTrue(ptI.hasNext());
		assertTrue(ptIE.hasNext());
		assertTrue(ptIP.hasNext());
		assertTrue(ptIPOS.hasNext());
		assertTrue(ptIG.hasNext());
		assertTrue(ptII.hasNext());
		assertTrue(ptIIE.hasNext());
		assertTrue(ptIIP.hasNext());
		assertTrue(ptIIPOS.hasNext());
		assertTrue(ptIIG.hasNext());
		
	}

}