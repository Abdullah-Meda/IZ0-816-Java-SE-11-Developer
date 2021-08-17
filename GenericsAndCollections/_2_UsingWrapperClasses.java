import java.util.ArrayList;
import java.util.List;

public class _2_UsingWrapperClasses {

    /* As you read in Chapter 5, each Java primitive has a corresponding wrapper class, shown in following table.
       With autoboxing, the compiler automatically converts a primitive to the corresponding wrapper. Unsurprisingly,
       unboxing is the process in which the compiler automatically converts a wrapper class back to a primitive.

       Wrapper classes:
          Primitive type          Wrapper class           Example of initializing
          --------------          -------------           -----------------------
          boolean                 Boolean                 Boolean.valueOf(true)
          byte                    Byte                    Byte.valueOf((byte) 1)
          short                   Short                   Short.valueOf((short) 1)
          int                     Integer                 Integer.valueOf(1)
          long                    Long                    Long.valueOf(1)
          float                   Float                   Float.valueOf((float)1.0)
          double                  Double                  Double.valueOf(1.0)
          char                    Character               Character.valueOf('c')
     */

    public static void main(String[] args) {

        /* Example */
        Integer wrapper = 120;      // auto-boxing
        int primitive = wrapper;    // unboxing

        /* There are two tricks in the space of autoboxing and unboxing. The first has to do with null values.
           This innocuous‐looking code throws an exception: */
        var heights = new ArrayList<Integer>();
        heights.add(null);
        int h = heights.get(0);     // NullPointerException

        /* The second comes when working with lists */
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(Integer.valueOf(3));
        numbers.add(Integer.valueOf(5));
        numbers.remove(1);              // parameter is an index of list to be removed
        numbers.remove(Integer.valueOf(5));  // parameter is value of list element to be removed
        System.out.println(numbers);         // [1]

    }

}
