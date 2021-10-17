package edu.stanford.slac.aida;

import java.util.Arrays;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.*;
import static java.lang.Math.E;
import static java.lang.Math.PI;

/**
 * This class is used to test the Reference Service Provider
 */
public class ReferenceTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA REFERENCE TESTS");

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
            testHeader(testId, "Get Boolean");

            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[1] ").with("x", 1).get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[0] ").with("x", 0).get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"true\"] ").with("x", "true").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"false\"] ").with("x", "false").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"TRUE\"] ").with("x", "TRUE").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"FALSE\"] ").with("x", "FALSE").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"T\"] ").with("x", "T").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"F\"] ").with("x", "F").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"t\"] ").with("x", "t").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"f\"] ").with("x", "f").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"Y\"] ").with("x", "Y").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"N\"] ").with("x", "N").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: y[\"y\"] ").with("x", "y").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"n\"] ").with("x", "n").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"YES\"] ").with("x", "YES").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"NO\"] ").with("x", "NO").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"yes\"] ").with("x", "yes").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"no\"] ").with("x", "no").get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[Boolean.TRUE]").with("x", Boolean.TRUE).get();
            channel("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[Boolean.FALSE]").with("x", Boolean.FALSE).get();
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Byte");
            channel("AIDA:SAMPLE:TEST:attribute02", "Byte").get();
            channel("AIDA:SAMPLE:TEST:attribute02", "Unprintable Char: 0x2").returning(CHAR).get();
            channel("AIDA:SAMPLE:TEST:attribute02", "Byte: 0x2 | x[0x4]").with("x", 0x4).get();
            channel("AIDA:SAMPLE:TEST:attribute02", "Byte: 0x2 | x[0x8]").with("x", 0x8).get();
            channel("AIDA:SAMPLE:TEST:attribute02", "Char: 0x2 | x[0x48]").with("x", 0x48).returning(CHAR).get();
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Short");
            channel("AIDA:SAMPLE:TEST:attribute03", "Short").get();
            channel("AIDA:SAMPLE:TEST:attribute03", "Short: 3 + x[3]").with("x", 3).get();
            channel("AIDA:SAMPLE:TEST:attribute03", "Short: 3 + x[-3]").with("x", -3).get();
            channel("AIDA:SAMPLE:TEST:attribute03", "Max: Short: 3 + x[" + (Short.MAX_VALUE) + "]").with("x", (Short.MAX_VALUE)).get();
            channel("AIDA:SAMPLE:TEST:attribute03", "Min: Short: 3 + x[" + (Short.MIN_VALUE) + "]").with("x", (Short.MIN_VALUE)).get();
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Integer");
            channel("AIDA:SAMPLE:TEST:attribute04", "Integer").get();
            channel("AIDA:SAMPLE:TEST:attribute04", "Integer: 4 + x[4]").with("x", 4).get();
            channel("AIDA:SAMPLE:TEST:attribute04", "Integer: 4 + x[-4]").with("x", -4).get();
            channel("AIDA:SAMPLE:TEST:attribute04", "Max: Integer: 4 + x[" + (Integer.MAX_VALUE) + "]").with("x", (Integer.MAX_VALUE)).get();
            channel("AIDA:SAMPLE:TEST:attribute04", "Min: Integer: 4 + x[" + (Integer.MIN_VALUE) + "]").with("x", (Integer.MIN_VALUE)).get();
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Long");
            channel("AIDA:SAMPLE:TEST:attribute05", "Long").get();
            channel("AIDA:SAMPLE:TEST:attribute05", "Long: 5 + x[5]").with("x", 5).get();
            channel("AIDA:SAMPLE:TEST:attribute05", "Long: 5 + x[-5]").with("x", -5).get();
            channel("AIDA:SAMPLE:TEST:attribute05", "Max (32bit): Long: 5 + x[" + (Integer.MAX_VALUE) + "]").with("x", (Integer.MAX_VALUE)).get();
            channel("AIDA:SAMPLE:TEST:attribute05", "Min (32bit): Long: 5 + x[" + (Integer.MIN_VALUE) + "]").with("x", (Integer.MIN_VALUE)).get();
            channel("AIDA:SAMPLE:TEST:attribute05", "Max (64bit): Long: 5 + x[" + (Long.MAX_VALUE) + "]").with("x", (Long.MAX_VALUE)).get();
            channel("AIDA:SAMPLE:TEST:attribute05", "Min (64bit): Long: 5 + x[" + (Long.MIN_VALUE) + "]").with("x", (Long.MIN_VALUE)).get();
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Float");
            channel("AIDA:SAMPLE:TEST:attribute06", "Float").get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[6.6f]").with("x", 6.6f).get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[-6.6f]").with("x", -6.6f).get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[pi]").with("x", PI).get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[pi/6.6f]").with("x", PI / 6.6f).get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[e]").with("x", E).get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[e/6.6f]").with("x", E / 6.6f).get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Max Float: 6.6f * x[" + Float.MAX_VALUE + "]").with("x", Float.MAX_VALUE).get();
            channel("AIDA:SAMPLE:TEST:attribute06", "Min Float: 6.6f * x[" + Float.MIN_VALUE + "]").with("x", Float.MIN_VALUE).get();
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Double");
            channel("AIDA:SAMPLE:TEST:attribute07", "Double").get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[7.7]").with("x", 7.7).get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[-7.7]").with("x", -7.7).get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[pi]").with("x", PI).get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[pi/7.7]").with("x", PI / 7.7).get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[e]").with("x", E).get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[e/7.7]").with("x", E / 7.7).get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Max Double: 7.7 * x[" + Double.MAX_VALUE + "]").with("x", Double.MAX_VALUE).get();
            channel("AIDA:SAMPLE:TEST:attribute07", "Min Double: 7.7 * x[" + Double.MIN_VALUE + "]").with("x", Double.MIN_VALUE).get();
        }

        // 08
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get String");
            channel("AIDA:SAMPLE:TEST:attribute08", "String").get();
            channel("AIDA:SAMPLE:TEST:attribute08", "Concatenate String: \"eight: \" + x[\"Hello World\"]").with("x", "Hello World").get();
        }

        // 09
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Boolean Array");
            channel("AIDA:SAMPLE:TEST:attribute11", "Boolean Array").get();
            channel("AIDA:SAMPLE:TEST:attribute11", "Boolean Array: x[[\"TRUE\", \"FALSE\", \"T\", \"F\", \"Y\", \"N\", \"YES\", \"NO\"]]")
                    .with("x", Arrays.asList("TRUE", "FALSE", "T", "F", "Y", "N", "YES", "NO"))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute11", "Boolean Array: x[[1, 0]]")
                    .with("x", Arrays.asList(1, 0))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute11", "Boolean Array: x[[Boolean.TRUE, Boolean.FALSE]]")
                    .with("x", Arrays.asList(Boolean.TRUE, Boolean.FALSE))
                    .get();
        }

        // 10
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Byte Array");
            channel("AIDA:SAMPLE:TEST:attribute12", "Byte Array").get();
            channel("AIDA:SAMPLE:TEST:attribute12", "Byte Array: 12 | x[[0x4, 0x8, 0x48, 65]]")
                    .with("x", Arrays.asList(0x4, 0x8, 0x48, 65))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute12", "Char Array: 12 | x[[0x40, 0x41, 0x48, 0x49]]")
                    .with("x", Arrays.asList(0x40, 0x41, 0x48, 0x49))
                    .returning(CHAR_ARRAY)
                    .get();
        }

        // 11
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Short Array");
            channel("AIDA:SAMPLE:TEST:attribute13", "Short Array").get();
            channel("AIDA:SAMPLE:TEST:attribute13", "Short Array: 13 + x[[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]]")
                    .with("x", Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute13", "Short Array: 13 + x[[32693, 32707, 32713, 32717, 32719, 32749]]")
                    .with("x", Arrays.asList(32693, 32707, 32713, 32717, 32719, 32749))
                    .get();
        }

        // 12
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Integer Array");
            channel("AIDA:SAMPLE:TEST:attribute14", "Integer Array").get();
            channel("AIDA:SAMPLE:TEST:attribute14", "Integer Array: 14 + x[[10000019, 10000079,10000103,10000121,10000139,10000141]]")
                    .with("x", Arrays.asList(10000019, 10000079, 10000103, 10000121, 10000139, 10000141))
                    .get();
        }

        // 13
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Long Array");
            channel("AIDA:SAMPLE:TEST:attribute15", "Long Array").get();
            channel("AIDA:SAMPLE:TEST:attribute15", "Long Array: 15 + x[[1000000007L, 1000000009L, 1000000021L, 1000000033L, 1000000087L, 1000000093L]]")
                    .with("x", Arrays.asList(1000000007L, 1000000009L, 1000000021L, 1000000033L, 1000000087L, 1000000093L))
                    .get();
        }

        // 14
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Float Array");
            channel("AIDA:SAMPLE:TEST:attribute16", "Float Array").get();
            channel("AIDA:SAMPLE:TEST:attribute16", "Formats: Float Array: 16.6 * x[[PI/16.6, E/16.6, 1e-10f, 1e+10f, 1.234567e-23f, 1.234567e+23f, 1.23456789e+23f]]")
                    .with("x", Arrays.asList(((float) (PI / 16.6)), ((float) (E / 16.6)), 1e-10f, 1e+10f, 1.234567e-23f, 1.234567e+23f, 1.23456789e+23f))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute16", "Precision: Float Array: 16.6 * x[[1.2345678f, 1.2345679f, -1.2345678f, -1.2345679f, 1.0000001f, 1.0000002f, -1.0000001f, -1.0000002f]]")
                    .with("x", Arrays.asList(1.2345678f, 1.2345679f, -1.2345678f, -1.2345679f, 1.0000001f, 1.0000002f, -1.0000001f, -1.0000002f))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute16", "Significance Limit: Float Array: 16.6 * x[[1.23456789f, 1.234567896f, -1.23456789f, -1.234567896f, 1.0000001f, 1.00000016f, -1.0000001f, -1.00000016f]]")
                    .with("x", Arrays.asList(1.23456789f, 1.234567896f, -1.23456789f, -1.234567896f, 1.0000001f, 1.00000016f, -1.0000001f, -1.00000016f))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute16", "Significance Limit e-notation: Float Array: 16.6 * x[[1.23456789e-23f, 1.234567896e-23f, 1.23456789e+23f, 1.234567896e+23f]]")
                    .with("x", Arrays.asList(1.23456789e-23f, 1.234567896e-23f, 1.23456789e+23f, 1.234567896e+23f))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute16", "Max/Min/pi/e: Float Array: 16.6 * x[[Float.MIN_VALUE, Float.MAX_VALUE, PI, E]]")
                    .with("x", Arrays.asList(Float.MIN_VALUE, Float.MAX_VALUE, PI, E))
                    .get();
        }

        // 15
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get Double Array");
            channel("AIDA:SAMPLE:TEST:attribute17", "Double Array").get();

            channel("AIDA:SAMPLE:TEST:attribute17", "Formats: Double Array: 17.7 * x[[PI/17.7, E/17.7, 1e-100, 1e+100, 1.0000000234567e-230, 1.0000000234567e+230, 1.000000023456789e+230]]")
                    .with("x", Arrays.asList(PI / 17.7, E / 17.7, 1e-100, 1e+100, 1.0000000234567e-230, 1.0000000234567e+230, 1.000000023456789e+230))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute17", "Precision: Double Array: 17.7 * x[[1.000000002345678, 1.000000002345679, -1.000000002345678, -1.000000002345679, 1.000000000000001, 1.000000000000002, -1.000000000000001, -1.000000000000002]]")
                    .with("x", Arrays.asList(1.000000002345678, 1.000000002345679, -1.000000002345678, -1.000000002345679, 1.000000000000001, 1.000000000000002, -1.000000000000001, -1.000000000000002))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute17", "Significance Limit: Double Array: 17.7 * x[[1.0000000023456787, 1.0000000023456788, -1.0000000023456787, -1.0000000023456788, 1.0000000000000017, 1.0000000000000018, -1.0000000000000017, -1.0000000000000018]]")
                    .with("x", Arrays.asList(1.0000000023456787, 1.0000000023456788, -1.0000000023456787, -1.0000000023456788, 1.0000000000000017, 1.0000000000000018, -1.0000000000000017, -1.0000000000000018))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute17", "Significance Limit e-notation: Double Array: 17.7 * x[[1.0000000023456787e-23, 1.0000000023456786e-23, 1.0000000023456787e+23, 1.0000000023456788e+23]]")
                    .with("x", Arrays.asList(1.0000000023456787e-23, 1.0000000023456786e-23, 1.0000000023456787e+23, 1.0000000023456788e+23))
                    .get();
            channel("AIDA:SAMPLE:TEST:attribute17", "Max/Min/pi/e: Double Array: 17.7 * x[[Double.MIN_VALUE, Double.MAX_VALUE, PI, E]]")
                    .with("x", Arrays.asList(Double.MIN_VALUE, Double.MAX_VALUE, PI, E))
                    .get();
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
