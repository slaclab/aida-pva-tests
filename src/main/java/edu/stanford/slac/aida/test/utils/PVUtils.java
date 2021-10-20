/**
 * @file
 * @brief Utilities to manipulate PVField.
 */
package edu.stanford.slac.aida.test.utils;

import org.epics.pvdata.pv.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PVUtils {

    /**
     * An iterator to iterate over PVArrays.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * e.g.
     * <pre>{@code
     *      PVBooleanArray booleanPvArray = pvStruct.getSubfield(PVBooleanArray.class, "value");
     *      arrayIterator(booleanPvArray, (bool) -> System.out.println("Value: " + bool));
     * }</pre>
     *
     * @param array    the array you provide to iterate over
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static <T extends PVField> void arrayIterator(T array, Consumer<Object> consumer) {
        arrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * Sometimes you want to process an array but have a index counter automatically maintained for
     * you so that you can know which element you're processing.  For that you'll use the
     * array loop.
     *
     * e.g.
     * <pre>{@code
     *      PVStringArray stringPvArray = pvStruct.getSubfield(PVStringArray.class, "value");
     *      arrayLoop(stringPvArray, (s, i) -> System.out.println("Value[" + i + "]=\"" + a "\""));
     * }</pre>
     *
     * @param array    the array you provide to iterate over
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
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
     * An iterator to iterate over PVBooleanArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv boolean array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void booleanArrayIterator(PVBooleanArray array, Consumer<Boolean> consumer) {
        booleanArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVBooleanArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv boolean array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void booleanArrayLoop(PVBooleanArray array, BiConsumer<Boolean, Integer> consumer) {
        var index = 0;
        var data = new BooleanArrayData();
        var offset = 0;
        var len = array.getLength();
        while (offset < len) {
            var num = array.get(offset, (len - offset), data);
            for (var i = 0; i < num; i++) {
                consumer.accept(data.data[offset + i], index++);
            }
            offset += num;
        }
    }

    /**
     * An iterator to iterate over PVStringArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv string array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void stringArrayIterator(PVStringArray array, Consumer<String> consumer) {
        stringArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVStringArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv string array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void stringArrayLoop(PVStringArray array, BiConsumer<String, Integer> consumer) {
        var index = 0;
        var data = new StringArrayData();
        var offset = 0;
        var len = array.getLength();
        while (offset < len) {
            var num = array.get(offset, (len - offset), data);
            for (var i = 0; i < num; i++) {
                consumer.accept(data.data[offset + i], index++);
            }
            offset += num;
        }
    }

    /**
     * An iterator to iterate over PVDoubleArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv double array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void doubleArrayIterator(PVDoubleArray array, Consumer<Double> consumer) {
        doubleArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVDoubleArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv double array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void doubleArrayLoop(PVDoubleArray array, BiConsumer<Double, Integer> consumer) {
        var index = 0;
        var it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextDouble(), index++);
        }
    }

    /**
     * An iterator to iterate over PVFloatArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv float array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void floatArrayIterator(PVFloatArray array, Consumer<Float> consumer) {
        floatArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVFloatArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv float array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void floatArrayLoop(PVFloatArray array, BiConsumer<Float, Integer> consumer) {
        var index = 0;
        var it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextFloat(), index++);
        }
    }

    /**
     * An iterator to iterate over PVLongArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv lon array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void longArrayIterator(PVLongArray array, Consumer<Long> consumer) {
        longArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVLongArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv lon array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void longArrayLoop(PVLongArray array, BiConsumer<Long, Integer> consumer) {
        var index = 0;
        var it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextLong(), index++);
        }
    }

    /**
     * An iterator to iterate over PVIntArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv integer array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void integerArrayIterator(PVIntArray array, Consumer<Integer> consumer) {
        integerArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVIntArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv integer array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void integerArrayLoop(PVIntArray array, BiConsumer<Integer, Integer> consumer) {
        var index = 0;
        var it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextInt(), index++);
        }
    }

    /**
     * An iterator to iterate over PVShortArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv shor array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void shortArrayIterator(PVShortArray array, Consumer<Short> consumer) {
        shortArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVShortArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv short array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void shortArrayLoop(PVShortArray array, BiConsumer<Short, Integer> consumer) {
        var index = 0;
        var it = array.get().iterator();
        while (it.hasNext()) {
            consumer.accept(it.nextShort(), index++);
        }
    }

    /**
     * An iterator to iterate over PVByteArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv byte array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code consumer(Object o); }</pre>
     */
    static void byteArrayIterator(PVByteArray array, Consumer<Byte> consumer) {
        byteArrayLoop(array, (s, i) -> consumer.accept(s));
    }

    /**
     * An iterator to iterate over PVByteArray.  You can provide a consumer of the items to carry out
     * any action you want on the array elements.
     *
     * @param array    the pv byte array
     * @param consumer the consumer function you provide to process the elements.
     *                 The consumer signature
     *                 <pre>@{code biConsumer(Object o, Integer i); }</pre>
     */
    static void byteArrayLoop(PVByteArray array, BiConsumer<Byte, Integer> consumer) {
        var index = 0;
        var it = array.get().iterator();
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
