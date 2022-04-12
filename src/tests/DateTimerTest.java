package timer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DateTimerTest {
	static private Vector<Integer> vec1;
	static private DateTimer datetime1;
	static private Iterator<Integer> it;
	static private TreeSet<Integer> ts;
	static private DateTimer datetime2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		vec1 = new Vector<>();
    	vec1.add(7);
    	vec1.add(8);
    	it = vec1.iterator();
    	datetime1 = new DateTimer(vec1);
    	ts = new TreeSet<>();
    	ts.add(1);
    	ts.add(2);
    	ts.add(3);
    	datetime2 = new DateTimer(ts);
	}


	@Test
	void testDateTimerTreeSetOfInteger() {
		int t = datetime2.it.next();
		Assert.assertEquals(1,t);
	}

	@Test
	void testDateTimerVectorOfInteger() {
		Assert.assertEquals(vec1, this.datetime1.lapsTimes);
	}

	@Test
	void testHasNext() {
		Assert.assertEquals(true, DateTimerTest.datetime1.hasNext());
	}

	@Test
	void testNext() {
		int value = DateTimerTest.datetime1.next();
		Assert.assertEquals(7, value);
	}

}
