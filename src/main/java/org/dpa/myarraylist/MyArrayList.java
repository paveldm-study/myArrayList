package org.dpa.myarraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Компактная реализация коллекции ArrayList с использованием массива
 * Тип данных: <T>
 * Имплементирует интерфейс Iterable
 * Реализована сортировка слиянием
 * @author Павел Дмитриев
 */

public class MyArrayList<T extends Comparable<T>> implements Iterable<T> {

    private Object[] array;
    private final int DEFAULT_CAPACITY = 10;
    int size = 0;

//   Конструкторы:

    /**
     * Конструктор с ёмкостью по умолчанию
     */
    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор с заданной ёмкостью
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            System.out.println("illegal capacity"); // exept.
        } else {
            this.array = new Object[initialCapacity];
        }
    }

//  Публичные методы
    /**
     * Добавление элемента в конец списка
     */
    public void add(T element) {
        if (size == array.length) {
            enlargeCapacity();
        }
        array[size] = element;
        size++;
    }

    /**
     * Получение значения элемента по индексу
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    /**
     * Установка значения элемента по индексу
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
     }

    /**
     * Очистка массива
     */
    public void clear() {
        size = 0;
        array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Удаление элемента по индексу
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    /**
     * Переопределенный метод для корректного вывода элементов
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < size; i++) {
                sb.append(array[i]);
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /**
     * Получение размера массива
     */
    public int size() {
        return size;
    }

    /**
     * Сортировка слиянием
     */
    public void mergeSort() {
        mergeSorting(array, 0, array.length - 1);
    }

//  Служебные методы
    /**
     * Получение значения ёмкости
     */
    public int getDEFAULT_CAPACITY() {
        return DEFAULT_CAPACITY;
    }

    /**
     * Увеличение ёмкости
     */
    void enlargeCapacity() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Получение итератора
     */
    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

//  Методы для сортировки
    public static void mergeSorting(Object[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSorting(array, left, middle);
            mergeSorting(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    public static void merge(Object[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Object[] leftArray = new Object[n1];
        Object[] rightArray = new Object[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
//            if (((Comparable) leftArray[i]).compareTo(rightArray[j]) <= 0) {
            if ((compare(leftArray[i], rightArray[j]) <= 0)) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static int compare(Object o1, Object o2) {
        if (o1 instanceof Integer && o2 instanceof Integer) {
            Integer i1 = (Integer) o1;
            Integer i2 = (Integer) o2;
            return i1.compareTo(i2);
        } else {
            System.out.println("Метод предназначен для сортировки элементов типа Integer");
            return 0;
        }
    }

//    Итератор
    /**
     * Класс итератора для прохождения по элементам массива
     */
    private class MyArrayListIterator implements Iterator<T> {
        private int cursor;

        public MyArrayListIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[cursor++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}



