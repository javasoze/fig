package fig.providers;

import java.io.FileInputStream;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import fig.ConfigException;
import fig.ConfigProvider;

public class PropertiesConfigProvider implements ConfigProvider {
  public Map<String, String> getConfig(URI uri) throws ConfigException {
    try {
      String configPath = uri.getPath();
      Properties props = new Properties();
      FileInputStream in = new FileInputStream(configPath);
      HashMap<String, String> out = new HashMap<String, String>();

      props.load(in);
      in.close();

      for (Entry<Object, Object> entry : props.entrySet()) {
        Object k = entry.getKey();
        Object v = entry.getValue();

        if (k != null) {
          out.put(k.toString(), (v == null) ? null : v.toString());
        }
      }

      return Collections.unmodifiableMap(out);
    } catch (Exception e) {
      throw new ConfigException(e);
    }
  }
}
