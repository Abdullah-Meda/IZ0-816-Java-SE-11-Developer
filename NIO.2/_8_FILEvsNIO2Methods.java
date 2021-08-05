public class _8_FILEvsNIO2Methods {

    /* Comparison of java.io.File and NIO.2 methods:

       Legacy I/O File method              NIO.2 method
       -------------------------------------------------------------------
       file.delete()                       Files.delete(path)
       file.exists()                       Files.exists(path)
       file.getAbsolutePath()              path.toAbsolutePath()
       file.getName()                      path.getFileName()
       file.getParent()                    path.getParent()
       file.isDirectory()                  Files.isDirectory(path)
       file.isFile()                       Files.isRegularFile(path)
       file.lastModified()                 Files.getLastModifiedTime(path)
       file.length()                       Files.size(path)
       file.listFiles()                    Files.list(path)
       file.mkdir()                        Files.createDirectory(path)
       file.mkdirs()                       Files.createDirectories(path)
       file.renameTo(otherFile)            Files.move(path,otherPath) */


}
