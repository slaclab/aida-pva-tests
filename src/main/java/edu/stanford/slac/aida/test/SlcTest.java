/**
 * @file
 * @brief SLC Control Database Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import java.util.Arrays;
import java.util.List;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.test.utils.AidaType.*;

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
            getRequest("XCOR:LI03:120:LEFF", AIDA_FLOAT, "Float BACT");
            getRequest("XCOR:LI03:120//LEFF", AIDA_FLOAT, "Backwards compatibility - using legacy request format: Float BACT");
            getRequest("XCOR:LI03:120:LEFF", AIDA_DOUBLE, "Double BACT");
            getRequest("XCOR:LI03:120:VSTA", AIDA_BOOLEAN, "Boolean VSTA");
            getRequest("XCOR:LI03:120:SETL", AIDA_SHORT, "Short SETL");
            getRequest("XCOR:LI03:120:ADCP", AIDA_BYTE, "Byte ADCP");
            getRequest("XCOR:LI03:120:ADCP", AIDA_CHAR, "Char ADCP");
            getRequest("XCOR:LI03:120:ADCP", AIDA_CHAR, "WCHAR not possible in AIDA-PVA: Falling back to CHAR: ADCP");
            getRequest("XCOR:LI03:120:PSNM", AIDA_STRING, "String PSNM");
            getRequest("XCOR:LI03:120:PSNM", AIDA_STRING, "WSTRING not possible in AIDA-PVA: Falling back to STRING: PSNM");

            getRequest("XCOR:LI03:120:PSNM", AIDA_BYTE_ARRAY, "Byte array PSNM");
            getRequest("XCOR:LI03:120:PSNM", AIDA_CHAR_ARRAY, "Char array PSNM");
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire FLOAT type SLC PMUS");
            getRequest("XCOR:LI03:120:BACT", AIDA_FLOAT, "BACT");
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire LONG INT type SLC PMUS");
            getRequest("XCOR:LI03:120:VSTA", AIDA_LONG, "VSTA");
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of FLOAT type SLC PMUS");
            getRequest("XCOR:LI03:120:IMMS", AIDA_FLOAT_ARRAY, "IMMS");
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of DOUBLE type SLC PMUS");
            getRequest("XCOR:LI03:120:IMMS", AIDA_DOUBLE_ARRAY, "IMMS");
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of SHORT type SLC PMUS");
            getRequest("XCOR:LI03:120:NSCY", AIDA_SHORT_ARRAY, "NSCY");
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of LONG type SLC PMUS");
            getRequest("XCOR:LI03:120:RAMP", AIDA_LONG_ARRAY, "RAMP");
        }

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire array of BOOLEAN type SLC PMUS");
            getRequest("XCOR:LI03:120:RAMP", AIDA_BOOLEAN_ARRAY, "RAMP");
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire FLOAT type SLC Database PV");
            getRequest("XCOR:LI03:120:LEFF", AIDA_FLOAT, "LEFF");
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            var numberOfAcquisitionsToMake = 10;        // Make 10 iterations
            var delayMilliseconds = 10 * 1000;          // pausing 10 seconds between each iteration

            testHeader(testId, "Looping acquire FLOAT type SLC Database PV" + " pausing " + delayMilliseconds / 1000 + " seconds between each.");
            for (var i = 0; i < numberOfAcquisitionsToMake; i++) {
                getRequest("XCOR:LI03:120:LEFF", AIDA_FLOAT, "LEFF");
                try {
                    Thread.sleep(delayMilliseconds);
                } catch (InterruptedException ignored) {
                }
            }
        }


        // 11
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "ASTS request name test");
            getRequest("ASTS:LI00:ISOPLAN2:DATA", AIDA_FLOAT, "DATA");
            getRequest("ASTS:PR02:VP3012:DATA", AIDA_FLOAT, "DATA");
            getRequest("ASTS:PR02:VP3012:STAT", AIDA_SHORT, "STAT");
            getRequest("ASTS:PR02:T2092BLW:LIM1", AIDA_FLOAT, "LIM1");
            getRequest("ASTS:PR02:T2092BLW:LIM2", AIDA_FLOAT, "LIM2");
            getRequest("ASTS:PR02:T2092BLW:LIMS", AIDA_FLOAT_ARRAY, "LIMS");
            getRequest("ASTS:PR02:T2092QUA:SCAL", AIDA_FLOAT_ARRAY, "SCAL");
            getRequest("ASTS:PR02:T2092QUA:RAW", AIDA_FLOAT, "RAW");
            getRequest("ASTS:PR02:T2092QUA:STAT", AIDA_STRING, "STAT");
        }

        // 12
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "ASTS request name with one of the pseudo-secondaries STAT, CTRL, or VSTA");
            getRequest("ASTS:PR02:T2092QUA:STAT", AIDA_STRING_ARRAY, "STAT");
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
