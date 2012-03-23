package fig.example;

import java.util.Map;

import fig.cli.ConfigRunner;

public class MyConfigPrinter implements ConfigRunner {
  @Override
  public void run(Map<String, String> config) {
    System.out.println(config);
  }
}
