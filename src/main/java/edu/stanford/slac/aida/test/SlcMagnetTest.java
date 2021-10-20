/**
 * @file
 * @brief SLC Magnet Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaPvaTestUtils;

import java.util.Collections;
import java.util.HashMap;

/**
 * This class is used to test the SLC Magnet AIDA-PVA provider
 */
public class SlcMagnetTest {
    public static void main(String[] args) {
        AidaPvaTestUtils.testSuiteHeader("AIDA-PVA SLC Magnet TESTS");

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
            AidaPvaTestUtils.testHeader(testId, "Test of magnet get without parameters");
            AidaPvaTestUtils.channel("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of magnet \"get\" with parameters");
            AidaPvaTestUtils.channel("DEV_DGRP:XCOR:BDES", "BDES")
                    .with("MICROS", "LI31-LI31")
                    .with("UNITS", "1-100")
                    .get();
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of magnet get without parameters");
            AidaPvaTestUtils.channel("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of magnet get without parameters (duplicate)");
            AidaPvaTestUtils.channel("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of magnet set");
            AidaPvaTestUtils.channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(4.0f));
                    }});
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            var value = "{" +
                    "\"names\": [\"XCOR:LI31:41\"]," +
                    "\"values\": [4.0]" +
                    "}";

            AidaPvaTestUtils.testHeader(testId, "Test of magnet set using JSON");
            AidaPvaTestUtils.channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of magnet set (duplicate)");
            AidaPvaTestUtils.channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(4.0f));
                    }});
        }

        // 08
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Error handling invalid name: magnet set");
            AidaPvaTestUtils.channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .setAndExpectFailure(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOZ:LI31:41"));
                        put("values", Collections.singletonList(4.0f));
                    }});
        }

        // 09
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Error handling values out of limits: magnet set");
            AidaPvaTestUtils.channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .setAndExpectFailure(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(10.0f));
                    }});
        }

        // 10
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of magnet set without trim or perturb");
            AidaPvaTestUtils.channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "NOFUNC")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(5.0f));
                    }});
        }

        // 11
        if (testNumber.equals(++testId) || testNumber == 0) {
            AidaPvaTestUtils.testHeader(testId, "Test of magnet set, setting the configuration secondary BCON");
            AidaPvaTestUtils.channel("MAGNETSET:BCON", "Magnet Set")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(5.0f));
                    }});
        }
        // Because of threads started in background to process requests
        System.exit(0);
    }
}
