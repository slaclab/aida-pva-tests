package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.*;

/**
 * This class is used to test the SLC Klystron AIDA-PVA provider
 */
public class SlcKlysTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC Klystron TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire SHORT type");
            channel("KLYS:LI31:31:TACT", "Short")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(SHORT)
                    .get();
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire STRING type");
            channel("KLYS:LI31:31:TACT", "String")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(STRING)
                    .get();
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Deactivate the specified klystron");
            channel("KLYS:LI31:31:TACT", "Deactivated")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .set(0);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Reactivate the specified klystron");
            channel("KLYS:LI31:31:TACT", "Reactivated")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .set(1);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set a sub-booster or klystron PDES value and trim it");
            channel("KLYS:LI31:31:PDES", "PDES").set(90.0f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set a subbooster or klystron PDES value without the trim operation");
            channel("KLYS:LI31:31:PDES", "PDES")
                    .with("TRIM", "NO")
                    .set(90.0f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set a subbooster or klystron KPHR value");
            channel("KLYS:LI31:31:KPHR", "KPHR").set(60.0f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set configuration secondary PCON value");
            setWithNoArguments("KLYS:LI31:31:PCON", 5.0f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set configuration secondary ACON value");
            setWithNoArguments("KLYS:LI31:31//ACON", 5.0f);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get configuration secondary PDES value");
            getWithNoArguments("KLYS:LI31:31//PDES", FLOAT, "PDES value");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get configuration secondary KPHR value");
            getWithNoArguments("KLYS:LI31:31//KPHR", FLOAT, "KPHR value");
        }


        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Error Test: Invalid BEAM");
            channel("KLYS:LI31:31//TACT", "PDES")
                    .with("BEAM", "XYZ")
                    .with("DGRP", "DEV_DGRP")
                    .set(1);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Error Test: Invalid BEAM");
            channel("KLYS:LI31:31//TACT", "PDES")
                    .with("BEAM", 1)
                    .with("DGRP", "LIN_KLYS")
                    .returning(SHORT)
                    .get();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
