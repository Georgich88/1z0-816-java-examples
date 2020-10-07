#Understanding CopyOnWrite Collections

`CopyOnWriteArrayList` and `CopyOnWriteArraySet`,  classes copy all of their elements to a new underlying structure anytime an element is added, modified, or removed from the collection. By a modified element, we mean that the reference in the collection is changed. Modifying the actual contents of objects within the collection will not cause a new structure to be allocated.

Although the data is copied to a new underlying structure, our reference to the Collection object does not change. This is particularly useful in multithreaded environments that need to iterate the collection. Any iterator established prior to a modification will not see the changes, but instead it will iterate over the original elements prior to the modification.