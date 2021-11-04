/**
 * @file
 * @brief SLC Util Native Channel Provider Tests
 */
package edu.stanford.slac.aida.test;

import edu.stanford.slac.aida.test.utils.AidaType;

import java.util.Arrays;

import static edu.stanford.slac.aida.test.utils.AidaPvaTestUtils.*;

/**
 * This class is used to test the SLC Utility AIDA-PVA provider
 */
public class SlcUtilTest {
    public static void main(String[] args) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;

        testSuiteHeader("AIDA-PVA SLC Utility TESTS");

        // 01
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "set value for MKB.  sleeping for 5 seconds between runs");
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL reversed sign")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        // 02
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "set value for MKB.  sleeping for 5 seconds between runs");
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL reversed sign")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        // 03
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Test of specified absolute multiknob file, which is not permitted.  " +
                    "sleeping for 5 seconds between runs. The requested set operation should " +
                    "fail since the specified multiknob file is absolute, which is not permitted");
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL")
                        .with("MKB", "mkb:li31test.mkb")
                        .setAndExpectFailure(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL reversed sign")
                        .with("MKB", "mkb:li31test.mkb")
                        .setAndExpectFailure(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        // 04
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "PROD environment test Extract device string and secondary value vectors.  This test should only be run in the PROD environment");
            System.out.println("Skipped");
            /*
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL")
                        .with("MKB", "mkb:li02b_xb.mkb")
                        .set(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            testHeader(testId, "reversing sign of relative delta");
            for (var i = 0; i < 3; i++) {
                request("MKB:VAL", "MKB VAL")
                        .with("MKB", "mkb:li02b_xb.mkb")
                        .set(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
*/
        }


        // 05
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire SHORT type");
            request("TRIG:LI31:109:TACT", "TACT")
                    .with("BEAM", 1)
                    .returning(AidaType.SHORT)
                    .get();
        }


        // 06
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Acquire STRING type");
            request("TRIG:LI31:109:TACT", "TACT")
                    .with("BEAM", 1)
                    .returning(AidaType.STRING)
                    .get();
        }

        // 07
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Deactivate the specified TRIG device");
            request("TRIG:LI31:109:TACT", "Deactivated")
                    .with("BEAM", 1)
                    .set(0);
        }

        // 08
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Reactivate the specified TRIG device");
            request("TRIG:LI31:109:TACT", "Reactivated")
                    .with("BEAM", 1)
                    .set(1);
        }

        // 09
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set the specified variable of a specified BGRP to the new value \"Y\"");
            request("BGRP:VAL", "Variable: T_CAV")
                    .with("BGRP", "LCLS")
                    .with("VARNAME", "T_CAV")
                    .set("N");
        }

        // 10
        if (argString.contains(" " + ++testId + ",") || allTests) {
            testHeader(testId, "Set the specified variable of a specified BGRP to the new value \"N\"");
            request("BGRP:VAL", "Variable: T_CAV")
                    .with("BGRP", "LCLS")
                    .with("VARNAME", "T_CAV")
                    .set("Y");
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
