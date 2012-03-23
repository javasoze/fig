package fig;

public class ConfigException extends Exception {
  private static final long serialVersionUID = 1L;
  private final Throwable wrappedException;
  private final String msg;

  public ConfigException(Throwable e) {
    this("", e);
  }

  public ConfigException(String msg, Throwable e) {
    this.msg = msg;
    this.wrappedException = e;
  }
}
