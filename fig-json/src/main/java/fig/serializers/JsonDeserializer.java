package fig.serializers;

import java.io.InputStream;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import fig.ConfigDeserializer;
import fig.ConfigException;

public class JsonDeserializer implements ConfigDeserializer {
  private final ObjectMapper jsonMapper = new ObjectMapper();

  @Override
  public Map<String, String> getConfig(InputStream in) {
    try {
      byte[] b = new byte[1024];
      int read = 0;
      StringBuffer sb = new StringBuffer();

      while ((read = in.read(b)) >= 0) {
        sb.append(new String(b, 0, read, "UTF-8"));
      }

      return jsonMapper.readValue(sb.toString(), Map.class);
    } catch (Exception e) {
      throw new ConfigException("Unable to get config from JSON.", e);
    }
  }
}
