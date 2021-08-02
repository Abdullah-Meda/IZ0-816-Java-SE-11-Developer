public class _2_CommonFeatures {

    /* A path symbol is a reserved series of characters that have special meaning within some file
       systems. For the exam, there are two path symbols you need to know:

       Symbol           Description
       ------           ------------------
         .              A reference to the current directory
         ..             A reference to the parent of the current directory
     */

    /* Many of the methods in this chapter include a varargs that takes an optional
       list of values.

       Common NIO.2 method arguments:
        Enum type            Interface inherited        Enum value           Details
       -------------------------------------------------------------------------------------------
        LinkOption              CopyOption              NOFOLLOW_LINKS       Do not follow symbolic links.
                                OpenOption

        StandardCopyOption      CopyOption              ATOMIC_MOVE         Move file as atomic file system operation
                                                        COPY_ATTRIBUTES     Copy existing attributes to new file
                                                        REPLACE_EXISTING    Overwrite if it already exists

        StandardOpenOption      OpenOption              APPEND              If file is already open for write, then append to the end
                                                        CREATE              Create a new file if it does not exist
                                                        CREATE_NEW          Create a new file only if it does not exist, fail otherwise
                                                        READ                Open for read access
                                                        TRUNCATE_EXISTING   If file is already open for write, then erase file and append to beginning
                                                        WRITE               Open for write access

        FileVisitOption            N/A                  FOLLOW_LINKS        Follow symbolic links
     */

}
