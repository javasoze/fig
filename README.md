# Fig

Fig is a basic configuartion library for Java libraries.

## Why?

* No static methods
* Pluggable
* Simple

## Using Fig

First, create a config file. In this example, we'll use a Java property file.

```
hello=world
```

Now, in Java, you can get your properties.

```java
new PropertiesConfigProvider(new URI("file:///tmp/config.properties"))
```
