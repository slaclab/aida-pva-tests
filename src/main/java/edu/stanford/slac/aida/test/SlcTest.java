/**
 * @file
 * @brief SLC Control Database Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaPvaTestUtils;
import edu.stanford.slac.aida.test.utils.AidaType;

import java.util.Collections;

/**
 * This class is used to test the SLC Database AIDA-PVA provider
 */
public class SlcTest {
    public static void main(String[] args) {
        AidaPvaTestUtils.testSuiteHeader("AIDA-PVA SLC TESTS");

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
            AidaPvaTestUtils.testHeader(testId, "Acquire scalar types SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:LEFF", AidaType.FLOAT, "Float BACT");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120//LEFF", AidaType.FLOAT, "Backwards compatibility - using legacy channel format: Float BACT");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:LEFF", AidaType.DOUBLE, "Double BACT");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:VSTA", AidaType.BOOLEAN, "Boolean VSTA");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:SETL", AidaType.SHORT, "Short SETL");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:ADCP", AidaType.BYTE, "Byte ADCP");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:ADCP", AidaType.CHAR, "Char ADCP");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:ADCP", AidaType.CHAR, "WCHAR not possible in AIDA-PVA: Falling back to CHAR: ADCP");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:PSNM", AidaType.STRING, "String PSNM");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:PSNM", AidaType.STRING, "WSTRING not possible in AIDA-PVA: Falling back to STRING: PSNM");

            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:PSNM", AidaType.BYTE_ARRAY, "Byte array PSNM");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:PSNM", AidaType.CHAR_ARRAY, "Char array PSNM");
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire FLOAT type SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:BACT", AidaType.FLOAT, "BACT");
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire LONG INT type SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:VSTA", AidaType.LONG, "VSTA");
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire array of FLOAT type SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:IMMS", AidaType.FLOAT_ARRAY, "IMMS");
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire array of DOUBLE type SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:IMMS", AidaType.DOUBLE_ARRAY, "IMMS");
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire array of SHORT type SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:NSCY", AidaType.SHORT_ARRAY, "NSCY");
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire array of LONG type SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:RAMP", AidaType.LONG_ARRAY, "RAMP");
        }

        // 08
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire array of BOOLEAN type SLC PMUS");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:RAMP", AidaType.BOOLEAN_ARRAY, "RAMP");
        }

        // 09
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Acquire FLOAT type SLC Database PV");
            AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:LEFF", AidaType.FLOAT, "LEFF");
        }

        // 10
        if (testNumber.equals(++testId) || testNumber == 0) {
            var numberOfAcquisitionsToMake = 10;        // Make 10 iterations
            var delayMilliseconds = 10 * 1000;          // pausing 10 seconds between each iteration

            AidaPvaTestUtils.testHeader(testId, "Looping acquire FLOAT type SLC Database PV" + " pausing " + delayMilliseconds / 1000 + " seconds between each.");
            for (var i = 0; i < numberOfAcquisitionsToMake; i++) {
                AidaPvaTestUtils.getWithNoArguments("XCOR:LI03:120:LEFF", AidaType.FLOAT, "LEFF");
                try {
                    Thread.sleep(delayMilliseconds);
                } catch (InterruptedException ignored) {
                }
            }
        }


        // 11
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "ASTS channel name test");
            AidaPvaTestUtils.getWithNoArguments("ASTS:LI00:ISOPLAN2:DATA", AidaType.FLOAT, "DATA");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:VP3012:DATA", AidaType.FLOAT, "DATA");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:VP3012:STAT", AidaType.SHORT, "STAT");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:T2092BLW:LIM1", AidaType.FLOAT, "LIM1");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:T2092BLW:LIM2", AidaType.FLOAT, "LIM2");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:T2092BLW:LIMS", AidaType.FLOAT_ARRAY, "LIMS");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:T2092QUA:SCAL", AidaType.FLOAT_ARRAY, "SCAL");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:T2092QUA:RAW", AidaType.FLOAT, "RAW");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:T2092QUA:STAT", AidaType.STRING, "STAT");
        }

        // 12
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "ASTS channel name with one of the pseudo-secondaries STAT, CTRL, or VSTA");
            AidaPvaTestUtils.getWithNoArguments("ASTS:PR02:T2092QUA:STAT", AidaType.STRING_ARRAY, "STAT");
        }

        // 13
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Set value test");
            AidaPvaTestUtils.setWithNoArguments("XCOR:LI31:41:BCON", Collections.singletonList(5.0f));
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
