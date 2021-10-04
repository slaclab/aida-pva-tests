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
## For Providers developers

For developers of new Providers this repository is where you'll write tests for functional verification of your
new provider before it is released into production.  The repository contains many utilities to make 
the testing process very easy. 

