package com.learn.jpa.relationship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learn.jpa.model.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "writers", callSuper = true)
@EqualsAndHashCode(exclude = "writers")
public class Book extends AbstractEntity<Long> {

  private String title;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JsonBackReference
  @JoinTable(name = "writer_book",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "writer_id", referencedColumnName = "id"))
  private List<Writer> writers = new ArrayList<>();

  public void addWriters(List<Writer> writers) {
    writers.addAll(writers);
  }
  public void removeWriter(Writer writer) {
    if (writers != null) {
      writers.remove(writer);
    }
  }
}
