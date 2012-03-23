package fig.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConfigHelper {
  protected final Map<String, String> map;

  public ConfigHelper(Map<String, String> map) {
    this.map = map;
  }

  public List<String> getAsList(String k) {
    Utils.notNull(k);

    String v = map.get(k);

    if (v == null) {
      return Collections.<String> emptyList();
    } else {
      return Collections.unmodifiableList(Arrays.asList(v.split("\\,")));
    }
  }

  public Map<String, String> getSubMap(String prefix, boolean stripPrefix) {
    Utils.notNull(prefix);

    Map<String, String> out = new HashMap<String, String>();

    for (Entry<String, String> kv : map.entrySet()) {
      String k = kv.getKey();

      if (k != null && k.startsWith(prefix)) {
        if (stripPrefix) {
          k = k.substring(prefix.length());
        }

        out.put(k, kv.getValue());
      }
    }

    return Collections.unmodifiableMap(out);
  }
}
