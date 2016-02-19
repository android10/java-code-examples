package com.fernandocejas.java.samples.optional;

import com.fernandocejas.arrow.annotations.See;
import java.util.ArrayList;
import java.util.List;

@See(ref = "")
public class OptionalMenuOption {
  private final List<Object> examples;

  public OptionalMenuOption() {
    examples = new ArrayList<>(3);
    examples.add(new UseCaseScenario01());
    examples.add(new UseCaseScenario02());
    examples.add(new UseCaseScenario03());
  }

  public void run() {
    for (Object object : examples) {
      printMessage(object.toString());
    }
    printMessage("Exiting example...");
    System.exit(0);
  }

  private void printMessage(String message) {
    System.out.println(message);
  }
}
