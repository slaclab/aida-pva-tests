/**
 * @file
 * @brief SLC BPM Orbit Data Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaPvaTestUtils;

import java.util.Arrays;

/**
 * This class is used to test the SLC BPM Orbit Data AIDA-PVA provider
 */
public class SlcBpmTest {

    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        AidaPvaTestUtils.NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (AidaPvaTestUtils.NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        AidaPvaTestUtils.testSuiteHeader("AIDA-PVA SLC BPM TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "All values of all BPMs of LCLS dgrp under meas def 55, default options");
            AidaPvaTestUtils.channel("LCLS_SL2:BPMS", "BPM Values").with("BPMD", 55).get();
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "All values of all BPMs of P2BPMHER dgrp under meas def 38, default options");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "BPM Values").with("BPMD", 38).get();
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "All values of all BPMs of P2BPMHER dgrp, under meas def 38, SORTORDER=1 (by dgrp z)");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("SORTORDER", 1)
                    .get();
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get the diff to the GOLD orbit of all BPMs of P2BPMHER dgrp, under meas def 38");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "Get the diff to the GOLD orbit BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("CNFTYPE", "GOLD")
                    .get();
        }

        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get the diff to the most recently loaded config, for all BPMs of P2BPMHER dgrp, under meas def 38");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "Diff to the most recently loaded BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("CNFTYPE", "LOADED")
                    .get();
        }

        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get the diff to SCRATCH config #16386, for all BPMs of P2BPMHER dgrp, under meas def 38");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "Diff to the SCRATCH config #16386 BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("CNFTYPE", "SCRATCH")
                    .with("CNFNUM", 16386)
                    .get();
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get the diff to NORMAL config #1262, for all BPMs of P2BPMHER dgrp, under meas def 38");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "Diff to the NORMAL config #1262 BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("CNFTYPE", "NORMAL")
                    .with("CNFNUM", 1262)
                    .get();
        }

        // Error handling tests follow. These should not succeed in
        // acquiring data, but rather test appropriate error
        // handling.

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Bad argument Name - superfish=7");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 38)
                    .with("superfish", 7)
                    .getAndExpectFailure();
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Bad argument value - invalid CNFNUM");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 38)
                    .with("CNFTYPE", "NORMAL")
                    .with("CNFNUM", 9999)
                    .getAndExpectFailure();
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "BPMD not valid for DGRP");
            AidaPvaTestUtils.channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 39)       // 39 is a LER measurement def.
                    .with("SORTORDER", 1)
                    .getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
