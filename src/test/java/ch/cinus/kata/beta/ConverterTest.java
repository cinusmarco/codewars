package ch.cinus.kata.beta;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class ConverterTest{

  @Test
  public void convert_test0(){
    int R = 10234567;
    String s = "CodeWars";

    assertEquals(R, Converter.convert(s));
  }

  @Test
  public void convert_test1(){
    int R = 1020;
    String s = "KATA";

    assertEquals(R, Converter.convert(s));
  }

  @Test
  public void convert_test2(){
    int R = 101;
    String s = "ABA";

    assertEquals(R, Converter.convert(s));
  }
}