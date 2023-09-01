package com.learn.jpa.relationship.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.jpa.model.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "writer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "writers", callSuper = true)
@EqualsAndHashCode(exclude = "writers")
public class Writer extends AbstractEntity<Long> {

  private String name;

  @ManyToMany(mappedBy = "writers",
      cascade = {CascadeType.ALL})
  private List<Book> books = new ArrayList<>();

  public void addBooks(List<Book> books) {
    this.books.addAll(books);
    books.forEach(book -> book.addWriters(List.of(this)));
  }

  public void removeBook(Book book) {
    this.books.remove(book);
  }
}
