package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int SIZE = 10;
    private T[] arrayList = (T[]) new Object[SIZE];
    private int iterator = 0;

    @Override
    public void add(T value) {
        if (iterator >= arrayList.length) {
            ensureCapacity();
        }
        arrayList[iterator] = value;
        iterator++;
    }

    @Override
    public void add(T value, int index) {
        if (iterator >= arrayList.length) {
            ensureCapacity();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, iterator - index);
        arrayList[index] = value;
        iterator++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < iterator) {
            return arrayList[index];
        }
        throw new ArrayIndexOutOfBoundsException("Can't get element");
    }

    @Override
    public void set(T value, int index) {
        if (index < iterator) {
            arrayList[index] = value;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Can't set element");
    }

    @Override
    public T remove(int index) {
        T removedItem = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, iterator - index);
        arrayList[iterator] = null;
        iterator--;
        return removedItem;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < iterator; i++) {
            if (t == arrayList[i] && t == null) {
                T removedItem = arrayList[i];
                System.arraycopy(arrayList, i + 1, arrayList, i, (iterator - 1) - i);
                arrayList[iterator - 1] = null;
                iterator--;
                return removedItem;
            }
            if (arrayList[i].equals(t)) {
                System.arraycopy(arrayList, i + 1, arrayList, i, (iterator - 1) - i);
                arrayList[iterator - 1] = null;
                iterator--;
                return t;
            }
        }
        throw new NoSuchElementException("Can't remove element");
    }

    @Override
    public int size() {
        return iterator;
    }

    @Override
    public boolean isEmpty() {
        return iterator == 0;
    }

    public void ensureCapacity() {
        T[] newArray = (T[]) new Object[(iterator * 3) / 2 + 1];
        System.arraycopy(arrayList, 0, newArray, 0, iterator);
        arrayList = newArray;
    }
}
