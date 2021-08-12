public class _4_TheModuleInfoFile {

    /* exports
         - It’s also possible to export a package to a specific module
             - Eg: exports zoo.animal.talks.content to zoo.staff;

         -  Access control with modules
               Level                     Within module code                      Outside module
               ------------------------------------------------------------------------------------
             - private                   Available only within class             No access
             - default (packageprivate)  Available only within package           No access
             - protected                 Available only within package or        Accessible to subclasses only
                                         to subclasses                           if package is exported
             - public                    Available to all classes                Accessible only if package
                                                                                 is exported
     */

    /* requires transitive
         - There’s also a requires transitive moduleName, which means that any module that requires this module
           will also depend on moduleName. Consider the following example:

           module zoo.animal.care {
                exports zoo.animal.care.medical;
                requires transitive zoo.animal.feeding;
            }

         - What the above means is that any module that "requires" zoo.animal.care will automatically
           require the zoo.animal.feeding module!
     */

    /* The following doesn't compile due to duplication of requires statements:
            module bad.module {
                requires zoo.animal.talks;
                requires transitive zoo.animal.talks;
            }
     */

    /* provides, uses and opens
         - You only need to be aware they exist rather than understanding them in detail for the 1Z0-815 exam
     */

}
