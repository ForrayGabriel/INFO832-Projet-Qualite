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
	void Cgi1() {
		Clock result = Clock.getInstance();
		Clock expected = clock;
		assertEquals(expected, result);
	}
	
	@Test
	void Civ1() {
		clock.setVirtual(true);
		Boolean expected = true;
		Boolean result = clock.isVirtual();
		assertEquals(expected, result);
	}
	
	@Test
	void Civ2() {
		clock.setVirtual(false);
		Boolean expected = false;
		Boolean result = clock.isVirtual();
		assertEquals(expected, result);
	}

	@Test
	void Csv1() {
		clock.setVirtual(true);
		assertEquals(clock.isVirtual(),true);
	}
	
	@Test
	void Csv2() {
		clock.setVirtual(false);
		assertEquals(clock.isVirtual(),false);
	}
	
	@Test
	void Csnj1() {
		try {
			clock.setNextJump(0);
		}
		catch(Exception e) {
			fail("there is a exception : " + e);
		}
		assertTrue(true);
	}
	
	@Test
	void Csnj2() {
		try {
			clock.setNextJump(-1);
		}
		catch(Exception e) {
			fail("there is a exception : " + e);
		}
		assertTrue(true);
	}
	
	@Test
	void Csnj3() {
		try {
			clock.setNextJump(999999);
		}
		catch(Exception e) {
			fail("there is a exception : " + e);
		}
		assertTrue(true);
	}

	@Test
	void Cgt1() {
		clock.setVirtual(true);
		long result = clock.getTime();
		long expected = 0;
		assertEquals(expected, result);
		

	}
	
	@Test
	void Cgt2() {
		clock.setVirtual(false);
		long result2 = clock.getTime();
		long expected2 = new Date().getTime();
		assertEquals(expected2, result2);

	}

	@Test
	void Cura1() {		
		try {
			clock.unlockReadAccess();	
		}
		catch(Exception e) {
			fail("there is a exception : " + e);
		}
		assertTrue(true);
	}


	@Test
	void Cts1() {
		String expected = "0";
		String result = clock.toString();
		assertEquals(expected, result);
	}


}
