import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _3_UsingTheDiamondOperator {

    /* The diamond operator is a shorthand notation that allows you to omit the generic type from the right side
       of a statement when the type can be inferred. It is called the diamond operator because <> looks like a
       diamond */

    public static void main(String[] args) {

        /* WITHOUT using the diamond operator - THE LENGTHIER WAY */
        List<Integer> list1 = new ArrayList<Integer>();
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        Map<Long,List<Integer>> mapLists1 = new HashMap<Long,List<Integer>>();

        /* More concise versions USING the diamond operator */
        List<Integer> list2 = new ArrayList<>();
        Map<String,Integer> map2 = new HashMap<>();
        Map<Long,List<Integer>> mapOfLists2 = new HashMap<>();

        /* Using var in variable declarations */
        var list3 = new ArrayList<Integer>();
        var list4 = new ArrayList<>();

    }

}
