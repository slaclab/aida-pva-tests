package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;

/**
 * This class is used to test the SLC BPM Orbit Data AIDA-PVA provider
 */
public class SlcBpmTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC BPM TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        // 01
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "All values of all BPMs of LCLS dgrp under meas def 55, default options");
            channel("LCLS_SL2:BPMS", "BPM Values").with("BPMD", 55).get();
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "All values of all BPMs of P2BPMHER dgrp under meas def 38, default options");
            channel("P2BPMHER:BPMS", "BPM Values").with("BPMD", 38).get();
        }

        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "All values of all BPMs of P2BPMHER dgrp, under meas def 38, SORTORDER=1 (by dgrp z)");
            channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("SORTORDER", 1)
                    .get();
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get the diff to the GOLD orbit of all BPMs of P2BPMHER dgrp, under meas def 38");
            channel("P2BPMHER:BPMS", "Get the diff to the GOLD orbit BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("CNFTYPE", "GOLD")
                    .get();
        }

        // 05
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get the diff to the most recently loaded config, for all BPMs of P2BPMHER dgrp, under meas def 38");
            channel("P2BPMHER:BPMS", "Diff to the most recently loaded BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("CNFTYPE", "LOADED")
                    .get();
        }

        // 06
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get the diff to SCRATCH config #16386, for all BPMs of P2BPMHER dgrp, under meas def 38");
            channel("P2BPMHER:BPMS", "Diff to the SCRATCH config #16386 BPM Values")
                    .with("BPMD", 38)
                    .with("N", 1024)
                    .with("CNFTYPE", "SCRATCH")
                    .with("CNFNUM", 16386)
                    .get();
        }

        // 07
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get the diff to NORMAL config #1262, for all BPMs of P2BPMHER dgrp, under meas def 38");
            channel("P2BPMHER:BPMS", "Diff to the NORMAL config #1262 BPM Values")
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
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Bad argument Name - superfish=7");
            channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 38)
                    .with("superfish", 7)
                    .getAndExpectFailure();
        }

        // 09
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Bad argument value - invalid CNFNUM");
            channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 38)
                    .with("CNFTYPE", "NORMAL")
                    .with("CNFNUM", 9999)
                    .getAndExpectFailure();
        }

        // 10
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "BPMD not valid for DGRP");
            channel("P2BPMHER:BPMS", "BPM Values")
                    .with("BPMD", 39)       // 39 is a LER measurement def.
                    .with("SORTORDER", 1)
                    .getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
