/**
 * @file
 * @brief SLC Master Oscilloscope Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaPvaTestUtils;
import edu.stanford.slac.aida.test.utils.AidaType;

/**
 * This class is used to test the SLC Master Oscillator AIDA-PVA provider
 */
public class SlcMoscTest {
    public static void main(String[] args) {
        AidaPvaTestUtils.testSuiteHeader("AIDA-PVA SLC Master Oscillator TESTS");

        Integer testNumber = 0, testId = 0;
        var argc = 0;
        if (args.length != argc) {
            // Optionally allow color flag to enable tests in color
            if (args[argc].equals("-color") || args[argc].equals("-c")) {
                AidaPvaTestUtils.NO_COLOR_FLAG = false;
                argc++;
            }
            if (args.length != argc) {
                testNumber = Integer.valueOf(args[argc]);
            }
        }

        // 01
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator get method");
            AidaPvaTestUtils.getWithNoArguments("MASTEROSC:VAL", AidaType.TABLE, "VAL");
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator get method for double");
            AidaPvaTestUtils.getWithNoArguments("MASTEROSC:VAL", AidaType.DOUBLE, "VAL");
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator get method for table");
            AidaPvaTestUtils.getWithNoArguments("MASTEROSC:VAL", AidaType.TABLE, "VAL");
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set");
            AidaPvaTestUtils.setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is HER");
            AidaPvaTestUtils.channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "HER")
                    .set(1.0f);
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is LER");
            AidaPvaTestUtils.channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "LER")
                    .set(1.0f);
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set");
            AidaPvaTestUtils.setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
