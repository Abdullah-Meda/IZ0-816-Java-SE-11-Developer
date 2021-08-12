public class _5_ConstructingSensitiveObjects {

    /* When constructing sensitive objects, you need to ensure that subclasses can't change the behavior */

    /* Making methods final
         - Especially getters and setters
         - Avoid allowing your constructors to call any methods that a subclass can provide its own
           implementation for */

    /* Making Classes final
         - If there is no point in creating a subclass */

    /* Making Constructor private
         - This technique requires static factory methods to obtain the object
         - The factory method technique gives you more control over the process of object creation. */

}
