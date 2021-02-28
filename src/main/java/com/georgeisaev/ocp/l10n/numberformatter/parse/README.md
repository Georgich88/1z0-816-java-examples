## What will the following code print when run?

```
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class TestClass
{
    public static void main(String[] args)
    {
		double amount = 987654.321;
		Locale frenchLocale = new Locale("fr", "FR");
		NumberFormat formatter = NumberFormat.getInstance(frenchLocale);
		String numberPresentation = formatter.format(amount) ;
		formatter = NumberFormat.getInstance();
		Number amount2 = formatter.parse(numberPresentation);
		System.out.println( amount + " " + amount2 );
    }
}
```

Select 1 option(s):

1) It will always print two equal numbers.
2) It will always print two unequal numbers.
3) It will not compile.
4) It will throw an exception at runtime.
5) None of these.

Answer: NumberFormat.parse(String) throws a checked exception. I won't compile.
