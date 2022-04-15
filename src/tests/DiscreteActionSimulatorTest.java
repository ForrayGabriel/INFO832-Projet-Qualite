package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.Vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import discreteBehaviorSimulator.Clock;
import action.DiscreteAction;
import action.DiscreteActionInterface;
import discreteBehaviorSimulator.DiscreteActionSimulator;
import timer.DateTimer;
import timer.OneShotTimer;
import timer.PeriodicTimer;
import timer.Timer;

/**
 * @author Quentin P
 * Test of the DiscreteActionSimulator
 */


// We create a TestThread class to simulate the Simulator and get private attributes
class TestThread {
	
	private int cpt; // Represent nbLoop

	public TestThread() {
		super();
		this.cpt = 0;
	}
	
	public void incr() {
		this.cpt++;
	}

	public int getCpt() {
		return cpt;
	}
	
	
	
}

class DiscreteActionSimulatorTest {
	
	private DiscreteActionSimulator dAS;
	private static Field[] reflexionFields;

	@BeforeEach
	private void setUp() {	
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		Class<?> reflexionDAS = dAS.getClass();
		reflexionFields = reflexionDAS.getDeclaredFields();
		
		for (Field field: reflexionFields) {
			field.setAccessible(true);
		}
		
	}
	
	 @Test
	 void DASta1() {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
        dAS.start();

        // testing normal behavior
        assertTrue(dAS.getRunning());

	 }
	 
	 @Test
	 void DASto1() {
		
        DiscreteActionSimulator dAS = new DiscreteActionSimulator();
        dAS.start();
        dAS.stop();

        // testing normal behavior
        assertFalse(dAS.getRunning());
       
	 }
	 
	 @Test
		void DASnlt1() {
			
			DiscreteActionSimulator dAS = new DiscreteActionSimulator();
			TestThread cpt = new TestThread();
			OneShotTimer oneShotTimer = new OneShotTimer(5);
			DiscreteAction testSNL = new DiscreteAction(cpt, "incr", oneShotTimer);
			dAS.addAction(testSNL); //On ajoute la DiscreteAction li� � la m�thode incr
			dAS.start();
			
			assertEquals(5, testSNL.getCurrentLapsTime());
			
		}
	 
	 @Test
		void DASnlt2() {
			
			DiscreteActionSimulator dAS = new DiscreteActionSimulator();
			TestThread cpt = new TestThread();
			OneShotTimer oneShotTimer = new OneShotTimer(5);
			DiscreteAction testSNL = new DiscreteAction(cpt, "incr", oneShotTimer);
			dAS.start();
			
			assertEquals(null, testSNL.getCurrentLapsTime());
			
		}

	
	@Test
	void DASsnl1() throws IllegalArgumentException, IllegalAccessException {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.start();
		dAS.addAction(testSNL); //On ajoute la DiscreteAction li� � la m�thode incr
		dAS.setNbLoop(5); //En s'ex�cutant 5 fois, getCtp( devrait �tre � 5

		assertEquals(5, reflexionFields[4].get(dAS)); //Les attentes ne se v�rifient pas
		assertEquals(1, reflexionFields[5].get(dAS));

	}
	
	@Test
	void DASsnl2() throws IllegalArgumentException, IllegalAccessException {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.setNbLoop(0);
		dAS.addAction(testSNL);
		dAS.start();
		
		assertEquals(0, reflexionFields[4].get(dAS));
		assertEquals(-1, reflexionFields[5].get(dAS));

	}
	
	@Test
	void DASsnl3() throws IllegalArgumentException, IllegalAccessException {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.setNbLoop(-5);
		dAS.addAction(testSNL);
		dAS.start();
		
		assertEquals(0, reflexionFields[4].get(dAS));
		assertEquals(-1, reflexionFields[5].get(dAS));

	}
	
	@Test
	void DASaa1() throws IllegalArgumentException, IllegalAccessException {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		
		dAS.addAction(testSNL);
		assertEquals(testSNL, ((Vector) reflexionFields[3].get(dAS)).firstElement());

	}
	
	@Test
	void DASaa2() throws IllegalArgumentException, IllegalAccessException {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.addAction(testSNL);
		assertEquals(testSNL, ((Vector) reflexionFields[3].get(dAS)).firstElement());
	}
	
	
	@Test
	void DASMultiAction() throws IllegalArgumentException, IllegalAccessException {
		
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL1 = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		DiscreteAction testSNL2 = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		DiscreteAction testSNL3 = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		
		dAS.addAction(testSNL1); //On ajoute la DiscreteAction li� � la m�thode incr
		dAS.addAction(testSNL2);
		dAS.addAction(testSNL3);
		dAS.start();
		dAS.setNbLoop(5); //En s'ex�cutant 5 fois, getCtp( devrait �tre � 5
		
		assertEquals(5, reflexionFields[4].get(dAS)); //Les attentes ne se v�rifient pas
		assertEquals(1, reflexionFields[5].get(dAS));
		dAS.setNbLoop(-5);
		//assertEquals(0, dAS.nbLoop); //N�cessite l'impl�mentation de getters
		//assertEquals(-1, dAS.step); //N�cessite l'impl�mentation de getters
	}
	
	@Test
    public void toStringTest() {
        DiscreteActionSimulator dAS = new DiscreteActionSimulator();
        assertTrue(dAS.toString() instanceof String);
    }
	
	@Test
	void DASra1() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		dAS.start();
		
	}
	
	@Test
	void DASra2() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		TestThread cpt = new TestThread();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(cpt, "incr", oneShotTimer);
		dAS.setNbLoop(5);
		dAS.start();
		
		assertEquals(cpt.getCpt(), 0);
		
	}
	
	@Test
	void DASra3() throws IllegalArgumentException, IllegalAccessException {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.addAction(testSNL);
		DiscreteActionInterface currentAction = (DiscreteActionInterface) ((Vector) reflexionFields[3].get(dAS)).firstElement();
		dAS.start();
		assertFalse(currentAction.hasNext());
		
	}
	
	@Test
	void DASra4() throws IllegalArgumentException, IllegalAccessException {
		
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL1 = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		DiscreteAction testSNL2 = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		DiscreteAction testSNL3 = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.addAction(testSNL1);
		dAS.addAction(testSNL2);
		dAS.addAction(testSNL3);
		
		assertTrue(reflexionFields[2].get(dAS) != null);
		
	}
	
	@Test
	 void TestIncr() {
		
		TestThread testThread = new TestThread();
		testThread.incr();

		// testing normal behavior
		assertEquals(1, testThread.getCpt());

	 }
	
	
	

}
