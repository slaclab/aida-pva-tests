# Aida-PVA-Test

This repository contains code to test the aida-PVA framework from a client perspective.  It will allow you to 
verify that the framework is functioning well.

## To build the tests
```shell
git clone git@github.com:slaclab/aida-pva-tests.git
cd aida-pva-tests
mvn install

```
The shaded jar file is found in the target directory. `./target/aida-pva-tests-1.0-SNAPSHOT.jar`

## To run the tests
Running the tests are equally simple.  You need to make sure that your environment is set up so that you can 
find the epics providers.  All you need to do is to set the `EPICS_PVA_ADDR_LIST` variable to identify the 
network or host you want to search.  

Test are all named starting with SLC and then the short code identifying the provider followed by `Test`. 
e.g. `SlcBpmTest`.  
Each test is in a the `edu.stanford.slac.aida` package directory so the full path to a test is something
like `edu.stanford.slac.aida.SlcBpmTest`.

As the jar is shaded it includes everything it needs so no need for a complicated classpath.
The full procedure to run the tests is as follows:


e.g. 1 Run the SLC Utility Provider tests 
```shell
export EPICS_PVA_ADDR_LIST=mccdev.slac.stanford.edu
cd ./target
java -cp aida-pva-tests.jar "edu.stanford.slac.aida.SlcUtilTest"
```

The output will be something like this:
```shell
#################################################
AIDA-PVA SLC TESTS
#################################################

████ Test 1: Acquire scalar types SLC PMUS
_________________________________________________

get: XCOR:LI03:120:LEFF => FLOAT
    Float BACT: 0.262 ✔
get: XCOR:LI03:120:LEFF => DOUBLE
    Double BACT: 0.2619999945163727 ✔
get: XCOR:LI03:120:VSTA => BOOLEAN
    Boolean VSTA: true ✔
get: XCOR:LI03:120:SETL => SHORT
    Short SETL: 3000 ✔
get: XCOR:LI03:120:ADCP => BYTE
    Byte ADCP: 48 ✔
get: XCOR:LI03:120:ADCP => CHAR
    Char ADCP: '0' ✔
get: XCOR:LI03:120:ADCP => SHORT
    Wchar ADCP: 48 ✔
get: XCOR:LI03:120:PSNM => STRING
    String PSNM: UNKNOWN  ✔
get: XCOR:LI03:120//PSNM => STRING
    WString PSNM: UNKNOWN  ✔
get: XCOR:LI03:120//PSNM => BYTE_ARRAY
    Byte array PSNM:
        85 ✔
        78 ✔
        75 ✔
        78 ✔
        79 ✔
        87 ✔
        78 ✔
        32 ✔
get: XCOR:LI03:120//PSNM => CHAR_ARRAY
    Char array PSNM:
        'U' ✔
        'N' ✔
        'K' ✔
        'N' ✔
        'O' ✔
        'W' ✔
        'N' ✔
        ' ' ✔

████ Test 2: Acquire FLOAT type SLC PMUS
_________________________________________________

get: XCOR:LI03:120//BACT => FLOAT
    BACT: 0.0 ✔

████ Test 3: Acquire LONG INT type SLC PMUS
_________________________________________________

get: XCOR:LI03:120//VSTA => LONG
    VSTA: 8256 ✔

████ Test 4: Acquire array of FLOAT type SLC PMUS
_________________________________________________

get: XCOR:LI03:120//IMMS => FLOAT_ARRAY
    IMMS:
        -5.0 ✔
        5.0 ✔
        0.0 ✔

████ Test 5: Acquire array of DOUBLE type SLC PMUS
_________________________________________________

get: XCOR:LI03:120//IMMS => DOUBLE_ARRAY
    IMMS:
        -5.0 ✔
        5.0 ✔
        0.0 ✔

████ Test 6: Acquire array of SHORT type SLC PMUS
_________________________________________________

get: XCOR:LI03:120//NSCY => SHORT_ARRAY
    NSCY:
        0 ✔
        0 ✔

████ Test 7: Acquire array of LONG type SLC PMUS
_________________________________________________

get: XCOR:LI03:120//RAMP => LONG_ARRAY
    RAMP:
        0 ✔
        -1149191961165430640 ✔

████ Test 8: Acquire array of BOOLEAN type SLC PMUS
_________________________________________________

get: XCOR:LI03:120//RAMP => BOOLEAN_ARRAY
    RAMP:
        true ✔
        false ✔

████ Test 9: Acquire FLOAT type SLC Database PV
_________________________________________________

get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔

████ Test 10: Looping acquire FLOAT type SLC Database PV pausing 10 seconds between each.
_________________________________________________

get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔
get: XCOR:LI03:120//LEFF => FLOAT
    LEFF: 0.262 ✔

████ Test 11: ASTS channel name test
_________________________________________________

get: ASTS:LI00:ISOPLAN2//DATA => FLOAT
    DATA: 0.0 ✔
get: ASTS:PR02:VP3012//DATA => FLOAT
    DATA: 0.0 ✔
get: ASTS:PR02:VP3012//STAT => SHORT
    STAT: 40 ✔
get: ASTS:PR02:T2092BLW//LIM1 => FLOAT
    LIM1: 32.0 ✔
get: ASTS:PR02:T2092BLW//LIM2 => FLOAT
    LIM2: 150.0 ✔
get: ASTS:PR02:T2092BLW//LIMS => FLOAT_ARRAY
    LIMS:
        32.0 ✔
        150.0 ✔
get: ASTS:PR02:T2092QUA//SCAL => FLOAT_ARRAY
    SCAL:
        32.0 ✔
        1.8 ✔
get: ASTS:PR02:T2092QUA//RAW => FLOAT
    RAW: 1.0E-9 ✔
get: ASTS:PR02:T2092QUA//STAT => STRING
    STAT: CamErr   RED        ✔

████ Test 12: ASTS channel name with one of the pseudo-secondaries STAT, CTRL, or VSTA
_________________________________________________

get: ASTS:PR02:T2092QUA//STAT => STRING_ARRAY
    STAT:
        CamErr   ✔
        RED      ✔
          ✔

████ Test 13: Set value test
_________________________________________________

set: XCOR:LI31:41//BCON (VALUE=5.0) ✔
    
```

e.g. 2 Run one test from the SLC Utility Provider test suite
```shell
export EPICS_PVA_ADDR_LIST=mccdev.slac.stanford.edu
cd ./target
java -cp aida-pva-tests.jar "edu.stanford.slac.aida.SlcUtilTest" 2
```

The output will be something like this:
```shell
#################################################
AIDA-PVA SLC Utility TESTS
#################################################

████ Test 2: set value for MKB.  sleeping for 5 seconds between runs
_________________________________________________

set: MKB//VAL (MKB=mkb:gregstestli31.mkb, VALUE=1.0)
    MKB VAL:  2 rows retrieved: ✔
       Device Name          Value
              name          value
 XCOR:LI31:41:BDES -1.5949696E-23
 YCOR:LI31:41:BDES  9.0401356E-33
set: MKB//VAL (MKB=mkb:gregstestli31.mkb, VALUE=1.0)
    MKB VAL:  2 rows retrieved: ✔
       Device Name          Value
              name          value
 XCOR:LI31:41:BDES   4.1969155E-8
 YCOR:LI31:41:BDES -1.50365043E14
set: MKB//VAL (MKB=mkb:gregstestli31.mkb, VALUE=1.0)
    MKB VAL:  2 rows retrieved: ✔
       Device Name         Value
              name         value
 XCOR:LI31:41:BDES -1.08137696E8
 YCOR:LI31:41:BDES -2.317549E-17

████ Test 2: reversing sign of relative delta
_________________________________________________

set: MKB//VAL (MKB=mkb:gregstestli31.mkb, VALUE=-1.0)
    MKB VAL:  2 rows retrieved: ✔
       Device Name          Value
              name          value
 XCOR:LI31:41:BDES   4.1969155E-8
 YCOR:LI31:41:BDES -1.50365043E14
set: MKB//VAL (MKB=mkb:gregstestli31.mkb, VALUE=-1.0)
    MKB VAL:  2 rows retrieved: ✔
       Device Name          Value
              name          value
 XCOR:LI31:41:BDES -1.5949696E-23
 YCOR:LI31:41:BDES  9.0401356E-33
set: MKB//VAL (MKB=mkb:gregstestli31.mkb, VALUE=-1.0)
    MKB VAL:  2 rows retrieved: ✔
       Device Name        Value
              name        value
 XCOR:LI31:41:BDES 0.0079956055
 YCOR:LI31:41:BDES  0.058290116


````

## For Providers developers

For developers of new Providers this repository is where you'll write tests for functional verification of your
new provider before it is released into production.  The repository contains many utilities to make 
the testing process very easy. 

### [AidaPvaTestUtils](./src/main/java/edu/stanford/slac/aida/utils/AidaPvaTestUtils.java)

Utility class to facilitate running all the AIDA-PVA tests
- Test Suite
  - Tests
    - Test Cases
<p>
In order to write a test its very easy.
<p>
e.g. 1: Simple get
<pre>{@code
     testSuiteHeader("AIDA-PVA SLC TESTS");
     testHeader(1, "Acquire scalar types SLC PMUS");
     getWithNoArguments("XCOR:LI03:120:LEFF", FLOAT, "Float BACT"
}</pre>
<p>
e.g. 2: Multiple arguments
<pre>{@code
     testSuiteHeader("AIDA-PVA SLC Buffered Acquisition TESTS");
     testHeader(2, "Get values of 4 BPMs");
     channel("NDRFACET//BUFFACQ", "BPM Values")
                    .with("BPMD", 57)
                    .with("NRPOS", 180)
                    .with("BPMS", Arrays.asList(
                            "BPMS:LI11:501",
                            "BPMS:LI11:601",
                            "BPMS:LI11:701",
                            "BPMS:LI11:801"))
                    .get();
}</pre>
<p>
e.g. 3: Simple set
<pre>{@code
     testSuiteHeader("AIDA-PVA SLC TESTS");
     testHeader(testId, "Set value test");
     setWithNoArguments("XCOR:LI31:41//BCON", 5.0f);
}</pre>
<p>
e.g. 4: Advanced set
<pre>{@code
     testSuiteHeader("AIDA-PVA SLC Klystron TESTS");
     testHeader(testId, "Deactivate the specified klystron");
     channel("KLYS:LI31:31//TACT", "Deactivated")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .set(0);
}</pre>
<p>
e.g. 5: Selecting the return value type
<pre>{@code
     testSuiteHeader("AIDA-PVA SLC Klystron TESTS");
     testHeader(testId, "Acquire STRING type");
     channel("KLYS:LI31:31//TACT", "String")
                    .with("BEAM", 8)
                    .with("DGRP", "DEV_DGRP")
                    .returning(STRING)
                    .get();
}</pre>

Enjoy!
