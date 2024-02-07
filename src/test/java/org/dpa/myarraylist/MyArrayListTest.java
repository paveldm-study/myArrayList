package org.dpa.myarraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование методов класса MyArrayList.
 */
public class MyArrayListTest {
    private MyArrayList<String> arrayList = new MyArrayList<>();


    @Test
    public void testAdd() {
        arrayList.add("one");

        assertEquals("one", arrayList.get(0));
    }

    @Test
    public void testGet() {
        arrayList.add("one");
        arrayList.add("two");
        assertEquals("two", arrayList.get(1));
    }

    @Test
    public void testGetForInvalidIndex() {
        arrayList.add("one");
        arrayList.add("two");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            arrayList.get(3);
        });
    }

    @Test
    void testSet() {
        arrayList.add("one");
        int index = 0;
        String element = "another";

        arrayList.set(index, element);

        // Assert
        assertEquals("another", arrayList.get(index));
    }

    @Test
    void testSetForInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(-1, "one"));
    }

    @Test
    public void testClear() {
        arrayList.add("one");
        arrayList.add("two");

        arrayList.clear();

        assertEquals(0, arrayList.size);
        assertEquals(10, arrayList.getDEFAULT_CAPACITY()); // если ассертов несколько то пихай их в assertAll
    }

    @Test
    public void testRemove() {
        arrayList.add("one");
        arrayList.add("two");

        arrayList.remove(1);

        assertEquals(1, arrayList.size());
        assertEquals("one", arrayList.get(0));
    }

    @Test
    public void testRemoveForInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(3));
    }

    @Test
    public void testToString() {
        arrayList.add("one");
        arrayList.add("two");
        arrayList.add("three");

        String expected = "[one, two, three]";
        String actual = arrayList.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testSize() {
        arrayList.add("one");

        assertEquals(1, arrayList.size());
    }

    @Test
    public void testMergeSort() {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(6);
        arrayList.add(60);
        arrayList.add(7);
        arrayList.add(-10);
        arrayList.add(1);
        arrayList.add(0);

        arrayList.mergeSort();

        assertEquals("[-10, 0, 1, 6, 7, 60]", arrayList.toString());
    }
}
