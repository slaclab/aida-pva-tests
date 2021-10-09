package edu.stanford.slac.aida;

import java.util.Collections;
import java.util.HashMap;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;

/**
 * This class is used to test the SLC Magnet AIDA-PVA provider
 */
public class SlcMagnetTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC Magnet TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        // 01
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet get without parameters");
            channel("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet \"get\" with parameters");
            channel("DEV_DGRP:XCOR:BDES", "BDES")
                    .with("MICROS", "LI31-LI31")
                    .with("UNITS", "1-100")
                    .get();
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet get without parameters");
            channel("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet get without parameters (duplicate)");
            channel("DEV_DGRP:XCOR:BDES", "BDES").get();
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet set");
            channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(4.0f));
                    }});
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            String value = "{" +
                                "\"names\": [\"XCOR:LI31:41\"]," +
                                "\"values\": [4.0]" +
                                "}";

            testHeader(testId, "Test of magnet set using JSON");
            channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet set (duplicate)");
            channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(4.0f));
                    }});
        }

        // 08
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Error handling test of magnet set");
            channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOZ:LI31:41"));
                        put("values", Collections.singletonList(4.0f));
                    }});
        }

        // 09
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet set");
            channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(10.0f));
                    }});
        }

        // 10
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet set without trim or perturb");
            channel("MAGNETSET:BDES", "Magnet Set")
                    .with("MAGFUNC", "NOFUNC")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(5.0f));
                    }});
        }

        // 11
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet set, setting the configuration secondary BCON");
            channel("MAGNETSET:BCON", "Magnet Set")
                    .set(new HashMap<String, Object>() {{
                        put("names", Collections.singletonList("XCOR:LI31:41"));
                        put("values", Collections.singletonList(5.0f));
                    }});
        }
        // Because of threads started in background to process requests
        System.exit(0);
    }
}
