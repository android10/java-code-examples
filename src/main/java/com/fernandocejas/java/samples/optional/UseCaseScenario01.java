package com.fernandocejas.java.samples.optional;

import com.fernandocejas.arrow.annotations.See;
import com.fernandocejas.arrow.checks.Preconditions;
import com.fernandocejas.arrow.optional.Optional;
import com.fernandocejas.java.samples.Const;

@See(ref = "http://fernandocejas.com/2016/02/20/how-to-use-optional-on-android-and-java/")
public class UseCaseScenario01 {

  private static final String BRAND = "BMW";
  private static final String MODEL = "X6";
  private static final String REGISTRATION_NUMBER = "123456";

  public UseCaseScenario01() {
    //empty
  }

  @Override public String toString() {
    final Car carWithRegNumber = new Car(BRAND, MODEL, REGISTRATION_NUMBER);
    final Car carWithoutRegNumber = new Car(BRAND, MODEL, null);
    final StringBuilder message = new StringBuilder();
    message.append("Car with Registration Number --> ").append(carWithRegNumber.information()).append(Const.LINE_FEED);
    message.append("Car without Registration Number --> ").append(carWithoutRegNumber.information()).append(Const.LINE_FEED);

    return message.toString();
  }

  private static final class Car {
    private final String brand;
    private final String model;
    private final Optional<String> registrationNumber;

    private Car(String brand, String model, String registrationNumber) {
      Preconditions.checkNotNull(brand);
      Preconditions.checkNotNull(model);
      this.brand = brand;
      this.model = model;
      this.registrationNumber = Optional.fromNullable(registrationNumber);
    }

    private String information() {
      final StringBuilder builder = new StringBuilder();
      builder.append("Model --> ").append(model).append(" -- ");
      builder.append("Brand --> ").append(brand);
      if (registrationNumber.isPresent()) {
        builder.append(" -- ").append("Registration Number --> ").append(registrationNumber.get());
      }
      return builder.toString();
    }
  }
}
