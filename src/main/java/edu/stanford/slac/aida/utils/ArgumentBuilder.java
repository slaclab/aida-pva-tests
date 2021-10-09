package edu.stanford.slac.aida.utils;

import org.epics.pvdata.factory.ConvertFactory;
import org.epics.pvdata.factory.FieldFactory;
import org.epics.pvdata.pv.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

/**
 * Used to build arguments for use with the AidaPvaRequest.
 * <p>
 * This follows the builder pattern except that you need to call initialise after build,
 * so you need to keep the object around after build. And add methods don't return
 * the object.
 * <p>
 * e.g.
 * <pre>{@code
 *      Structure argumentBuilder = new ArgumentBuilder()
 *          .argumentBuilder.addArgument("TYPE", "FLOAT");
 *
 *      // Prepare the arguments
 *      Structure arguments = argumentBuilder.build();
 *
 *      Structure uriStructure =
 *         fieldCreate.createStructure("epics:nt/NTURI:1.0",
 *                 new String[]{"path", "query"&#125;,
 *                 new Field[]{fieldCreate.createScalar(ScalarType.pvString), arguments&#125;
 *         );
 *
 *      // Make the query (contains the uri and arguments
 *       PVStructure request = PVDataFactory
 *          .getPVDataCreate()
 *              .createPVStructure(uriStructure);
 *
 *      // Set the request path
 *      request.getStringField("path").put("XCOR:LI03:120:LEFF");
 *
 *      // Set the request query values
 *      PVStructure query = request.getStructureField("query");
 *      argumentBuilder.initializeQuery(query);
 *
 *      ...
 * }</pre>
 */
class ArgumentBuilder {
    /**
     * To be able to create fields in PV Access you need a field creator factory
     */
    private final static FieldCreate fieldCreate = FieldFactory.getFieldCreate();

    /**
     * To be able to convert types
     */
    private final static Convert convert = ConvertFactory.getConvert();

    /**
     * Map of name setTo pairs for arguments and values
     */
    private final Map<String, Object> fieldMap = new HashMap<>();

    /**
     * Add an argument
     * See {@link ArgumentBuilder} for more information.
     *
     * @param name  name of argument
     * @param value value of argument
     */
    protected void addArgument(String name, Object value) {
        fieldMap.put(name, value);
    }

    /**
     * Builds the set of arguments based on the ones you've specified
     * See {@link ArgumentBuilder} for more information.
     *
     * @return EPICS PVStructure containing the fields you've specified
     */
    public Structure build() {
        return getStructure(fieldMap);
    }

    /**
     * Get a structure from the given value map
     *
     * @param valueMap the given value map
     * @return the corresponding structure
     */
    private Structure getStructure(Map<String, Object> valueMap) {
        List<String> names = new ArrayList<>();
        List<Field> fields = new ArrayList<>();

        // Create the list of names and fields to create the structure with
        for (Map.Entry<String, Object> entrySet : valueMap.entrySet()) {
            names.add(entrySet.getKey());
            Object value = entrySet.getValue();
            fields.add(getField(value));
        }

        // Create the query structure that will host the fields
        // note that you need to call initialise to fill in the actual values
        return FieldFactory.getFieldCreate().createStructure(
                names.toArray(new String[0]),
                fields.toArray(new Field[0])
        );
    }

    /**
     * From a given value determine the type of field that should be created to hold its value
     *
     * @param value the given value
     * @return the field to hold this value
     */
    private Field getField(Object value) {
        if (value instanceof Boolean) {
            return (fieldCreate.createScalar(ScalarType.pvBoolean));
        } else if (value instanceof Byte) {
            return (fieldCreate.createScalar(ScalarType.pvByte));
        } else if (value instanceof Short) {
            return (fieldCreate.createScalar(ScalarType.pvShort));
        } else if (value instanceof Integer) {
            return (fieldCreate.createScalar(ScalarType.pvInt));
        } else if (value instanceof Long) {
            return (fieldCreate.createScalar(ScalarType.pvLong));
        } else if (value instanceof Float) {
            return (fieldCreate.createScalar(ScalarType.pvFloat));
        } else if (value instanceof Double) {
            return (fieldCreate.createScalar(ScalarType.pvDouble));
        } else if (value instanceof String) {
            return (fieldCreate.createScalar(ScalarType.pvString));
        } else if (value instanceof List) {
            // determine type of list by getting first element.
            List<?> valueList = (List<?>) value;
            if (valueList.isEmpty()) {
                return fieldCreate.createScalarArray(ScalarType.pvString);
            }
            Object firstElement = valueList.get(0);
            if (firstElement instanceof Boolean) {
                return (fieldCreate.createScalarArray(ScalarType.pvBoolean));
            } else if (firstElement instanceof Byte) {
                return (fieldCreate.createScalarArray(ScalarType.pvByte));
            } else if (firstElement instanceof Short) {
                return (fieldCreate.createScalarArray(ScalarType.pvShort));
            } else if (firstElement instanceof Integer) {
                return (fieldCreate.createScalarArray(ScalarType.pvInt));
            } else if (firstElement instanceof Long) {
                return (fieldCreate.createScalarArray(ScalarType.pvLong));
            } else if (firstElement instanceof Float) {
                return (fieldCreate.createScalarArray(ScalarType.pvFloat));
            } else if (firstElement instanceof Double) {
                return (fieldCreate.createScalarArray(ScalarType.pvDouble));
            } else if (firstElement instanceof String) {
                return (fieldCreate.createScalarArray(ScalarType.pvString));
            }
            return (fieldCreate.createScalar(ScalarType.pvString));
        } else if (value instanceof Map) {
            // For structures then we recurse through this creating subfields for each key
            // with fields for the associated values
            @SuppressWarnings("unchecked") Map<String, Object> map = (Map<String, Object>) value;
            return getStructure(map);
        } else {
            throw new RuntimeException("Unknown type specified for argument value");
        }
    }

    /**
     * Set the field values in the arguments in the query.
     * See {@link ArgumentBuilder} for more information.
     *
     * @param query the query
     */
    public void initializeQuery(PVStructure query) {
        initializeStructure(query, fieldMap);
    }

    /**
     * Set the field values in the given structure.
     * Assumes that the structure has been created with field names that match the keys in the valueMap
     * Also assumes that the types of the values and structure fields match
     *
     * @param structure the given structure
     * @param valueMap  the values to set in the structure
     */
    @SuppressWarnings("unchecked")
    public void initializeStructure(PVStructure structure, Map<String, Object> valueMap) {
        for (Map.Entry<String, Object> entrySet : valueMap.entrySet()) {
            String name = entrySet.getKey();
            Object value = entrySet.getValue();
            PVField pvField = structure.getSubField(name);

            if (pvField instanceof PVBoolean) {
                structure.getBooleanField(name).put((Boolean) value);
            } else if (pvField instanceof PVByte) {
                convert.fromByte((PVScalar) pvField, (Byte) value);
            } else if (pvField instanceof PVShort) {
                convert.fromShort((PVScalar) pvField, (Short) value);
            } else if (pvField instanceof PVInt) {
                convert.fromInt((PVScalar) pvField, (Integer) value);
            } else if (pvField instanceof PVLong) {
                convert.fromLong((PVScalar) pvField, (Long) value);
            } else if (pvField instanceof PVFloat) {
                convert.fromFloat((PVScalar) pvField, (Float) value);
            } else if (pvField instanceof PVDouble) {
                convert.fromDouble((PVScalar) pvField, (Double) value);
            } else if (pvField instanceof PVString) {
                convert.fromString((PVScalar) pvField, (String) value);
            } else if (pvField instanceof PVBooleanArray) {
                List<Boolean> valueList = (List<Boolean>) value;
                byte[] list = new byte[valueList.size()];
                for (int i = 0; i < valueList.size(); i++) {
                    list[i] = valueList.get(i) ? (byte) 1 : (byte) 0;
                }
                convert.fromByteArray((PVScalarArray) pvField, 0, valueList.size(), list, 0);
            } else if (pvField instanceof PVByteArray) {
                List<Byte> valueList = (List<Byte>) value;
                Byte[] list = valueList.toArray(new Byte[0]);
                convert.fromByteArray((PVScalarArray) pvField, 0, valueList.size(), toPrimitive(list), 0);
            } else if (pvField instanceof PVShortArray) {
                List<Short> valueList = (List<Short>) value;
                Short[] list = valueList.toArray(new Short[0]);
                convert.fromShortArray((PVScalarArray) pvField, 0, valueList.size(), toPrimitive(list), 0);
            } else if (pvField instanceof PVIntArray) {
                List<Integer> valueList = (List<Integer>) value;
                Integer[] list = valueList.toArray(new Integer[0]);
                convert.fromIntArray((PVScalarArray) pvField, 0, valueList.size(), toPrimitive(list), 0);
            } else if (pvField instanceof PVLongArray) {
                List<Long> valueList = (List<Long>) value;
                Long[] list = valueList.toArray(new Long[0]);
                convert.fromLongArray((PVScalarArray) pvField, 0, valueList.size(), toPrimitive(list), 0);
            } else if (pvField instanceof PVFloatArray) {
                List<Float> valueList = (List<Float>) value;
                Float[] list = valueList.toArray(new Float[0]);
                convert.fromFloatArray((PVScalarArray) pvField, 0, valueList.size(), toPrimitive(list), 0);
            } else if (pvField instanceof PVDoubleArray) {
                List<Double> valueList = (List<Double>) value;
                Double[] list = valueList.toArray(new Double[0]);
                convert.fromDoubleArray((PVScalarArray) pvField, 0, valueList.size(), toPrimitive(list), 0);
            } else if (pvField instanceof PVStringArray) {
                // TODO check for empty string array because this is the default so it may be empty
                List<String> valueList = (List<String>) value;
                String[] list = valueList.toArray(new String[0]);
                convert.fromStringArray((PVScalarArray) pvField, 0, valueList.size(), list, 0);
            } else if (pvField instanceof PVStructure) {
                Map<String, Object> subValueMap = (Map<String, Object>) value;
                initializeStructure((PVStructure) pvField, subValueMap);
            } else {
                throw new RuntimeException("Unknown type specified for argument value");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean firstTime = true;
        for (Map.Entry<String, Object> entrySet : fieldMap.entrySet()) {
            String name = entrySet.getKey();
            Object value = entrySet.getValue();
            if (!firstTime) {
                stringBuilder.append(", ");
            }
            firstTime = false;
            stringBuilder.append(name).append("=").append(value);
        }
        return stringBuilder.toString();
    }
}
