package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
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
	void ts5() throws IllegalArgumentException, IllegalAccessException {
		Assert.assertEquals(true, oneShotTimer.hasNext());
		Assert.assertEquals(at1, reflexionFields[0].get(oneShotTimer));
	}
	
	@Test
	void ts6() throws IllegalArgumentException, IllegalAccessException {
		Assert.assertEquals(true, oneShotTimer.hasNext());
	}
	
	@Test
	void ts7() throws IllegalArgumentException, IllegalAccessException {
		Integer d = oneShotTimer.next();
		Assert.assertEquals(at1, d);
		Assert.assertEquals(false, oneShotTimer.hasNext());
		Assert.assertEquals(null, reflexionFields[0].get(oneShotTimer));
	}
}
