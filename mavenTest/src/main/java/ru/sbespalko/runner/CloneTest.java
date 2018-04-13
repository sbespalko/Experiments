package ru.sbespalko.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author bespalko
 * @since 29.12.2017
 */
public class CloneTest {
  public static void main(String[] args) throws CloneNotSupportedException, IOException {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(5);
    list.add(10);
    list = removeByIterator(list, 5);
    System.out.println(list);

    Map<Integer, Integer> map = new HashMap<>();
    map.put(0,0);
    map.put(5,5);
    map.put(10,10);
    map = removeByIterator(map, 5);
    System.out.println(map);
  }

  private static <E> List<E> removeByIterator(List<E> list, E item) {
    list.removeIf(value -> Objects.equals(value, item));
    return list;
  }

  private static <E, T> Map<E, T> removeByIterator(Map<E, T> map, E key) {
    map.entrySet().removeIf(entry -> Objects.equals(entry.getKey(), key));
    return map;
  }
}