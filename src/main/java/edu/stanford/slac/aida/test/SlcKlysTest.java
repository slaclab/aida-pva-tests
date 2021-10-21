/**
 * @file
 * @brief SLC Klystron Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaPvaTestUtils;
import edu.stanford.slac.aida.test.utils.AidaType;

import java.util.Arrays;

/**
 * This class is used to test the SLC Klystron AIDA-PVA provider
 */
public class SlcKlysTest {
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        AidaPvaTestUtils.NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (AidaPvaTestUtils.NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        AidaPvaTestUtils.testSuiteHeader("AIDA-PVA SLC Klystron TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Acquire SHORT type");
            AidaPvaTestUtils.channel("KLYS:LI31:31:TACT", "Short")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(AidaType.SHORT)
                    .get();
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Acquire STRING type");
            AidaPvaTestUtils.channel("KLYS:LI31:31:TACT", "String")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(AidaType.STRING)
                    .get();
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Acquire TABLE type");
            AidaPvaTestUtils.channel("KLYS:LI31:31:TACT", "Table")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(AidaType.TABLE)
                    .get();
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Deactivate the specified klystron");
            AidaPvaTestUtils.channel("KLYS:LI31:31:TACT", "Deactivated")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .set(0);
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Reactivate the specified klystron");
            AidaPvaTestUtils.channel("KLYS:LI31:31:TACT", "Reactivated")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .set(1);
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Set a sub-booster or klystron PDES value and trim it");
            AidaPvaTestUtils.channel("KLYS:LI31:31:PDES", "PDES").set(90.0f);
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Set a subbooster or klystron PDES value without the trim operation");
            AidaPvaTestUtils.channel("KLYS:LI31:31:PDES", "PDES")
                    .with("TRIM", "NO")
                    .set(90.0f);
        }

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Set a subbooster or klystron KPHR value");
            AidaPvaTestUtils.channel("KLYS:LI31:31:KPHR", "KPHR").set(60.0f);
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Set configuration secondary PCON value");
            AidaPvaTestUtils.setWithNoArguments("KLYS:LI31:31:PCON", 5.0f);
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Set configuration secondary ACON value");
            AidaPvaTestUtils.setWithNoArguments("KLYS:LI31:31:ACON", 5.0f);
        }

        // 11
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get configuration secondary PDES value");
            AidaPvaTestUtils.getWithNoArguments("SLC::KLYS:LI31:31:PDES", AidaType.FLOAT, "PDES value");
        }

        // 12
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get configuration secondary KPHR value");
            AidaPvaTestUtils.getWithNoArguments("SLC::KLYS:LI31:31:KPHR", AidaType.FLOAT, "KPHR value");
        }


        // 13
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Error Test: Invalid BEAM");
            AidaPvaTestUtils.channel("KLYS:LI31:31:TACT", "PDES")
                    .with("BEAM", "XYZ")
                    .with("DGRP", "DEV_DGRP")
                    .setAndExpectFailure(1);
        }

        // 14
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Error Test: Invalid BEAM");
            AidaPvaTestUtils.channel("KLYS:LI31:31:TACT", "PDES")
                    .with("BEAM", 1)
                    .with("DGRP", "LIN_KLYS")
                    .returning(AidaType.SHORT)
                    .getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
