package fig.providers;

import java.net.URI;
import java.util.Map;

import fig.ConfigException;
import fig.ConfigProvider;

import org.I0Itec.zkclient.ZkClient;

public class ZookeeperConfigProvider implements ConfigProvider {
  public Map<String, String> getConfig(URI uri) throws ConfigException {
    String hostPortPath = uri.getHost() + ":" + uri.getPort() + uri.getPath();
    ZkClient zk = new ZkClient(hostPortPath, 5000, 5000, new ZooKeeperJsonConfigSerializer());
    return zk.<Map<String, String>> readData("/");
  }
}
