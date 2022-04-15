package tests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import action.DiscreteAction;
import discreteBehaviorSimulator.Clock;
import junit.framework.Assert;
import timer.OneShotTimer;
import timer.Timer;

public class DiscreteActionTest {
	private static Object object;
	private Method method;		
	private static Timer timmer;
	private Integer lapsTime; 			
	private Logger logger;
	private static Field[] reflexionFields;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DiscreteAction  discreteAction = new DiscreteAction(object,"m",timmer);
		Class<?> reflexionDiscreteAction = discreteAction.getClass();
				reflexionFields = reflexionDiscreteAction.getDeclaredFields();

				for (Field field: reflexionFields) {
					field.setAccessible(true);
				}
			}
	

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	public void t1() throws IllegalArgumentException, IllegalAccessException {
		OneShotTimer  ost = new OneShotTimer(1);
		DiscreteAction  discreteAction = new DiscreteAction(ost,"next",timmer);
		assertEquals(ost,reflexionFields[0].get(discreteAction));
		assertEquals(timmer,reflexionFields[2].get(discreteAction));
		
	}

	@Test
	public void t2() throws IllegalArgumentException, IllegalAccessException {
		OneShotTimer  ost = new OneShotTimer(3);

		DiscreteAction  discreteAction = new DiscreteAction(ost,"next",ost);
		assertEquals(reflexionFields[3].get(discreteAction),null);
		discreteAction.next();
		discreteAction.spendTime(1);
		assertEquals(reflexionFields[3].get(discreteAction),2);

		
	}

	@Test
	public void t3() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException {
		OneShotTimer  ost = new OneShotTimer(2);
		DiscreteAction  discreteAction = new DiscreteAction(ost,"next",ost);
		assertEquals(reflexionFields[1].get(discreteAction),discreteAction.getMethod());
		assertEquals(reflexionFields[1].get(discreteAction),ost.getClass().getDeclaredMethod("next", new Class<?>[0]));	
}
	@Test
	public void t4() throws IllegalArgumentException, IllegalAccessException {
		OneShotTimer  ost = new OneShotTimer(1);
		DiscreteAction  discreteAction = new DiscreteAction(ost,"next",ost);
		assertEquals(reflexionFields[3].get(discreteAction),discreteAction.getCurrentLapsTime());
	}

	@Test
	public void t5() throws IllegalArgumentException, IllegalAccessException {
		OneShotTimer  ost = new OneShotTimer(1);
		DiscreteAction  discreteAction = new DiscreteAction(ost,"next",ost);
		assertEquals(reflexionFields[0].get(discreteAction),discreteAction.getObject());

		
	}

	@Test
	public void t6() {
		Clock clock1 = Clock.getInstance();
		Clock clock2 = Clock.getInstance();
        OneShotTimer ost = new OneShotTimer(4);
        DiscreteAction discreteAction1 = new DiscreteAction(clock1,"getInstance",ost);
        DiscreteAction discreteAction2 = new DiscreteAction(clock2,"getInstance",ost);
        assertEquals(1,discreteAction1.compareTo(discreteAction2));
        
	}

	@Test
	public void t7() {
		Clock clock = Clock.getInstance();
        OneShotTimer ost = new OneShotTimer(5);
        DiscreteAction discreteAction = new DiscreteAction(clock,"getInstance",ost);
        assertEquals("Object : "+ discreteAction.getObject().getClass().getName()+"\n"
        		+ " Method : "+discreteAction.getMethod().getName()+"\n"
        		+ " Stat. : "+ost+"\n"
        		+ " delay: "+ discreteAction.getCurrentLapsTime(),discreteAction.toString());
	}

	@Test
	public void t8() {
		Clock clock = Clock.getInstance();
        OneShotTimer ost = new OneShotTimer(5);
        DiscreteAction discreteAction = new DiscreteAction(clock,"getInstance",ost);
        assertEquals(discreteAction,discreteAction.next());
	}

	@Test
	public void t9() {
		Clock clock = Clock.getInstance();
        OneShotTimer ost = new OneShotTimer(5);
        DiscreteAction discreteAction = new DiscreteAction(clock,"getInstance",ost);
        // Test 3
        assertTrue(discreteAction.hasNext());
        // Test 4
        ost.next();
        assertFalse(discreteAction.hasNext());
        
	}
}

