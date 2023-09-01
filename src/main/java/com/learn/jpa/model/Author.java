package com.learn.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "author")
@EqualsAndHashCode(exclude = {"posts"}, callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author extends AbstractEntity<Long> {

    private String name;
    private String email;

    @ManyToMany(mappedBy = "authors")
    private List<Post> posts;

}
