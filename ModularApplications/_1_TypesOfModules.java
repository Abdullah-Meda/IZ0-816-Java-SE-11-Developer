public class _1_TypesOfModules {

    /* Named Modules
         - A named module is one containing a module‐info file
         - Named modules appear on the module path rather than the classpath
         - As a way of remembering this, a named module has the name inside the module‐info file and is on the
           module path. */

    /* Automatic Modules
         - An automatic module appears on the module path but does not contain a module‐info file.
         - It is simply a regular JAR file that is placed on the module path and gets treated as a module
         - The code referencing an automatic module treats it as if there is a module‐info file present. It
           automatically exports all packages. It also determines the module name
         - Algorithm for determining the name of an automatic module:                        │     Example
              1. If the MANIFEST.MF specifies an Automatic‐Module‐Name, use that. Otherwise, │  mod_$-1.0.jar
                 proceed with the remaining rules.                                           │
              2. Remove the file extension from the JAR name.                                │  mod_$-1.0
              3. Remove any version information from the end of the name. A version is       │  mod_$
                 digits and dots with possible extra information at the end, for example,    │
                 ‐1.0.0 or ‐1.0‐RC.                                                          │
              4. Replace any remaining characters other than letters and numbers with dots.  │  mod..
              5. Replace any sequences of dots with a single dot.                            │  mod.
              6. Remove the dot if it is the first or last character of the result.          │  mod
    */

    /* Unnamed Modules
         - An unnamed module appears on the classpath.
         - Like an automatic module, it is a regular JAR.
         - Unlike an automatic module, it is on the classpath rather than the module path.
         - An unnamed module does not usually contain a module‐info file. If it happens to contain one, that file
           will be ignored since it is on the classpath.
         - Unnamed modules do not export any packages to named or automatic modules.
         - The unnamed module can read from any JARs on the classpath or module path */

    /* Comparing Module Types
         -> A key point to remember is that code on the classpath can access the module path. By contrast, code
            on the module path is unable to read from the classpath
         - Properties of modules types:
                             Property                     │      Named       │   Automatic   │      Unnamed
            ----------------------------------------------│------------------│---------------│-------------------
           - A ______ module contains a module‐info file? │     Yes          │     No        │ Ignored if present
           - A ______ module exports which packages to    │  Those in the    │               │
             other modules?                               │ module‐info file │  All packages │    No packages
           - A ______ module is readable by other modules │     Yes          │     Yes       │         No
             on the module path?                          │                  │               │
           - A ______ module is readable by other JARs    │     Yes          │     Yes       │         Yes
             on the classpath?                            │                  │               │

     */

    /* Code used in this chapter: https://github.com/boyarsky/sybex-1Z0-816-chapter-6 */
}
