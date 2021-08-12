public class _5_DiscoveringModules {

    /* The java Command

         - Describing a Module
             - The following 2 commands are equivalent
                - java -p mods --describe-module zoo.animal.feeding
                - java -p mods -d zoo.animal.feeding

         - Listing available modules
             - java --list-modules
             - Listing modules with the directory containing our zoo modules
                - java -p mods --list-modules

         - Showing module resolution
             - The following command runs the program too!
             - java --show-module-resolution
                 -p feeding
                 -m zoo.animal.feeding/zoo.animal.feeding.Task

     */

    /* The jar Command

         - Like the java command, the jar command can describe a module
         - Both of these commands are equivalent:
             - jar -f mods/zoo.animal.feeding.jar -d
             - jar -f mods/zoo.animal/feeding.jat --describe-module

     */

    /* The jdeps Command

        - The jdeps command gives you information about dependencies within a module
        - Let’s start with a simple example and ask for a summary of the dependencies in zoo.animal.feeding.
          Both of these commands give the same output:
             - jdeps -s mods/zoo.animal.feeding.jar
             - jdeps -summary mods/zoo.animal.feeding.jar

        - Alternatively, you can call jdeps without the summary option and get the long form:
             - jdeps mods/zoo.animal.feeding.jar

        - Now, let’s look at a more complicated example. This time, we pick a module that depends on
          zoo.animal.feeding. We need to specify the module path so jdeps knows where to find information about
          the dependent module
             - jdeps -s
                  --module-path mods
                  mods/zoo.animal.feeding.jar
        - NOTE: There is not a short form of --module-path in the jdeps command

     */

    /* The jmod Command

        - The most important thing to remember is that jmod is only for working with the JMOD files
        - Modes using jmod:
             Operation         Description
             ---------         ------------------
             create            Creates a JMOD file.
             extract           Extracts all files from the JMOD. Works like unzipping.
             describe          Prints the module details such as requires.
             list              Lists all files in the JMOD file.
             hash              Shows a long string that goes with the file
     */

}
