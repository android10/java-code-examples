package com.fernandocejas.java.samples.optional;

import com.fernandocejas.arrow.annotations.See;
import com.fernandocejas.arrow.optional.Optional;
import com.fernandocejas.java.samples.Const;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@See(ref = "http://fernandocejas.com/2016/02/20/how-to-use-optional-on-android-and-java/")
class UseCaseScenario03 {

  private static final Func1<Optional<List<String>>, Observable<List<String>>> TO_AD_ITEM =
      ads -> ads.isPresent()
          ? Observable.just(ads.get())
          : Observable.just(Collections.<String>emptyList());

  private static final Func1<List<String>, Boolean> EMPTY_ELEMENTS = ads -> !ads.isEmpty();

  UseCaseScenario03() {
    //empty
  }

  private Observable<List<String>> feed() {
    return ads()
        .flatMap(TO_AD_ITEM)
        .filter(EMPTY_ELEMENTS)
        .concatWith(tracks())
        .observeOn(Schedulers.immediate());
  }

  private Observable<Optional<List<String>>> ads() {
    return Observable.just(Optional.fromNullable(Collections.singletonList("This is and Ad")));
  }

  private Observable<List<String>> tracks() {
    return Observable.just(Arrays.asList("IronMan Song", "Wolverine Song", "Batman Sound"));
  }

  @Override public String toString() {
    final StringBuilder builder = new StringBuilder();
    feed().subscribe(feed -> {
      for (String feedElement : feed) {
        builder.append(Const.LINE_FEED).append(feedElement);
      }
    });
    return builder.toString();
  }
}
