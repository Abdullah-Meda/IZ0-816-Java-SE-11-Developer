import java.lang.annotation.*;

public class _3_AnnotationSpecific_Annotations {

    /* MARKER ANNOTATION - An annotation that doesn't contain any element */

    /* @Target
        - When you want to limit your annotation to a type or set of types
        - More specifically, the @Target annotation takes an array of "ElementType" enum values as its value() element

        The following are the values for the @Target annotation:
         - TYPE                Classes, interfaces, enums, annotations
         - FIELD               Instance and static variables, enum values
         - METHOD              Method declarations
         - PARAMETER           Constructor, method, and lambda parameters
         - CONSTRUCTOR         Constructor declarations
         - LOCAL_VARIABLE      Local variables
         - ANNOTATION_TYPE     Annotations
         - PACKAGE             Packages declared in package‐info.java
         - TYPE_PARAMETER      Parameterized types, generic declarations
         - TYPE_USE            Able to be applied anywhere there is a Java typedeclared or used
         - MODULE              Modules

         * Read bottom essay of Page 570 on TYPE_USE and its exceptions
    */
    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    public @interface ZooAttraction { }
    /* The above example means that the annotation "ZooAttraction" can be applied only
            to Method and Constructor declarations */

    //----------------------------------------------------------

    /* @Retention
        - Annotations may be discarded by the compiler or at runtime. We say “may,” because we
                    can actually specify how they are handled using the @Retention annotation.
        - This annotation takes a value() of the enum RetentionPolicy.

        The following are the values for the @Retention annotation:
         - SOURCE       Used only in the source file, discarded by the compiler
         - CLASS        Stored in the .class file but not available at runtime (default compiler behavior)
         - RUNTIME      Stored in the .class file and available at runtime
     */
    @Retention(RetentionPolicy.CLASS) @interface Flier { }
    @Retention(RetentionPolicy.RUNTIME) @interface Swimmer { }

    //----------------------------------------------------------

    /* @Documented
        - For the exam, you should be familiar with the marker annotation
            @Documented. If present, then the generated Javadoc will include
            annotation information defined on Java types. Because it is a marker
            annotation, it doesn't take any values; therefore, using it is pretty easy.
    */
    @Documented public @interface Hunter { } // Hunter.java
    @Hunter public class Lion { }            // Lion.java
    /* In the above example, the @Hunter annotation would be published with the Lion
        Javadoc information because it's marked with the @Documented annotation */

    //----------------------------------------------------------

    /* @Inherited
        - When this annotation is applied to a class, subclasses will inherit the
                annotation information found in the parent class
     */
    @Inherited public @interface Vertebrate { }  // Vertebrate.java
    @Vertebrate public class Mammal { }          // Mammal.java
    public class Dolphin extends Mammal { }      // Dolphin.java
    /* In this example, the @Vertebrate annotation will be applied to both Mammal and Dolphin
        objects. Without the @Inherited annotation, @Vertebrate would apply only to Mammal instances. */

    //----------------------------------------------------------

    /* @Repeatable
        - The @Repeatable annotation is used when you want to specify an annotation more than once on a type
        - You use repeatable annotations when you want to apply the same annotation with different values.

        Rules for declaring a repeatable annotation, along with its associated containing type annotation:
            - The repeatable annotation must be declared with @Repeatable and contain
                    a value that refers to the containing type annotation.
            - The containing type annotation must include an element named value(),
                    which is a primitive array of the repeatable annotation type.
     */

    public @interface Risks { Risk[] value();
    }

    @Repeatable(Risks.class)
    public @interface Risk {
        String danger();
        int level() default 1;
    }

    @Risk(danger = "Silly")
    @Risk(danger = "Aggressive", level = 5)
    @Risk(danger = "Violent", level = 10)
    public class Monkey { }

}
