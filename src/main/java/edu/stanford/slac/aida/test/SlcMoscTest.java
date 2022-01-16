/**
 * @file
 * @brief SLC Master Oscilloscope Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import java.util.Arrays;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.test.utils.AidaType.AIDA_DOUBLE;
import static edu.stanford.slac.aida.test.utils.AidaType.AIDA_TABLE;

/**
 * This class is used to test the SLC Master Oscillator AIDA-PVA provider
 */
public class SlcMoscTest {
    /**
     * Main entry point to the Master Oscillator test
     *
     * @param args command line arguments -c for color, list of numbers to select test numbers
     */
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        testSuiteHeader("AIDA-PVA SLC Master Oscillator TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of Master Oscillator get method");
            getRequest("MASTEROSC:VAL", AIDA_TABLE, "VAL");
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of Master Oscillator get method for double");
            getRequest("MASTEROSC:VAL", AIDA_DOUBLE, "VAL");
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of Master Oscillator get method for table");
            getRequest("MASTEROSC:VAL", AIDA_TABLE, "VAL");
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of Master Oscillator set");
            setRequest("MASTEROSC:VAL", 0.328f);
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is HER");
            request("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "HER")
                    .set(1.0f);
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is LER");
            request("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "LER")
                    .set(1.0f);
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of Master Oscillator set");
            setRequest("MASTEROSC:VAL", 0.328f);
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
