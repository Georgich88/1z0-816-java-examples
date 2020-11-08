# Byte Stream Vs. Character Streams

The `java.io API` defines two sets of stream classes for reading and writing streams: byte streams and character streams.

Differences between Byte and Character Streams
* `Byte` streams read/write binary data (zeros and ones) and have class names that end in `InputStream` or `OutputStream`.
* `Character` streams read/write text data and have class names that end in `Reader` or `Writer`.

The API frequently includes similar classes for both byte and character streams, such as `FileInputStream` and `FileReader`. The difference between the two classes is based on how the bytes of the stream are read or written.
