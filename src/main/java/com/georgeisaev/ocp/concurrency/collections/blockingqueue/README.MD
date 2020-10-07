#Understanding Blocking Queues
`LinkedBlockingQueue`, which implements `the BlockingQueue` interface. The `BlockingQueue` is just like a regular `Queue`, except that it includes methods that will wait a specific amount of time to complete an operation.

BlockingQueue waiting methods

|Method name|Description|
|---|---|
|`offer(E e, long timeout, TimeUnit unit)`|Adds an item to the queue, waiting the specified time and returning false if the time elapses before space is available|
|`poll(long timeout, TimeUnit unit)`|Retrieves and removes an item from the queue, waiting the specified time and returning null if the time elapses before the item is available|