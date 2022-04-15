package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import discretebehavior.Clock;
import timer.DateTimer;
import timer.OneShotTimer;
import timer.Timer;

class DiscreteActionDependentTest {

	// Test 23
	@Test
	void T1() throws NoSuchMethodException, SecurityException {
		Clock clock = Clock.getInstance();
		Timer timerOn = new OneShotTimer(10);
		Timer timerOff = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff = new DiscreteActionOnOffDependent(clock,"getInstance",timerOn,"getTime",timerOff);
		actionOnOff.nextAction();
		assertEquals(clock.getClass().getDeclaredMethod("getInstance", new Class<?>[0]),actionOnOff.getMethod());
		actionOnOff.nextAction();
		assertEquals(clock.getClass().getDeclaredMethod("getTime", new Class<?>[0]),actionOnOff.getMethod());
	}

	// Test 24
	@Test
	void T2() {
		Clock clock = Clock.getInstance();
		Timer timerOn = new OneShotTimer(10);
		Timer timerOff = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff = new DiscreteActionOnOffDependent(clock,"getInstance",timerOn,"getTime",timerOff);
		actionOnOff.spendTime(0);
	    assertEquals(null,actionOnOff.getCurrentLapsTime());
	}

	// Test 25
	@Test
	void T3() throws NoSuchMethodException, SecurityException {
		Clock clock = Clock.getInstance();
		Timer timerOn = new OneShotTimer(10);
		Timer timerOff = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff = new DiscreteActionOnOffDependent(clock,"getInstance",timerOn,"getTime",timerOff);
		assertEquals(clock.getClass().getDeclaredMethod("getTime", new Class<?>[0]),actionOnOff.getMethod());
	}

	// Test 26
	@Test
	void T4() {
		Clock clock = Clock.getInstance();
		Timer timerOn = new OneShotTimer(10);
		Timer timerOff = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff = new DiscreteActionOnOffDependent(clock,"getInstance",timerOn,"getTime",timerOff);
		assertEquals(null,actionOnOff.getCurrentLapsTime());
	}

	// Test 27
	@Test
	void T5() {
		Clock clock = Clock.getInstance();
		Timer timerOn = new OneShotTimer(10);
		Timer timerOff = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff = new DiscreteActionOnOffDependent(clock,"getInstance",timerOn,"getTime",timerOff);
		assertSame(clock,actionOnOff.getObject());
	}
	
	// Test 28
	@Test
	void T6() {
		Clock clock1 = Clock.getInstance();
		Timer timerOn1 = new OneShotTimer(15);
		Timer timerOff1 = new OneShotTimer(25);
		DiscreteActionOnOffDependent actionOnOff1 = new DiscreteActionOnOffDependent(clock1,"getInstance",timerOn1,"getTime",timerOff1);
		Clock clock2 = Clock.getInstance();
		Timer timerOn2 = new OneShotTimer(10);
		Timer timerOff2 = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff2 = new DiscreteActionOnOffDependent(clock2,"getInstance",timerOn2,"getTime",timerOff2);
		assertEquals(1,actionOnOff1.compareTo(actionOnOff2));
	}

	// Test 29
	@Test
	void T7() throws NoSuchMethodException, SecurityException {
		Clock clock = Clock.getInstance();
		Timer timerOn = new OneShotTimer(10);
		Timer timerOff = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff = new DiscreteActionOnOffDependent(clock,"getInstance",timerOn,"getTime",timerOff);
		DiscreteActionInterface result = actionOnOff.next();
		assertEquals(clock.getClass().getDeclaredMethod("getInstance", new Class<?>[0]),result.getMethod());
	}

	
	@Test
	void T8() {
		Clock clock = Clock.getInstance();
		Timer timerOn = new OneShotTimer(10);
		Timer timerOff = new OneShotTimer(20);
		DiscreteActionOnOffDependent actionOnOff = new DiscreteActionOnOffDependent(clock,"getInstance",timerOn,"getTime",timerOff);
		// Test 30
		assertTrue(actionOnOff.hasNext());
		timerOn.next();
		// Test 31
		assertTrue(actionOnOff.hasNext());
		timerOff.next();
		// Test 32
		assertFalse(actionOnOff.hasNext());
	}
	
	@Test
	void T9() {
		Clock clock = Clock.getInstance();
		// Test 33
		TreeSet<Integer> datesOn1 = new TreeSet<Integer>();
		TreeSet<Integer> datesOff1 = new TreeSet<Integer>();
        datesOn1.add(new Integer(4));
        datesOff1.add(new Integer(5));
        DiscreteActionOnOffDependent actionOnOff1 = new DiscreteActionOnOffDependent(clock,"getInstance",datesOn1,"getTime",datesOff1);
        assertSame(clock,actionOnOff1.getObject());
        
        // Test 34
        TreeSet<Integer> datesOn2 = new TreeSet<Integer>();
		TreeSet<Integer> datesOff2 = new TreeSet<Integer>();
        datesOn2.add(new Integer(5));
        datesOff2.add(new Integer(4));
        DiscreteActionOnOffDependent actionOnOff2 = new DiscreteActionOnOffDependent(clock,"getInstance",datesOn2,"getTime",datesOff2);
        assertSame(clock,actionOnOff2.getObject());
        

	
	}

}