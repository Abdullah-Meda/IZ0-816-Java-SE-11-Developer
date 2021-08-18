import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _5_TheListInterface {

    /* You use a list when you want an ordered collection that can contain duplicate entries */

    /* Factory methods to create a List:
                                                                          Can add     Can replace   Can delete
          Method                        Description                      elements?     element?     elements?
          ---------                     ----------------                ---------------------------------------
       -  Arrays. asList(varargs)       Returns fixed size list backed      No           Yes            No
                                        by an array
       -  List.of(varargs)              Returns immutable list              No            No            No
       -  List.copyOf(collection)       Returns immutable list with         No            No            No
                                        copy of original collection's
                                        values
     */

    /* List methods
              Method                     Description
        -------------------------------------------------------
       - boolean add(E element)          Adds element to end (available on all Collection APIs)
       - void add(int index, E element)  Adds element at index and moves the rest toward the end
       - E get(int index)                Returns element at index
       - E remove(int index)             Removes element at index and moves the rest toward the front
       - void replaceAll                 Replaces each element in the list with the result of the operator
         (UnaryOperator<E> op)
       - E set(int index, E e)           Replaces element at index and returns original. Throws
                                         IndexOutOfBoundsException if the index is larger than the maximum one set
     */


    public static void main(String[] args) {

        /* Creating a List using a factory method - REFER TO TABLE ABOVE
             - All of the following list are immutable! */
        String[] arr = {"A", "B", "C", "D", "E"};
        List<String> list1 = Arrays.asList(arr);
        List<String> list2 = List.of(arr);
        List<String> list3 = List.copyOf(list1);


        /* Using List Methods */
        List<String> list = new ArrayList<>();
        list.add("SD");                      // [SD]
        list.add(0, "NY");      // [NY,SD]
        list.set(1, "FL");                   // [NY,FL]
        System.out.println(list.get(0));     // NY
        list.remove("NY");                // [FL]
        list.remove(0);                // []
//        list.set(0, "?");                  // IndexOutOfBoundsException as the list is empty

        /* Using replaceAll() method */
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        intList.replaceAll(x -> x * 2);         // [2, 4, 6, 8, 10]

    }

}
