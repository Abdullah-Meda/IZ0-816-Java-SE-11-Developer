import java.util.ArrayList;
import java.util.List;

public class _4_CommonAnnotations {

    /* @Override
        - The @Override is a marker annotation that is used to indicate a method is
                overriding an inherited method
     */
    public interface Intelligence { int cunning();}
    public class Canine implements Intelligence {
        @Override public int cunning() { return 500; }
    }

    //----------------------------------------------------------

    /* @FunctionalInterface
        - The @FunctionalInterface marker annotation can be applied to any valid functional interface
     */
    @FunctionalInterface
    interface Reptile { public abstract String getName(); }

    //----------------------------------------------------------

    /* @Deprecated
        - The @Deprecated annotation is similar to a marker annotation, in that it can be
                used without any values, but it includes some optional elements.
        - The @Deprecated annotation can be applied to nearly any Java declaration, such
                as classes, methods, or variables.
     */
    @Deprecated public class ZooPlanner { }
    /* @Deprecated annotation does support two optional values: String since() and boolean forRemoval() */
    @Deprecated(since="1.8", forRemoval=true) public void plan() { }

    //----------------------------------------------------------

    /* @SuppressWarnings
        - Applying this annotation to a class, method, or type basically tells the compiler, “I know
                what I am doing; do not warn me about this.”
        - Unlike the previous annotations, it requires a String[] value() parameter

        Common @SuppressWarnings values:
         - "deprecation"        Ignore warnings related to types or methods marked with the
                                    @Deprecated annotation.
         - "unchecked"          Ignore warnings related to the use of raw types, such as List
                                    instead of List<String>.
     */
    @Deprecated void doSomething(List<Integer> list) {}
    @SuppressWarnings("deprecation") public void wakeUp() {
        doSomething(new ArrayList<Integer>());
    }
    @SuppressWarnings("unchecked") public void goToBed() {
        doSomething(new ArrayList());
    }

    //----------------------------------------------------------

    /* SafeVarargs
        - The @SafeVargs marker annotation indicates that a method does not perform any
                potential unsafe operations on its varargs parameter.
        - IMPORTANT: It can be applied only to constructors or methods that cannot be overridden
                (aka methods marked private, static, or final).
     */
    @SafeVarargs final void safevarargs(List<Integer>... unused) {
        System.out.println("Printing");
    }
}
