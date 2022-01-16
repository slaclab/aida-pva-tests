/**
 * @file
 * @brief Reference Provider Tests
 */
package edu.stanford.slac.aida.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.test.utils.AidaType.*;
import static java.lang.Math.E;
import static java.lang.Math.PI;

/**
 * This class is used to test the Reference Service Provider
 */
public class ReferenceTest {
    /**
     * Main entry point to the reference test
     *
     * @param args command line arguments -c for color, list of numbers to select test numbers
     */
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        testSuiteHeader("AIDA-PVA REFERENCE TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Boolean");

            request("AIDA:SAMPLE:TEST:attribute01", "Boolean").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[1] ").with("x", 1).get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[0] ").with("x", 0).get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"true\"] ").with("x", "true").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"false\"] ").with("x", "false").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"TRUE\"] ").with("x", "TRUE").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"FALSE\"] ").with("x", "FALSE").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"T\"] ").with("x", "T").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"F\"] ").with("x", "F").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"t\"] ").with("x", "t").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"f\"] ").with("x", "f").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"Y\"] ").with("x", "Y").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"N\"] ").with("x", "N").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: y[\"y\"] ").with("x", "y").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"n\"] ").with("x", "n").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"YES\"] ").with("x", "YES").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"NO\"] ").with("x", "NO").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"yes\"] ").with("x", "yes").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[\"no\"] ").with("x", "no").get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[Boolean.TRUE]").with("x", Boolean.TRUE).get();
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: x[Boolean.FALSE]").with("x", Boolean.FALSE).get();
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Byte");
            request("AIDA:SAMPLE:TEST:attribute02", "Byte").get();
            request("AIDA:SAMPLE:TEST:attribute02", "Unprintable Char: 0x2").returning(AIDA_CHAR).get();
            request("AIDA:SAMPLE:TEST:attribute02", "Byte: 0x2 | x[0x4]").with("x", 0x4).get();
            request("AIDA:SAMPLE:TEST:attribute02", "Byte: 0x2 | x[0x8]").with("x", 0x8).get();
            request("AIDA:SAMPLE:TEST:attribute02", "Char: 0x2 | x[0x48]").with("x", 0x48).returning(AIDA_CHAR).get();
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Short");
            request("AIDA:SAMPLE:TEST:attribute03", "Short").get();
            request("AIDA:SAMPLE:TEST:attribute03", "Short: 3 + x[3]").with("x", 3).get();
            request("AIDA:SAMPLE:TEST:attribute03", "Short: 3 + x[-3]").with("x", -3).get();
            request("AIDA:SAMPLE:TEST:attribute03", "Max: Short: 3 + x[" + (Short.MAX_VALUE) + "]").with("x", (Short.MAX_VALUE)).get();
            request("AIDA:SAMPLE:TEST:attribute03", "Min: Short: 3 + x[" + (Short.MIN_VALUE) + "]").with("x", (Short.MIN_VALUE)).get();
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Integer");
            request("AIDA:SAMPLE:TEST:attribute04", "Integer").get();
            request("AIDA:SAMPLE:TEST:attribute04", "Integer: 4 + x[4]").with("x", 4).get();
            request("AIDA:SAMPLE:TEST:attribute04", "Integer: 4 + x[-4]").with("x", -4).get();
            request("AIDA:SAMPLE:TEST:attribute04", "Max: Integer: 4 + x[" + (Integer.MAX_VALUE) + "]").with("x", (Integer.MAX_VALUE)).get();
            request("AIDA:SAMPLE:TEST:attribute04", "Min: Integer: 4 + x[" + (Integer.MIN_VALUE) + "]").with("x", (Integer.MIN_VALUE)).get();
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Long");
            request("AIDA:SAMPLE:TEST:attribute05", "Long").get();
            request("AIDA:SAMPLE:TEST:attribute05", "Long: 5 + x[5]").with("x", 5).get();
            request("AIDA:SAMPLE:TEST:attribute05", "Long: 5 + x[-5]").with("x", -5).get();
            request("AIDA:SAMPLE:TEST:attribute05", "Max (32bit): Long: 5 + x[" + (Integer.MAX_VALUE) + "]").with("x", (Integer.MAX_VALUE)).get();
            request("AIDA:SAMPLE:TEST:attribute05", "Min (32bit): Long: 5 + x[" + (Integer.MIN_VALUE) + "]").with("x", (Integer.MIN_VALUE)).get();
            request("AIDA:SAMPLE:TEST:attribute05", "Max (64bit) Fail: Long: 5 + x[" + (Long.MAX_VALUE) + "]").with("x", (Long.MAX_VALUE)).getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute05", "Min (64bit) Fail: Long: 5 + x[" + (Long.MIN_VALUE) + "]").with("x", (Long.MIN_VALUE)).getAndExpectFailure();
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Float");
            request("AIDA:SAMPLE:TEST:attribute06", "Float").get();
            request("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[6.6f]").with("x", 6.6f).get();
            request("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[-6.6f]").with("x", -6.6f).get();
            request("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[pi]").with("x", PI).get();
            request("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[pi/6.6f]").with("x", PI / 6.6f).get();
            request("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[e]").with("x", E).get();
            request("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * x[e/6.6f]").with("x", E / 6.6f).get();
            request("AIDA:SAMPLE:TEST:attribute06", "Max Float: 6.6f * x[" + Float.MAX_VALUE + "]").with("x", Float.MAX_VALUE).get();
            request("AIDA:SAMPLE:TEST:attribute06", "Min Float: 6.6f * x[" + Float.MIN_VALUE + "]").with("x", Float.MIN_VALUE).get();
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Double");
            request("AIDA:SAMPLE:TEST:attribute07", "Double").get();
            request("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[7.7]").with("x", 7.7).get();
            request("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[-7.7]").with("x", -7.7).get();
            request("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[pi]").with("x", PI).get();
            request("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[pi/7.7]").with("x", PI / 7.7).get();
            request("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[e]").with("x", E).get();
            request("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * x[e/7.7]").with("x", E / 7.7).get();
            request("AIDA:SAMPLE:TEST:attribute07", "Max Double: 7.7 * x[" + Double.MAX_VALUE + "]").with("x", Double.MAX_VALUE).get();
            request("AIDA:SAMPLE:TEST:attribute07", "Min Double: 7.7 * x[" + Double.MIN_VALUE + "]").with("x", Double.MIN_VALUE).get();
        }

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get String");
            request("AIDA:SAMPLE:TEST:attribute08", "String").get();
            request("AIDA:SAMPLE:TEST:attribute08", "Concatenate String: \"eight: \" + x[\"Hello World\"]").with("x", "Hello World").get();
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Boolean Array");
            request("AIDA:SAMPLE:TEST:attribute11", "Boolean Array").get();
            request("AIDA:SAMPLE:TEST:attribute11", "Boolean Array: x[[\"TRUE\", \"FALSE\", \"T\", \"F\", \"Y\", \"N\", \"YES\", \"NO\"]]")
                    .with("x", List.of("TRUE", "FALSE", "T", "F", "Y", "N", "YES", "NO"))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute11", "Boolean Array: x[[1, 0]]")
                    .with("x", List.of(1, 0))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute11", "Boolean Array: x[[Boolean.TRUE, Boolean.FALSE]]")
                    .with("x", List.of(Boolean.TRUE, Boolean.FALSE))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute11", "As Json: x[[1, 0]]")
                    .with("x", "[1, 0]")
                    .get();
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Byte Array");
            request("AIDA:SAMPLE:TEST:attribute12", "Byte Array").get();
            request("AIDA:SAMPLE:TEST:attribute12", "Byte Array: 12 | x[[0x4, 0x8, 0x48, 65]]")
                    .with("x", List.of(0x4, 0x8, 0x48, 65))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute12", "Char Array: 12 | x[[0x40, 0x41, 0x48, 0x49]]")
                    .with("x", List.of(0x40, 0x41, 0x48, 0x49))
                    .returning(AIDA_CHAR_ARRAY)
                    .get();
            request("AIDA:SAMPLE:TEST:attribute12", "As Json: 12 | x[[0x4, 0x8, 0x48, 65]]")
                    .with("x", "[4, 8, 72, 65]")
                    .get();
        }

        // 11
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Short Array");
            request("AIDA:SAMPLE:TEST:attribute13", "Short Array").get();
            request("AIDA:SAMPLE:TEST:attribute13", "Short Array: 13 + x[[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]]")
                    .with("x", List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute13", "Short Array: 13 + x[[32693, 32707, 32713, 32717, 32719, 32749]]")
                    .with("x", List.of(32693, 32707, 32713, 32717, 32719, 32749))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute13", "As Json: 13 + x[[32693, 32707, 32713, 32717, 32719, 32749]]")
                    .with("x", "[32693, 32707, 32713, 32717, 32719, 32749]")
                    .get();
        }

        // 12
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Integer Array");
            request("AIDA:SAMPLE:TEST:attribute14", "Integer Array").get();
            request("AIDA:SAMPLE:TEST:attribute14", "Integer Array: 14 + x[[10000019, 10000079,10000103,10000121,10000139,10000141]]")
                    .with("x", List.of(10000019, 10000079, 10000103, 10000121, 10000139, 10000141))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute14", "As Json: 14 + x[[10000019, 10000079,10000103,10000121,10000139,10000141]]")
                    .with("x", "[10000019, 10000079,10000103,10000121,10000139,10000141]")
                    .get();
        }

        // 13
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Long Array");
            request("AIDA:SAMPLE:TEST:attribute15", "Long Array").get();
            request("AIDA:SAMPLE:TEST:attribute15", "Long Array: 15 + x[[1000000007L, 1000000009L, 1000000021L, 1000000033L, 1000000087L, 1000000093L]]")
                    .with("x", List.of(1000000007L, 1000000009L, 1000000021L, 1000000033L, 1000000087L, 1000000093L))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute15", "As Json: 15 + x[[1000000007, 1000000009, 1000000021, 1000000033, 1000000087, 1000000093]]")
                    .with("x", "[1000000007, 1000000009, 1000000021, 1000000033, 1000000087, 1000000093]")
                    .get();
        }

        // 14
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Float Array");
            request("AIDA:SAMPLE:TEST:attribute16", "Float Array").get();
            request("AIDA:SAMPLE:TEST:attribute16", "Formats: Float Array: 16.6 * x[[PI/16.6, E/16.6, 1e-10f, 1e+10f, 1.234567e-23f, 1.234567e+23f, 1.23456789e+23f]]")
                    .with("x", List.of(((float) (PI / 16.6)), ((float) (E / 16.6)), 1e-10f, 1e+10f, 1.234567e-23f, 1.234567e+23f, 1.23456789e+23f))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute16", "Precision: Float Array: 16.6 * x[[1.2345678f, 1.2345679f, -1.2345678f, -1.2345679f, 1.0000001f, 1.0000002f, -1.0000001f, -1.0000002f]]")
                    .with("x", List.of(1.2345678f, 1.2345679f, -1.2345678f, -1.2345679f, 1.0000001f, 1.0000002f, -1.0000001f, -1.0000002f))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute16", "Significance Limit: Float Array: 16.6 * x[[1.23456789f, 1.234567896f, -1.23456789f, -1.234567896f, 1.0000001f, 1.00000016f, -1.0000001f, -1.00000016f]]")
                    .with("x", List.of(1.23456789f, 1.234567896f, -1.23456789f, -1.234567896f, 1.0000001f, 1.00000016f, -1.0000001f, -1.00000016f))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute16", "Significance Limit e-notation: Float Array: 16.6 * x[[1.23456789e-23f, 1.234567896e-23f, 1.23456789e+23f, 1.234567896e+23f]]")
                    .with("x", List.of(1.23456789e-23f, 1.234567896e-23f, 1.23456789e+23f, 1.234567896e+23f))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute16", "Max/Min/pi/e: Float Array: 16.6 * x[[Float.MIN_VALUE, Float.MAX_VALUE, PI, E]]")
                    .with("x", List.of(Float.MIN_VALUE, Float.MAX_VALUE, PI, E))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute16", "Max/Min/pi/e: As Json Array: 16.6 * x[[1.4E-45, 3.4028235E38, 3.141592653589793, 2.718281828459045]]]")
                    .with("x", "[1.4E-45, 3.4028235E38, 3.141592653589793, 2.718281828459045]")
                    .get();
        }

        // 15
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Double Array");
            request("AIDA:SAMPLE:TEST:attribute17", "Double Array").get();
            request("AIDA:SAMPLE:TEST:attribute17", "Formats: Double Array: 17.7 * x[[PI/17.7, E/17.7, 1e-100, 1e+100, 1.0000000234567e-230, 1.0000000234567e+230, 1.000000023456789e+230]]")
                    .with("x", List.of(PI / 17.7, E / 17.7, 1e-100, 1e+100, 1.0000000234567e-230, 1.0000000234567e+230, 1.000000023456789e+230))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute17", "Precision: Double Array: 17.7 * x[[1.000000002345678, 1.000000002345679, -1.000000002345678, -1.000000002345679, 1.000000000000001, 1.000000000000002, -1.000000000000001, -1.000000000000002]]")
                    .with("x", List.of(1.000000002345678, 1.000000002345679, -1.000000002345678, -1.000000002345679, 1.000000000000001, 1.000000000000002, -1.000000000000001, -1.000000000000002))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute17", "Significance Limit: Double Array: 17.7 * x[[1.0000000023456787, 1.0000000023456788, -1.0000000023456787, -1.0000000023456788, 1.0000000000000017, 1.0000000000000018, -1.0000000000000017, -1.0000000000000018]]")
                    .with("x", List.of(1.0000000023456787, 1.0000000023456788, -1.0000000023456787, -1.0000000023456788, 1.0000000000000017, 1.0000000000000018, -1.0000000000000017, -1.0000000000000018))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute17", "Significance Limit e-notation: Double Array: 17.7 * x[[1.0000000023456787e-23, 1.0000000023456786e-23, 1.0000000023456787e+23, 1.0000000023456788e+23]]")
                    .with("x", List.of(1.0000000023456787e-23, 1.0000000023456786e-23, 1.0000000023456787e+23, 1.0000000023456788e+23))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute17", "Max/Min/pi/e: Double Array: 17.7 * x[[Double.MIN_VALUE, Double.MAX_VALUE, PI, E]]")
                    .with("x", List.of(Double.MIN_VALUE, Double.MAX_VALUE, PI, E))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute17", "Max/Min/pi/e: As Json Array: 17.7 * x[[4.9E-324, 1.7976931348623157E308, 3.141592653589793, 2.718281828459045]]")
                    .with("x", "[4.9E-324, 1.7976931348623157E308, 3.141592653589793, 2.718281828459045]")
                    .get();
        }

        // 16
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get String Array");
            request("AIDA:SAMPLE:TEST:attribute18", "String Array").get();
            request("AIDA:SAMPLE:TEST:attribute18", "Multiple Strings")
                    .with("x", List.of("Hello", "AIDA-PVA", "World", "Have", "a", "nice", "day"))
                    .get();
            request("AIDA:SAMPLE:TEST:attribute18", "As json")
                    .with("x", "[\"Hello\", \"AIDA-PVA\", \"World\", \"Have\", \"a\", \"json\", \"day\"]")
                    .get();
        }

        // 17
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get Table");
            request("AIDA:SAMPLE:TEST:attribute20", "Table").get();
            request("AIDA:SAMPLE:TEST:attribute20", "Table x[same operations as scalar]")
                    .with("x",
                            Map.of("boolean", "true",
                                    "byte", 102,
                                    "short", 103,
                                    "integer", 104,
                                    "long", 105,
                                    "float", 106.5f,
                                    "double", 107.7,
                                    "string", "one hundred and eight"
                            )
                    )
                    .get();
        }

        // 18
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Void Setter");
            setRequest("AIDA:SAMPLE:TEST:attribute30", Boolean.TRUE);
            request("AIDA:SAMPLE:TEST:attribute30", "Set Value: TRUE, x=FALSE")
                    .with("x", Boolean.FALSE)
                    .set(Boolean.TRUE);
        }

        // 19
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Setter returning Table");
            request("AIDA:SAMPLE:TEST:attribute31", "Set Value: True, Return Table").set(Boolean.TRUE);
            request("AIDA:SAMPLE:TEST:attribute31", "Set Value: False, Return Table").set(Boolean.FALSE);
            request("AIDA:SAMPLE:TEST:attribute31", "Set Value: 1, Return Table").set(1);
            request("AIDA:SAMPLE:TEST:attribute31", "Set Value: 0, Return Table").set(0);
            request("AIDA:SAMPLE:TEST:attribute31", "Set Value: True, x=FALSE, Return Table")
                    .with("x", Boolean.FALSE)
                    .set(Boolean.TRUE);
        }

        // 20
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Arbitrary Getters on the same request");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_BOOLEAN, "Boolean");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_BYTE, "Byte");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_CHAR, "Char");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_SHORT, "Short");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_INTEGER, "Integer");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_LONG, "Long");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_FLOAT, "Float");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_DOUBLE, "Double");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_STRING, "String");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_BOOLEAN_ARRAY, "Boolean Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_BYTE_ARRAY, "Byte Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_CHAR_ARRAY, "Char Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_SHORT_ARRAY, "Short Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_INTEGER_ARRAY, "Integer Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_LONG_ARRAY, "Long Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_FLOAT_ARRAY, "Float Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_DOUBLE_ARRAY, "Double Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_STRING_ARRAY, "String Array");
            getRequest("AIDA:SAMPLE:TEST:attribute32", AIDA_TABLE, "Table");
        }

        // 21
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Channel Not Supported");
            request("AIDA:SAMPLE:TEST:attribute00", "Not Supported").getAndExpectFailure();
        }

        // 22
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Invalid Parameters");
            request("AIDA:SAMPLE:TEST:attribute01", "Boolean: y[1] ").with("y", 1).getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute02", "Byte: 0x2 | y[0x2]").with("y", 0x2).getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute03", "Short: 3 + y[3]").with("y", 3).getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute04", "Integer: 4 + y[4]").with("y", 4).getAndExpectFailure();

            request("AIDA:SAMPLE:TEST:attribute05", "Long: 5 + y[5]").with("y", 5).getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute06", "Float: 6.6f * y[6.6f]").with("y", 6.6f).getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute07", "Double: 7.7 * y[7.7]").with("y", 7.7).getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute08", "Concatenate String: \"eight: \" + y[\"Hello World\"]").with("y", "Hello World").getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute11", "Boolean Array: y[[\"TRUE\", \"FALSE\", \"T\", \"F\", \"Y\", \"N\", \"YES\", \"NO\"]]")
                    .with("y", List.of("TRUE", "FALSE", "T", "F", "Y", "N", "YES", "NO"))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute12", "Byte Array: 12 | y[[0x4, 0x8, 0x48, 65]]")
                    .with("y", List.of(0x4, 0x8, 0x48, 65))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute13", "Short Array: 13 + y[[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37]]")
                    .with("y", List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute14", "Integer Array: 14 + y[[10000019, 10000079,10000103,10000121,10000139,10000141]]")
                    .with("y", List.of(10000019, 10000079, 10000103, 10000121, 10000139, 10000141))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute15", "Long Array: 15 + y[[1000000007L, 1000000009L, 1000000021L, 1000000033L, 1000000087L, 1000000093L]]")
                    .with("y", List.of(1000000007L, 1000000009L, 1000000021L, 1000000033L, 1000000087L, 1000000093L))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute16", "Formats: Float Array: 16.6 * y[[PI/16.6, E/16.6, 1e-10f, 1e+10f, 1.234567e-23f, 1.234567e+23f, 1.23456789e+23f]]")
                    .with("y", List.of(((float) (PI / 16.6)), ((float) (E / 16.6)), 1e-10f, 1e+10f, 1.234567e-23f, 1.234567e+23f, 1.23456789e+23f))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute17", "Formats: Double Array: 17.7 * y[[PI/17.7, E/17.7, 1e-100, 1e+100, 1.0000000234567e-230, 1.0000000234567e+230, 1.000000023456789e+230]]")
                    .with("y", List.of(PI / 17.7, E / 17.7, 1e-100, 1e+100, 1.0000000234567e-230, 1.0000000234567e+230, 1.000000023456789e+230))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute18", "Multiple Strings")
                    .with("y", List.of("Hello", "AIDA-PVA", "World", "Have", "a", "nice", "day"))
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute20", "Table y[same operations as scalar]")
                    .with("y",
                            Map.of("boolean", "true",
                                    "byte", 102,
                                    "short", 103,
                                    "integer", 104,
                                    "long", 105,
                                    "float", 106.5f,
                                    "double", 107.7,
                                    "string", "one hundred and eight"
                            )
                    )
                    .getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute30", "Set Value: TRUE, y=FALSE")
                    .with("y", Boolean.FALSE)
                    .setAndExpectFailure(Boolean.TRUE);
            request("AIDA:SAMPLE:TEST:attribute31", "Set Value: True, y=FALSE, Return Table")
                    .with("y", Boolean.FALSE)
                    .setAndExpectFailure(Boolean.TRUE);
        }

        // 23
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Invalid Booleans");
            request("AIDA:SAMPLE:TEST:attribute01", "Valid Boolean: x[1100] ").with("x", 1100).get();
            request("AIDA:SAMPLE:TEST:attribute01", "Valid Boolean: x[0.0] ").with("x", 0.0).get();
            request("AIDA:SAMPLE:TEST:attribute01", "Valid Boolean: x[0.1] ").with("x", 0.1).get();
            request("AIDA:SAMPLE:TEST:attribute01", "Invalid Boolean: x[\"truly\"] ").with("x", "truly").getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute01", "Invalid Boolean: x[\"UNTRUE\"] ").with("x", "UNTRUE").getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute01", "Invalid Boolean: x[\"O\"] ").with("x", "O").getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute01", "Invalid Boolean: x[\"yeah\"] ").with("x", "yeah").getAndExpectFailure();
            request("AIDA:SAMPLE:TEST:attribute01", "Invalid Boolean: x[\"naw\"] ").with("x", "naw").getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
