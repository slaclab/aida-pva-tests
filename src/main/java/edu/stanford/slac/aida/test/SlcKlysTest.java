/**
 * @file
 * @brief SLC Klystron Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import java.util.Arrays;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.test.utils.AidaType.*;

/**
 * This class is used to test the SLC Klystron AIDA-PVA provider
 */
public class SlcKlysTest {
    /**
     * Main entry point to the Klystron test
     *
     * @param args command line arguments -c for color, list of numbers to select test numbers
     */
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        testSuiteHeader("AIDA-PVA SLC Klystron TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire SHORT type");
            request("KLYS:LI31:31:TACT", "Short")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(AIDA_SHORT)
                    .get();
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire STRING type");
            request("KLYS:LI31:31:TACT", "String")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(AIDA_STRING)
                    .get();
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire TABLE type");
            request("KLYS:LI31:31:TACT", "Table")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(AIDA_TABLE)
                    .get();
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Deactivate the specified klystron");
            request("KLYS:LI31:31:TACT", "Deactivated")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .set(0);
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Reactivate the specified klystron");
            request("KLYS:LI31:31:TACT", "Reactivated")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .set(1);
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set a sub-booster or klystron PDES value and trim it");
            request("KLYS:LI31:31:PDES", "PDES").set(90.0f);
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set a sub-booster or klystron PDES value without the trim operation");
            request("KLYS:LI31:31:PDES", "PDES")
                    .with("TRIM", "NO")
                    .set(90.0f);
        }

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set a sub-booster or klystron KPHR value");
            request("KLYS:LI31:31:KPHR", "KPHR").set(60.0f);
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set configuration secondary PCON value");
            setRequest("KLYS:LI31:31:PCON", 5.0f);
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set configuration secondary ACON value");
            setRequest("KLYS:LI31:31:ACON", 5.0f);
        }

        // 11
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get configuration secondary PDES value");
            getRequest("SLC::KLYS:LI31:31:PDES", AIDA_FLOAT, "PDES value");
        }

        // 12
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get configuration secondary KPHR value");
            getRequest("SLC::KLYS:LI31:31:KPHR", AIDA_FLOAT, "KPHR value");
        }


        // 13
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Error Test: Invalid BEAM");
            request("KLYS:LI31:31:TACT", "PDES")
                    .with("BEAM", "XYZ")
                    .with("DGRP", "DEV_DGRP")
                    .setAndExpectFailure(1);
        }

        // 14
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Error Test: Invalid BEAM");
            request("KLYS:LI31:31:TACT", "PDES")
                    .with("BEAM", 1)
                    .with("DGRP", "LIN_KLYS")
                    .returning(AIDA_SHORT)
                    .getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
