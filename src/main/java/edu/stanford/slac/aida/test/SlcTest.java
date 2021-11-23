/**
 * @file
 * @brief SLC Control Database Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaType;

import java.util.Arrays;
import java.util.List;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;

/**
 * This class is used to test the SLC Database AIDA-PVA provider
 */
public class SlcTest {
    /**
     * Main entry point to the Database test
     *
     * @param args command line arguments -c for color, list of numbers to select test numbers
     */
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        testSuiteHeader("AIDA-PVA SLC TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire scalar types SLC PMUS");
            getRequest("XCOR:LI03:120:LEFF", AidaType.FLOAT, "Float BACT");
            getRequest("XCOR:LI03:120//LEFF", AidaType.FLOAT, "Backwards compatibility - using legacy request format: Float BACT");
            getRequest("XCOR:LI03:120:LEFF", AidaType.DOUBLE, "Double BACT");
            getRequest("XCOR:LI03:120:VSTA", AidaType.BOOLEAN, "Boolean VSTA");
            getRequest("XCOR:LI03:120:SETL", AidaType.SHORT, "Short SETL");
            getRequest("XCOR:LI03:120:ADCP", AidaType.BYTE, "Byte ADCP");
            getRequest("XCOR:LI03:120:ADCP", AidaType.CHAR, "Char ADCP");
            getRequest("XCOR:LI03:120:ADCP", AidaType.CHAR, "WCHAR not possible in AIDA-PVA: Falling back to CHAR: ADCP");
            getRequest("XCOR:LI03:120:PSNM", AidaType.STRING, "String PSNM");
            getRequest("XCOR:LI03:120:PSNM", AidaType.STRING, "WSTRING not possible in AIDA-PVA: Falling back to STRING: PSNM");

            getRequest("XCOR:LI03:120:PSNM", AidaType.BYTE_ARRAY, "Byte array PSNM");
            getRequest("XCOR:LI03:120:PSNM", AidaType.CHAR_ARRAY, "Char array PSNM");
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire FLOAT type SLC PMUS");
            getRequest("XCOR:LI03:120:BACT", AidaType.FLOAT, "BACT");
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire LONG INT type SLC PMUS");
            getRequest("XCOR:LI03:120:VSTA", AidaType.LONG, "VSTA");
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of FLOAT type SLC PMUS");
            getRequest("XCOR:LI03:120:IMMS", AidaType.FLOAT_ARRAY, "IMMS");
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of DOUBLE type SLC PMUS");
            getRequest("XCOR:LI03:120:IMMS", AidaType.DOUBLE_ARRAY, "IMMS");
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of SHORT type SLC PMUS");
            getRequest("XCOR:LI03:120:NSCY", AidaType.SHORT_ARRAY, "NSCY");
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of LONG type SLC PMUS");
            getRequest("XCOR:LI03:120:RAMP", AidaType.LONG_ARRAY, "RAMP");
        }

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of BOOLEAN type SLC PMUS");
            getRequest("XCOR:LI03:120:RAMP", AidaType.BOOLEAN_ARRAY, "RAMP");
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire FLOAT type SLC Database PV");
            getRequest("XCOR:LI03:120:LEFF", AidaType.FLOAT, "LEFF");
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            var numberOfAcquisitionsToMake = 10;        // Make 10 iterations
            var delayMilliseconds = 10 * 1000;          // pausing 10 seconds between each iteration

            testHeader(testId, "Looping acquire FLOAT type SLC Database PV" + " pausing " + delayMilliseconds / 1000 + " seconds between each.");
            for (var i = 0; i < numberOfAcquisitionsToMake; i++) {
                getRequest("XCOR:LI03:120:LEFF", AidaType.FLOAT, "LEFF");
                try {
                    Thread.sleep(delayMilliseconds);
                } catch (InterruptedException ignored) {
                }
            }
        }


        // 11
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "ASTS request name test");
            getRequest("ASTS:LI00:ISOPLAN2:DATA", AidaType.FLOAT, "DATA");
            getRequest("ASTS:PR02:VP3012:DATA", AidaType.FLOAT, "DATA");
            getRequest("ASTS:PR02:VP3012:STAT", AidaType.SHORT, "STAT");
            getRequest("ASTS:PR02:T2092BLW:LIM1", AidaType.FLOAT, "LIM1");
            getRequest("ASTS:PR02:T2092BLW:LIM2", AidaType.FLOAT, "LIM2");
            getRequest("ASTS:PR02:T2092BLW:LIMS", AidaType.FLOAT_ARRAY, "LIMS");
            getRequest("ASTS:PR02:T2092QUA:SCAL", AidaType.FLOAT_ARRAY, "SCAL");
            getRequest("ASTS:PR02:T2092QUA:RAW", AidaType.FLOAT, "RAW");
            getRequest("ASTS:PR02:T2092QUA:STAT", AidaType.STRING, "STAT");
        }

        // 12
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "ASTS request name with one of the pseudo-secondaries STAT, CTRL, or VSTA");
            getRequest("ASTS:PR02:T2092QUA:STAT", AidaType.STRING_ARRAY, "STAT");
        }

        // 13
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set value test");
            setRequest("XCOR:LI31:41:BCON", List.of(5.0f));
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
