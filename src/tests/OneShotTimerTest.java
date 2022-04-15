package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import timer.OneShotTimer;

import java.lang.reflect.Field;
import java.util.Iterator;

class OneShotTimerTest {
    private static Integer at1;
    private static boolean hasNext;
    private static OneShotTimer oneShotTimer;
    private static Field[] reflexionFields;
	@BeforeAll
	static void setUpBeforeClass() {
		at1 = 1;
		oneShotTimer = new OneShotTimer(at1);
		Class<?> reflexionOneShotTimer = oneShotTimer.getClass();
		reflexionFields = reflexionOneShotTimer.getDeclaredFields();

		for (Field field: reflexionFields) {
			field.setAccessible(true);
		}
	}

	@Test
	void testOneShotTimerforint() throws IllegalArgumentException, IllegalAccessException {
		
		assertTrue(oneShotTimer.hasNext());
		assertEquals(at1, reflexionFields[0].get(oneShotTimer));
	}
	@Test
	void testhasNext() throws IllegalArgumentException, IllegalAccessException {
		assertTrue(oneShotTimer.hasNext());
	}
	@Test
	void testnext() throws IllegalArgumentException, IllegalAccessException {
		Integer d = oneShotTimer.next();
		assertEquals(at1, d);
		assertFalse(oneShotTimer.hasNext());
		assertNull(reflexionFields[0].get(oneShotTimer));
	}
}
