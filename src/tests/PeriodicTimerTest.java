package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import timer.PeriodicTimer;
import timer.RandomTimer;
import timer.RandomTimer.randomDistribution;

class PeriodicTimerTest {
	private static int period1;
	private static int at;
	private static RandomTimer moreOrLess1 = null;
	private static PeriodicTimer periodicTimer1;
	private static PeriodicTimer periodicTimer2;
	private static PeriodicTimer periodicTimer3;
	private static Field[] reflexionFields;
	private static Field[] reflexionFields1;
	@SuppressWarnings("deprecation")
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		period1 = 1;
		at = 1;
		moreOrLess1 = new RandomTimer(randomDistribution.EXP,1);
		periodicTimer1 = new PeriodicTimer(2);
		periodicTimer2 = new PeriodicTimer(at,moreOrLess1);
		Class<?> reflexionPeriodicTimer = periodicTimer1.getClass();
		reflexionFields = reflexionPeriodicTimer.getDeclaredFields();
     
		for (Field field: reflexionFields) {
			field.setAccessible(true);
		}
		Class<?> reflexionPeriodicTimer1 = periodicTimer2.getClass();
		reflexionFields1 = reflexionPeriodicTimer1.getDeclaredFields();
     
		for (Field field: reflexionFields1) {
			field.setAccessible(true);
		}
	}

	@Test
	void testperiodictimerforint() throws IllegalArgumentException, IllegalAccessException {
		assertEquals(2,reflexionFields[1].get(periodicTimer1));
		assertEquals(2,reflexionFields[0].get(periodicTimer1));	
	}
    @Test
    void testPeriodTimerformoreorless() throws IllegalArgumentException, IllegalAccessException {
    	int period = (int) reflexionFields[0].get(periodicTimer2);
    	RandomTimer moreorless = (RandomTimer) reflexionFields[2].get(periodicTimer2);
    	int next = (int) reflexionFields[1].get(periodicTimer2);
    	System.out.println(period);
    }
}
