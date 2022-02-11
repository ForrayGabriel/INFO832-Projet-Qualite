package tests;

import static org.junit.jupiter.api.Assertions.*;
import timer.*;

import org.junit.jupiter.api.Test;

class TimeBoundedTimerTest {

	/**
	 * Test the boolean hasNext when the timer has a normal behavior
	 */
	@Test
	void tbt1() {
		PeriodicTimer periodic = new PeriodicTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodic, 3);
		assertTrue(tbTimer.hasNext());
	}
	
	/**
	 * Test the boolean hasNext when the last value of the timer is on the end limit
	 */
	@Test
	void tbt2() {
		PeriodicTimer periodic = new PeriodicTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodic, 2,3);
		tbTimer.next();
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test the boolean hasNext when the last value of the timer is beyond the end limit
	 */
	@Test
	void tbt3() {
		PeriodicTimer periodic = new PeriodicTimer(10);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodic, 5,15);
		tbTimer.next();
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test the boolean hasNext when the timer stops before the start value
	 */
	@Test
	void tbt4() {
		OneShotTimer oneshot = new OneShotTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2);
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test the boolean hasNext when the first value of the timer if beyond the end limit
	 */
	@Test
	void tbt5() {
		OneShotTimer oneshot = new OneShotTimer(4);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2, 3);
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test what value the timer return in its normal behavior
	 */
	@Test
	void tbt6() {
		PeriodicTimer periodic = new PeriodicTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodic, 3,6);
		assertEquals(tbTimer.next(), 3);
		assertEquals(tbTimer.next(), 1);
		assertEquals(tbTimer.next(), 1);
		assertNull(tbTimer.next());
	}
	
	/**
	 * Test what value the timer return when the timer stops before the start value
	 */
	@Test
	void tbt7() {
		OneShotTimer oneshot = new OneShotTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2);
		assertNull(tbTimer.next());
	}
	
	/**
	 * Test what value the timer return when the first value of the timer if beyond the end limit
	 */
	@Test
	void tbt8() {
		OneShotTimer oneshot = new OneShotTimer(4);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2, 3);
		assertNull(tbTimer.next());
	}
	
	/**
	 * Test if the constructor return an error if the start time is bigger than the end time
	 */
	@Test
	void tbt9() {
		OneShotTimer oneshot = new OneShotTimer(4);
		assertThrows(Exception.class, () -> {
			new TimeBoundedTimer(oneshot, 4, 3);
		});		
	}


}
