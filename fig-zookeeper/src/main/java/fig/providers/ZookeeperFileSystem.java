package fig.providers;

import java.net.URI;
import java.util.Map;

import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.ConfigFileSystem;

import org.I0Itec.zkclient.ZkClient;

public class ZookeeperFileSystem implements ConfigFileSystem {
  @Override
  public Map<String, String> getConfig(URI uri, ConfigDeserializer deserializer) {
    String hostPortPath = uri.getHost() + ":" + uri.getPort() + uri.getPath();
    ZkClient zk = new ZkClient(hostPortPath, 5000, 5000, new ZooKeeperConfigDeserializer(deserializer));
    return zk.<Map<String, String>> readData("/");
  }
}
