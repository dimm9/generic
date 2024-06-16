package org.example;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        CustomList<Integer> myList = new CustomList<>();
        myList.add(15);
        myList.addLast(3);
        myList.addFirst(2);
        // Accessing elements
        System.out.println("First element: " + myList.getFirst()); // Should print: 2
        System.out.println("Last element: " + myList.getLast());   // Should print: 3

        // Removing elements
        System.out.println("Removed first element: " + myList.removeFirst()); // Should print: 2
        System.out.println("Removed last element: " + myList.removeLast());   // Should print: 3

        // Checking size
        System.out.println("Size of the list: " + myList.size()); // Should print: 1

        // Using iterator
        System.out.print("Elements in the list: ");
        Iterator<Integer> iterator = myList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next() + " ");
        }
        CustomList<Integer> anotherList = new CustomList<>();
        anotherList.add(1);
        anotherList.add(2);
        CustomList<Integer>.CollectionSize sizeComparator = myList.new CollectionSize();
        System.out.println("Comparison by size: " + sizeComparator.compare(myList, anotherList)); // Example comparison

        // Example usage of CollectionSum comparator
        CustomList<Integer> list1 = new CustomList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        CustomList<Integer> list2 = new CustomList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        CustomList<Integer>.CollectionSum sumComparator = myList.new CollectionSum();
        System.out.println("Comparison by sum: " + sumComparator.compare(list1, list2)); // Example comparison
    }
}