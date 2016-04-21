package test;

import entities.NipCode;
import junit.framework.TestCase;

import static org.junit.Assert.*;

public class NipCodeTest extends TestCase {
    @org.junit.Test
    public void testIsValid() throws Exception {
        // Correct NIP code
        assertTrue(NipCode.isValid("106-00-00-062"));

        // Same but without hyphens
        assertTrue(NipCode.isValid("106-00-00-062"));

        // Invalid code
        assertFalse(NipCode.isValid("106-00-00-063"));
    }
}