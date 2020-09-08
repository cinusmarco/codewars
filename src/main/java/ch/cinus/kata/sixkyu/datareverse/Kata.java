package ch.cinus.kata.sixkyu.datareverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Kata {
  public static int[] DataReverse(int[] data) {
    final int chunkSize = 8;
    final AtomicInteger counter = new AtomicInteger();

    final var chunked =
        new ArrayList<>(
            Arrays.stream(data)
                .boxed()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                .values());

    Collections.reverse(chunked);

    return chunked.stream().flatMap(Collection::stream).mapToInt(Integer::intValue).toArray();
  }
}
