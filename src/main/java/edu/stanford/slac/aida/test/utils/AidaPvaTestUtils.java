/**
 * @noop @formatter:off
 * @file
 * @brief Utility class to facilitate running all the AIDA-PVA tests.
 * - Test Suite
 *  - Tests
 *    - Test Cases
 *
 * @see
 *  AidaGetter::testSuiteHeader(),
 *  testCaseHeader(),
 *  testHeader(),
 *  channel(),
 *  getWithNoArguments(),
 *  setWithNoArguments()
 * @noop @formatter:on
 */
package edu.stanford.slac.aida.test.utils;

import org.epics.pvaccess.server.rpc.RPCRequestException;
import org.epics.pvdata.pv.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @noop @formatter:off
 * Utility class to facilitate running all the AIDA-PVA tests.
 *
 * @paragraph Details
 * In order to write a test its very easy.
 * @paragraph p1 e.g. 1: Simple get
 * @code
 *  testSuiteHeader(" AIDA - PVA SLC TESTS ");
 *  testHeader(1, "Acquire scalar types SLC PMUS");
 *  getWithNoArguments("XCOR:LI03:120:LEFF", FLOAT, "Float BACT");
 * @endcode
 * @paragraph p2 e.g. 2: Multiple arguments
 * @code
 *  testSuiteHeader(" AIDA - PVA SLC Buffered Acquisition TESTS ");
 *  testHeader(2, "Get values of 4 BPMs");
 *  channel("NDRFACET:BUFFACQ", "BPM Values")
 *      .with("BPMD", 57)
 *      .with("NRPOS", 180)
 *      .with("BPMS", Arrays.asList(
 *              "BPMS:LI11:501",
 *              "BPMS:LI11:601",
 *              "BPMS:LI11:701",
 *              "BPMS:LI11:801"))
 *      .get();
 * @endcode
 * @paragraph p3 e.g. 3: Simple set
 * @code
 *  testSuiteHeader(" AIDA - PVA SLC TESTS ");
 *  testHeader(testId, "Set value test");
 *  setWithNoArguments("XCOR:LI31:41:BCON", 5.0f);
 * @endcode
 * @paragraph p4 e.g. 4: Advanced set
 * @code
 *  testSuiteHeader(" AIDA - PVA SLC Klystron TESTS ");
 *  testHeader(testId, "Deactivate the specified klystron");
 *  channel("KLYS:LI31:31:TACT", "Deactivated")
 *      .with("BEAM", 8)
 *      .with("DGRP", "DEV_DGRP")
 *      .set(0);
 * @endcode
 * @paragraph p5 e.g. 5: Selecting the return value type
 * @code testSuiteHeader(" AIDA - PVA SLC Klystron TESTS ");
 *  testHeader(testId, "Acquire STRING type");
 *  channel("KLYS:LI31:31:TACT", "String")
 *      .with("BEAM", 8)
 *      .with("DGRP", "DEV_DGRP")
 *      .returning(STRING)
 *      .get();
 * @endcode
 * @noop @formatter:on
 */
public class AidaPvaTestUtils {
    // For pretty output - colors
    public static boolean NO_COLOR_FLAG = true;

    private static final String ANSI_RESET = "\u001B[0m";           //< ANSI escape sequence for RESET
    private static final String ANSI_BLACK = "\u001B[30m";          //< ANSI escape sequence for BLACK
    private static final String ANSI_RED = "\u001B[31m";            //< ANSI escape sequence for RED
    private static final String ANSI_GREEN = "\u001B[32m";          //< ANSI escape sequence for GREEN
    private static final String ANSI_YELLOW = "\u001B[33m";         //< ANSI escape sequence for YELLOW
    private static final String ANSI_BLUE = "\u001B[34m";           //< ANSI escape sequence for BLUE
    private static final String ANSI_PURPLE = "\u001B[35m";         //< ANSI escape sequence for PURPLE
    private static final String ANSI_CYAN = "\u001B[36m";           //< ANSI escape sequence for CYAN
    private static final String ANSI_WHITE = "\u001B[37m";          //< ANSI escape sequence for WHITE

    private static final String ANSI_INVERSE = "\u001B[7m";         //< ANSI escape sequence for INVERTED
    private static final String ANSI_NORMAL = "\u001B[m";           //< ANSI escape sequence for NON-INVERTED

    // To show success and failure signs
    private static final String TEST_SUCCESS = "✔";                 //< Check mark symbol
    private static final String TEST_FAILURE = "✘";                 //< Cross symbol
    private static final String TEST_SUCCESS_COLOR = ANSI_GREEN + "✔" + ANSI_RESET;     //< Colored Check mark symbol
    private static final String TEST_FAILURE_COLOR = ANSI_RED + "✘" + ANSI_RESET;       //< Colored Cross symbol

    // To introduce each test
    private static final String BLOCK = "████";                     //< A graphic block

    // For abbreviate(), this is the maximum length of messages that will be displayed
    private static final int MAX_MESSAGE_LEN = 250;                //< Maximum message length

    public static AidaPvaTestUtils testRunner(Object testId, String testHeader) {
        return new AidaPvaTestUtils();
    }

    public static AidaPvaTestUtils testRunner() {
        return new AidaPvaTestUtils();
    }

    /**
     * This functional interface is the similar to a Supplier except that
     * we throw RPCRequestExceptions for errors and always return PVStructures
     */
    @FunctionalInterface
    public interface AidaGetter<T extends PVStructure> {
        T get() throws RPCRequestException;
    }

    public static void testSuiteRunner(String[] args, String testSuiteHeading, Consumer<Integer>... tests) {
        var argString = Arrays.toString(args).replace("]", ",").replace("[", " ");
        AidaPvaTestUtils.NO_COLOR_FLAG = !argString.contains("-c") && !argString.contains("-color");
        var allTests = (AidaPvaTestUtils.NO_COLOR_FLAG ? args.length == 0 : args.length == 1);
        var testId = 0;
        var totalTests = tests.length;

        AidaPvaTestUtils.testSuiteHeader(testSuiteHeading);

        for (testId = 1; testId <= totalTests; testId++) {
            if (argString.contains(" " + ++testId + ",") || allTests) {
                AidaPvaTestUtils.testHeader(testId, "Acquire SHORT type");
                AidaPvaTestUtils.channel("TRIG:LI31:109:TACT", "TACT")
                        .with("BEAM", 1)
                        .returning(AidaType.SHORT)
                        .get();
            }
        }

        // Because of threads started in background to process requests
        System.exit(0);
    }

    /**
     * Print out a standard test suite header.
     *
     * @param heading the heading text to include in the header
     */
    public static void testSuiteHeader(String heading) {
        System.out.println("#################################################");
        System.out.println(color(ANSI_CYAN) + heading + color(ANSI_RESET));
        System.out.println("#################################################");
    }

    /**
     * Print a line that shows the operation that is being requested in a standard way
     *
     * @param query       the query
     * @param arguments   the arguments
     * @param returning   what the query is expected to return
     * @param withNewLine to add a newline after the header
     */
    public static void testCaseHeader(String query, String arguments, String returning, Boolean withNewLine) {
        // If there is a VALUE argument then this is a setter otherwise its a getter
        var isGetter = arguments == null || !arguments.toUpperCase().contains("VALUE=");

        System.out.print(isGetter ? "get:" : "set:");
        System.out.print(color(ANSI_YELLOW));
        System.out.print(" " + query);
        System.out.print(color(ANSI_RESET));
        if (arguments != null) {
            System.out.print(" (");
            System.out.print(color(ANSI_YELLOW));
            System.out.print(arguments);
            System.out.print(color(ANSI_RESET));
            System.out.print(")");
        }
        if (returning != null) {
            System.out.print(" => ");
            System.out.print(color(ANSI_YELLOW));
            System.out.print(returning);
            System.out.print(color(ANSI_RESET));
        }
        if (withNewLine) {
            System.out.println();
        }
    }

    /**
     * Print a standard heading above each test within a test suite
     *
     * @param number  test number
     * @param heading the heading text
     */
    public static void testHeader(Integer number, String heading) {
        System.out.println();
        System.out.println(color(ANSI_CYAN + BLOCK) + " Test " + number + ": " + color(ANSI_WHITE) + heading + color(ANSI_RESET));
        System.out.println(color(ANSI_CYAN) + "_________________________________________________\n" + color(ANSI_RESET));
    }

    /**
     * Builder for any channel request.  When the get() or set() are finally called the test runs and
     * the results are displayed in a standardised format
     *
     * @param query   the starting query for this channel request
     * @param message the message to be displayed alongside successful operation
     * @return An AidaPvaRequest that can be further configured before calling get() or set()
     */
//    public AidaPvaRequest channel(final String query, String message) {
    public static AidaPvaRequest channel(final String query, String message) {
        return new AidaPvaRequest(query, message);
    }

    /**
     * Call this to run a test and display results for channels with no arguments
     *
     * @param query   the channel
     * @param type    the type expected
     * @param message any message to display next to the returned data
     */
    public static void getWithNoArguments(final String query, AidaType type, String message) {
        var stringType = type.toString();
        if (type.equals(AidaType.TABLE)) {
            getTableWithNoArguments(query, message);
        } else if (stringType.endsWith("_ARRAY")) {
            getArrayWithNoArguments(query, type, message);
        } else {
            getScalarWithNoArguments(query, type, message);
        }
    }

    /**
     * Call this to run a setter test that does not return anything
     *
     * @param query the channel
     * @param value the value to set
     */
    public static void setWithNoArguments(final String query, Object value) {
        testCaseHeader(query, "VALUE=" + value, null, false);
        try {
            new AidaPvaRequest(query).setter(value);
            System.out.println(" " + TEST_SUCCESS);
        } catch (Exception e) {
            errors(e, false);
        }
    }

    /**
     * This will get a list of scalar value from the returned result structure.
     * In AIDA-PVA CHAR_ARRAY does not exist so requests are made using
     * BYTE_ARRAY and marshalled into CHAR_ARRAY on return
     *
     * @param result         the result to retrieve values from
     * @param clazz          the class to use to pull out the data.  Must extend PVField
     * @param isForCharArray is this for the pseudo-type CHAR_ARRAY.
     * @return the list of objects of the desired type
     */
    static <T extends PVField> List<Object> getScalarArrayValues(PVStructure result, Class<T> clazz, boolean isForCharArray) {
        var values = new ArrayList<>();
        var array = result.getSubField(clazz, AidaType.NT_FIELD_NAME);
        if (PVByteArray.class.equals(clazz)) {
            PVUtils.byteArrayIterator((PVByteArray) array, b -> values.add(isForCharArray ? "'" + (char) (b & 0xFF) + "'" : b));
        } else {
            PVUtils.arrayIterator(array, values::add);
        }
        return values;
    }

    /**
     * Display a scalar result.  This displays one line in a standard way for any scalar result.
     * It uses the supplied result-supplier to get the result so that if it gives an
     * error, the error can be displayed in a standard way too.
     *
     * @param supplier     the supplier of the results
     * @param message      any message to be displayed preceding the result
     * @param expectToFail if this test is expected to fail set to true
     */
    static void displayResult(AidaGetter<PVStructure> supplier, String message, boolean isForCharOrCharArray, boolean expectToFail) {
        try {
            var result = supplier.get();

            var type = AidaType.from(result);
            var clazz = type.toPVFieldClass();

            System.out.print("    " + message + ": ");
            if (type == AidaType.VOID || clazz == null) {
                System.out.println(" " + (expectToFail ? color(TEST_FAILURE_COLOR, TEST_FAILURE) : color(TEST_SUCCESS_COLOR, TEST_SUCCESS)));
                return;
            }

            switch (result.getStructure().getID()) {
                case AidaType.NTSCALAR_ID:
                    scalarResults(clazz, isForCharOrCharArray, result, expectToFail);
                    return;
                case AidaType.NTSCALARARRAY_ID:
                    scalarArrayResults(result, clazz, isForCharOrCharArray, expectToFail);
                    return;
                case AidaType.NTTABLE_ID:
                    tableResults(result, expectToFail);
            }
        } catch (Exception e) {
            errors(e, expectToFail);
        }
    }

    /**
     * Display scalar array result.  This displays one line for each array entry in a standard way
     * for any scalar array results.
     * It uses the supplied result-supplier to get the result so that if it gives an
     * error, the error can be displayed in a standard way too.
     *
     * @param supplier       the supplier of the results
     * @param clazz          the class of the scalar array data that has been returned in the structure
     * @param message        any message to be displayed on a line preceding the result
     * @param isForCharArray if this is for a pseudo-type CHAR_ARRAY
     */
    static <T extends PVField> void displayScalarArrayResults(
            AidaGetter<PVStructure> supplier, Class<T> clazz, String message, boolean isForCharArray) {
        try {
            var result = supplier.get();
            System.out.print("    " + message + ": ");
            assertNTScalarArray(result);
            scalarArrayResults(result, clazz, isForCharArray, false);
        } catch (Exception e) {
            errors(e, false);
        }
    }

    /**
     * Display a scalar result.  This displays one line in a standard way for any scalar result.
     * It uses the supplied result-supplier to get the result so that if it gives an
     * error, the error can be displayed in a standard way too.
     *
     * @param supplier  the supplier of the results
     * @param clazz     the class of the scalar data returned in the structure
     * @param message   any message to be displayed preceding the result
     * @param isForChar if this is for a pseudo-type CHAR
     */
    static <T extends PVField> void displayScalarResult(
            AidaGetter<PVStructure> supplier, Class<T> clazz, String message, boolean isForChar) {
        try {
            var result = supplier.get();
            System.out.print("    " + message + ": ");
            assertNTScalar(result);
            scalarResults(clazz, isForChar, result, false);
        } catch (Exception e) {
            errors(e, false);
        }
    }

    /**
     * Verify that the returned value is a scalar
     *
     * @param result the returned value
     * @throws RuntimeException if not
     */
    static void assertNTScalar(PVStructure result) {
        if (!result.getStructure().getID().equals(AidaType.NTSCALAR_ID)) {
            throw new RuntimeException("Expected " + AidaType.NTSCALAR_ID + ", but got " + result.getStructure().getID());
        }
    }

    /**
     * Call this to run a test and display results for scalar channels with no arguments
     *
     * @param query   the channel
     * @param type    the scalar type expected
     * @param message any message to display next to the returned data
     */
    private static void getScalarWithNoArguments(final String query, AidaType type, String message) {
        var clazz = type.toPVFieldClass();
        testCaseHeader(query, null, pseudoReturnType(type), true);
        displayScalarResult(
                () -> new AidaPvaRequest(query)
                        .returning(AidaType.valueOf(realReturnType(type)))
                        .getter(),
                clazz, message, type.equals(AidaType.CHAR));
    }

    /**
     * Call this to run a test and display results for scalar array channels with no arguments
     *
     * @param query   the channel
     * @param type    the scalar array type expected
     * @param message any message to display above the returned data
     */
    private static <T extends PVField> void getArrayWithNoArguments(final String query, AidaType type, String message) {
        Class<T> clazz = type.toPVFieldClass();
        testCaseHeader(query, null, pseudoReturnType(type), true);
        displayScalarArrayResults(
                () -> new AidaPvaRequest(query)
                        .returning(AidaType.valueOf(realReturnType(type)))
                        .getter(),
                clazz, message, type.equals(AidaType.CHAR_ARRAY));
    }

    /**
     * Call this to run a test and display results for scalar array channels with no arguments
     *
     * @param query   the channel
     * @param message any message to display above the returned data
     */
    private static void getTableWithNoArguments(final String query, String message) {
        testCaseHeader(query, null, "TABLE", true);
        displayTableResults(
                () -> new AidaPvaRequest(query)
                        .returning(AidaType.TABLE)
                        .getter(),
                message);
    }

    /**
     * This will get a scalar value from the returned result structure.
     * In AIDA-PVA CHAR does not exist so requests are made using BYTE and marshalled into char on return
     *
     * @param result    the result to retrieve value from
     * @param clazz     the class to use to pull out the data.  Must extend PVField
     * @param isForChar is this for the pseudo-type CHAR.
     * @return the object of the desired type
     */
    private static <T extends PVField> Object getScalarValue(PVStructure result, Class<T> clazz, boolean isForChar) {
        var value = result.getSubField(clazz, AidaType.NT_FIELD_NAME);
        return getDisplayValue(value, isForChar);
    }

    /**
     * Get a value for display - this simply changes bytes to single quoted characters
     *
     * @param value     the value to display
     * @param isForChar if this is for CHAR
     * @return the displayable value
     */
    static Object getDisplayValue(PVField value, boolean isForChar) {
        var extractedValue = PVUtils.extractScalarValue(value);
        if (extractedValue instanceof Byte && isForChar) {
            return "'" + (char) ((Byte) extractedValue & 0xFF) + "'";
        } else {
            return extractedValue;
        }
    }

    /**
     * Display a table result.  This displays tabular results in a standard way
     * It uses the supplied result-supplier to get the result so that if it gives an
     * error, the error can be displayed in a standard way too.
     *
     * @param supplier the supplier of the results
     * @param message  any message to be displayed preceding the result
     */
    private static void displayTableResults(AidaGetter<PVStructure> supplier, String message) {
        try {
            var result = supplier.get();
            System.out.print("    " + message + ": ");
            tableResults(result, false);
        } catch (Exception e) {
            errors(e, false);
        }
    }

    /**
     * To display formatted table results in a standard way
     *
     * @param result       the results to be displayed
     * @param expectToFail if this test is expected to fail set to true
     */
    private static void tableResults(PVStructure result, boolean expectToFail) {
        // Get the labels array and the table values
        var labels = result.getSubField(PVStringArray.class, AidaType.NT_LABELS_NAME);
        var values = result.getSubField(PVStructure.class, AidaType.NT_FIELD_NAME);

        // Determine the number of columns and
        // get the fields that are the column arrays from the table values structure
        var columns = labels.getLength();
        var columnValues = values.getPVFields();

        // Determine the number of rows by looking into the first column and checking its length
        var rows = ((PVArray) (columnValues[0])).getLength();

        // Not allocate enough space to store the string versions of the column labels,
        // column names, and column data
        var stringLabels = new String[columns];
        var stringNames = new String[columns];
        var stringData = new String[rows][columns];

        // An array to record the max widths for each column
        var columnWidths = new Integer[columns];
        // Initialise with 0
        for (var column = 0; column < columns; column++) {
            columnWidths[column] = 0;
        }

        // Now show the number of rows
        System.out.println(" " + rows + " rows retrieved: " + (expectToFail ? color(TEST_FAILURE_COLOR, TEST_FAILURE) : color(TEST_SUCCESS_COLOR, TEST_SUCCESS)));
        System.out.print(color(ANSI_CYAN));

        /*
         * First pass we will simply extract all the strings and populate the tables
         */
        tableFirstPass(columns, labels, columnValues, columnWidths, stringLabels, stringNames, stringData);

        /*
         * Second pass we will display all the collected strings
         */
        tableSecondPass(rows, columns, columnWidths, stringLabels, stringNames, stringData);
    }

    /**
     * First pass we will simply extract all the strings and populate the tables. We will
     * calculate the width of each column by finding the max string length for that column
     * and adding 1
     *
     * @param columns      the number of columns in the table
     * @param resultLabels the list of labels in the table
     * @param resultValues an array containing the values of all the columns - an array of PVArrays
     * @param columnWidths place we will store the calculated width of each column
     * @param columnLabels place we will store all the string column labels for the caller
     * @param columnNames  place we will store the string column names for the caller
     * @param tableData    place we will store the string data for each column's rows to be returned to caller
     */
    private static void tableFirstPass(
            int columns, PVStringArray resultLabels, PVField[] resultValues,
            Integer[] columnWidths, String[] columnLabels, String[] columnNames,
            String[][] tableData) {

        // Get labels
        PVUtils.stringArrayLoop(resultLabels, (s, i) -> {
            columnWidths[i] = Math.max(columnWidths[i], s.length());
            columnLabels[i] = s;
        });

        // field names
        for (var column = 0; column < columns; column++) {
            var s = resultValues[column].getFieldName();
            columnWidths[column] = Math.max(columnWidths[column], s.length());
            columnNames[column] = s;
        }

        // Get each value on the first pass and store them in the stringData two-dimensional array
        for (var column = 0; column < columns; column++) {
            final var array = ((PVArray) resultValues[column]);
            var c = column;
            PVUtils.arrayLoop(array, (s, r) -> {
                columnWidths[c] = Math.max(columnWidths[c], s.toString().length());
                tableData[r][c] = s.toString();
            });
        }

        // Increase all column widths by 1
        for (var column = 0; column < columns; column++) {
            columnWidths[column] += 1;
        }
    }

    /**
     * Second pass we will display all the collected strings.  We will receive a two dimensional
     * array of all the columns in the table and will
     * also receive an array with the widths of each column, an array with the labels
     * and one with the names
     *
     * @param rows         the number of rows in the table
     * @param columns      the number of columns in the table
     * @param columnWidths the list of column widths
     * @param columnLabels the list of labels
     * @param columnNames  the list of string names
     * @param tableData    the two-dimensional array of table data
     */
    private static void tableSecondPass(
            int rows, int columns,
            Integer[] columnWidths, String[] columnLabels, String[] columnNames,
            String[][] tableData) {

        // Labels
        var inverse = true;
        for (var column = 0; column < columns; column++) {
            // Alternate CYAN and WHITE
            inverse = inverse(color(ANSI_CYAN), inverse);
            System.out.print(padLeft(columnLabels[column], columnWidths[column]));
        }
        System.out.println(color(ANSI_NORMAL + ANSI_CYAN));

        // Field names
        inverse = true;
        System.out.print(color(ANSI_INVERSE));
        for (var column = 0; column < columns; column++) {
            // Alternate CYAN and WHITE
            inverse = inverse(color(ANSI_CYAN), inverse);
            System.out.print(padLeft(columnNames[column], columnWidths[column]));
        }
        System.out.println(color(ANSI_NORMAL + ANSI_CYAN));

        // Data
        for (var row = 0; row < rows; row++) {
            inverse = true;
            for (var column = 0; column < columns; column++) {
                inverse = alternate(color(ANSI_CYAN), color(ANSI_WHITE), inverse);
                System.out.print(padLeft(tableData[row][column], columnWidths[column]));
            }
            System.out.println();
        }
    }

    /**
     * Display scalar results in a standardised way
     *
     * @param clazz        the class of the scalar array
     * @param isForChar    is this for a char
     * @param result       the result
     * @param expectToFail if this test is expected to fail set to true
     */
    private static <T extends PVField> void scalarResults(Class<T> clazz, boolean isForChar, PVStructure result, boolean expectToFail) {
        System.out.print(color(ANSI_CYAN));
        System.out.print(getScalarValue(result, clazz, isForChar));
        System.out.println(" " + (expectToFail ? color(TEST_FAILURE_COLOR, TEST_FAILURE) : color(TEST_SUCCESS_COLOR, TEST_SUCCESS)));
    }

    /**
     * Display scalar array results in a standardised way
     *
     * @param result         the results
     * @param clazz          the class of the scalar array
     * @param isForCharArray is this for a char array
     * @param expectToFail   if this test is expected to fail set to true
     */
    private static <T extends PVField> void scalarArrayResults(PVStructure result, Class<T> clazz, boolean isForCharArray, boolean expectToFail) {
        var values = getScalarArrayValues(result, clazz, isForCharArray);
        System.out.println();
        for (var value : values) {
            System.out.print(color(ANSI_CYAN));
            System.out.println("        " + value + " " + (expectToFail ? color(TEST_FAILURE_COLOR, TEST_FAILURE) : color(TEST_SUCCESS_COLOR, TEST_SUCCESS)));
            System.out.print(color(ANSI_RESET));
        }
    }

    /**
     * Determine the real return type value for TYPE argument from the given AidaType
     *
     * @param type the given AidaType
     * @return the real return type value for TYPE argument
     */
    private static String realReturnType(AidaType type) {
        return type.equals(AidaType.CHAR_ARRAY) ? "BYTE_ARRAY" : type.equals(AidaType.CHAR) ? "BYTE" : type.toString();
    }

    /**
     * Determine the pseudo return type for channel to be used in display
     *
     * @param type the given AidaType
     * @return the pseudo return type
     */
    private static String pseudoReturnType(AidaType type) {
        return type.equals(AidaType.CHAR_ARRAY) ? "CHAR_ARRAY" : type.equals(AidaType.CHAR) ? "CHAR" : realReturnType(type);
    }

    /**
     * Omverse the foreground and background colours but alternate between the primary color and white at the same time
     *
     * @param primary the primary color
     * @param inverse are we to inverse colors on this pass
     * @return the last value of inverse flag (alternates each pass)
     */
    private static boolean inverse(String primary, boolean inverse) {
        return alternate(primary + color(ANSI_INVERSE), color(ANSI_WHITE + ANSI_INVERSE), inverse);
    }

    /**
     * To actually alternate the inverse flag
     *
     * @param primary   the primary color
     * @param secondary the secondary color
     * @param inverse   to invoice or not
     * @return the new value of inverse after alternating it
     */
    private static boolean alternate(String primary, String secondary, boolean inverse) {
        System.out.print((inverse ? primary : secondary));
        return !inverse;
    }

    /**
     * Utility to pad a string right
     *
     * @param s string to pad
     * @param n width
     * @return the padded string
     */
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    /**
     * Utility to pad a string left
     *
     * @param s string to pad
     * @param n width
     * @return the padded string
     */
    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    /**
     * To display errors in a standard way
     *
     * @param e            the exception that occurred
     * @param expectToFail if this test is expected to fail set to true
     */
    private static void errors(Exception e, boolean expectToFail) {
        if (expectToFail) {
            System.out.print(" " + color(ANSI_RESET));
        } else {
            System.out.print(" " + color(ANSI_RED));
        }
        System.err.print(abbreviate(e));
        if (expectToFail) {
            System.out.println(" " + color(TEST_SUCCESS_COLOR, TEST_SUCCESS));
        } else {
            System.out.println(" " + color(TEST_FAILURE_COLOR, TEST_FAILURE));
        }
    }

    /**
     * Verify that the returned value is a scalar array
     *
     * @param result the returned value
     * @throws RuntimeException if not
     */
    public static void assertNTScalarArray(PVStructure result) {
        if (!result.getStructure().getID().equals(AidaType.NTSCALARARRAY_ID)) {
            throw new RuntimeException("Expected " + AidaType.NTSCALARARRAY_ID + ", but got " + result.getStructure().getID());
        }
    }

    /**
     * Simple utility function to abbreviate long exception messages to fit the test output
     * Will stop at the first new line if there is one
     *
     * @param exception the exception to abbreviate
     * @return an abbreviated string
     */
    private static String abbreviate(Exception exception) {
        var exceptionMessage = exception.getMessage();
        var exceptionRaised = exception.getClass().toString();
        if (exceptionMessage == null) {
            return "TestUtil: can't determine error: " + exceptionRaised;
        }

        var length = exceptionMessage.length();
        var newLineLen = exceptionMessage.indexOf("\n");
        if (newLineLen != -1) {
            exceptionMessage = exceptionMessage.substring(0, newLineLen);
            length = newLineLen;
        }
        var cause = exceptionMessage.indexOf(", cause:");
        if (cause != -1) {
            exceptionMessage = exceptionMessage.substring(0, cause);
        }

        if (length > MAX_MESSAGE_LEN) {
            return exceptionMessage.substring(0, MAX_MESSAGE_LEN) + " ...";
        } else {
            return exceptionMessage;
        }
    }

    /**
     * Outputs the given color string if color is enabled otherwise it is suppressed
     *
     * @param colorString the given color string
     * @return the color string or nothing if color is not enabled
     */
    private static String color(String colorString) {
        return color(colorString, "");
    }

    /**
     * Outputs the given color string if color is enabled otherwise it is suppressed
     *
     * @param colorString the given color string
     * @return the color string or nothing if color is not enabled
     */
    private static String color(String colorString, String replacement) {
        return NO_COLOR_FLAG ? replacement : colorString;
    }
}

