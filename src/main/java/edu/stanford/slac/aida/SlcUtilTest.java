package edu.stanford.slac.aida;

import static edu.stanford.slac.aida.utils.AidaPvaTestUtils.*;
import static edu.stanford.slac.aida.utils.AidaType.SHORT;
import static edu.stanford.slac.aida.utils.AidaType.STRING;

/**
 * This class is used to test the SLC Utility AIDA-PVA provider
 */
public class SlcUtilTest {
    public static void main(String[] args) {
        testSuiteHeader("AIDA-PVA SLC Utility TESTS");

        Integer testNumber = 0, testId = 0;
        if (args.length != 0) {
            testNumber = Integer.valueOf(args[0]);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "set value for MKB.  sleeping for 5 seconds between runs");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            testHeader(testId, "reversing sign of relative delta");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "set value for MKB.  sleeping for 5 seconds between runs");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            testHeader(testId, "reversing sign of relative delta");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:gregstestli31.mkb")
                        .set(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Test of specified absolute multiknob file, which is not permitted.  sleeping for 5 seconds between runs. The requested set operation should fail since the specified multiknob file is absolute, which is not permitted");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:li31test.mkb")
                        .set(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            testHeader(testId, "reversing sign of relative delta");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:li31test.mkb")
                        .set(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "PROD environment test Extract device string and secondary value vectors.  This test should only be run in the PROD environment");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:li02b_xb.mkb")
                        .set(1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
            testHeader(testId, "reversing sign of relative delta");
            for (int i = 0; i < 3; i++) {
                channel("MKB//VAL", "MKB VAL")
                        .with("MKB", "mkb:li02b_xb.mkb")
                        .set(-1.0f);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }


        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire SHORT type");
            getWithNoArguments("TRIG:LI31:109//TACT", SHORT, "TACT");
        }


        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Acquire STRING type");
            getWithNoArguments("TRIG:LI31:109//TACT", STRING, "TACT");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Deactivate the specified TRIG device");
            channel("TRIG:LI31:109//TACT", "Deactivated")
                    .with("BEAM", 1)
                    .set(0);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Reactivate the specified TRIG device");
            channel("TRIG:LI31:109//TACT", "Reactivated")
                    .with("BEAM", 1)
                    .set(1);
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set the specified variable of a specified BGRP to the new value \"Y\"");
            channel("BGRP//VAL", "Variable: T_CAV")
                    .with("BGRP", "LCLS")
                    .with("VARNAME", "T_CAV")
                    .set("N");
        }

        if (testNumber.equals(++testId) || testNumber == 0) {
            testHeader(testId, "Set the specified variable of a specified BGRP to the new value \"N\"");
            channel("BGRP//VAL", "Variable: T_CAV")
                    .with("BGRP", "LCLS")
                    .with("VARNAME", "T_CAV")
                    .set("Y");
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }
}
