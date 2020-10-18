# Files and Directories

## File Class
The `java.io.File` class is used to read information about existing files and directories, list the contents of a directory, and create/delete files and directories.
A `File` class instance represents the path to a particular file or directory on the file system.

### Constructors
```
    public File(String pathname)
    public File(File parent, String child)
    public File(String parent, String child)
```

### The File Object vs. the Actual File
The `File` instance only represents a path to a file. Unless operated upon, it is not connected to an actual file within the file system.

### The File class main methods

| **Method Name** | **Description** |
|---|---|
| boolean delete() | *Deletes the file or directory and returns true only if successful. If this instance denotes a directory, then the directory must be empty in order to be deleted.* |
| boolean exists() | *Checks if a file exists* |
| String getAbsolutePath() | *Retrieves the absolute name of the file or directory within the file system* |
| String getName() | *Retrieves the name of the file or directory* |
| String getParent() | *Retrieves the parent directory that the path is contained in or null if there is none* |
| boolean isDirectory() | *Checks if a `File` reference is a directory within the file system* |
| boolean isFile() | *Checks if a `File` reference is a file within the file system* |
| long lastModified() | *Returns the number of milliseconds since the epoch (number of milliseconds since 12 a.m. UTC on January 1, 1970) when the file was last modified* |
| long length() | *Retrieves the number of bytes in the file* |
| File[] listFiles() | *Retrieves a list of files within a directory* |
| boolean mkdir() | *Creates the directory named by this path* |
| boolean mkdirs() | *Creates the directory named by this path including any nonexistent parent directories* |
| boolean renameTo(File dest) | *Renames the file or directory denoted by this path to dest and returns true only if successful* |