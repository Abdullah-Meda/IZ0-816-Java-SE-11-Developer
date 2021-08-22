public class _3_MigratingAnApplication {

    /* Exploring a Bottom-Up Migration:
        1. Pick the lowest‐level project that has not yet been migrated.
        2. Add a module‐info.java file to that project. Be sure to add any exports to expose any package used by
           higher‐level JAR files. Also, add a requires directive for any modules it depends on.
        3. Move this newly migrated named module from the classpath to the module path.
        4. Ensure any projects that have not yet been migrated stay as unnamed modules on the classpath.
        5. Repeat with the next‐lowest‐level project until you are done

        Limitations:
            - What if a dependency that we cant control isn't modularised? We could wait until it is modularised
              or patch the source code either of which are not great options. Here's where Top-down Migration
              hops in
     */


    /* Exploring a Top-Down Migration:
        1. Place all projects on the module path.
        2. Pick the highest‐level project that has not yet been migrated.
        3. Add a module‐info file to that project to convert the automatic module into a named module. Again,
           remember to add any exports or requires directives. You can use the automatic module name of other
           modules when writing the requires directive since most of the projects on the module path do not have
           names yet.
        4. Repeat with the next‐lowest‐level project until you are done.

        - What if a dependency we need isn't modularised? Move the module that uses the dependency from the
          classpath to the module path and as for the dependency, We take the existing jar file of the dependency
          on the classpath and put it and alter it on the module path as an Automatic Module. NOTE that the jar
          file of the dependency is untouched!
     */


    /* Cyclic Dependencies
        - The Java Platform Module System does not allow for cyclic dependencies. A cyclic dependency, or
          circular dependency, is when two things directly or indirectly depend on each other

        - It is extremely important to understand that Java will not allow you to compile modules that have
          circular dependencies between each other

        - A WorkAround? ? A common technique is to introduce another module. That module contains the code that
          the other two modules share
     */

}
