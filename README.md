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

## ConfigRunner

Fig also provides a simple way to execute programs from the CLI using config (instead of CLI arguments).

Let's create another Java property file.

```
main-class=fig.example.MyConfigPrinter
foo=bar
```

Now, let's execute it.

```
./bin/run-config.sh --config-path=file:///tmp/config.properties --config-provider=fig.providers.PropertiesConfigProvider
```

It really is that simple!

## Example Project

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
