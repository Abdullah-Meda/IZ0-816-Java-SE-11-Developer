public class _2_ApplyingAnnotations {

    /* Annotations can be applied to
        - Classes, interfaces, enums, and modules
        - Variables ( static, instance, local)
        - Methods and constructors
        - Method, constructor, and lambda parameters
        - Cast expressions
        - Other annotations
     */

    //----------------------------------------------------------

    /* CREATING A VALUE() ELEMENT - annotation with a value, written without the elementName
        - The annotation declaration must contain an element named value(), which may be optional or required.
        - The annotation declaration must not contain any other elements THAT ARE REQUIRED.
        - The annotation usage must not provide values for any other elements.
    */
    public @interface Injured {
        String value() default "foot";
        int age() default 1;
    }
    /* The following usages are all correct */
    public abstract class Elephant {
        @Injured("Legs") void fallDown() {}
        @Injured(value="Legs") public abstract int trip();
        @Injured String injuries[];
    }

    //----------------------------------------------------------

    /* PASSING AN ARRAY OF VALUES
        - Annotations support a shorthand notation for providing an array that contains a single element
    */
    public @interface Music { String[] genres(); }
    /* Normal Usage */
    @Music(genres={"Rock and roll"}) String mostDisliked;   // Note the use of "{}"
    /* Optional usage if only 1 element in array */
    @Music(genres="Classical") String favorite;             // Note the absence of "{}"
    // NOTE: Use {} for an empty array, using "null" causes a compiler error!

    //----------------------------------------------------------

    /* COMBINING SHORTHAND NOTATIONS
        - Combining value() and the shorthand for passing an array
    */
    public @interface Rhythm { String[] value(); }
    /* Each of the following four annotations is valid: */
    @Rhythm(value={"Swing"}) String favourite;
    @Rhythm(value="R&B") String secondFavorite;
    @Rhythm({"Classical"}) String disliked;
    @Rhythm("Country") String lastDisliked;
}
