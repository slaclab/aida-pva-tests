package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.DOUBLE;
import static edu.stanford.slac.aida.utils.AidaType.TABLE;

/**
 * This class is used to test the SLC Master Oscillator AIDA-PVA provider
 */
public class SlcMoscTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC Master Oscillator TESTS");

        Integer testNumber = 0, testId = 0;
        var argc = 0;
        if (args.length != argc) {
            // Optionally allow color flag to enable tests in color
            if (args[argc].equals("-color") || args[argc].equals("-c")) {
                NO_COLOR_FLAG = false;
                argc++;
            }
            if (args.length != argc) {
                testNumber = Integer.valueOf(args[argc]);
            }
        }

        // 01
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator get method");
            getWithNoArguments("MASTEROSC:VAL", TABLE, "VAL");
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator get method for double");
            getWithNoArguments("MASTEROSC:VAL", DOUBLE, "VAL");
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator get method for table");
            getWithNoArguments("MASTEROSC:VAL", TABLE, "VAL");
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set");
            setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is HER");
            channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "HER")
                    .set(1.0f);
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is LER");
            channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "LER")
                    .set(1.0f);
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of Master Oscillator set");
            setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
