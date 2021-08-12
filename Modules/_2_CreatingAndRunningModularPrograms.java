public class _2_CreatingAndRunningModularPrograms {

    /* Contents of feeding module in my File System
        D:
        └--Java
             └--Zoo
                 ├--mods
                 └--feeding
                       ├---zoo.animal.feeding
                       │          └-------------Task.java
                       └---module-info.java
     */

    /* There are a few key differences between a module-info file and a regular Java class:
         - The module-info file must be in the root directory of your module. Regular Java classes should be in
           packages.
         - The module-info file must use the keyword module instead of class, interface, or enum.
         - The module name follows the naming rules for package names. It often includes periods (.) in its name.
           Regular class and package names are not allowed to have dashes (-). Module names follow the same rule. */

    /* In particular, feeding is the module directory, and the module-info file is directly under it */

    /* Compiling the feeding Module
         - The -d option specifies the directory to place the class files in
         - The --module-path indicates the location of any custom module files
         - The syntax --module-path and -p are equivalent

         - Current working directory: D:/Java/Zoo/feeding
         - Command to compile:
              javac --module-path mods
                  -d feeding
                  feeding/zoo/animal/feeding/*.java
                  feeding/module-info.java
     */

    /* Running the feeding Module
         - The syntax --module and -m are equivalent
         - In the following examples, we used feeding as the module path because that’s where we compiled the
           code. This will change once we package the module and run that.

         - Current working directory: D:/Java/Zoo/feeding
         - Command to run:
              java --module-path feeding
                 --module zoo.animal.feeding/zoo.animal.feeding.Task
     */

    /* Packaging the feeding module
         - A module isn’t much use if we can run it only in the folder it was created in
         - In the following example, we are packaging everything under the feeding directory and storing it in a
           JAR file named zoo.animal.feeding.jar under the mods folder

         - Command to package:
              jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .

         - Now we run the program again, but using the JAR file we just created
         - Command to run:
              java -p mods
                 --module zoo.animal.feeding/zoo.animal.feeding.Task
     */

}
