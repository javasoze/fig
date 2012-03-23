# Fig

Fig is a simple configuartion library for Java.

## Why?

*   No static methods
*   Pluggable
*   No types
*   Stupid simple

## Using Fig

Fig is really easy to use. I promise.

Let's use Fig with a Java property file.

```
hello=world
```

Now, in Java, you can get your properties.

```java
Map<String, String> config = new LocalFileSystem().getConfig(new URI("file:///tmp/config.properties"), new PropertiesDeserializer())
System.out.println("hello " + config.get("hello"));
```

## Command Line Execution

Fig also provides an easy way to execute programs from the CLI using config (instead of CLI arguments).

Let's create another Java property file.

```
main-class=fig.example.MyConfigPrinter
foo=bar
```
The main-class allows us to execute the MyConfigPrinter.

```
./bin/run-config.sh --config-path=file:///tmp/config.properties
```

It really is that simple!

## Example Project

Fig has an example project falled "fig-example", which demonstrates how to use Fig with the run-config.sh script.

Follow these instructions to try things out.

```
git://github.com/criccomini/fig.git
cd fig
mvn package
cd fig-example/target
unzip fig-example-bin.zip
cd fig-example
./bin/run-config.sh --config-path=file://`pwd`/config/example.properties --config-provider=fig.providers.PropertiesConfigProvider
```

This will execute a little Java class that prints the configuration in example.properties.

