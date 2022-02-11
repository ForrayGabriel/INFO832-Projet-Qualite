/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import timer.*;

/**
 * @author Gabriel 
 * Test of the merged Timer
 */
class MergedTimerTest {

	@Test
	void mt1() {
		// We create two timer, both having next
		PeriodicTimer periodic1 = new PeriodicTimer(10);
		PeriodicTimer periodic2 = new PeriodicTimer(20);
		MergedTimer mgTimer = new MergedTimer(periodic1, periodic2);
		assertTrue(mgTimer.hasNext());
	}
	
	@Test
	void mt2() {
		// We create two timer, one of which doesn't have next
		PeriodicTimer periodic = new PeriodicTimer(10);
		OneShotTimer oneshot = new OneShotTimer(10);
		oneshot.next();
		MergedTimer mgTimer = new MergedTimer(periodic, oneshot);
		assertFalse(mgTimer.hasNext());
		
	}
	
	@Test
	void mt3() {
		// We create two timer, both doesn't have next
		OneShotTimer oneshot1 = new OneShotTimer(10);
		OneShotTimer oneshot2 = new OneShotTimer(10);
		oneshot1.next();
		oneshot2.next();
		MergedTimer mgTimer = new MergedTimer(oneshot1, oneshot2);
		assertFalse(mgTimer.hasNext());
		
	}
	
	@Test
	void mt4() {
		// We create two timer, both having next
		PeriodicTimer periodic1 = new PeriodicTimer(10);
		PeriodicTimer periodic2 = new PeriodicTimer(20);
		MergedTimer mgTimer = new MergedTimer(periodic1, periodic2);
		assertEquals(mgTimer.next(), 30);
	}
	
	@Test
	void mt5() {
		// We create two timer, one of which doesn't have next
		PeriodicTimer periodic = new PeriodicTimer(10);
		OneShotTimer oneshot = new OneShotTimer(10);
		oneshot.next();
		MergedTimer mgTimer = new MergedTimer(periodic, oneshot);
		assertNull(mgTimer.next());	
	}
	
	@Test
	void mt6() {
		// We create two timer, both doesn't have next
		OneShotTimer oneshot1 = new OneShotTimer(10);
		OneShotTimer oneshot2 = new OneShotTimer(10);
		oneshot1.next();
		oneshot2.next();
		MergedTimer mgTimer = new MergedTimer(oneshot1, oneshot2);
		assertNull(mgTimer.next());	
	}

}
