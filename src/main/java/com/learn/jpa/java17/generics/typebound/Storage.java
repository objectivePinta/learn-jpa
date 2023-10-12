package com.learn.jpa.java17.generics.typebound;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class Storage<T extends Book & Weight> {

  T stored;


  public static void main (String[] args) {

    Storage<Book> bookStorage = new Storage<>();
    Storage<Brochure> brochureStorage = new Storage<>();
    /*
    Type parameter 'com.learn.jpa.java17.generics.typebound.Computer'
    is not within its bound; should extend 'com.learn.jpa.java17.generics.typebound.Book'
     */
//    Storage<Computer> computerStorage = new Storage<Computer>();
  }
}

/*
Query Language (JPQL), Criteria API, Native Queries, Callbacks and Listeners
 */