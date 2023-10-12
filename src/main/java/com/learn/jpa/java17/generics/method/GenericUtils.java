package com.learn.jpa.java17.generics.method;


public class GenericUtils {

  public static <T> int length (T[] oneArray) {

    return oneArray.length;

  }


  public static void main (String[] args) {

    String[] strings = {"A", "B", "C"};
    Integer[] ints = {1, 2, 3, 4, 5, 6};
    System.out.println(GenericUtils.length(ints));
    System.out.println(GenericUtils.length(strings));
  }
}
