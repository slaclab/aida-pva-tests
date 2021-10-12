package edu.stanford.slac.aida;

import java.util.Collections;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.*;

/**
 * This class is used to test the SLC Database AIDA-PVA provider
 */
public class SlcTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC TESTS");

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
            testHeader(testId, "Acquire scalar types SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:LEFF", FLOAT, "Float BACT");
            getWithNoArguments("XCOR:LI03:120//LEFF", FLOAT, "Backwards compatibility - using legacy channel format: Float BACT");
            getWithNoArguments("XCOR:LI03:120:LEFF", DOUBLE, "Double BACT");
            getWithNoArguments("XCOR:LI03:120:VSTA", BOOLEAN, "Boolean VSTA");
            getWithNoArguments("XCOR:LI03:120:SETL", SHORT, "Short SETL");
            getWithNoArguments("XCOR:LI03:120:ADCP", BYTE, "Byte ADCP");
            getWithNoArguments("XCOR:LI03:120:ADCP", CHAR, "Char ADCP");
            getWithNoArguments("XCOR:LI03:120:ADCP", CHAR, "WCHAR not possible in AIDA-PVA: Falling back to CHAR: ADCP");
            getWithNoArguments("XCOR:LI03:120:PSNM", STRING, "String PSNM");
            getWithNoArguments("XCOR:LI03:120:PSNM", STRING, "WSTRING not possible in AIDA-PVA: Falling back to STRING: PSNM");

            getWithNoArguments("XCOR:LI03:120:PSNM", BYTE_ARRAY, "Byte array PSNM");
            getWithNoArguments("XCOR:LI03:120:PSNM", CHAR_ARRAY, "Char array PSNM");
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire FLOAT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:BACT", FLOAT, "BACT");
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire LONG INT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:VSTA", LONG, "VSTA");
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of FLOAT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:IMMS", FLOAT_ARRAY, "IMMS");
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of DOUBLE type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:IMMS", DOUBLE_ARRAY, "IMMS");
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of SHORT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:NSCY", SHORT_ARRAY, "NSCY");
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of LONG type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:RAMP", LONG_ARRAY, "RAMP");
        }

        // 08
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of BOOLEAN type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:RAMP", BOOLEAN_ARRAY, "RAMP");
        }

        // 09
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire FLOAT type SLC Database PV");
            getWithNoArguments("XCOR:LI03:120:LEFF", FLOAT, "LEFF");
        }

        // 10
        if (testNumber.equals(++testId) || testNumber == 0) {
            var numberOfAcquisitionsToMake = 10;        // Make 10 iterations
            var delayMilliseconds = 10 * 1000;          // pausing 10 seconds between each iteration

            testHeader(testId, "Looping acquire FLOAT type SLC Database PV" + " pausing " + delayMilliseconds / 1000 + " seconds between each.");
            for (var i = 0; i < numberOfAcquisitionsToMake; i++) {
                getWithNoArguments("XCOR:LI03:120:LEFF", FLOAT, "LEFF");
                try {
                    Thread.sleep(delayMilliseconds);
                } catch (InterruptedException ignored) {
                }
            }
        }


        // 11
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "ASTS channel name test");
            getWithNoArguments("ASTS:LI00:ISOPLAN2:DATA", FLOAT, "DATA");
            getWithNoArguments("ASTS:PR02:VP3012:DATA", FLOAT, "DATA");
            getWithNoArguments("ASTS:PR02:VP3012:STAT", SHORT, "STAT");
            getWithNoArguments("ASTS:PR02:T2092BLW:LIM1", FLOAT, "LIM1");
            getWithNoArguments("ASTS:PR02:T2092BLW:LIM2", FLOAT, "LIM2");
            getWithNoArguments("ASTS:PR02:T2092BLW:LIMS", FLOAT_ARRAY, "LIMS");
            getWithNoArguments("ASTS:PR02:T2092QUA:SCAL", FLOAT_ARRAY, "SCAL");
            getWithNoArguments("ASTS:PR02:T2092QUA:RAW", FLOAT, "RAW");
            getWithNoArguments("ASTS:PR02:T2092QUA:STAT", STRING, "STAT");
        }

        // 12
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "ASTS channel name with one of the pseudo-secondaries STAT, CTRL, or VSTA");
            getWithNoArguments("ASTS:PR02:T2092QUA:STAT", STRING_ARRAY, "STAT");
        }

        // 13
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set value test");
            setWithNoArguments("XCOR:LI31:41:BCON", Collections.singletonList(5.0f));
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
