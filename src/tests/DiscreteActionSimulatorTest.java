package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import action.DiscreteAction;
import action.DiscreteActionInterface;
import discreteBehaviorSimulator.DiscreteActionSimulator;
import timer.DateTimer;
import timer.OneShotTimer;
import timer.PeriodicTimer;
import timer.Timer;

class TestThread {
	
	private int cpt;
	private int step;
	
	

	public TestThread() {
		super();
		this.cpt = 0;
	}
	
	public void incr() {
		this.cpt++;
		System.out.println("J'INCREMENTE");
	}

	public int getCpt() {
		return cpt;
	}

	public void setCpt(int cpt) {
		this.cpt = cpt;
	}
	
	public int getStep() {
		return step;
	}

	public void setStep() {
		if(this.cpt>0){
			this.step = 1;
		}else{ // running infinitely
			this.step = -1;
		}
	}

	
	
	
}

class DiscreteActionSimulatorTest {
	
	private DiscreteActionSimulator dAS;

	@BeforeEach
	private void setUp() {	
		
		
	}
	
	 @Test
	 void DAS1() {
        DiscreteActionSimulator discrAct = new DiscreteActionSimulator();
        discrAct.start();

        // testing normal behavior
        assertTrue(discrAct.getRunning());
	 }
	 
	 @Test
	 void stopTest() {
        DiscreteActionSimulator discrAct = new DiscreteActionSimulator();
        discrAct.start();
        discrAct.stop();

        // testing normal behavior
        assertFalse(discrAct.getRunning());
	 }

	
	@Test
	void DASsnl1() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		TestThread cpt = new TestThread();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(cpt, "incr", oneShotTimer);
		dAS.addAction(testSNL); //On ajoute la DiscreteAction lié à la méthode incr
		dAS.setNbLoop(5); //En s'exécutant 5 fois, getCtp( devrait être à 5
		dAS.start();
		cpt.setStep(); //getCpt() > 0 donc getStep() = 1
		assertEquals(5, cpt.getCpt()); //Les attentes ne se vérifient pas
		assertEquals(1, cpt.getStep());
	}
	
	@Test
	void DASsnl2() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		TestThread cpt = new TestThread();
		DiscreteAction testSNL = new DiscreteAction((Object) cpt, "incr", (Timer) new PeriodicTimer(1));
		cpt.setStep();
		dAS.setNbLoop(0);
		dAS.addAction(testSNL);
		dAS.start();
		
		assertEquals(cpt.getCpt(), 0);
		assertEquals(cpt.getStep(), -1);
	}
	
	@Test
	void DASsnl3() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		TestThread cpt = new TestThread();
		DiscreteAction testSNL = new DiscreteAction((Object) cpt, "incr", (Timer) new PeriodicTimer(1));
		cpt.setStep();
		dAS.setNbLoop(-5);
		dAS.addAction(testSNL);
		dAS.start();
		
		assertEquals(cpt.getCpt(), 0);
		assertEquals(cpt.getStep(), -1);
	}
	
	@Test
	void DASaa1() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		TestThread cpt = new TestThread();
		DiscreteAction testSNL = new DiscreteAction((Object) cpt, "incr", (Timer) new PeriodicTimer(1));
		dAS.addAction(testSNL);
		//assertEquals(dAS.actionsList[0], act1); //Nécessite l'implémentation de getters
	}
	
	@Test
	void DASaa2() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		dAS.setNbLoop(-5);
		//assertEquals(0, dAS.nbLoop); //Nécessite l'implémentation de getters
		//assertEquals(-1, dAS.step); //Nécessite l'implémentation de getters
	}
	
	@Test
	void DASra1() {
		DiscreteActionSimulator dAS = new DiscreteActionSimulator();
		TestThread cpt = new TestThread();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteAction testSNL = new DiscreteAction(cpt, "incr", oneShotTimer);
		dAS.addAction(testSNL);
		dAS.start();
		
	}

}
