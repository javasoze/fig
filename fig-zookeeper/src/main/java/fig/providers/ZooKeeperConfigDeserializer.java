package fig.providers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import fig.ConfigDeserializer;
import fig.ConfigException;

public class ZooKeeperConfigDeserializer implements ZkSerializer {
  private final ConfigDeserializer deserializer;

  public ZooKeeperConfigDeserializer(ConfigDeserializer deserializer) {
    this.deserializer = deserializer;
  }

  public byte[] serialize(Object data) {
    throw new ConfigException("Serialization not supported for ZooKeeper.");
  }

  public Object deserialize(byte[] bytes) {
    if (bytes == null)
      return null;
    else {
      try {
        InputStream in = new ByteArrayInputStream(bytes);
        Map<String, String> config = deserializer.getConfig(in);
        in.close();
        return config;
      } catch (Exception e) {
        throw new ConfigException(e);
      }
    }
  }
}
