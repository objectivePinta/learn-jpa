package com.learn.jpa.relationship.repository;

import com.learn.jpa.relationship.model.Book;
import com.learn.jpa.relationship.model.Writer;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class ManyToManyBookAuthorTest {

  @Autowired
  WriterRepository writerRepository;

  @Autowired
  BookRepository bookRepository;

  @Test
  public List<Writer> createAndPersistData() {
    // Writer 1 with 2 books
    Writer writer1 = new Writer();
    writer1.setName("Writer 1");

    Book book1 = new Book();
    book1.setTitle("Book 1 of Writer 1");
    book1.getWriters().add(writer1);

    Book book2 = new Book();
    book2.setTitle("Book 2 of Writer 1");
    book2.getWriters().add(writer1);

    writer1.addBooks(Arrays.asList(book1, book2));

    // Writer 2 with 1 book
    Writer writer2 = new Writer();
    writer2.setName("Writer 2");

    Book book3 = new Book();
    book3.setTitle("Book 1 of Writer 2");
    book3.getWriters().add(writer2);

    // Writer 3 with 3 books
    Writer writer3 = new Writer();
    writer3.setName("Writer 3");

    Book book4 = new Book();
    book4.setTitle("Book 1 of Writer 3");

    Book book5 = new Book();
    book5.setTitle("Book 2 of Writer 3");

    Book book6 = new Book();
    book6.setTitle("Book 3 of Writer 3");
    writer2.addBooks(Arrays.asList(book3, book4, book5, book6));
    writer3.addBooks(Arrays.asList(book4, book5, book6));

    // Save to database
    List<Writer> writers = writerRepository.saveAll(Arrays.asList(writer1, writer2, writer3));
    return writers;
  }

  @Test
  public List<Writer> createAndPersistDataOneBook() {
    // Writer 1 with 2 books
    Writer writer1 = new Writer();
    writer1.setName("Writer 1");

    Book book1 = new Book();
    book1.setTitle("Book 1 of Writer 1");
    book1.getWriters().add(writer1);

    Book book2 = new Book();
    book2.setTitle("Book 2 of Writer 1");
    book2.getWriters().add(writer1);

    writer1.addBooks(Arrays.asList(book1, book2));

    // Writer 2 with 1 book
    Writer writer2 = new Writer();
    writer2.setName("Writer 2");

    Book book3 = new Book();
    book3.setTitle("Book 1 of Writer 2");
    book3.getWriters().add(writer2);

    // Writer 3 with 3 books
    Writer writer3 = new Writer();
    writer3.setName("Writer 3");

    Book book4 = new Book();
    book4.setTitle("Book 1 of Writer 3");

    Book book5 = new Book();
    book5.setTitle("Book 2 of Writer 3");

    Book book6 = new Book();
    book6.setTitle("Book 3 of Writer 3");
    writer2.addBooks(Arrays.asList(book3, book4, book5, book6));
    writer3.addBooks(Arrays.asList(book4, book5, book6));

    // Save to database
    List<Writer> writers = writerRepository.saveAll(Arrays.asList(writer1, writer2, writer3));
    return writers;
  }

  @Test
  @Transactional
  void testWriterDeletion() {

    Book book = bookRepository.findById(3L).get();
    book.getWriters().forEach(writer -> writer.removeBook(book));
    bookRepository.deleteById(book.getId());
    bookRepository.flush();
  }

}
