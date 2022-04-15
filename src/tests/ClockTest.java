package tests;

import static org.junit.jupiter.api.Assertions.*;
import discreteBehaviorSimulator.Clock;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClockTest {	
	private Clock clock;
	private int nextJ;
	
	@BeforeEach
	void setUp() {
		clock = Clock.getInstance();
	}
	
	@Test
	// numero 40
	void testTrue() {
		assertTrue(true);
		
	}

	@Test 
	// numero 35
	void testGetInstance() {
		Clock result = Clock.getInstance();
		Clock expected = clock;
		assertEquals(expected, result);
	}
	
	@Test
	// numero 38
	void testIsVirtual() {
		clock.setVirtual(true);
		Boolean expected = true;
		Boolean result = clock.isVirtual();
		assertEquals(expected, result);
	}

	@Test
	// numero 39
	void testSetVirtual() {
		clock.setVirtual(false);
		assertEquals(clock.isVirtual(),false);
	}
	
	@Test
	// numero 40
	void testSetNextJump() {
		try {
			clock.setNextJump(nextJ);
			
		}
		catch(Exception e) {
			fail("there is a exception : " + e);
		}
		assertTrue(true);
		
	}

	@Test
	// numero 43
	void testGetTime() {
		clock.setVirtual(true);
		long result = clock.getTime();
		long expected = 0;
		assertEquals(expected, result);
	
	// numero 44
		clock.setVirtual(false);
		long result2 = clock.getTime();
		long expected2 = new Date().getTime();
		assertEquals(expected2, result2);

	}

	@Test
	// numero 45 et 46
	void testUnlockReadAccess() {		
		try {
			clock.unlockReadAccess();	
		}
		catch(Exception e) {
			fail("there is a exception : " + e);
		}
		assertTrue(true);
	}


	@Test
	// numero 47
	void testToString() {
		String expected = "0";
		String result = clock.toString();
		assertEquals(expected, result);
	}


}
