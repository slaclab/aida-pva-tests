/**
 * @file
 * @brief SLC Master Oscilloscope Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaPvaTestUtils;
import edu.stanford.slac.aida.test.utils.AidaType;

import java.util.Arrays;

/**
 * This class is used to test the SLC Master Oscillator AIDA-PVA provider
 */
public class SlcMoscTest {
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        AidaPvaTestUtils.NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (AidaPvaTestUtils.NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        AidaPvaTestUtils.testSuiteHeader("AIDA-PVA SLC Master Oscillator TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator get method");
            AidaPvaTestUtils.getWithNoArguments("MASTEROSC:VAL", AidaType.TABLE, "VAL");
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator get method for double");
            AidaPvaTestUtils.getWithNoArguments("MASTEROSC:VAL", AidaType.DOUBLE, "VAL");
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator get method for table");
            AidaPvaTestUtils.getWithNoArguments("MASTEROSC:VAL", AidaType.TABLE, "VAL");
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set");
            AidaPvaTestUtils.setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is HER");
            AidaPvaTestUtils.channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "HER")
                    .set(1.0f);
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set method Set value is relative energy.  Ring is LER");
            AidaPvaTestUtils.channel("MASTEROSC:VAL", "VAL")
                    .with("UNITS", "ENERGY")
                    .with("RING", "LER")
                    .set(1.0f);
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Test of Master Oscillator set");
            AidaPvaTestUtils.setWithNoArguments("MASTEROSC:VAL", 0.328f);
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
