# Fig

Fig is a basic configuartion library for Java libraries.

## Why?

Most configuration libraries are implemented using a static class (e.g. Config.getConfig()). Fig does not use static methods. Instead, a config factory is provided, which takes a URI, and returns a Map.
