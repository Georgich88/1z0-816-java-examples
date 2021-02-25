# Resource bundle

## Ways to get values from the bundle

Given:
Locale locale = new Locale("en", "US"); ResourceBundle rb = ResourceBundle.getBundle("test.MyBundle", locale);

Which of the following are valid lines of code?
(Assume that the ResourceBundle has the values for the given keys.)
Select 2 option(s):

```
String obj = rb.getObject("key1");
```

```
Object obj = rb.getObject("key1");
```

```
String[] vals =  rb.getStringArray("key2");
```

```
Object obj = rb.getValue("key3");
```

```
Object obj = rb.getObject(1);
```

There are three main methods to get values by key:

* `String getString(String key)`
* `String[] getStringArray(String key)`
* `getObject(String key)`
