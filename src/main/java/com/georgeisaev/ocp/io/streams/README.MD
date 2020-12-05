# I/O Streams
## I/O Stream Fundamentals
Streams should be conceptually thought of as a long, nearly never-ending “stream of water” with data presented one “wave” at a time.

**All Java I/O Streams Use Bytes**
>Although the `java.io API` is full of streams that handle characters, strings, groups of bytes, nearly all are built on top of reading or writing an individual byte or an array of bytes at a time.
 The reason higher-level streams exist is for convenience, as well as performance.

**I/O Streams Can Be Big**
>It may be helpful to visualize a stream as being so large that all of the data contained in it could not possibly fit into memory.
## I/O Stream Nomenclature
