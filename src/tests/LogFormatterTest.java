package tests;

import org.junit.jupiter.api.Test;

import discreteBehaviorSimulator.LogFormatter;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Quentin P
 * Test of the DiscreteActionSimulator
 */

public class LogFormatterTest {

    private LogFormatter logForm = new LogFormatter();
    
    @Test
    public void LFf1() {
    // Test of a logForm WARNING with a message	
        LogRecord logRec    = new LogRecord(Level.WARNING, "msg");
        String formatted = this.logForm.format(logRec);
        String dateFormatted = formatted.split(": ")[0];
        
        assertTrue(dateFormatted instanceof String);
    }
    
    @Test
    public void LFf2() {
    // Test of a logForm WARNING with a message	empty
        LogRecord logRec    = new LogRecord(Level.WARNING, "");
        String formatted = this.logForm.format(logRec);
        String dateFormatted = formatted.split(": ")[0];
        
        assertTrue(dateFormatted instanceof String);
    }
    
    @Test
    public void LFgh1() {
    // logForm.getHead() only return ""
        assertEquals("", logForm.getHead(null));
    }
    
    @Test
    public void LFgt1() {
    // logForm.getTail() only return ""    
        assertEquals("", logForm.getTail(null));
    }
    
    



}