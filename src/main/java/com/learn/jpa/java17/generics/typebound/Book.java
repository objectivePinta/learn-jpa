package com.learn.jpa.java17.generics.typebound;


public class Book implements Weight {

  private long weight = 981;


  @Override
  public Long getWeight () {

    return weight;
  }
}
