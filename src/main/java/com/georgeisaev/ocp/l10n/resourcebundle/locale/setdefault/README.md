## Which of the following statements are correct?

Consider the following piece of code:

```
		Locale.setDefault(new Locale("fr", "CA")); // Set default locale to French Canada
		Locale japanLocale = new Locale("jp", "JP");
		ResourceBundle bundle = ResourceBundle.getBundle("greetingMessages", japanLocale);
		String greeting = bundle.getString("greeting");
		System.out.println(greeting);
```

There are two resource bundle files with the following contents:

```
#In the greetingMessages.properties:
greetings=Hello

#In the greetingMessages_fr_FR.properties:
greetings=bonjour
```

**Assume that this code is run on machines all over the world.  
Which of the following statements are correct?**

Select 1 option(s):

1) Exception will be thrown when the default locale of the machine where it is run is different from fr/FR and fr/CA.
2) Exception will be thrown anywhere.
3) Exception will be thrown when the default locale of the machine is fr/CA.
4) Exception will be thrown when the default locale of the machine is jp/JP.
5) It will run without any exception anywhere.
