public class _1_DesigningSecureObjects {

    /* Method 1: Limiting Accessibility
         - One good practice is to limit accessibility by making instance variables private or package‐private,
           whenever possible.
         - If your application is using modules, you can do even better by only exporting the security packages
           to the specific modules that should have access */


    /* Method 2: Restricting Extensibility
         - Mark classes as final if you do not want them to be instantiated by others */


    /* Method 3: Creating immutable objects
         - A common strategy for making a class immutable:
              1. Mark the class as final.
              2. Mark all the instance variables private.
              3. Don't define any setter methods and make fields final.
              4. Don't allow referenced mutable objects to be modified.
                    - This means you shouldn't expose any getter methods as the hacker may "get" the object and
                      call methods on it  which may destroy original data
                    - A safe alternative would be to return a copy of the object
              5. Use a constructor to set all properties of the object, making a copy if needed.
                    - Validate that objects provided as constructor arguments are not null!
                    - Make a copy of the object provided as an argument before setting it as another reference may
                      alter its contents */


    /* Method 4: Cloning Objects
         - Java has a Cloneable interface that you can implement if you want classes to be able to call the
           clone() method on your object. This helps with making defensive copies. The following 2 statements are
           equivalent:
                this.favoriteFoods = new ArrayList<String> (favoriteFoods);
                this.favoriteFoods = (ArrayList) favoriteFoods.clone();
         - By default, the clone() method makes a shallow copy of the data, which means only the top‐level object
           references and primitives are copied. No new objects from within the cloned object are created. For
           example, if the object contains a reference to an ArrayList, a shallow copy contains a reference to
           that same ArrayList. Changes to the ArrayList in one object will be visible in the other since it is
           the same object. */

}
