package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;

public class SlcMagnetTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC Magnet TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet get without parameters");
            channel("DEV_DGRP//XCOR:BDES", "BDES").get();
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet \"get\" with parameters");
            channel("DEV_DGRP//XCOR:BDES", "BDES")
                    .with("MICROS", "LI31-LI31")
                    .with("UNITS", "1-100")
                    .get();
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet get without parameters");
            channel("DEV_DGRP//XCOR:BDES", "BDES").get();
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of magnet get without parameters");
            channel("DEV_DGRP//XCOR:BDES", "BDES").get();
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            String value = "{" +
                    "\"names\": [\"XCOR:LI31:41\"]," +
                    "\"values\": [4.0]" +
                    "}";

            testHeader(testId, "Test of magnet set");
            channel("MAGNETSET//BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            String value = "{" +
                    "\"names\": [\"XCOR:LI31:41\"]," +
                    "\"values\": [4.0]" +
                    "}";

            testHeader(testId, "Test of magnet set");
            channel("MAGNETSET//BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            String value = "{" +
                    "\"names\": [\"XCOR:LI31:41\"]," +
                    "\"values\": [4.0]" +
                    "}";

            testHeader(testId, "Test of magnet set");
            channel("MAGNETSET//BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            String value = "{" +
                    "\"names\": [\"XCOZ:LI31:41\"]," +
                    "\"values\": [4.0]" +
                    "}";

            testHeader(testId, "Error handling test of magnet set");
            channel("MAGNETSET//BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            String value = "{" +
                    "\"names\": [\"XCOR:LI31:41\"]," +
                    "\"values\": [10.0]" +
                    "}";

            testHeader(testId, "Test of magnet set");
            channel("MAGNETSET//BDES", "Magnet Set")
                    .with("MAGFUNC", "TRIM")
                    .set(value);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            String value = "{" +
                    "\"names\": [\"XCOR:LI31:41\"]," +
                    "\"values\": [5.0]" +
                    "}";

            testHeader(testId, "Test of magnet set");
            channel("MAGNETSET//BCON", "Magnet Set").set(value);
        }
        // Because of threads started in background to process requests
        System.exit(0);
    }
}
