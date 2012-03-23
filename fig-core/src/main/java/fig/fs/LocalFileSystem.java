package fig.fs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.ConfigFileSystem;

public class LocalFileSystem implements ConfigFileSystem {
  @Override
  public Map<String, String> getConfig(URI uri, ConfigDeserializer deserializer) {
    try {
      InputStream in = new FileInputStream(uri.getRawPath());
      Map<String, String> config = deserializer.getConfig(in);
      in.close();
      return config;
    } catch (Exception e) {
      throw new ConfigException(e);
    }
  }
}
