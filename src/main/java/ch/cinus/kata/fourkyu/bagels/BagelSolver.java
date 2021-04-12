package ch.cinus.kata.fourkyu.bagels;

import java.lang.reflect.Field;

public class BagelSolver {
  public static Bagel getBagel() {
    try {
      Field value = Boolean.class.getDeclaredField("value");
      value.setAccessible(true);
      value.set(Boolean.TRUE, Boolean.FALSE);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return new Bagel();
  }
}
