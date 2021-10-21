/**
 * @file
 * @brief SLC Buffered Acquisition Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaPvaTestUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * This class is used to test the SLC Buffered Acquisition AIDA-PVA provider
 */
public class SlcBuffAcqTest {
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        AidaPvaTestUtils.NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (AidaPvaTestUtils.NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        AidaPvaTestUtils.testSuiteHeader("AIDA-PVA SLC Buffered Acquisition TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get values of given BPM for 1 pulse");
            AidaPvaTestUtils.channel("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("BPMS", Collections.singletonList("BPMS:LI11:501"))
                    .get();
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Get values of 4 BPMs");
            AidaPvaTestUtils.channel("NDRFACET:BUFFACQ", "BPM Values")
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
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Bad argument Name - superfish=7");
            AidaPvaTestUtils.channel("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("superfish", 7)
                    .getAndExpectFailure();
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            AidaPvaTestUtils.testHeader(testId, "Bad argument value BPM1=BPMS:GREG:DOESNTEXIST");
            AidaPvaTestUtils.channel("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("BPMS", Collections.singletonList("BPMS:GREG:DOESNTEXIST"))
                    .getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
