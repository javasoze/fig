package fig.serializers;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import fig.ConfigDeserializer;
import fig.ConfigException;

public class PropertiesDeserializer implements ConfigDeserializer {
  public Map<String, String> getConfig(InputStream in) {
    Properties props = new Properties();
    HashMap<String, String> out = new HashMap<String, String>();

    try {
      props.load(in);
    } catch (Exception e) {
      throw new ConfigException("Unable to load properties config.", e);
    }

    for (Entry<Object, Object> entry : props.entrySet()) {
      Object v = entry.getValue();
      out.put(entry.getKey().toString(), (v == null) ? null : v.toString());
    }

    return Collections.unmodifiableMap(out);
  }
}
