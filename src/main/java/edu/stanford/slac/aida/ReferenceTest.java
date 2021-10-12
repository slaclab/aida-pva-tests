package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.*;

/**
 * This class is used to test the Reference Service Provider
 */
public class ReferenceTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA REFERENCE TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        // 01
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Boolean");
            channel("AIDA:SAMPLE:TEST:attribute01", "Float").get();
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Byte");
            channel("AIDA:SAMPLE:TEST:attribute02", "Byte").get();
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Short");
            channel("AIDA:SAMPLE:TEST:attribute03", "Short").get();
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Integer");
            channel("AIDA:SAMPLE:TEST:attribute04", "Integer").get();
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Long");
            channel("AIDA:SAMPLE:TEST:attribute05", "Long").get();
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Float");
            channel("AIDA:SAMPLE:TEST:attribute06", "Float").get();
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Double");
            channel("AIDA:SAMPLE:TEST:attribute07", "Double").get();
        }

        // 08
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get String");
            channel("AIDA:SAMPLE:TEST:attribute08", "String").get();
        }

        // 09
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Boolean Array");
            channel("AIDA:SAMPLE:TEST:attribute11", "Float Array").get();
        }

        // 10
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Byte Array");
            channel("AIDA:SAMPLE:TEST:attribute12", "Byte Array").get();
        }

        // 11
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Short Array");
            channel("AIDA:SAMPLE:TEST:attribute13", "Short Array").get();
        }

        // 12
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Integer Array");
            channel("AIDA:SAMPLE:TEST:attribute14", "Integer Array").get();
        }

        // 13
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Long Array");
            channel("AIDA:SAMPLE:TEST:attribute15", "Long Array").get();
        }

        // 14
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Float Array");
            channel("AIDA:SAMPLE:TEST:attribute16", "Float Array").get();
        }

        // 15
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Double Array");
            channel("AIDA:SAMPLE:TEST:attribute17", "Double Array").get();
        }

        // 16
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get String Array");
            channel("AIDA:SAMPLE:TEST:attribute18", "String Array").get();
        }

        // 17
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Table");
            channel("AIDA:SAMPLE:TEST:attribute20", "Table").get();
        }

        // 18
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Void Setter");
            setWithNoArguments("AIDA:SAMPLE:TEST:attribute30", "Set");
        }

        // 19
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Setter returning Table");
            setWithNoArguments("AIDA:SAMPLE:TEST:attribute31", "Set Table");
        }

        // 20
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Arbitrary Getter Boolean");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", BOOLEAN, "Boolean");

            testHeader(testId, "Arbitrary Getter Byte");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", BYTE, "Byte");

            testHeader(testId, "Arbitrary Getter Char");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", CHAR, "Char");

            testHeader(testId, "Arbitrary Getter Short");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", SHORT, "Short");

            testHeader(testId, "Arbitrary Getter Integer");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", INTEGER, "Integer");

            testHeader(testId, "Arbitrary Getter Long");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", LONG, "Long");

            testHeader(testId, "Arbitrary Getter Float");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", FLOAT, "Float");

            testHeader(testId, "Arbitrary Getter Double");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", DOUBLE, "Double");

            testHeader(testId, "Arbitrary Getter String");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", STRING, "String");

            testHeader(testId, "Arbitrary Getter Boolean Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", BOOLEAN, "Boolean Array");

            testHeader(testId, "Arbitrary Getter Byte Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", BYTE_ARRAY, "Byte Array");

            testHeader(testId, "Arbitrary Getter Char Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", CHAR_ARRAY, "Char Array");

            testHeader(testId, "Arbitrary Getter Short Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", SHORT_ARRAY, "Short Array");

            testHeader(testId, "Arbitrary Getter Integer Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", INTEGER_ARRAY, "Integer Array");

            testHeader(testId, "Arbitrary Getter Long Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", LONG_ARRAY, "Long Array");

            testHeader(testId, "Arbitrary Getter Float Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", FLOAT_ARRAY, "Float Array");

            testHeader(testId, "Arbitrary Getter Double Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", DOUBLE_ARRAY, "Double Array");

            testHeader(testId, "Arbitrary Getter String Array");
            getWithNoArguments("AIDA:SAMPLE:TEST:attribute32", STRING_ARRAY, "String Array");
        }

        // 21
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Channel Not Supported");
            channel("AIDA:SAMPLE:TEST:attribute00", "Not Supported").getAndExpectFailure();
        }


        // Because of threads started in background to process requests
        System.exit(0);
    }
}
