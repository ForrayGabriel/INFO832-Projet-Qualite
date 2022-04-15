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
import timer.TimeBoundedTimer;
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
	// Before each tests, we create an reflexionFields to get the private DiscreteActionSimulator attributes
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		Class<?> reflexionDAS = dAS.getClass();
		reflexionFields = reflexionDAS.getDeclaredFields();
		
		for (Field field: reflexionFields) {
			field.setAccessible(true);
		}
		
	}
	
	@Test
	void DASta1() {
	// Test of the start function
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
        dAS.start();

        assertTrue(dAS.getRunning());

	 }
	 
	@Test
	void DASto1() {
	// Test of the stop function	
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
        dAS.start();
        dAS.stop();

        assertFalse(dAS.getRunning());
       
	 }

	@Test
	void DASnlt1() {
	// Test of nextLapsTime() by calling DiscreteAction.getCurrentLapsTime()
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
	// Test of nextLapsTime() with none action added	
			DiscreteActionSimulator dAS = new DiscreteActionSimulator();
			TestThread cpt = new TestThread();
			OneShotTimer oneShotTimer = new OneShotTimer(5);
			DiscreteAction testSNL = new DiscreteAction(cpt, "incr", oneShotTimer);
			dAS.start();
			
			assertNull(testSNL.getCurrentLapsTime());
			
		}

	
	@Test
	void DASsnl1() throws IllegalArgumentException, IllegalAccessException {
	// Test of setNbLoop(), if nbLoop = 5, step should be 1
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.start();
		dAS.addAction(testSNL);
		dAS.setNbLoop(5);

		assertEquals(5, reflexionFields[4].get(dAS));
		assertEquals(1, reflexionFields[5].get(dAS));

	}
	
	@Test
	void DASsnl2() throws IllegalArgumentException, IllegalAccessException {
	// Test of setNbLoop(), if nbLoop = 0, step should be -1
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
	// Test of setNbLoop(), if nbLoop = -5, it should create an error
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
	// Test of actionAction() by adding an action to the simulator
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		dAS.addAction(testSNL);
		
		assertEquals(testSNL, ((Vector) reflexionFields[3].get(dAS)).firstElement());

	}
	
	@Test
	void DASaa2() throws IllegalArgumentException, IllegalAccessException {
		// Test of actionAction() by not adding an action to the simulator
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(reflexionFields[0], "incr", oneShotTimer);
		
		assertThrows(Exception.class, () -> {
			((Vector) reflexionFields[3].get(dAS)).firstElement();
		});
	}
	
	@Test
	void DAStt1() {
	//Test to not adding the DiscreteAction
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		TestThread cpt = new TestThread();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(cpt, "incr", oneShotTimer);
		dAS.setNbLoop(5);
		dAS.start();
		
		assertEquals(cpt.getCpt(), 0); //Should be equals 5
		
	}
	
	@Test
	void TestIncr() {
	// Test of the incrementation of the TestThreadClass
		TestThread testThread = new TestThread();
		testThread.incr();

		assertEquals(1, testThread.getCpt());

	 }
	
	
	

}
