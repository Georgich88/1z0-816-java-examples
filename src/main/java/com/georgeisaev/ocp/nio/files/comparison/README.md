# Comparison of Legacy `java.io.File` and `NIO.2` Methods

| Legacy I/O File methods | NIO.2 method |
| ----------------------- | ----------------------- |
| **Create** |  |
| file.mkdir() | Files.createDirectory(path) |
| file.mkdirs() | Files.createDirectories(path) |
| **Retrieve** |  |
| file.exists() | Files.exists(path) |
| file.getAbsolutePath() | path.toAbsolutePath() |
| file.getName() | path.getFileName() |
| file.getParent() | path.getParent() |
| file.isDirectory() | Files.isDirectory(path) |
| file.isFile() | Files.isRegularFile(path) |
| file.lastModified() | Files.getLastModifiedTime(path) |
| file.length() | Files.size(path) |
| file.listFiles() | Files.list(path) |
| **Update** |  |
| file.renameTo(otherFile) | Files.move(path,otherPath) |
| **Delete** |  |
| file.delete() | Files.delete(path) |