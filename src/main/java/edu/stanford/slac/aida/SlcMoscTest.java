package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.BOOLEAN;
import static edu.stanford.slac.aida.utils.AidaType.TABLE;

/**
 * This class is used to test the SLC Master Oscillator AIDA-PVA provider
 */
public class SlcMoscTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC Master Oscillator TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator get method");
            channel("MASTEROSC:VAL", "VAL").get();
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator get method for double");
            getWithNoArguments("MASTEROSC:VAL", BOOLEAN, "VAL");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator get method for table");
            getWithNoArguments("MASTEROSC:VAL", TABLE, "VAL");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set");
            setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is HER");
            channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "HER")
                    .set(1.0f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is LER");
            channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "LER")
                    .set(1.0f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set");
            setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
