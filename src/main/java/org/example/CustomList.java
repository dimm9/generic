package org.example;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {
    public Node start;
    public Node end;

    public CustomList() {
        this.start = null;
        this.end = null;
    }

    public void addLast(T value) {
        Node newElement = new Node();
        newElement.value = value;
        newElement.next = null;
        if (start == null) {
            start = end = newElement;
        } else {
            end.next = newElement;
            end = newElement;
        }
    }

    public T getLast() {
        if (end == null) {
            throw new NoSuchElementException();
        }
        return end.value;
    }

    public void addFirst(T value) {
        Node newElement = new Node();
        newElement.value = value;

        if (start == null) {
            start = end = newElement;
        } else {
            newElement.next = start;
            start = newElement;
        }

    }

    public T getFirst() {
        if (start == null) {
            throw new NoSuchElementException();
        }
        return start.value;
    }

    public T removeFirst() {
        if (start == null) {
            throw new NoSuchElementException();
        }
        T startValue = start.value;
        if (start == end) {
            start = null;
            end = null;
        } else {
            start = start.next;
        }
        return startValue;
    }

    public T removeLast() {
        if (start == null) {
            throw new NoSuchElementException();
        }
        T endValue = end.value;
        if (start == end) {
            start = null;
            end = null;
        } else {
            Node temp = start;
            while (temp.next != end) {
                temp = temp.next;
            }
            temp.next = null;
            end = temp;
        }
        return endValue;
    }

    private class Node {
        T value;
        Node next;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public int size() {
        int counter = 1; //with start node
        Node temp = start;
        while (temp != null) {
            temp = temp.next;
            counter++;
        }
        return counter;
    }

    @Override
    public T get(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }
        int counter = 0;
        Node temp = start;
        while (counter < i) {
            temp = temp.next;
            counter++;
        }
        if (temp == null) {
            throw new IndexOutOfBoundsException("Index is bigger than list size");
        }
        return temp.next.value;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node temp = start;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T next() {
                T value = temp.value;
                temp = temp.next;
                return value;
            }
        };
    }

    //zwraca strumień z zawartością listy
    public Stream<T> stream() {
        Stream.Builder<T> stream = Stream.builder();
        for (T item : this) {
            stream.accept(item);
        }
        return stream.build();
    }

    // Metoda zwraca listę obiektów, które należą do wskazanej klasy.
    public static <T> List<T> sameClassElements(List<T> list, Class<?> clazz) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (clazz.equals(element.getClass())) {
                result.add(element);
            }
        }
        return result;
    }

    // Metoda szablonowa, która filtruje elementy listy na podstawie klasy bazowej
    public static <T> List<T> sameClassInherited(List<T> list, Class<?> clazz) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (clazz.isInstance(element)) {
                result.add(element);
            }
        }
        return result;
    }

    // Metoda tworząca predykat sprawdzający, czy wartość znajduje się w otwartym przedziale
    public static <T extends Comparable<T>> Predicate<T> isInRange(T lowerBound, T upperBound) {
        return value -> value.compareTo(lowerBound) > 0 && value.compareTo(upperBound) < 0;
    }

    //Metoda zliczająca elementy listy spełniające warunek predykatu
    public static <T extends Comparable<T>> long countElementsInRange(List<T> list, T lowerBound, T upperBound) {
        Predicate<T> predicate = isInRange(lowerBound, upperBound);
        return list.stream().filter(predicate).count();
    }

    //Predykaty są często używane do testowania, filtrowania i porównywania wartości
    //predykat to funkcja/wyrażenie, które przyjmuje jeden lub więcej argumentów
    // i zwraca wartość typu boolean
    public static void predicateUsageExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> isGreaterThanFive = number -> number > 5;

        // Filtrowanie listy z użyciem predykatu
        List<Integer> filteredNumbers = numbers.stream()
                .filter(isGreaterThanFive)
                .collect(Collectors.toList());
    }

    public class CollectionSize implements Comparator<CustomList<T>> {
        @Override
        public int compare(CustomList<T> ts, CustomList<T> t1) {
            return Integer.compare(ts.size(), t1.size());
        }
    }

    public class CollectionSum implements Comparator<CustomList<Integer>> {

        @Override
        public int compare(CustomList<Integer> integers, CustomList<Integer> t1) {
            return Integer.compare(integers.stream().mapToInt(Number::intValue).sum(), t1.stream().mapToInt(Number::intValue).sum());
        }
    }
}





