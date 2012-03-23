package fig.cli;

import java.net.URI;
import java.util.Map;

import fig.ConfigProvider;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ConfigCli {
  public static void main(String[] args) throws Exception {
    // Define parameters.
    OptionParser parser = new OptionParser();
    parser.accepts("config-provider", "The config provider to use to read your config file.").withRequiredArg().ofType(String.class);
    parser.accepts("config-path", "URI location to a config file (e.g. file:///some/local/path.properties).").withRequiredArg().ofType(String.class);
    OptionSet options = parser.parse(args);

    // Verify legitimate parameters.
    if (!options.has("config-provider") || !options.has("config-path")) {
      parser.printHelpOn(System.err);
      System.exit(-1);
    }

    // Set up the job parameters.
    String configProviderClass = options.valueOf("config-provider").toString();
    URI uri = new URI(options.valueOf("config-path").toString());
    ConfigProvider configProvider = (ConfigProvider) Class.forName(configProviderClass).newInstance();
    Map<String, String> config = configProvider.getConfig(uri);
    String mainClass = config.get("main-class");
    ConfigRunner runner = (ConfigRunner) Class.forName(mainClass).newInstance();
    runner.run(config);
  }
}
