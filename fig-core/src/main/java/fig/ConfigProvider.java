package fig;

import java.net.URI;
import java.util.Map;

public interface ConfigProvider {
  Map<String, String> getConfig(URI uri);
}
