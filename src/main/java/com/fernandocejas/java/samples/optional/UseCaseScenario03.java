package com.fernandocejas.java.samples.optional;

import com.fernandocejas.arrow.annotations.See;
import com.fernandocejas.arrow.optional.Optional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@See(ref = "")
public class UseCaseScenario03 {

  public static final Func1<Optional<List<String>>, Observable<List<String>>> TO_AD_ITEM =
      ads -> ads.isPresent()
          ? Observable.just(ads.get())
          : Observable.just(Collections.<String>emptyList());

  public static final Func1<List<String>, Boolean> EMPTY_ELEMENTS = ads -> !ads.isEmpty();

  public UseCaseScenario03() {
    //empty
  }

  @Override public String toString() {
    final StringBuilder builder = new StringBuilder();
    feed().subscribe(feed -> {
      for (String feedElement : feed) {
        builder.append("\n").append(feedElement);
      }
    });
    return builder.toString();
  }

  private Observable<List<String>> feed() {
    return ads()
        .flatMap(TO_AD_ITEM)
        .filter(EMPTY_ELEMENTS)
        .concatWith(users())
        .observeOn(Schedulers.immediate());
  }

  private Observable<Optional<List<String>>> ads() {
    return Observable.just(Optional.fromNullable(Collections.singletonList("This is and Ad")));
  }

  private Observable<List<String>> users() {
    return Observable.just(Arrays.asList("IronMan", "Wolverine", "Batman", "Superman"));
  }
}
