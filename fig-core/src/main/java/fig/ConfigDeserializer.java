package fig;

import java.io.InputStream;
import java.util.Map;

public interface ConfigDeserializer {
  Map<String, String> getConfig(InputStream in);
}
