package com.fernandocejas.java.samples.observable;

import com.fernandocejas.java.samples.Const;
import java.util.Arrays;
import rx.Observable;

class Scenario01 {

  Scenario01() {
    //empty
  }

  @Override public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Single item emitted: ")
        .append(Const.LINE_FEED)
        .append(String.valueOf(emitSingleElement()))
        .append(Const.LINE_FEED);
    builder.append("FlatMap elements: ")
        .append(Const.LINE_FEED)
        .append(flatMapElements());
    builder.append("Map elements: ")
        .append(Const.LINE_FEED)
        .append(mapElement());
    return builder.toString();
  }

  private int emitSingleElement() {
    return Observable.just(1).toBlocking().single();
  }

  private String flatMapElements() {
    final StringBuilder output = new StringBuilder();
    Observable.just(1, 2, 3)
        .flatMap(integer -> Observable.just("Number: " + String.valueOf(integer)))
        .subscribe(string -> { output.append(string).append(Const.LINE_FEED); });
    return output.toString();
  }

  private String mapElement() {
    final StringBuilder output = new StringBuilder();
    Observable.from(Arrays.asList(1, 3, 5))
        .map(number -> output
            .append("Map element: ")
            .append(String.valueOf(number + number))
            .append(Const.LINE_FEED))
        .subscribe();
    return output.toString();
  }
}
