package fig.cli;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.ConfigFileSystem;
import fig.serializers.PropertiesDeserializer;
import fig.fs.LocalFileSystem;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ConfigCli {
  public static final Map<String, String> DEFAULT_FS = new HashMap<String, String>();
  public static final Map<String, String> DEFAULT_DESERIALIZERS = new HashMap<String, String>();

  static {
    DEFAULT_FS.put("file", LocalFileSystem.class.getName());
    DEFAULT_DESERIALIZERS.put("properties", PropertiesDeserializer.class.getName());
    DEFAULT_DESERIALIZERS.put("props", PropertiesDeserializer.class.getName());
  }

  public static void main(String[] args) throws Exception {
    // Define parameters.
    OptionParser parser = new OptionParser();
    parser.accepts("config-path", "URI location to a config file (e.g. file:///some/local/path.properties).").withRequiredArg().ofType(String.class);
    parser.accepts("config-fs", "The file system class to use when reading the URI. Example: fig.fs.LocalFileSystem").withOptionalArg().ofType(String.class);
    parser.accepts("config-deserializer", "The deserializer to use when reading the URI. Example: fig.serializers.PropderiesDeserializer").withOptionalArg().ofType(String.class);
    OptionSet options = parser.parse(args);

    // Verify legitimate parameters.
    if (!options.has("config-path")) {
      parser.printHelpOn(System.err);
      System.exit(-1);
    }

    URI uri = new URI(options.valueOf("config-path").toString());

    // Get the file system.
    String fsClass = DEFAULT_FS.get(uri.getScheme());

    if (options.hasArgument("config-fs")) {
      fsClass = options.valueOf("config-fs").toString();
    } else if (fsClass == null) {
      throw new ConfigException("No config-fs specified, and unable to determine which file system to use.");
    }

    // Get the deserializer.
    String path = uri.getRawPath();
    String extn = "";

    if (path.contains(".")) {
      extn = path.substring(path.lastIndexOf('.') + 1, path.length());
    }

    String deserializerClass = DEFAULT_DESERIALIZERS.get(extn);

    if (options.hasArgument("config-deserializer")) {
      deserializerClass = options.valueOf("config-deserializer").toString();
    } else if (deserializerClass == null) {
      throw new ConfigException("No config-deserializer specified, and unable to determine which file system to use.");
    }

    // Get the config.
    ConfigFileSystem fs = (ConfigFileSystem) Class.forName(fsClass).newInstance();
    ConfigDeserializer deserializer = (ConfigDeserializer) Class.forName(deserializerClass).newInstance();
    Map<String, String> config = fs.getConfig(uri, deserializer);

    // Run main-class.
    String mainClass = config.get("main-class");
    ConfigRunner runner = (ConfigRunner) Class.forName(mainClass).newInstance();
    runner.run(config);
  }
}
