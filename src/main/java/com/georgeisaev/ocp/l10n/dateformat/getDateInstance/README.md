# DateFormat

## DateFormat.format() method signature

Assume that `currentDate` refers to a valid java.util.Date object and that df is a reference variable of class 
DateFormat.
Which of the following code fragments will print the country and the date in the correct local format?

Select 1 option(s):
```
final Locale locale = Locale.getDefault();
final DateFormat dateFormat = DateFormat.getDateInstance(l);
System.out.println(l.getCountry() + " " + dateFormat.format(currentDate));
```

```
final Locale locale = Locale.getDefault();
final DateFormat dateFormat = DateFormat.getDateInstance();
System.out.println(l.getCountry() + " " + dateFormat.format(currentDate, locale));
```

```
final Locale locale = Locale.getDefault();
final DateFormat dateFormat = DateFormat.getDateInstance();
System.out.println(l.getCountry() + " " + dateFormat.format(currentDate));
```

```
final Locale locale = new Locale();
final DateFormat dateFormat = DateFormat.getDateInstance();
System.out.println(l.getCountry() + " " +  dateFormat.format(currentDate));
```

## DateFormat.format() method signature
