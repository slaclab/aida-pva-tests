package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.*;

/**
 * This class is used to test the SLC Database AIDA-PVA provider
 */
public class SlcTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire scalar types SLC PMUS");
            getWithNoArguments("XCOR:LI03:120:LEFF", FLOAT, "Float BACT");
            getWithNoArguments("XCOR:LI03:120:LEFF", DOUBLE, "Double BACT");
            getWithNoArguments("XCOR:LI03:120:VSTA", BOOLEAN, "Boolean VSTA");
            getWithNoArguments("XCOR:LI03:120:SETL", SHORT, "Short SETL");
            getWithNoArguments("XCOR:LI03:120:ADCP", BYTE, "Byte ADCP");
            getWithNoArguments("XCOR:LI03:120:ADCP", CHAR, "Char ADCP");
            getWithNoArguments("XCOR:LI03:120:ADCP", SHORT, "Wchar ADCP");
            getWithNoArguments("XCOR:LI03:120:PSNM", STRING, "String PSNM");
            getWithNoArguments("XCOR:LI03:120//PSNM", STRING, "WString PSNM");

            getWithNoArguments("XCOR:LI03:120//PSNM", BYTE_ARRAY, "Byte array PSNM");
            getWithNoArguments("XCOR:LI03:120//PSNM", CHAR_ARRAY, "Char array PSNM");

        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire FLOAT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120//BACT", FLOAT, "BACT");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire LONG INT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120//VSTA", LONG, "VSTA");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of FLOAT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120//IMMS", FLOAT_ARRAY, "IMMS");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of DOUBLE type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120//IMMS", DOUBLE_ARRAY, "IMMS");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of SHORT type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120//NSCY", SHORT_ARRAY, "NSCY");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of LONG type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120//RAMP", LONG_ARRAY, "RAMP");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire array of BOOLEAN type SLC PMUS");
            getWithNoArguments("XCOR:LI03:120//RAMP", BOOLEAN_ARRAY, "RAMP");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire FLOAT type SLC Database PV");
            getWithNoArguments("XCOR:LI03:120//LEFF", FLOAT, "LEFF");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            int numberOfAcquisitionsToMake = 10;        // Make 10 iterations
            int delayMilliseconds = 10 * 1000;          // pausing 10 seconds between each iteration

            testHeader(testId, "Looping acquire FLOAT type SLC Database PV" + " pausing " + delayMilliseconds / 1000 + " seconds between each.");
            for (int i = 0; i < numberOfAcquisitionsToMake; i++) {
                getWithNoArguments("XCOR:LI03:120//LEFF", FLOAT, "LEFF");
                try {
                    Thread.sleep(delayMilliseconds);
                } catch (InterruptedException ignored) {
                }
            }
        }


        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "ASTS channel name test");
            getWithNoArguments("ASTS:LI00:ISOPLAN2//DATA", FLOAT, "DATA");
            getWithNoArguments("ASTS:PR02:VP3012//DATA", FLOAT, "DATA");
            getWithNoArguments("ASTS:PR02:VP3012//STAT", SHORT, "STAT");
            getWithNoArguments("ASTS:PR02:T2092BLW//LIM1", FLOAT, "LIM1");
            getWithNoArguments("ASTS:PR02:T2092BLW//LIM2", FLOAT, "LIM2");
            getWithNoArguments("ASTS:PR02:T2092BLW//LIMS", FLOAT_ARRAY, "LIMS");
            getWithNoArguments("ASTS:PR02:T2092QUA//SCAL", FLOAT_ARRAY, "SCAL");
            getWithNoArguments("ASTS:PR02:T2092QUA//RAW", FLOAT, "RAW");
            getWithNoArguments("ASTS:PR02:T2092QUA//STAT", STRING, "STAT");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "ASTS channel name with one of the pseudo-secondaries STAT, CTRL, or VSTA");
            getWithNoArguments("ASTS:PR02:T2092QUA//STAT", STRING_ARRAY, "STAT");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set value test");
            setWithNoArguments("XCOR:LI31:41//BCON", 5.0f);
        }
        // Because of threads started in background to process requests
        System.exit(0);
    }
}
