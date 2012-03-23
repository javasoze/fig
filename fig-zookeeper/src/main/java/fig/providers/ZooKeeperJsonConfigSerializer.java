package fig.providers;

import java.util.HashMap;
import java.util.Map;

import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.codehaus.jackson.map.ObjectMapper;

import fig.ConfigException;

public class ZooKeeperJsonConfigSerializer implements ZkSerializer {
  private final ObjectMapper jsonMapper = new ObjectMapper();

  public byte[] serialize(Object data) {
    if (data == null)
      return null;
    else {
      try {
        return jsonMapper.writeValueAsString(data).getBytes("UTF-8");
      } catch (Exception e) {
        throw new ConfigException(e);
      }
    }
  }

  public Object deserialize(byte[] bytes) {
    if (bytes == null)
      return null;
    else {
      try {
        return jsonMapper.readValue(new String(bytes, "UTF-8"), Map.class);
      } catch (Exception e) {
        throw new ConfigException(e);
      }
    }
  }
}
