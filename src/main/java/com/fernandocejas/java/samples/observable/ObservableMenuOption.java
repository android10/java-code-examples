package com.fernandocejas.java.samples.observable;

import com.fernandocejas.java.samples.Const;
import java.util.ArrayList;
import java.util.List;

public class ObservableMenuOption {
  private final List<Object> examples;

  public ObservableMenuOption() {
    examples = new ArrayList<>(1);
    examples.add(new Scenario01());
  }

  public void run() {
    for (Object object : examples) {
      printMessage("--------- Printing example ---------");
      printMessage(object.toString());
      printMessage(Const.LINE_FEED);
    }
    printMessage("Exiting application...");
    System.exit(0);
  }

  private void printMessage(String message) {
    System.out.println(message);
  }
}
