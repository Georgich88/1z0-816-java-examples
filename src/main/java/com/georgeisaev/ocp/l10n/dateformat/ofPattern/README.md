## What will the following code print?

```
DateTimeFormatter df = DateTimeFormatter.ofPattern("eeee");
LocalDate d = LocalDate.of(2000, 1, 1); //assume that it was Friday on this date
System.out.println(df.format(d));
```

Select 1 option(s):

1) Frid

2) Friday

3) Fri

4) 0005

5) 5

Correct answer:
`Friday`, because `eeee` is a full format of day.   
See `java.time.format.DateTimeFormatterBuilder#appendPattern` for more details.
