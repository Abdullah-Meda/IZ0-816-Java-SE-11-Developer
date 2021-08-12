public class _3_UpdatingForMultipleModules {

    /* The exports keyword is used to indicate that a module intends for those packages to be used by Java code
       outside the module */

    /* The requires statement specifies that a module is needed */

    /* Compiling and packaging the care Module
         - Command to compile:
              javac -p mods
                 -d care
                 care/zoo/animal/care/details/*.java
                 care/zoo/animal/care/medical/*.java
                 care/module-info.java

         - Command to create the module JAR
              jar -cvf mods/zoo.animal.care.jar -C care/ .
     */

    /* Compiling and packaging the talks Module
         - Command to compile:
              javac -p mods
                -d talks
                talks/zoo/animal/talks/content/*.java
                talks/zoo/animal/talks/media/*.java
                talks/zoo/animal/talks/schedule/*.java
                talks/module-info.java

         - Command to create the module JAR
              jar -cvf mods/zoo.animal.talks.jar -C talks/ .
     */

    /* Compiling and packaging the staff Module
         - Command to compile:
              javac -p mods
                 -d staff
                 staff/zoo/staff/*.java
                 staff/module-info.java

         - Command to create the module JAR
              jar -cvf mods/zoo.staff.jar -C staff/ .
     */

}
