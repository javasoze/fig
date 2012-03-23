package fig;

import java.net.URI;
import java.util.Map;

public interface ConfigFileSystem {
  Map<String, String> getConfig(URI uri, ConfigDeserializer deserializer);
}
