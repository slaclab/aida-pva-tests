/**
 * @file
 * @brief Enumerated type representing an AIDA-PVA data type.  This enum
 * contains static methods to help manipulate and work with AidaType.
 */
package edu.stanford.slac.aida.test.utils;

import org.epics.pvdata.pv.*;

/**
 * Enumerated type representing an AIDA-PVA data type.  This enum
 * contains static methods to help manipulate and work with AidaType.
 */
public enum AidaType {
    AIDA_VOID,           ///< Means that this setter does not return a value (only valid for setters)

    AIDA_BOOLEAN,        ///< Getter returns a boolean
    AIDA_BYTE,           ///< Getter returns a byte
    AIDA_CHAR,           ///< There is no CHAR type in AIDA-PVA you must use BYTE, but if you want to obtain a char you can specify CHAR to our test suite, and it will request a BYTE and automatically translate the resulting byte response to char.
    AIDA_SHORT,          ///< Getter returns a short
    AIDA_INTEGER,        ///< Getter returns an integer
    AIDA_LONG,           ///< Getter returns a long
    AIDA_FLOAT,          ///< Getter returns a float
    AIDA_DOUBLE,         ///< Getter returns a double
    AIDA_STRING,         ///< Getter returns a string

    AIDA_BOOLEAN_ARRAY,  ///< Getter returns a boolean array
    AIDA_BYTE_ARRAY,     ///< Getter returns a byte array
    /**
     * There is no #CHAR_ARRAY type in AIDA-PVA's edu.stanford.slac.aida.lib.model.AidaType you must use
     * edu.stanford.slac.aida.lib.model.AidaType.BYTE_ARRAY, but if you want to obtain a char[]
     * you can specify #CHAR_ARRAY to our test suite, and it will request
     * a edu.stanford.slac.aida.lib.model.AidaType.BYTE_ARRAY and automatically translate the resulting byte[] response to char[].
     */
    AIDA_CHAR_ARRAY,
    AIDA_SHORT_ARRAY,    ///< Getter returns a short array
    AIDA_INTEGER_ARRAY,  ///< Getter returns an integer array
    AIDA_LONG_ARRAY,     ///< Getter returns a long array
    AIDA_FLOAT_ARRAY,    ///< Getter returns a float array
    AIDA_DOUBLE_ARRAY,   ///< Getter returns a double array
    AIDA_STRING_ARRAY,   ///< Getter returns a string array
    AIDA_TABLE;          ///< Getter or setter returns a table

    private final static String PREFIX = "AIDA_";
    private final static int PREFIX_LENGTH = PREFIX.length();

    /**
     * Normative Type ID string for URIs
     */
    public static final String NTURI_ID = "epics:nt/NTURI:1.0";
    /**
     * Normative Type ID string for Scalars
     */
    public static final String NTSCALAR_ID = "epics:nt/NTScalar:1.0";
    /**
     *  Normative Type ID string for Scalar Arrays
     */
    public static final String NTSCALARARRAY_ID = "epics:nt/NTScalarArray:1.0";
    /**
     *  Normative Type ID string for Tables
     */
    public static final String NTTABLE_ID = "epics:nt/NTTable:1.0";

    // AIDA-PVA standard field names in normative types
    /**
     * Normative Type standard field name for value field
     */
    public static final String NT_FIELD_NAME = "value";
    /**
     * Normative Type standard field name for labels field
     */
    public static final String NT_LABELS_NAME = "labels";
    /**
     * Normative Type standard field name for description field
     */
    public static final String NT_DESCRIPTIONS_NAME = "descriptions";

    /**
     * Return the string representation of the type without the prefix
     *
     * @return the string representation of the type without the prefix
     */
    public final String string() {
        return toString().substring(PREFIX_LENGTH);
    }

    /**
     * Convert this AidaType to a PVField.
     * This method maps {@link AidaType}s to PVFields.
     *
     * @return the PVField that maps to this AidaType.
     */
    @SuppressWarnings("unchecked")
    public <T extends PVField> Class<T> toPVFieldClass() {
        switch (this) {
            case AIDA_BOOLEAN:
                return (Class<T>) PVBoolean.class;
            case AIDA_BYTE:
            case AIDA_CHAR:
                return (Class<T>) PVByte.class;
            case AIDA_SHORT:
                return (Class<T>) PVShort.class;
            case AIDA_INTEGER:
                return (Class<T>) PVInt.class;
            case AIDA_LONG:
                return (Class<T>) PVLong.class;
            case AIDA_FLOAT:
                return (Class<T>) PVFloat.class;
            case AIDA_DOUBLE:
                return (Class<T>) PVDouble.class;
            case AIDA_STRING:
                return (Class<T>) PVString.class;
            case AIDA_BOOLEAN_ARRAY:
                return (Class<T>) PVBooleanArray.class;
            case AIDA_BYTE_ARRAY:
            case AIDA_CHAR_ARRAY:
                return (Class<T>) PVByteArray.class;
            case AIDA_SHORT_ARRAY:
                return (Class<T>) PVShortArray.class;
            case AIDA_INTEGER_ARRAY:
                return (Class<T>) PVIntArray.class;
            case AIDA_LONG_ARRAY:
                return (Class<T>) PVLongArray.class;
            case AIDA_FLOAT_ARRAY:
                return (Class<T>) PVFloatArray.class;
            case AIDA_DOUBLE_ARRAY:
                return (Class<T>) PVDoubleArray.class;
            case AIDA_STRING_ARRAY:
                return (Class<T>) PVStringArray.class;
            case AIDA_TABLE:
                return (Class<T>) PVStructure.class;
        }
        return null;
    }

    /**
     * Get the AidaType of the given @p pvStructure.
     *
     * Look in the field that stores all Normative Type's
     * @param pvStructure the pvStructure to determine the AidaType of
     * @return the AidaType corresponding to the given @p pvStructure
     */
    public static AidaType from(PVStructure pvStructure) {
        if (pvStructure == null) {
            return AIDA_VOID;
        }

        return from(pvStructure, NT_FIELD_NAME);
    }

    /**
     * Get the AidaType of the @p fieldName in the given @p pvStructure.
     *
     * @param pvStructure the pvStructure to pull the @p fieldName out of
     * @param fieldName the name of the field within the pvStructure to determine the AidaType of
     * @return the AidaType corresponding to the p fieldName in the given @p pvStructure
     */
    public static AidaType from(PVStructure pvStructure, String fieldName) {
        if (pvStructure.getStructure().getID().equals(NTTABLE_ID)) {
            return AIDA_TABLE;
        }

        var field = pvStructure.getSubField(fieldName);
        if (field instanceof PVBoolean) {
            return AIDA_BOOLEAN;
        } else if (field instanceof PVByte) {
            return AIDA_BYTE;
        } else if (field instanceof PVShort) {
            return AIDA_SHORT;
        } else if (field instanceof PVInt) {
            return AIDA_INTEGER;
        } else if (field instanceof PVLong) {
            return AIDA_LONG;
        } else if (field instanceof PVFloat) {
            return AIDA_FLOAT;
        } else if (field instanceof PVDouble) {
            return AIDA_DOUBLE;
        } else if (field instanceof PVString) {
            return AIDA_STRING;
        } else if (field instanceof PVBooleanArray) {
            return AIDA_BOOLEAN_ARRAY;
        } else if (field instanceof PVByteArray) {
            return AIDA_BYTE_ARRAY;
        } else if (field instanceof PVShortArray) {
            return AIDA_SHORT_ARRAY;
        } else if (field instanceof PVIntArray) {
            return AIDA_INTEGER_ARRAY;
        } else if (field instanceof PVLongArray) {
            return AIDA_LONG_ARRAY;
        } else if (field instanceof PVFloatArray) {
            return AIDA_FLOAT_ARRAY;
        } else if (field instanceof PVDoubleArray) {
            return AIDA_DOUBLE_ARRAY;
        } else if (field instanceof PVStringArray) {
            return AIDA_STRING_ARRAY;
        } else {
            return AIDA_VOID;
        }
    }
}
