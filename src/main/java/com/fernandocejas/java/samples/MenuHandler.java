package com.fernandocejas.java.samples;

import com.fernandocejas.java.samples.observable.ObservableMenuOption;
import com.fernandocejas.java.samples.optional.OptionalMenuOption;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MenuHandler {

  enum MenuItem {
    OPTIONAL, OBSERVABLE, EXIT;

    static MenuItem fromString(String option) {
      MenuItem menuItem = EXIT;
      if (option == null) { return menuItem; }
      try {
        int menuOption = Integer.parseInt(option.trim());
        for (MenuItem item : MenuItem.values()) {
          if (item.ordinal() == menuOption) {
            menuItem = item;
          }
        }
      } catch (NumberFormatException exception) {
        return menuItem;
      }
      return menuItem;
    }
  }

  MenuHandler() {}

  void createMainMenu() {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("--- Java Code Samples ---" + Const.LINE_FEED);
    for (MenuItem menuItem : MenuItem.values()) {
      System.out.println(menuItem.ordinal() + " - " + menuItem.name());
    }
    System.out.print(Const.LINE_FEED + "Choose an option: ");

    try {
      processOption(reader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void processOption(String menuOption) {
    final MenuItem menuItem = MenuItem.fromString(menuOption);
    switch (menuItem) {
      case OPTIONAL:
        new OptionalMenuOption().run();
        break;
      case OBSERVABLE:
        new ObservableMenuOption().run();
        break;
      case EXIT:
      default:
        new ExitMenuOption().run();
    }
  }
}
