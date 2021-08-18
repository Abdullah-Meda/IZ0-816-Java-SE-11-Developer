import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class _8_TheMapInterface {

    /* You use a map when you want to identify values by a key */

    /* SCROLL to end for a table on all common methods of the Map Interface */

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "Java");
        map.put(2, "SE 11");
        map.put(3, "Developer");
        map.put(4, "Certification");
        map.put(5, "Oracle");

        for (String value : map.values())
            System.out.print(value + " ");              // Java SE 11 Developer Certification Oracle

        System.out.println(map.containsKey(2));         // true
        System.out.println(map.containsValue("Sybex")); // false
        System.out.println(map.size());                 // 5
        System.out.println(map.isEmpty());              // false

        /* forEach() and entrySet() */
        map.forEach((k, v) -> System.out.println("[ " + k + ", " + v + " ]"));
        map.values().forEach(System.out::println);
        map.entrySet().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

        /* getOrDefault() */
        System.out.println(map.get(1));                                         // Java
        System.out.println(map.getOrDefault(7, "DefaultValue")); // DefaultValue

        /* replace() and replaceAll() */
        String original = map.replace(5, "By Oracle");       // Oracle
        map.replaceAll((k, v) -> v + " ");                   // Appends a space to all values

        /* putIfAbsent() */
        map.put(6, null);
        map.putIfAbsent(6, "Sybex Study Guide");
        map.putIfAbsent(5, "Not Updated");                   // Redundant as value already present

        /* merge()
             - The merge() method adds logic of what to choose
             -  Suppose we want to choose the ride with the longest name: */
        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1 : v2;

        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Tram");

        String jenny = favorites.merge("Jenny", "Skyride", mapper);
        String tom = favorites.merge("Tom", "Skyride", mapper);

        System.out.println(favorites);              // {Tom=Skyride, Jenny=Bus Tour}
        System.out.println(jenny);                  // Bus Tour (value is unchanged as initial value is longer)
        System.out.println(tom);                    // Skyride  (Skyride is just longer than Tram)

        /* The merge() method also has logic for what happens if null values or missing keys are involved. In
           this case, it doesn't call the BiFunction at all, and it simply uses the new value. */
        favorites.put("Sam", null);
        favorites.merge("Sam", "Skyride", mapper);
        System.out.println(favorites);              // {Tom=Skyride, Jenny=Bus Tour, Sam=Skyride}

        /* The final thing to know about merge() is what happens when the mapping function is called and returns
           null. The key is removed from the map when this happens */

    }

    /* Map methods:
        Method                              Description
        ----------                          -------------------
      - void clear()                        Removes all keys and values from the map.
      - boolean containsKey(Object key)     Returns whether key is in map.
      - boolean containsValue(Object value) Returns whether value is in map.
      - Set<Map.Entry<K,V>> entrySet()      Returns a Set of key/value pairs.
      - void forEach                        Loop through each key/value pair.
        (BiConsumer(K key, V value))
      - V get(Object key)                   Returns the value mapped by key or null if none is mapped.
      - V getOrDefault                      Returns the value mapped by the key or the default value if none is mapped.
        (Object key, V defaultValue)
      - boolean isEmpty()                   Returns whether the map is empty.
      - Set<K> keySet()                     Returns set of all keys.
      - V merge(K key, V value,             Sets value if key not set. Runs the function if the key is set to determine the new value. Removes if null.
      - V put(K key, V value)               Adds or replaces key/value pair. Returns previous value or null.
      - V putIfAbsent(K key, V value)       Adds value if key not present and returns null. Otherwise, returns existing value.
      - V remove(Object key)                Removes and returns value mapped to key. Returns null if none.
      - V replace(K key, V value)           Replaces the value for a given key if the key is set. Returns the original value or null if none.
      - void replaceAll                     Replaces each value with the results of the function.
        (BiFunction<K, V, V> func)
      - int size()                          Returns the number of entries (key/value pairs) in the map.
      - Collection<V> values()              Returns Collection of all values.
*/

}
