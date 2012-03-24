package fig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import fig.util.Utils;

public abstract class Configuration {

  public abstract String get(String s);
  
  public abstract Set<String> getKeys();
  
  public abstract Configuration subset(String prefix,boolean stripPrefix);
  
  public String get(String s,String defaultString){
    String val = get(s);
    if (val == null){
      return defaultString;
    }
    return val;
  }
  
  public int getInt(String s){
    return Integer.parseInt(get(s));
  }
  
  public int getInt(String s,int defaultInt){
    String val = get(s);
    if (val==null){
      return defaultInt;
    }
    return Integer.parseInt(val);
  }
  
  public short getShort(String s){
    return Short.parseShort(get(s));
  }
  
  public short getShort(String s,short defaultShort){
    String val = get(s);
    if (val==null){
      return defaultShort;
    }
    return Short.parseShort(val);
  }
  
  public long getLong(String s){
    return Long.parseLong(get(s));
  }
  
  public long getLong(String s,long defaultLong){
    String val = get(s);
    if (val==null){
      return defaultLong;
    }
    return Long.parseLong(val);
  }
  
  public boolean getBoolean(String s){
    return Boolean.getBoolean(get(s));
  }
  
  public boolean getBoolean(String s,boolean defaultBool){
    String val = get(s);
    if (val==null){
      return defaultBool;
    }
    return Boolean.parseBoolean(val);
  }
  
  public List<String> getAsList(String k) {
    Utils.notNull(k);

    String v = get(k);

    if (v == null) {
      return Collections.<String> emptyList();
    } else {
      return Collections.unmodifiableList(Arrays.asList(v.split("\\,")));
    }
  }

}
