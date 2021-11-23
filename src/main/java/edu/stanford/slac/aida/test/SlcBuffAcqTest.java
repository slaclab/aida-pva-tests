/**
 * @file
 * @brief SLC Buffered Acquisition Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import java.util.Arrays;
import java.util.List;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;

/**
 * This class is used to test the SLC Buffered Acquisition AIDA-PVA provider
 */
public class SlcBuffAcqTest {
    /**
     * Main entry point to the BPM Buffer Acquisition test
     *
     * @param args command line arguments -c for color, list of numbers to select test numbers
     */
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        testSuiteHeader("AIDA-PVA SLC Buffered Acquisition TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get values of given BPM for 1 pulse");
            request("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("BPMS", List.of("BPMS:LI11:501"))
                    .get();
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Get values of 4 BPMs");
            request("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("NRPOS", 180)
                    .with("BPMS", List.of(
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
            testHeader(testId, "Bad argument Name - superfish=7");
            request("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("superfish", 7)
                    .getAndExpectFailure();
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Bad argument value BPM1=BPMS:GREG:DOESNTEXIST");
            request("NDRFACET:BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("BPMS", List.of("BPMS:GREG:DOESNTEXIST"))
                    .getAndExpectFailure();
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
