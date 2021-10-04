package edu.stanford.slac.aida.utils;

import org.epics.pvdata.factory.FieldFactory;
import org.epics.pvdata.pv.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A private class used to build arguments.
 * This follows the builder pattern except that you need to call initialise after build,
 * so you need to keep the object around after build. And add methods don't return
 * the object.
 */
class ArgumentBuilder {
    /**
     * To be able to create fields in PV Access you need a field creator factory
     */
    private final static FieldCreate fieldCreate = FieldFactory.getFieldCreate();

    /**
     * Map of name setTo pairs for arguments and values
     */
    private final Map<String, Object> fieldMap = new HashMap<>();

    /**
     * Add a string argument
     *
     * @param name  name of argument
     * @param value string setTo of argument
     */
    protected void addStringArgument(String name, String value) {
        fieldMap.put(name, value);
    }

    /**
     * Builds the set of arguments based on the ones you've specified
     *
     * @return EPICS PVStructure containing the fields you've specified
     */
    public Structure build() {
        List<String> names = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        for (Map.Entry<String, Object> entrySet : fieldMap.entrySet()) {
            names.add(entrySet.getKey());
            Object value = entrySet.getValue();
            if (value instanceof Boolean) {
                fields.add(fieldCreate.createScalar(ScalarType.pvBoolean));
            } else if (value instanceof Byte) {
                fields.add(fieldCreate.createScalar(ScalarType.pvByte));
            } else if (value instanceof Short) {
                fields.add(fieldCreate.createScalar(ScalarType.pvShort));
            } else if (value instanceof Integer) {
                fields.add(fieldCreate.createScalar(ScalarType.pvInt));
            } else if (value instanceof Long) {
                fields.add(fieldCreate.createScalar(ScalarType.pvLong));
            } else if (value instanceof Float) {
                fields.add(fieldCreate.createScalar(ScalarType.pvFloat));
            } else if (value instanceof Double) {
                fields.add(fieldCreate.createScalar(ScalarType.pvDouble));
            } else if (value instanceof String) {
                fields.add(fieldCreate.createScalar(ScalarType.pvString));
            } else {
                throw new RuntimeException("Unknown type specified for argument value");
            }
        }

        // Create the query structure that will host the fields
        // note that you need to call initialise to fill in the actual values
        return FieldFactory.getFieldCreate().createStructure(
                names.toArray(new String[0]),
                fields.toArray(new Field[0])
        );
    }

    /**
     * Set the field values in the query
     *
     * @param query the query
     */
    public void initialize(PVStructure query) {
        for (Map.Entry<String, Object> entrySet : fieldMap.entrySet()) {
            String name = entrySet.getKey();
            Object value = entrySet.getValue();
            if (value instanceof Boolean) {
                query.getBooleanField(name).put((Boolean) value);
            } else if (value instanceof Byte) {
                query.getByteField(name).put((Byte) value);
            } else if (value instanceof Short) {
                query.getShortField(name).put((Short) value);
            } else if (value instanceof Integer) {
                query.getIntField(name).put((Integer) value);
            } else if (value instanceof Long) {
                query.getLongField(name).put((Long) value);
            } else if (value instanceof Float) {
                query.getFloatField(name).put((Float) value);
            } else if (value instanceof Double) {
                query.getDoubleField(name).put((Double) value);
            } else if (value instanceof String) {
                query.getStringField(name).put((String) value);
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
