public class _2_AnalyzingJDKDependencies {

    /* Animatronic.java in my File System
        D:
        └--Java
             └--CH17
                 └--zoo
                     └--dinos
                          └--Animatronic.java
     */

    /* Using jdeps
         - The jdeps command gives you information about dependencies
         - First, Compile and package:
             - javac zoo/dinos/*.java
             - jar -cvf zoo.dino.jar .
         - View dependencies and a summary of the dependencies using the following commands respectively:
             - jdeps zoo.dino.jar
             - jdeps -s zoo.dino.jar
         - The jdeps command has an option to provide details about unsupported APIs:
             - jdeps --jdk-internals zoo.dino.jar
             - Note that ‐jdkinternals is equivalent to ‐‐jdk‐internals.
     */

}
