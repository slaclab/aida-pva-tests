package edu.stanford.slac.aida;

import java.util.Arrays;
import java.util.Collections;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;

/**
 * This class is used to test the SLC Buffered Acquisition AIDA-PVA provider
 */
public class SlcBuffAcqTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC Buffered Acquisition TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        // 01
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get values of given BPM for 1 pulse");
            channel("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("BPMS", Collections.singletonList("BPMS:LI11:501"))
                    .get();
        }

        // 02
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Get values of 4 BPMs");
            channel("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("NRPOS", 180)
                    .with("BPMS", Arrays.asList(
                            "BPMS:LI11:501",
                            "BPMS:LI11:601",
                            "BPMS:LI11:701",
                            "BPMS:LI11:801"))
                    .get();
        }


        // Error handling tests follow. These should not succeed in
        // acquiring data, but rather test appropriate error
        // handling.


        // 03
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Bad argument Name - superfish=7");
            channel("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("superfish", 7)
                    .getAndExpectFailure();
        }

        // 04
        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Bad argument value BPM1=BPMS:GREG:DOESNTEXIST");
            channel("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("BPMS", Collections.singletonList("BPMS:GREG:DOESNTEXIST"))
                    .getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
