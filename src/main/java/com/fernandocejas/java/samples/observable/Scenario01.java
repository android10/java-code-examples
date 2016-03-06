package com.fernandocejas.java.samples.observable;

import com.fernandocejas.java.samples.Const;
import rx.Observable;

public class Scenario01 {

  public Scenario01() {
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
}
