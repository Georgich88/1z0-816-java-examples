# Attribute and View Types

`NIO.2` contains two methods for working with attributes in a single method call:

* a read-only attributes' method
* an updatable view method.

For each method, file system type object should be provided to inform the NIO.2 method which type of view you are
requesting. By updatable view can both read and write attributes with the same object.

| *Attributes interface* | *View interface* | *Description* |
| -------------------- | -------------- | ----------- |
| BasicFileAttributes | BasicFileAttributeView | Basic set of attributes supported by all file systems |
| DosFileAttributes | DosFileAttributeView | Basic set of attributes along with those supported by DOS/Windows-based systems |
| PosixFileAttributes | PosixFileAttributeView | Basic set of attributes along with those supported by POSIX systems, such as UNIX, Linux, Mac, etc. |

## Retrieving Attributes with `readAttributes()`

The Files class includes the following method to read attributes of a class in a read-only capacity:

```
public static <A extends BasicFileAttributes> A readAttributes(
    Path path,
    Class<A> type,
    LinkOption... options) throws IOException
```

## Modifying Attributes with `getFileAttributeView()`

The following Files method returns an updatable view:

```
public static <V extends FileAttributeView> V getFileAttributeView (
    Path path,
    Class<V> type,
    LinkOption... options)
```