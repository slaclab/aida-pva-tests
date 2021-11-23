/**
 * @file
 * @brief SLC Magnet Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;

/**
 * This class is used to test the SLC Magnet AIDA-PVA provider
 */
public class SlcMagnetTest {
    /**
     * Main entry point to the Magnet test
     *
     * @param args command line arguments -c for color, list of numbers to select test numbers
     */
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        testSuiteHeader("AIDA-PVA SLC Magnet TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of magnet get without parameters");
            request("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of magnet \"get\" with parameters");
            request("DEV_DGRP:XCOR:BDES", "BDES")
                    .with("MICROS", "LI31-LI31")
                    .with("UNITS", "1-100")
                    .get();
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of magnet get without parameters");
            request("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of magnet set");
            request("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(Map.of(
                            "names", List.of("XCOR:LI31:41"),
                            "values", List.of(4.0f)
                    ));
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            var value = "{" +
                    "\"names\": [\"XCOR:LI31:41\"]," +
                    "\"values\": [4.0]" +
                    "}";

            testHeader(testId, "Test of magnet set using JSON");
            request("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of magnet set (duplicate)");
            request("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(Map.of(
                            "names", List.of("XCOR:LI31:41"),
                            "values", List.of(4.0f)
                    ));
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Error handling invalid name: magnet set");
            request("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .setAndExpectFailure(new HashMap<String, Object>() {{
                        put("names", List.of("XCOZ:LI31:41"));
                        put("values", List.of(4.0f));
                    }});
        }

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Error handling values out of limits: magnet set");
            request("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .setAndExpectFailure(Map.of(
                            "names", List.of("XCOR:LI31:41"),
                            "values", List.of(10.0f)
                    ));
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of magnet set without trim or perturb");
            request("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "NOFUNC")
                    .set(Map.of(
                            "names", List.of("XCOR:LI31:41"),
                            "values", List.of(5.0f)
                    ));
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of magnet set, setting the configuration secondary BCON");
            request("MAGNETSET:BCON", "Magnet Set")
                    .set(Map.of(
                            "names", List.of("XCOR:LI31:41"),
                            "values", List.of(5.0f)
                    ));
        }
        // Because of threads started in background to process requests
        System.exit(0);
    }
}
