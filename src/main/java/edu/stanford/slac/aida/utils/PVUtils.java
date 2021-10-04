package edu.stanford.slac.aida.utils;

import org.epics.pvdata.pv.*;
import org.epics.util.array.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PVUtils {

    static <T extends PVField> void arrayIterator(T array, Consumer<Object> consumer) {
        arrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static <T extends PVField> void arrayLoop(T array, BiConsumer<Object, Integer> consumer) {
        if (array instanceof PVBooleanArray) {
            booleanArrayLoop((PVBooleanArray) array, consumer::accept);
        } else if (array instanceof PVShortArray) {
            shortArrayLoop((PVShortArray) array, consumer::accept);
        } else if (array instanceof PVIntArray) {
            integerArrayLoop((PVIntArray) array, consumer::accept);
        } else if (array instanceof PVLongArray) {
            longArrayLoop((PVLongArray) array, consumer::accept);
        } else if (array instanceof PVFloatArray) {
            floatArrayLoop((PVFloatArray) array, consumer::accept);
        } else if (array instanceof PVDoubleArray) {
            doubleArrayLoop((PVDoubleArray) array, consumer::accept);
        } else if (array instanceof PVStringArray) {
            stringArrayLoop((PVStringArray) array, consumer::accept);
        } else if (array instanceof PVByteArray) {
            byteArrayLoop((PVByteArray) array, consumer::accept);
        }
    }

    /**
     * Iterator for processing  PVBooleanArrays
     *
     * @param array    the pv boolean array
     * @param consumer the consumer
     */
    static void booleanArrayIterator(PVBooleanArray array, Consumer<Boolean> consumer) {
        booleanArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static void booleanArrayLoop(PVBooleanArray array, BiConsumer<Boolean, Integer> consumer) {
        int index = 0;
        BooleanArrayData data = new BooleanArrayData();
        int offset = 0, len = array.getLength();
        while (offset < len) {
            int num = array.get(offset, (len - offset), data);
            for (int i = 0; i < num; i++) {
                consumer.accept(data.data[offset + i], index++);
            }
            offset += num;
        }
    }

    /**
     * Iterator for processing  PVStringArrays
     *
     * @param array    the pv string array
     * @param consumer the consumer
     */
    static void stringArrayIterator(PVStringArray array, Consumer<String> consumer) {
        stringArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * Loop for processing PVStringArrays provides an index as well as the string
     *
     * @param array    the pv string array
     * @param consumer the consumer
     */
    static void stringArrayLoop(PVStringArray array, BiConsumer<String, Integer> consumer) {
        int index = 0;
        StringArrayData data = new StringArrayData();
        int offset = 0, len = array.getLength();
        while (offset < len) {
            int num = array.get(offset, (len - offset), data);
            for (int i = 0; i < num; i++) {
                consumer.accept(data.data[offset + i], index++);
            }
            offset += num;
        }
    }

    /**
     * Iterator for processing  PVDoubleArrays
     *
     * @param array    the pv double array
     * @param consumer the consumer
     */
    static void doubleArrayIterator(PVDoubleArray array, Consumer<Double> consumer) {
        doubleArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static void doubleArrayLoop(PVDoubleArray array, BiConsumer<Double, Integer> consumer) {
        int index = 0;
        IteratorDouble it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextDouble(), index++);
        }
    }

    /**
     * Iterator for processing  PVFloatArrays
     *
     * @param array    the pv float array
     * @param consumer the consumer
     */
    static void floatArrayIterator(PVFloatArray array, Consumer<Float> consumer) {
        floatArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static void floatArrayLoop(PVFloatArray array, BiConsumer<Float, Integer> consumer) {
        int index = 0;
        IteratorFloat it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextFloat(), index++);
        }
    }

    /**
     * Iterator for processing  PVLongArrays
     *
     * @param array    the pv lon array
     * @param consumer the consumer
     */
    static void longArrayIterator(PVLongArray array, Consumer<Long> consumer) {
        longArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static void longArrayLoop(PVLongArray array, BiConsumer<Long, Integer> consumer) {
        int index = 0;
        IteratorLong it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextLong(), index++);
        }
    }

    /**
     * Iterator for processing  PVIntArrays
     *
     * @param array    the pv integer array
     * @param consumer the consumer
     */
    static void integerArrayIterator(PVIntArray array, Consumer<Integer> consumer) {
        integerArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static void integerArrayLoop(PVIntArray array, BiConsumer<Integer, Integer> consumer) {
        int index = 0;
        IteratorInteger it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextInt(), index++);
        }
    }

    /**
     * Iterator for processing  PVShortArrays
     *
     * @param array    the pv shor array
     * @param consumer the consumer
     */
    static void shortArrayIterator(PVShortArray array, Consumer<Short> consumer) {
        shortArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static void shortArrayLoop(PVShortArray array, BiConsumer<Short, Integer> consumer) {
        int index = 0;
        IteratorShort it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextShort(), index++);
        }
    }

    /**
     * Iterator for processing  PVByteArrays
     *
     * @param array    the pv byte array
     * @param consumer the consumer
     */
    static void byteArrayIterator(PVByteArray array, Consumer<Byte> consumer) {
        byteArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    static void byteArrayLoop(PVByteArray array, BiConsumer<Byte, Integer> consumer) {
        int index = 0;
        IteratorByte it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextByte(), index++);
        }
    }

    /**
     * Get a value from the given PVField by using the PVField class provided as the determinant
     *
     * @param value the value to extract
     * @return the extracted value
     */
    static Object extractScalarValue(PVField value) {
        if (value instanceof PVBoolean) {
            return ((PVBoolean) value).get();
        } else if (value instanceof PVShort) {
            return ((PVShort) value).get();
        } else if (value instanceof PVInt) {
            return ((PVInt) value).get();
        } else if (value instanceof PVLong) {
            return ((PVLong) value).get();
        } else if (value instanceof PVFloat) {
            return ((PVFloat) value).get();
        } else if (value instanceof PVDouble) {
            return ((PVDouble) value).get();
        } else if (value instanceof PVString) {
            return ((PVString) value).get();
        } else if (value instanceof PVByte) {
            return ((PVByte) value).get();
        }
        return null;
    }
}
